package com.example.examplemod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.init.DataboxBlocks;

import java.util.concurrent.CompletableFuture;

public class DataboxBlockTags extends IntrinsicHolderTagsProvider<Block> {

	public DataboxBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), ExampleMod.MOD_ID,
				existingFileHelper);
	}

	@Override
	public String getName() {
		return "Databox Block Tags";
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {

		// mod forge
		tag(DataboxTags.Blocks.ORES_ADAMANTIUM)
				.add(DataboxBlocks.ADAMANTIUM_ORE.get(), DataboxBlocks.DEEPSLATE_ADAMANTIUM_ORE.get());

		tag(DataboxTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM).add(DataboxBlocks.ADAMANTIUM_BLOCK.get());

		tag(DataboxTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM_DEBRIS).add(DataboxBlocks.ADAMANTIUM_DEBRIS.get());

		// vanilla

		// forge
		tag(Tags.Blocks.ORES).addTags(DataboxTags.Blocks.ORES_ADAMANTIUM);

		// huge mineables lists!
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
				DataboxBlocks.ADAMANTIUM_BLOCK.get(),
				DataboxBlocks.ADAMANTIUM_DEBRIS.get(),
				DataboxBlocks.ADAMANTIUM_ORE.get(),
				DataboxBlocks.DEEPSLATE_ADAMANTIUM_ORE.get(),
				DataboxBlocks.CORE_BLOCK.get(),
				DataboxBlocks.JUMP_BLOCK.get());

		// tag(BlockTags.MINEABLE_WITH_AXE).add( );

		// tag(BlockTags.MINEABLE_WITH_SHOVEL).add();

		// tag(BlockTags.MINEABLE_WITH_HOE).add();

		// tag(BlockTags.NEEDS_STONE_TOOL).add();

		tag(BlockTags.NEEDS_IRON_TOOL).add(
				DataboxBlocks.CORE_BLOCK.get(),
				DataboxBlocks.JUMP_BLOCK.get());

		tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
				DataboxBlocks.ADAMANTIUM_BLOCK.get(),
				DataboxBlocks.ADAMANTIUM_DEBRIS.get(),
				DataboxBlocks.ADAMANTIUM_ORE.get(),
				DataboxBlocks.DEEPSLATE_ADAMANTIUM_ORE.get());
	}
}