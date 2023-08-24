package com.example.examplemod.datagen;

import com.example.examplemod.init.DataboxCarvers;
import com.example.examplemod.lib.LibUtils;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.util.valueproviders.ConstantFloat;

public class DataboxConfiguredCarvers {
  public static final ResourceKey<ConfiguredWorldCarver<?>> UNDERGARDEN_CAVE = ResourceKey.create(
      Registries.CONFIGURED_CARVER,
      LibUtils.resourceLocation("undergarden_cave"));

  public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> context) {
    HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

    context.register(
        UNDERGARDEN_CAVE,
        DataboxCarvers.UNDERGARDEN_CAVE.get().configured(
            new CaveCarverConfiguration(
                0.5f,
                UniformHeight.of(VerticalAnchor.aboveBottom(5), VerticalAnchor.belowTop(1)),
                ConstantFloat.of(0.5f),
                VerticalAnchor.aboveBottom(10),
                BuiltInRegistries.BLOCK.getOrCreateTag(DataboxTags.Blocks.DATABOX_CARVER_REPLACEABLES),
                ConstantFloat.of(1.0f),
                ConstantFloat.of(1.0f),
                ConstantFloat.of(-0.7f))));

    return;
  }
}
