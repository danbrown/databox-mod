package com.dannbrown.databox.init;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.eventbus.api.IEventBus;

import com.dannbrown.databox.DataboxMod;

import net.minecraft.core.Registry;

public class DataboxDimensions {
  public static final ResourceKey<Level> DATABOX_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY,
      new ResourceLocation(DataboxMod.MOD_ID, "databox_dimension"));

  public static final ResourceKey<DimensionType> DATABOX_DIMENSION = ResourceKey
      .create(Registry.DIMENSION_TYPE_REGISTRY, DATABOX_LEVEL.registry());

  // @ Register
  public static void register(IEventBus eventBus) {
  }
}
