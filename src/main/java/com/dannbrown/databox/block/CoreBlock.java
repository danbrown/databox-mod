package com.dannbrown.databox.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class CoreBlock extends Block {
  public CoreBlock(Properties properties) {
    super(properties);
  }

  public static final BooleanProperty LIT = BooleanProperty.create("lit");

  @Override
  protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
    builder.add(LIT);
  }

  @Override
  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult blockHitResult) {

    if (!level.isClientSide() && hand.equals(InteractionHand.MAIN_HAND)) {
      level.setBlock(blockPos, blockState.setValue(LIT, !blockState.getValue(LIT)), 3);
    }

    return super.use(blockState, level, blockPos, player, hand, blockHitResult);
  }

}
