package com.nick.nmeconomy.block.custom;

import com.mojang.serialization.MapCodec;
import com.nick.nmeconomy.render.screen.atm.ATMScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ATMBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<ATMBlock> CODEC = simpleCodec(ATMBlock::new);
    public static final EnumProperty<Direction> FACING;
    public MapCodec<? extends ATMBlock> codec() {
        return CODEC;
    }

    public ATMBlock(final BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH));
    }

    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(FACING);
        return switch (dir) {
            case NORTH -> Block.box(2.0, 0.0, 5.0, 14.0, 30.0, 14.0);
            case SOUTH -> Block.box(2.0, 0.0, 2.0, 14.0, 30.0, 11.0);
            case EAST -> Block.box(2.0, 0.0, 2.0, 11.0, 30.0, 14.0);
            case WEST -> Block.box(5.0, 0.0, 2.0, 14.0, 30.0, 14.0);
            default -> Block.box(10.0, 0.0, 5.0, 14.0, 30.0, 14.0);
        };
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            Minecraft.getInstance().setScreenAndShow(
                    new ATMScreen(Component.empty())
            );
        }
        return InteractionResult.SUCCESS;
    }
}
