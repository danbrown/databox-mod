package com.example.examplemod.lib;

import com.example.examplemod.datagen.DataboxConfiguredCarvers;
import com.example.examplemod.datagen.DataboxPlacedFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.registries.RegistryObject;

public class LibBiomeFeatures {
  public static BiomeGenerationSettings.Builder addOresAndCaves(BiomeGenerationSettings.Builder builder) {
    return builder
        .addCarver(GenerationStep.Carving.AIR, DataboxConfiguredCarvers.SAMPLE_CAVE)
        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DataboxPlacedFeatures.ADAMANTIUM_ORE);
  }

  public static MobSpawnSettings.Builder addCaveMobs(MobSpawnSettings.Builder builder) {
    return builder
        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.FROG, 50, 1, 1))
        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CAVE_SPIDER, 50, 1, 1))
        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 50, 1, 1));
  }

  public static MobSpawnSettings.Builder addRotspawn(MobSpawnSettings.Builder builder) {
    return builder
        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.EVOKER, 100, 2, 4))
        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 100, 4, 4))
        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SNOW_GOLEM, 100, 1, 2));
  }

  public static BiomeSpecialEffects.Builder generateColors(
      BiomeSpecialEffects.Builder builder,
      int skyFog,
      int grass) {
    return builder
        .skyColor(1186057)
        .fogColor(skyFog)
        .waterColor(342306)
        .waterFogColor(332810)
        .grassColorOverride(grass)
        .foliageColorOverride(grass);
  }
}
