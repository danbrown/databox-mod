package com.dannbrown.databox.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.ListTag;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BlinkingStrikeUseProcedure {
	@SubscribeEvent
	public void onHitEntity(EntityHitResult event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(),
					event.getEntity().getZ(), event.getEntity());
		}
	}

	// @SubscribeEvent
	// public void onHitBlock(BlockHitResult event) {
	// if (event != null && event.getEntity() != null) {
	// execute(event, event.getEntity().getEntityWorld(), event.getEntity().getX(),
	// event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	// }
	// }

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable EntityHitResult event, LevelAccessor world, double x, double y, double z,
			Entity entity) {
		if (entity == null)
			return;

		if (entity instanceof ThrownTrident _trident) {
			// ItemStack tridentItem = _trident.getPickupItem();
			//
			// if(EnchantmentHelper.getItemEnchantmentLevel(DataboxModEnchantments.BLINKING_STRIKE.get(),
			// tridentItem) > 0){
			//
			// }

			world.setBlock(new BlockPos(x, y, z), Blocks.CALCITE.defaultBlockState(), 3);
		}
	}
}
