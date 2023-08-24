package com.example.examplemod.content.block;

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
  public static final BooleanProperty lit = BooleanProperty.create("lit");

  public CoreBlock(Properties properties) {
    super(properties);
  }

  @Override
  protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
    builder.add(lit);
    super.createBlockStateDefinition(builder);
  }

  @Override
  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult blockHitResult) {
    if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
      level.setBlockAndUpdate(blockPos, blockState.setValue(lit, !blockState.getValue(lit)));
    }
    return super.use(blockState, level, blockPos, player, hand, blockHitResult);
  }
}
