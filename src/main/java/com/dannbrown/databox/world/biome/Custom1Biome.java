package com.dannbrown.databox.world.biome;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.sounds.Music;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.core.particles.ParticleTypes;

import java.util.List;

public class Custom1Biome {
  public static Biome createBiome() {
    BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder().fogColor(12638463).waterColor(4159204)
        .waterFogColor(329011).skyColor(7972607).foliageColorOverride(10387789).grassColorOverride(9470285)
        .ambientLoopSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.soul_sand_valley.loop")))
        .ambientMoodSound(new AmbientMoodSettings(
            ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.soul_sand_valley.mood")), 6000, 8, 2))
        .ambientAdditionsSound(new AmbientAdditionsSettings(
            ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.soul_sand_valley.additions")), 0.0111D))
        .backgroundMusic(new Music(
            ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("music.overworld.meadow")), 12000, 24000, true))
        .ambientParticle(new AmbientParticleSettings(ParticleTypes.SPIT, 0.005f)).build();

    BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder();

    biomeGenerationSettings.addFeature(
        GenerationStep.Decoration.VEGETAL_DECORATION,
        PlacementUtils.register("databox:tree_custom_biome_1",
            FeatureUtils.register("databox:tree_custom_biome_1", Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()),
                    new MegaJungleTrunkPlacer(10, 2, 19),
                    BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()),
                    new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                    new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().build()),
            List.of(CountPlacement.of(1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
                BiomeFilter.biome())));

    BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenerationSettings);
    BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
    BiomeDefaultFeatures.addSurfaceFreezing(biomeGenerationSettings);

    MobSpawnSettings.Builder mobSpawnInfo = new MobSpawnSettings.Builder();

    mobSpawnInfo.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 20, 4, 4));

    return new Biome.BiomeBuilder().precipitation(Biome.Precipitation.RAIN).temperature(0.5f).downfall(0.5f)
        .specialEffects(effects).mobSpawnSettings(mobSpawnInfo.build())
        .generationSettings(biomeGenerationSettings.build()).build();
  }
}