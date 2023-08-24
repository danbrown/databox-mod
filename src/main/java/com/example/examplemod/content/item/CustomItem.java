package com.example.examplemod.content.item;

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

import java.util.List;

public class CustomItem extends Item {

  public CustomItem(Properties properties) {
    super(properties);
  }

  @Override
  public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag tooltipFlag) {
    if (Screen.hasShiftDown()) {
      components.add(Component.literal("This is a custom item!").withStyle(ChatFormatting.GRAY));
    } else {
      components.add(Component.literal("Hold SHIFT for more info").withStyle(ChatFormatting.YELLOW));
    }

    super.appendHoverText(stack, level, components, tooltipFlag);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
      // output a random number between 0 and 100 in the chat
      outputRandomNumber(player);
      // add a cooldown to prevent spam
      player.getCooldowns().addCooldown(this, 20);
    }
    return super.use(level, player, hand);
  }

  private void outputRandomNumber(Player player) {
    player.sendSystemMessage(Component.literal("Random number: " + getRandomNumber()));
  }

  private int getRandomNumber() {
    return (int) (Math.random() * (100 - 0) + 0);
  }
}
