package com.dannbrown.databox.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class BlinkingStrikeEnchantment extends Enchantment {
	public BlinkingStrikeEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.RARE, EnchantmentCategory.TRIDENT, slots);
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		// allow enchantment to be combined with other enchantments, except for itself,
		// and riptide
		return this != ench
				&& !List.of(Enchantments.RIPTIDE).contains(ench);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		// allow enchantment to be applied only to tridents and if it's not enchanted
		return stack.getItem() instanceof Item && stack.getItem() == Items.TRIDENT && !stack.isEnchanted();
	}

	@Override
	public boolean canEnchant(ItemStack stack) {
		// allow enchantment to be applied only to tridents and if it's not enchanted
		return stack.getItem() instanceof Item && stack.getItem() == Items.TRIDENT && !stack.isEnchanted();
	}

	@Override
	public boolean isTreasureOnly() {
		return true;
	}
}
