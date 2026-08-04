package com.nick.nmeconomy.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ATMBlock extends Block {
    public ATMBlock(Properties properties) {
        super(properties);
    }

    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 32.0, -10.0);

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    // look at carved pumpkin for how to do directional facing
}
