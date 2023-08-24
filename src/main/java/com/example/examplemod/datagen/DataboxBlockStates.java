package com.example.examplemod.datagen;

import com.example.examplemod.datagen.provider.DataboxBlockstateProvider;
import com.example.examplemod.init.DataboxBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DataboxBlockStates extends DataboxBlockstateProvider {

	public DataboxBlockStates(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, fileHelper);
	}

	@Override
	public String getName() {
		return "Databox Block States";
	}

	@Override
	protected void registerStatesAndModels() {
		block(DataboxBlocks.ADAMANTIUM_BLOCK);
		block(DataboxBlocks.ADAMANTIUM_DEBRIS);
		block(DataboxBlocks.ADAMANTIUM_ORE);
		block(DataboxBlocks.DEEPSLATE_ADAMANTIUM_ORE);
		blockLamp(DataboxBlocks.CORE_BLOCK);
		block(DataboxBlocks.JUMP_BLOCK);
	}
}