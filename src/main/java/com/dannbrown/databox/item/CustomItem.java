package com.dannbrown.databox.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CustomItem extends Item {
  public CustomItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

    if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
      // output a random number between 0 and 100 in the chat
      outputRandomNumber(level, player, 0, 100);
      // add a cooldown to prevent spam
      player.getCooldowns().addCooldown(this, 20);
    }

    return super.use(level, player, hand);
  }

  @Override
  public void appendHoverText(ItemStack itemStack, Level level, List<Component> component, TooltipFlag tooltipFlag) {
    if (Screen.hasShiftDown()) {
      component.add(Component.literal("This is a custom item!").withStyle(ChatFormatting.GRAY));
    } else {
      component.add(Component.literal("Hold SHIFT for more info").withStyle(ChatFormatting.YELLOW));
    }

    super.appendHoverText(itemStack, level, component, tooltipFlag);
  }

  private int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }

  private void outputRandomNumber(Level level, Player player, int min, int max) {
    player.sendSystemMessage(Component.literal("Random number: " + getRandomNumber(min, max)));
  }
}
