package com.turnereison.wirelessredstone.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Transmitter extends Block {
    public static final BooleanProperty TRANSMITTING = BooleanProperty.of("transmitting");

    public Transmitter(Settings settings) {
        super(settings);
        setDefaultState(
                getStateManager().getDefaultState()
                        .with(TRANSMITTING, false)
        );
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // toggle transmitting property
        boolean transmitting = state.get(TRANSMITTING);
        world.setBlockState(pos, state.with(TRANSMITTING, !transmitting));
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TRANSMITTING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                Block.createCuboidShape(1,0,1,15,1,15),
                Block.createCuboidShape(7,1,7, 9,12,9)
        );
    }
}
