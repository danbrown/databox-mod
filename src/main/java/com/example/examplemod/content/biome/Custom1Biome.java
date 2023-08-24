package com.example.examplemod.content.biome;

import com.example.examplemod.lib.LibBiomeFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.*;

public class Custom1Biome {

        public static Biome createBiome(HolderGetter<PlacedFeature> placedFeatures,
                        HolderGetter<ConfiguredWorldCarver<?>> caveGetter) {
                BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatures,
                                caveGetter);

                BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
                BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
                BiomeDefaultFeatures.addDefaultOres(generationSettings);
                LibBiomeFeatures.addOresAndCaves(generationSettings);

                BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder();

                LibBiomeFeatures.generateColors(effects, 1186057, 4477507)
                                .fogColor(12638463)
                                .waterColor(4159204)
                                .waterFogColor(329011)
                                .skyColor(7972607)
                                .foliageColorOverride(10387789)
                                .grassColorOverride(9470285)
                                .ambientParticle(new AmbientParticleSettings(ParticleTypes.PORTAL, 0.005f));

                MobSpawnSettings.Builder mobSpawnInfo = new MobSpawnSettings.Builder()
                                .addSpawn(MobCategory.CREATURE, new SpawnerData(EntityType.FROG, 20, 4, 4));

                LibBiomeFeatures.addCaveMobs(mobSpawnInfo);

                return new Biome.BiomeBuilder()
                                .hasPrecipitation(true)
                                .temperature(1.5f)
                                .downfall(1.25f)
                                .generationSettings(generationSettings.build())
                                .mobSpawnSettings(mobSpawnInfo.build())
                                .specialEffects(effects.build())
                                .build();
        }
}
