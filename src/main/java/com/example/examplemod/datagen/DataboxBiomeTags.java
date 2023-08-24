package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DataboxBiomeTags extends BiomeTagsProvider {
  public DataboxBiomeTags(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> future,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(output, future, ExampleMod.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    super.addTags(provider);

    // databox
    this.tag(DataboxTags.Biomes.IS_DATABOX).add(
        DataboxBiomes.ANCIENT_SEA);

    // vanilla
    this.tag(BiomeTags.WITHOUT_ZOMBIE_SIEGES).addTag(DataboxTags.Biomes.IS_DATABOX);
    this.tag(BiomeTags.WITHOUT_PATROL_SPAWNS).addTag(DataboxTags.Biomes.IS_DATABOX);
    this.tag(BiomeTags.WITHOUT_WANDERING_TRADER_SPAWNS).addTag(DataboxTags.Biomes.IS_DATABOX);
  }

  @Override
  public String getName() {
    return "Databox Biome Tags";
  }
}
