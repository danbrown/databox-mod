package com.example.examplemod.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class JumpBlock extends Block {

  public JumpBlock(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult blockHitResult) {
    player.sendSystemMessage(Component.literal("You clicked on a jump block!"));
    return super.use(blockState, level, blockPos, player, hand, blockHitResult);
  }

  @Override
  public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
    if (entity instanceof LivingEntity) {
      if (entity instanceof Player && ((Player) entity).isShiftKeyDown()) {
        return;
      }
      entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 1.0, 0.0));
    }
    super.stepOn(level, blockPos, blockState, entity);
  }
}
