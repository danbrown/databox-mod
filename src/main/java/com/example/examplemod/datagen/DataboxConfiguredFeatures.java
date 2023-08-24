package com.example.examplemod.datagen;

import com.example.examplemod.init.DataboxBlocks;
import com.example.examplemod.lib.LibUtils;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.TargetBlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class DataboxConfiguredFeatures {
  public static final RuleTest BASALT_ORE_REPLACEABLES = new TagMatchTest(DataboxTags.Blocks.BASALT_ORE_REPLACEABLES);

  public static final ResourceKey<ConfiguredFeature<?, ?>> ADAMANTIUM_ORE = create("adamantium_ore");

  public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
    return ResourceKey.create(Registries.CONFIGURED_FEATURE, LibUtils.resourceLocation(name));
  }

  public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
    List<TargetBlockState> targets = ImmutableList.of(
        OreConfiguration.target(BASALT_ORE_REPLACEABLES, DataboxBlocks.ADAMANTIUM_ORE.get().defaultBlockState()),
        OreConfiguration.target(BASALT_ORE_REPLACEABLES, DataboxBlocks.ADAMANTIUM_ORE.get().defaultBlockState()));

    context.register(ADAMANTIUM_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targets,
        17)));

    return;

    // context.register(
    // ADAMANTIUM_ORE,
    // new ConfiguredFeature<>(
    // Feature.ORE,
    // new OreConfiguration(targets,
    // 17)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE)
    // .decorate(ConfiguredFeatures.Decorators.TOP_SOLID_HEIGHTMAP)
    // .applyChance(4)
    // .build());
  }
}
