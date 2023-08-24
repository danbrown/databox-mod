package com.example.examplemod.init;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.content.enchantment.BlinkingStrikeEnchantment;
import com.example.examplemod.lib.LibEnchantmentNames;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class DataboxEnchantments {

  // @ Register
  public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS,
      ExampleMod.MOD_ID);

  public static void register(IEventBus bus) {
    ENCHANTMENTS.register(bus);
  }

  // @ Enchantments
  public static final RegistryObject<Enchantment> BLINKING_STRIKE = ENCHANTMENTS
      .register(LibEnchantmentNames.BLINKING_STRIKE, BlinkingStrikeEnchantment::new);

  // @ Blacklist for creative tab
  public static final List<RegistryObject<Enchantment>> DONT_INCLUDE_CREATIVE = List.of();
}
