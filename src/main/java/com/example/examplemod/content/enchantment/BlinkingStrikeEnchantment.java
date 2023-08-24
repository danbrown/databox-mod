package com.example.examplemod.content.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlinkingStrikeEnchantment extends Enchantment {
  public BlinkingStrikeEnchantment(EquipmentSlot... slots) {
    super(Enchantment.Rarity.RARE, EnchantmentCategory.TRIDENT, slots);
  }

  @Override
  public boolean checkCompatibility(Enchantment ench) {
    // allow enchantment to be combined with other enchantments, except for itself,
    // and riptide
    List<Enchantment> excludedEnchantments = new ArrayList<>(Arrays.asList(Enchantments.RIPTIDE));
    return this != ench && !excludedEnchantments.contains(ench);
  }

  @Override
  public boolean canApplyAtEnchantingTable(ItemStack stack) {
    // allow enchantment to be applied only to tridents and if it's not enchanted
    return stack.getItem() instanceof Item && stack.getItem() == Items.TRIDENT && !stack.isEnchanted();
  }

  @Override
  public boolean canEnchant(ItemStack stack) {
    // allow enchantment to be applied only to tridents
    return stack.getItem() instanceof Item && stack.getItem() == Items.TRIDENT;
  }

  @Override
  public boolean isTreasureOnly() {
    return true;
  }
}
