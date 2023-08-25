package com.example.examplemod.datagen;

import com.example.examplemod.lib.LibUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class DataboxTags {

  public static class Items {

    public static final TagKey<Item> RAW_MATERIALS_ADADMANTIUM = forgeTag("raw_materials/adamantium");

    public static final TagKey<Item> INGOTS_ADAMANTIUM = forgeTag("ingots/adamantium");

    public static final TagKey<Item> ORES_ADAMANTIUM = forgeTag("ores/adamantium");

    public static final TagKey<Item> STORAGE_BLOCKS_ADAMANTIUM = forgeTag("storage_blocks/adamantium");

    public static final TagKey<Item> STORAGE_BLOCKS_ADAMANTIUM_DEBRIS = forgeTag("storage_blocks/raw_adamantium");

    private static TagKey<Item> tag(String name) {
      return ItemTags.create(LibUtils.resourceLocation(name));
    }

    private static TagKey<Item> forgeTag(String name) {
      return ItemTags.create(LibUtils.forgeResourceLocation(name));
    }
  }

  public static class Blocks {
    public static final TagKey<Block> BASE_STONE_SAMPLE = tag("base_stone_sample");
    public static final TagKey<Block> DEPTHROCK_ORE_REPLACEABLES = tag("depthrock_ore_replaceables");
    public static final TagKey<Block> SHIVERSTONE_ORE_REPLACEABLES = tag("shiverstone_ore_replaceables");
    public static final TagKey<Block> TREMBLECRUST_ORE_REPLACEABLES = tag("tremblecrust_ore_replaceables");
    public static final TagKey<Block> BASALT_ORE_REPLACEABLES = tag("basalt_ore_replaceables");
    public static final TagKey<Block> DATABOX_CARVER_REPLACEABLES = tag("databox_carver_replaceables");

    // @ ORES
    public static final TagKey<Block> ORES_ADAMANTIUM = forgeTag("ores/adamantium");

    // @ Storage blocks
    public static final TagKey<Block> STORAGE_BLOCKS_ADAMANTIUM = forgeTag("storage_blocks/adamantium");
    public static final TagKey<Block> STORAGE_BLOCKS_ADAMANTIUM_DEBRIS = forgeTag("storage_blocks/adamantium_debris");

    private static TagKey<Block> tag(String name) {
      return BlockTags.create(LibUtils.resourceLocation(name));
    }

    private static TagKey<Block> forgeTag(String name) {
      return createTagKey(Registries.BLOCK, LibUtils.forgeResourceLocation(name));
    }
  }

  public static class Entities {
    public static final TagKey<EntityType<?>> ROTSPAWN = tag("rotspawn");
    public static final TagKey<EntityType<?>> CAVERN_CREATURE = tag("cavern_creature");
    public static final TagKey<EntityType<?>> IMMUNE_TO_VIRULENT_MIX = tag("immune_to_virulent_mix");
    public static final TagKey<EntityType<?>> IMMUNE_TO_SCINTLING_GOO = tag("immune_to_scintling_goo");

    private static TagKey<EntityType<?>> tag(String name) {
      return createTagKey(Registries.ENTITY_TYPE, LibUtils.resourceLocation(name));
    }
  }

  public static class Fluids {
    public static final TagKey<Fluid> VIRULENT = tag("virulent");

    private static TagKey<Fluid> tag(String name) {
      return createTagKey(Registries.FLUID, LibUtils.resourceLocation(name));
    }
  }

  public static class Biomes {
    public static final TagKey<Biome> IS_DATABOX = tag("is_databox");
    public static final TagKey<Biome> HAS_CATACOMBS = tag("has_structure/catacombs");

    private static TagKey<Biome> tag(String name) {
      return createTagKey(Registries.BIOME, LibUtils.resourceLocation(name));
    }

    private static TagKey<Biome> forgeTag(String name) {
      return createTagKey(Registries.BIOME, LibUtils.forgeResourceLocation(name));
    }
  }

  private static <T> TagKey<T> createTagKey(ResourceKey<? extends Registry<T>> registry, ResourceLocation key) {
    return TagKey.create(registry, key);
  }
}
