package com.turnereison.wirelessredstone.blocks;

import com.turnereison.wirelessredstone.blocks.entities.ReceiverEntity;
import com.turnereison.wirelessredstone.blocks.entities.TransmitterEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Receiver extends Block implements BlockEntityProvider {
    public static final BooleanProperty RECEIVING = BooleanProperty.of("receiving");

    public Receiver(Settings settings) {
        super(settings);
        setDefaultState(
                getStateManager().getDefaultState()
                    .with(RECEIVING, false)
        );
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Toggle receiving property.

        // Entity goes logic goes here.
        ReceiverEntity entity = (ReceiverEntity) world.getBlockEntity(pos);
        entity.setReceiving(!entity.getReceiving());

        boolean receiving = state.get(RECEIVING);
        world.setBlockState(pos, state.with(RECEIVING, !receiving));
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(RECEIVING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape base = Block.createCuboidShape(1,0,1,15,1,15);

        VoxelShape antenna1 = Block.createCuboidShape(11,1,11,12,7,12);
        VoxelShape antenna2 = Block.createCuboidShape(4,1,4,5,7,5);

        VoxelShape partialUnion = VoxelShapes.union(base, antenna1);
        VoxelShape completeUnion = VoxelShapes.union(partialUnion, antenna2);

        return completeUnion;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ReceiverEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return ReceiverEntity::tick;
    }
}
