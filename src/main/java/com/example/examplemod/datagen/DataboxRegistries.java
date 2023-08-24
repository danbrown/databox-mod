package com.example.examplemod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.LevelGenAA;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.HashSet;

public class DataboxRegistries {
  private static final Logger LOGGER = LogManager.getLogger(ExampleMod.MOD_ID);

  public static void registerDataProviders(GatherDataEvent event) {
    final var generator = event.getGenerator();
    final var output = generator.getPackOutput();
    final var helper = event.getExistingFileHelper();
    final var provider = event.getLookupProvider().thenApply(DataboxRegistries::createLookup);

    LOGGER.log(Level.INFO, ExampleMod.MOD_ID + " is registering data providers!");

    Set<String> modIds = new HashSet<>();
    modIds.add("minecraft");
    modIds.add(ExampleMod.MOD_ID);

    generator.addProvider(event.includeClient(), new DataboxBlockStates(output, helper));
    generator.addProvider(event.includeClient(), new DataboxItemModels(output, helper));

    // DataboxBlockTags blockTags = new DataboxBlockTags(output, provider, helper);
    generator.addProvider(event.includeServer(), new DataboxBlockTags(output, provider, helper));
    // generator.addProvider(event.includeServer(),
    // new DataboxItemTags(output, provider, blockTags.contentsGetter(), helper));

    DatapackBuiltinEntriesProvider datapackEntries = new DatapackBuiltinEntriesProvider(output, provider, modIds);
    CompletableFuture<HolderLookup.Provider> lookupProvider = datapackEntries.getRegistryProvider();
    generator.addProvider(event.includeServer(), datapackEntries);
    generator.addProvider(event.includeServer(), new DataboxBiomeTags(output, lookupProvider, helper));

  }

  private static HolderLookup.Provider createLookup(HolderLookup.Provider vanillaLookupProvider) {
    final var registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
    RegistrySetBuilder builder = new RegistrySetBuilder();

    builder.add(Registries.DENSITY_FUNCTION, LevelGenAA::bootstrap);
    builder.add(Registries.BIOME, DataboxBiomes::bootstrap);
    builder.add(Registries.CONFIGURED_FEATURE, (c) -> DataboxConfiguredFeatures.bootstrap(c));
    builder.add(Registries.CONFIGURED_CARVER, (c) -> DataboxConfiguredCarvers.bootstrap(c));
    builder.add(Registries.PLACED_FEATURE, DataboxPlacedFeatures::bootstrap);
    // builder.add(Registries.DENSITY_FUNCTION, DataboxNoiseRouterData::bootstrap);
    // builder.add(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST,
    // DataboxDimensions::bootstrapNoiseBiomeSource);
    builder.add(Registries.DIMENSION_TYPE, UGDimensions::bootstrapType);
    builder.add(Registries.LEVEL_STEM, UGDimensions::bootstrapStem);
    builder.add(Registries.NOISE_SETTINGS, UGDimensions::bootstrapNoise);

    return builder.buildPatch(registryAccess, vanillaLookupProvider);
  }
}
