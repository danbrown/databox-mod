package com.example.examplemod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.init.DataboxItems;

import java.util.concurrent.CompletableFuture;

public class DataboxItemTags extends ItemTagsProvider {

	public DataboxItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future,
			CompletableFuture<TagLookup<Block>> provider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, future, provider, ExampleMod.MOD_ID, existingFileHelper);
	}

	@Override
	public String getName() {
		return "Databox Item Tags";
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {

		// modded forge
		tag(DataboxTags.Items.RAW_MATERIALS_ADADMANTIUM).add(DataboxItems.ADAMANTIUM_FRAGMENT.get());

		tag(DataboxTags.Items.INGOTS_ADAMANTIUM).add(DataboxItems.ADAMANTIUM_INGOT.get());

		copy(DataboxTags.Blocks.ORES_ADAMANTIUM, DataboxTags.Items.ORES_ADAMANTIUM);

		copy(DataboxTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM,
				DataboxTags.Items.STORAGE_BLOCKS_ADAMANTIUM);

		copy(DataboxTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM_DEBRIS,
				DataboxTags.Items.STORAGE_BLOCKS_ADAMANTIUM_DEBRIS);
	}
}