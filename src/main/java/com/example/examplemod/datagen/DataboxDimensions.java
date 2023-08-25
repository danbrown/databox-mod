package com.example.examplemod.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.OptionalLong;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.init.DataboxBlocks;

public class DataboxDimensions {

	public static String dimensionName = "sampledim";

	public static final ResourceKey<Level> SAMPLE_LEVEL = ResourceKey.create(Registries.DIMENSION,
			name(dimensionName));

	public static final ResourceKey<NoiseGeneratorSettings> SAMPLE_NOISE_GEN = ResourceKey
			.create(Registries.NOISE_SETTINGS, name(dimensionName));

	public static final ResourceKey<DimensionType> SAMPLE_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
			name(dimensionName));

	public static final ResourceKey<LevelStem> SAMPLE_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM,
			name(dimensionName));

	private static ResourceLocation name(String name) {
		return new ResourceLocation(ExampleMod.MOD_ID, name);
	}

	public static void bootstrapType(BootstapContext<DimensionType> context) {
		context.register(SAMPLE_DIM_TYPE, new DimensionType(
				OptionalLong.of(18000L), // fixed time
				false, // skylight
				true, // ceiling
				false, // ultrawarm
				true, // natural
				4.0D, // coordinate scale
				true, // bed works
				false, // respawn anchor works
				0, // Minimum Y Level
				128, // Height + Min Y = Max Y
				128, // Logical Height
				BlockTags.INFINIBURN_OVERWORLD, // infiniburn
				name(dimensionName), // DimensionRenderInfo
				0.1F, // ambient light
				new DimensionType.MonsterSettings(true, false, UniformInt.of(0, 7), 0)));
	}

	public static void bootstrapStem(BootstapContext<LevelStem> context) {
		HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
		HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
		HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
		context.register(SAMPLE_LEVEL_STEM, new LevelStem(dimTypes.getOrThrow(SAMPLE_DIM_TYPE),
				new NoiseBasedChunkGenerator(DataboxBiomes.buildBiomeSource(biomeRegistry),
						noiseGenSettings.getOrThrow(SAMPLE_NOISE_GEN))));
	}

	public static void bootstrapNoise(BootstapContext<NoiseGeneratorSettings> context) {
		HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
		HolderGetter<NormalNoise.NoiseParameters> noises = context.lookup(Registries.NOISE);
		DensityFunction densityfunction = DataboxDensityFunctions.getFunction(functions, DataboxDensityFunctions.SHIFT_X);
		DensityFunction densityfunction1 = DataboxDensityFunctions.getFunction(functions, DataboxDensityFunctions.SHIFT_Z);

		context.register(SAMPLE_NOISE_GEN, new NoiseGeneratorSettings(
				NoiseSettings.create(0, 128, 2, 2),
				DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState(),
				Blocks.WATER.defaultBlockState(),
				new NoiseRouter(
						DensityFunctions.zero(), // barrier
						DensityFunctions.zero(), // fluid level floodedness
						DensityFunctions.zero(), // fluid level spread
						DensityFunctions.zero(), // lava
						DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D,
								noises.getOrThrow(Noises.TEMPERATURE)), // temperature
						DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D,
								noises.getOrThrow(Noises.VEGETATION)), // vegetation
						DataboxDensityFunctions.getFunction(functions, NoiseRouterData.CONTINENTS), // continents
						DataboxDensityFunctions.getFunction(functions, NoiseRouterData.EROSION), // erosion
						DensityFunctions.rangeChoice(
								DataboxDensityFunctions.getFunction(functions, DataboxDensityFunctions.Y),
								0.0D,
								32.0D,
								DensityFunctions.constant(2.0D),
								DensityFunctions.constant(-2.0D)), // depth
						DataboxDensityFunctions.getFunction(functions, NoiseRouterData.RIDGES), // ridges
						DensityFunctions.zero(), // initial density
						DensityFunctions.mul(
								DensityFunctions.constant(0.64D),
								DensityFunctions.interpolated(
										DensityFunctions.blendDensity(
												DensityFunctions.add(
														DensityFunctions.constant(2.5D),
														DensityFunctions.mul(
																DensityFunctions.yClampedGradient(-8, 24, 0.0D, 1.0D),
																DensityFunctions.add(
																		DensityFunctions.constant(-2.5D),
																		DensityFunctions.add(
																				DensityFunctions.constant(0.5D),
																				DensityFunctions.mul(
																						DensityFunctions.yClampedGradient(110, 128, 1.0D, 0.0D),
																						DensityFunctions.add(
																								DensityFunctions.constant(-0.5F),
																								BlendedNoise.createUnseeded(0.1D, 0.3D, 80.0D, 60.0D, 1.0D))))))))))
								.squeeze(), // final density
						DensityFunctions.zero(), // vein toggle
						DensityFunctions.zero(), // vein ridged
						DensityFunctions.zero() // vein gap
				),
				SurfaceRules.sequence(
						// bedrock floor
						SurfaceRules.ifTrue(SurfaceRules.verticalGradient("minecraft:bedrock_floor", VerticalAnchor.bottom(),
								VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
						// bedrock ceiling
						SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("minecraft:bedrock_roof",
								VerticalAnchor.belowTop(5), VerticalAnchor.top())),
								SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
						// filler depthrock
						SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0),
								SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState())),
						// sediment
						SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
								SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(33), 0)),
										SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState()))),
						// frozen deepturf
						SurfaceRules.ifTrue(SurfaceRules.isBiome(DataboxBiomes.CUSTOM_BIOME_1, DataboxBiomes.CUSTOM_BIOME_1),
								SurfaceRules.ifTrue(
										SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
										SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState()))),
						// mix coarse deepsoil into blood bog
						SurfaceRules.ifTrue(
								SurfaceRules.isBiome(DataboxBiomes.CUSTOM_BIOME_1),
								SurfaceRules.ifTrue(
										SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.noiseCondition(noises.getOrThrow(Noises.NETHER_STATE_SELECTOR).key(), 0.0D,
																1.8D),
														SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState())),
												SurfaceRules.ifTrue(
														SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
														SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState()))))),
						// mix coarse deepsoil into smog spires
						SurfaceRules.ifTrue(
								SurfaceRules.isBiome(DataboxBiomes.CUSTOM_BIOME_1),
								SurfaceRules.ifTrue(
										SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.noiseCondition(noises.getOrThrow(Noises.NETHER_STATE_SELECTOR).key(), 0.0D,
																1.8D),
														SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState())),
												SurfaceRules.ifTrue(
														SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
														SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState()))))),
						// mix coarse deepsoil into barren biomes
						SurfaceRules.ifTrue(
								SurfaceRules.isBiome(DataboxBiomes.CUSTOM_BIOME_1, DataboxBiomes.CUSTOM_BIOME_1),
								SurfaceRules.ifTrue(
										SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.noiseCondition(noises.getOrThrow(Noises.NETHER_STATE_SELECTOR).key(), 0.0D,
																1.8D),
														SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState())),
												SurfaceRules.ifTrue(
														SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
														SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState())),
												SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState())))),
						// cover the ground in deepturf
						SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
								SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState())),
						// add deepsoil underneath
						SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
								SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState())),
						// add shiverstone to icy biomes
						SurfaceRules.ifTrue(SurfaceRules.isBiome(DataboxBiomes.CUSTOM_BIOME_1, DataboxBiomes.CUSTOM_BIOME_1),
								SurfaceRules.state(DataboxBlocks.ADAMANTIUM_DEBRIS.get().defaultBlockState()))),
				List.of(), // spawn targets
				32,
				false,
				false,
				false,
				false));
	}
}