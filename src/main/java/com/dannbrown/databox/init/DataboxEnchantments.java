package com.dannbrown.databox.init;

import com.dannbrown.databox.DataboxMod;
import com.dannbrown.databox.enchantment.BlinkingStrikeEnchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DataboxEnchantments {
  public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS,
      DataboxMod.MOD_ID);

  public static final RegistryObject<Enchantment> BLINKING_STRIKE = ENCHANTMENTS.register(
      "blinking_strike",
      () -> new BlinkingStrikeEnchantment());

  // @ Register
  public static void register(IEventBus eventBus) {
    ENCHANTMENTS.register(eventBus);
  }
}
