package com.example.examplemod.init;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.content.carver.CustomCaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DataboxCarvers {
  public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS,
      ExampleMod.MOD_ID);

  public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> UNDERGARDEN_CAVE = CARVERS.register(
      "undergarden_cave",
      () -> new CustomCaveWorldCarver(CaveCarverConfiguration.CODEC));

  public static void register(IEventBus eventBus) {
    CARVERS.register(eventBus);
  }
}
