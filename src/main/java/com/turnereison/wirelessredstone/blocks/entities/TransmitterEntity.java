package com.turnereison.wirelessredstone.blocks.entities;

import com.turnereison.wirelessredstone.blocks.BlockRegisterer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TransmitterEntity extends BlockEntity {
    private static final String TRANSMITTING_KEY = "transmitting";
    private boolean transmitting = false;
    
    public TransmitterEntity(BlockPos pos, BlockState state) {
        super(BlockRegisterer.TRANSMITTER_ENTITY_TYPE, pos, state);
    }

    public void setTransmitting(boolean transmitting) {
        this.transmitting = transmitting;
        markDirty();
    }

    public boolean getTransmitting() {
        return transmitting;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        
        nbt.putBoolean(TRANSMITTING_KEY, transmitting);
        
        return nbt;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        
        transmitting = nbt.getBoolean(TRANSMITTING_KEY);
    }

    public static <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, T e) {
        if(e instanceof TransmitterEntity) {
            TransmitterEntity entity = (TransmitterEntity) e;

            if(entity.transmitting) {
                for (PlayerEntity player : world.getPlayers()) {
                    player.sendMessage(new LiteralText("Transmitting!"), false);
                }
            }
        }
    }
}
