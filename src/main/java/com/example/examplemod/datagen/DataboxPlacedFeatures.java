package com.example.examplemod.datagen;

import com.example.examplemod.lib.LibUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;

import java.util.List;

public class DataboxPlacedFeatures {
  public static final ResourceKey<PlacedFeature> ADAMANTIUM_ORE = createPlacedFeature("adamantium_ore");

  public static ResourceKey<PlacedFeature> createPlacedFeature(String name) {
    return ResourceKey.create(Registries.PLACED_FEATURE, LibUtils.resourceLocation(name));
  }

  public static void bootstrap(BootstapContext<PlacedFeature> context) {
    HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

    context.register(
        ADAMANTIUM_ORE,
        new PlacedFeature(
            features.getOrThrow(DataboxConfiguredFeatures.ADAMANTIUM_ORE),
            rareOrePlacement(30, PlacementUtils.FULL_RANGE)));

    return;
  }

  private static List<PlacementModifier> tree(int count) {
    return List.of(
        CountOnEveryLayerPlacement.of(count),
        BiomeFilter.biome(),
        BlockPredicateFilter.forPredicate(
            BlockPredicate.wouldSurvive(
                Blocks.OAK_SAPLING.defaultBlockState(),
                BlockPos.ZERO)));
  }

  private static List<PlacementModifier> patch(int count) {
    return List.of(
        CountPlacement.of(count),
        InSquarePlacement.spread(),
        PlacementUtils.FULL_RANGE,
        BiomeFilter.biome());
  }

  private static List<PlacementModifier> patchWithFilter(int count, BlockPredicate filter) {
    return List.of(
        CountPlacement.of(count),
        InSquarePlacement.spread(),
        PlacementUtils.FULL_RANGE,
        BlockPredicateFilter.forPredicate(filter),
        BiomeFilter.biome());
  }

  private static List<PlacementModifier> noise(int noiseToCountRatio, double factor, double offset) {
    return List.of(
        NoiseBasedCountPlacement.of(noiseToCountRatio, factor, offset),
        InSquarePlacement.spread(),
        PlacementUtils.FULL_RANGE,
        BiomeFilter.biome());
  }

  private static List<PlacementModifier> noiseWithFilter(int noiseToCountRatio, double factor, double offset,
      BlockPredicate filter) {
    return List.of(
        NoiseBasedCountPlacement.of(noiseToCountRatio, factor, offset),
        InSquarePlacement.spread(),
        PlacementUtils.FULL_RANGE,
        BlockPredicateFilter.forPredicate(filter),
        BiomeFilter.biome());
  }

  private static List<PlacementModifier> orePlacement(PlacementModifier firstModifier,
      PlacementModifier secondModifier) {
    return List.of(firstModifier, InSquarePlacement.spread(), secondModifier, BiomeFilter.biome());
  }

  private static List<PlacementModifier> commonOrePlacement(int placement, PlacementModifier modifier) {
    return orePlacement(CountPlacement.of(placement), modifier);
  }

  private static List<PlacementModifier> rareOrePlacement(int placement, PlacementModifier modifier) {
    return orePlacement(RarityFilter.onAverageOnceEvery(placement), modifier);
  }
}
