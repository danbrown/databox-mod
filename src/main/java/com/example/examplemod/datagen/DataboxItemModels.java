package com.example.examplemod.datagen;

import com.example.examplemod.datagen.provider.DataboxItemModelProvider;
import com.example.examplemod.init.DataboxBlocks;
import com.example.examplemod.init.DataboxItems;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DataboxItemModels extends DataboxItemModelProvider {

	public DataboxItemModels(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, fileHelper);
	}

	@Override
	public String getName() {
		return "Databox Item Models";
	}

	@Override
	protected void registerModels() {
		block(DataboxBlocks.ADAMANTIUM_BLOCK);
		block(DataboxBlocks.ADAMANTIUM_ORE);
		block(DataboxBlocks.DEEPSLATE_ADAMANTIUM_ORE);
		block(DataboxBlocks.ADAMANTIUM_DEBRIS);
		blockLamp(DataboxBlocks.CORE_BLOCK);
		block(DataboxBlocks.JUMP_BLOCK);

		// @ ITEMS
		normalItem(DataboxItems.ADAMANTIUM_INGOT);
		normalItem(DataboxItems.ADAMANTIUM_FRAGMENT);
		normalItem(DataboxItems.CUSTOM_ITEM);

	}
}