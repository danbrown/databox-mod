package com.example.examplemod.datagen;

import com.example.examplemod.content.biome.AncientSeaBiome;
import com.example.examplemod.content.biome.Custom1Biome;
import com.example.examplemod.lib.LibUtils;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.*;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class DataboxBiomes {
  public static final ResourceKey<Biome> CUSTOM_BIOME_1 = createBiomeKey("custom_biome_1");
  public static final ResourceKey<Biome> ANCIENT_SEA = createBiomeKey("ancient_sea");

  private static ResourceKey<Biome> createBiomeKey(String name) {
    return ResourceKey.create(Registries.BIOME, LibUtils.resourceLocation(name));
  }

  public static void bootstrap(BootstapContext<Biome> context) {
    HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
    HolderGetter<ConfiguredWorldCarver<?>> worldCarvers = context.lookup(Registries.CONFIGURED_CARVER);

    context.register(CUSTOM_BIOME_1, Custom1Biome.createBiome(placedFeatures, worldCarvers));
    context.register(ANCIENT_SEA, AncientSeaBiome.createBiome(placedFeatures, worldCarvers));
  }

  // Utility functions

  public static BiomeSource buildBiomeSource(HolderGetter<Biome> biomes) {
    return MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(ImmutableList.of(
        Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(-1.0F, -0.4F, -0.9F, -0.7F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(1.0F, 0.0F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(0.0F, -0.4F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(1.0F, 0.4F, 0.0F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(0.0F, 0.0F, 0.9F, 0.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.7F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 1.0F, -2.0F, 0.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, -2.0F, 1.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(1.0F, 0.0F, 0.0F, 0.0F, -2.0F, 1.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, -2.0F, -1.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.7F, -2.0F, -1.0F, 0.0F), biomes.getOrThrow(CUSTOM_BIOME_1)),
        Pair.of(Climate.parameters(Climate.Parameter.span(0.0F, 1.0F), Climate.Parameter.span(0.0F, 0.4F),
            Climate.Parameter.span(0.0F, 0.9F), Climate.Parameter.point(0.0F), Climate.Parameter.point(2.0F),
            Climate.Parameter.span(-1.0F, 1.0F), 0.0F), biomes.getOrThrow(ANCIENT_SEA)),
        Pair.of(Climate.parameters(Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F),
            Climate.Parameter.point(0.0F), Climate.Parameter.span(0.7F, 1.0F), Climate.Parameter.point(2.0F),
            Climate.Parameter.point(0.0F), 0.0F), biomes.getOrThrow(ANCIENT_SEA)),
        Pair.of(Climate.parameters(-0.7F, -0.7F, -0.7F, 0.0F, 2.0F, 0.0F, 0.0F), biomes.getOrThrow(ANCIENT_SEA)))));
  }
}
