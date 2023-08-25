package com.example.examplemod.datagen;

import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.OreVeinifier;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class DataboxDensityFunctions {
  public static final float GLOBAL_OFFSET = -0.50375F;
  private static final float ORE_THICKNESS = 0.08F;
  private static final double VEININESS_FREQUENCY = 1.5D;
  private static final double NOODLE_SPACING_AND_STRAIGHTNESS = 1.5D;
  private static final double SURFACE_DENSITY_THRESHOLD = 1.5625D;
  private static final double CHEESE_NOISE_TARGET = -0.703125D;
  public static final int ISLAND_CHUNK_DISTANCE = 64;
  public static final long ISLAND_CHUNK_DISTANCE_SQR = 4096L;
  private static final DensityFunction BLENDING_FACTOR = DensityFunctions.constant(10.0D);
  private static final DensityFunction BLENDING_JAGGEDNESS = DensityFunctions.zero();
  private static final ResourceKey<DensityFunction> ZERO = createKey("zero");
  public static final ResourceKey<DensityFunction> Y = createKey("y");
  public static final ResourceKey<DensityFunction> SHIFT_X = createKey("shift_x");
  public static final ResourceKey<DensityFunction> SHIFT_Z = createKey("shift_z");
  private static final ResourceKey<DensityFunction> BASE_3D_NOISE_OVERWORLD = createKey("overworld/base_3d_noise");
  private static final ResourceKey<DensityFunction> BASE_3D_NOISE_NETHER = createKey("nether/base_3d_noise");
  private static final ResourceKey<DensityFunction> BASE_3D_NOISE_END = createKey("end/base_3d_noise");
  public static final ResourceKey<DensityFunction> CONTINENTS = createKey("overworld/continents");
  public static final ResourceKey<DensityFunction> EROSION = createKey("overworld/erosion");
  public static final ResourceKey<DensityFunction> RIDGES = createKey("overworld/ridges");
  public static final ResourceKey<DensityFunction> RIDGES_FOLDED = createKey("overworld/ridges_folded");
  public static final ResourceKey<DensityFunction> OFFSET = createKey("overworld/offset");
  public static final ResourceKey<DensityFunction> FACTOR = createKey("overworld/factor");
  public static final ResourceKey<DensityFunction> JAGGEDNESS = createKey("overworld/jaggedness");
  public static final ResourceKey<DensityFunction> DEPTH = createKey("overworld/depth");
  private static final ResourceKey<DensityFunction> SLOPED_CHEESE = createKey("overworld/sloped_cheese");
  public static final ResourceKey<DensityFunction> CONTINENTS_LARGE = createKey("overworld_large_biomes/continents");
  public static final ResourceKey<DensityFunction> EROSION_LARGE = createKey("overworld_large_biomes/erosion");
  private static final ResourceKey<DensityFunction> OFFSET_LARGE = createKey("overworld_large_biomes/offset");
  private static final ResourceKey<DensityFunction> FACTOR_LARGE = createKey("overworld_large_biomes/factor");
  private static final ResourceKey<DensityFunction> JAGGEDNESS_LARGE = createKey("overworld_large_biomes/jaggedness");
  private static final ResourceKey<DensityFunction> DEPTH_LARGE = createKey("overworld_large_biomes/depth");
  private static final ResourceKey<DensityFunction> SLOPED_CHEESE_LARGE = createKey(
      "overworld_large_biomes/sloped_cheese");
  private static final ResourceKey<DensityFunction> OFFSET_AMPLIFIED = createKey("overworld_amplified/offset");
  private static final ResourceKey<DensityFunction> FACTOR_AMPLIFIED = createKey("overworld_amplified/factor");
  private static final ResourceKey<DensityFunction> JAGGEDNESS_AMPLIFIED = createKey("overworld_amplified/jaggedness");
  private static final ResourceKey<DensityFunction> DEPTH_AMPLIFIED = createKey("overworld_amplified/depth");
  private static final ResourceKey<DensityFunction> SLOPED_CHEESE_AMPLIFIED = createKey(
      "overworld_amplified/sloped_cheese");
  private static final ResourceKey<DensityFunction> SLOPED_CHEESE_END = createKey("end/sloped_cheese");
  private static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = createKey(
      "overworld/caves/spaghetti_roughness_function");
  private static final ResourceKey<DensityFunction> ENTRANCES = createKey("overworld/caves/entrances");
  private static final ResourceKey<DensityFunction> NOODLE = createKey("overworld/caves/noodle");
  private static final ResourceKey<DensityFunction> PILLARS = createKey("overworld/caves/pillars");
  private static final ResourceKey<DensityFunction> SPAGHETTI_2D_THICKNESS_MODULATOR = createKey(
      "overworld/caves/spaghetti_2d_thickness_modulator");
  private static final ResourceKey<DensityFunction> SPAGHETTI_2D = createKey("overworld/caves/spaghetti_2d");

  private static ResourceKey<DensityFunction> createKey(String p_209537_) {
    return ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(p_209537_));
  }

  public static DensityFunction getFunction(HolderGetter<DensityFunction> p_256312_,
      ResourceKey<DensityFunction> p_256077_) {
    return new DensityFunctions.HolderHolder(p_256312_.getOrThrow(p_256077_));
  }

  private static DensityFunction registerAndWrap(BootstapContext<DensityFunction> p_256149_,
      ResourceKey<DensityFunction> p_255905_, DensityFunction p_255856_) {
    return new DensityFunctions.HolderHolder(p_256149_.register(p_255905_, p_255856_));
  }

  private static DensityFunction peaksAndValleys(DensityFunction p_224438_) {
    return DensityFunctions.mul(DensityFunctions.add(
        DensityFunctions.add(p_224438_.abs(), DensityFunctions.constant(-0.6666666666666666D)).abs(),
        DensityFunctions.constant(-0.3333333333333333D)), DensityFunctions.constant(-3.0D));
  }

  public static float peaksAndValleys(float p_224436_) {
    return -(Math.abs(Math.abs(p_224436_) - 0.6666667F) - 0.33333334F) * 3.0F;
  }

  private static DensityFunction pillars(HolderGetter<NormalNoise.NoiseParameters> p_255985_) {
    double d0 = 25.0D;
    double d1 = 0.3D;
    DensityFunction densityfunction = DensityFunctions.noise(p_255985_.getOrThrow(Noises.PILLAR), 25.0D, 0.3D);
    DensityFunction densityfunction1 = DensityFunctions.mappedNoise(p_255985_.getOrThrow(Noises.PILLAR_RARENESS), 0.0D,
        -2.0D);
    DensityFunction densityfunction2 = DensityFunctions.mappedNoise(p_255985_.getOrThrow(Noises.PILLAR_THICKNESS), 0.0D,
        1.1D);
    DensityFunction densityfunction3 = DensityFunctions
        .add(DensityFunctions.mul(densityfunction, DensityFunctions.constant(2.0D)), densityfunction1);
    return DensityFunctions.cacheOnce(DensityFunctions.mul(densityfunction3, densityfunction2.cube()));
  }

  public static Holder<? extends DensityFunction> bootstrap(BootstapContext<DensityFunction> p_256220_) {
    HolderGetter<NormalNoise.NoiseParameters> holdergetter = p_256220_.lookup(Registries.NOISE);
    HolderGetter<DensityFunction> holdergetter1 = p_256220_.lookup(Registries.DENSITY_FUNCTION);
    p_256220_.register(ZERO, DensityFunctions.zero());
    int i = DimensionType.MIN_Y * 2;
    int j = DimensionType.MAX_Y * 2;
    p_256220_.register(Y, DensityFunctions.yClampedGradient(i, j, (double) i, (double) j));
    DensityFunction densityfunction = registerAndWrap(p_256220_, SHIFT_X, DensityFunctions
        .flatCache(DensityFunctions.cache2d(DensityFunctions.shiftA(holdergetter.getOrThrow(Noises.SHIFT)))));
    DensityFunction densityfunction1 = registerAndWrap(p_256220_, SHIFT_Z, DensityFunctions
        .flatCache(DensityFunctions.cache2d(DensityFunctions.shiftB(holdergetter.getOrThrow(Noises.SHIFT)))));
    p_256220_.register(BASE_3D_NOISE_OVERWORLD, BlendedNoise.createUnseeded(0.25D, 0.125D, 80.0D, 160.0D, 8.0D));
    p_256220_.register(BASE_3D_NOISE_NETHER, BlendedNoise.createUnseeded(0.25D, 0.375D, 80.0D, 60.0D, 8.0D));
    p_256220_.register(BASE_3D_NOISE_END, BlendedNoise.createUnseeded(0.25D, 0.25D, 80.0D, 160.0D, 4.0D));
    Holder<DensityFunction> holder = p_256220_.register(CONTINENTS, DensityFunctions.flatCache(DensityFunctions
        .shiftedNoise2d(densityfunction, densityfunction1, 0.25D, holdergetter.getOrThrow(Noises.CONTINENTALNESS))));
    Holder<DensityFunction> holder1 = p_256220_.register(EROSION, DensityFunctions.flatCache(DensityFunctions
        .shiftedNoise2d(densityfunction, densityfunction1, 0.25D, holdergetter.getOrThrow(Noises.EROSION))));
    DensityFunction densityfunction2 = registerAndWrap(p_256220_, RIDGES, DensityFunctions.flatCache(DensityFunctions
        .shiftedNoise2d(densityfunction, densityfunction1, 0.25D, holdergetter.getOrThrow(Noises.RIDGE))));
    p_256220_.register(RIDGES_FOLDED, peaksAndValleys(densityfunction2));

    return p_256220_.register(PILLARS, pillars(holdergetter));
  }

}
