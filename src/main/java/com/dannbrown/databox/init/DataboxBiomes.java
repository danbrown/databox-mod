package com.dannbrown.databox.init;

import com.dannbrown.databox.DataboxMod;
import com.dannbrown.databox.world.biome.Custom1Biome;

import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DataboxBiomes {
  public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES,
      DataboxMod.MOD_ID);
  public static final RegistryObject<Biome> CUSTOM_BIOME_1 = BIOMES.register("custom_biome_1",
      Custom1Biome::createBiome);

  // Register
  public static void register(IEventBus eventBus) {
    BIOMES.register(eventBus);
  }
}
