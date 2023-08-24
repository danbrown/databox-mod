package com.example.examplemod.content.biome;

import com.example.examplemod.lib.LibBiomeFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class AncientSeaBiome {

        public static Biome createBiome(
                        HolderGetter<PlacedFeature> placedFeatures,
                        HolderGetter<ConfiguredWorldCarver<?>> caveGetter) {

                BiomeGenerationSettings generationSettings = LibBiomeFeatures.addOresAndCaves(
                                new BiomeGenerationSettings.Builder(placedFeatures, caveGetter))
                                .build();

                MobSpawnSettings mobSpawnSettings = MobSpawnSettings.EMPTY;

                BiomeSpecialEffects biomeSpecialEffects = LibBiomeFeatures
                                .generateColors(new BiomeSpecialEffects.Builder(), 1186057, 4477507)
                                .build();

                return new Biome.BiomeBuilder()
                                .generationSettings(generationSettings)
                                .mobSpawnSettings(mobSpawnSettings)
                                .specialEffects(biomeSpecialEffects)
                                .hasPrecipitation(false)
                                .downfall(0.0F)
                                .temperature(0.8F)
                                .build();
        }
}
