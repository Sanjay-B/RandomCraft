package com.turnereison.wirelessredstone.blocks.entities;

import com.turnereison.wirelessredstone.blocks.BlockRegisterer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ReceiverEntity extends BlockEntity {
    private static final String RECEIVING_KEY = "receiving";
    private boolean receiving = false;

    public ReceiverEntity(BlockPos pos, BlockState state) {
        super(BlockRegisterer.RECEIVER_ENTITY_TYPE, pos, state);
    }

    public void setReceiving(boolean receiving) {
        this.receiving = receiving;
        markDirty();
    }

    public boolean getReceiving() {
        return receiving;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean(RECEIVING_KEY, receiving);
        return nbt;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        receiving = nbt.getBoolean(RECEIVING_KEY);
    }

    public static <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, T e) {
        if(e instanceof ReceiverEntity) {
            ReceiverEntity entity = (ReceiverEntity) e;
            if(entity.receiving) {
                for(PlayerEntity player : world.getPlayers()) {
                    player.sendMessage(new LiteralText("Receiving!"), false);
                }
            }
        }
    }
}
