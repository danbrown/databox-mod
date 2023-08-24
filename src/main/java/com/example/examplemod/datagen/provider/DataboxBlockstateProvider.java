package com.example.examplemod.datagen.provider;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import com.example.examplemod.ExampleMod;

public abstract class DataboxBlockstateProvider extends BlockStateProvider {

	public DataboxBlockstateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, ExampleMod.MOD_ID, helper);
	}

	protected ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	protected String name(Supplier<? extends Block> block) {
		return ForgeRegistries.BLOCKS.getKey(block.get()).getPath();
	}

	public void block(Supplier<? extends Block> block) {
		simpleBlock(block.get());
	}

	public void blockLamp(Supplier<? extends Block> block) {

		// {
		// "variants": {
		// "lit=true": { "model": "databox:block/core_block_on" },
		// "lit=false": { "model": "databox:block/core_block_off" }
		// }
		// }

		getVariantBuilder(block.get()).forAllStates(state -> {
			boolean lit = state.getValue(BlockStateProperties.LIT);
			return ConfiguredModel.builder()
					.modelFile(models().cubeAll(name(block) + (lit ? "_on" : "_off"), texture(name(block) + (lit ? "_on" : "_off"))))
					.build();
		});

	}

	public void blockTranslucent(Supplier<? extends Block> block) {
		simpleBlock(block.get(), models().cubeAll(name(block), blockTexture(block.get())).renderType("translucent"));
	}

	public void log(Supplier<? extends RotatedPillarBlock> block, String name) {
		axisBlock(block.get(), texture(name));
	}

	private void crossBlock(Supplier<? extends Block> block, ModelFile model) {
		getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.builder()
				.modelFile(model)
				.build());
	}

	public void torchBlock(Supplier<? extends Block> block, Supplier<? extends Block> wall) {
		ModelFile torch = models().torch(name(block), texture(name(block))).renderType("cutout");
		ModelFile torchwall = models().torchWall(name(wall), texture(name(block))).renderType("cutout");
		simpleBlock(block.get(), torch);
		getVariantBuilder(wall.get()).forAllStates(state -> ConfiguredModel.builder()
				.modelFile(torchwall)
				.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
				.build());
	}

	public void crossBlock(Supplier<? extends Block> block) {
		crossBlock(block, models().cross(name(block), texture(name(block))).renderType("cutout"));
	}

	public void tintedCrossBlock(Supplier<? extends Block> block) {
		crossBlock(block, models().withExistingParent(name(block), mcLoc("block/tinted_cross"))
				.texture("cross", texture(name(block))).renderType("cutout"));
	}

	public void stairs(Supplier<? extends StairBlock> block, Supplier<? extends Block> fullBlock) {
		stairsBlock(block.get(), texture(name(fullBlock)));
	}

	public void slab(Supplier<? extends SlabBlock> block, Supplier<? extends Block> fullBlock) {
		slabBlock(block.get(), texture(name(fullBlock)), texture(name(fullBlock)));
	}

	public void wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
		wallBlock(wall.get(), texture(name(fullBlock)));
	}

	public void fence(Supplier<? extends FenceBlock> block, Supplier<? extends Block> fullBlock) {
		fenceBlock(block.get(), texture(name(fullBlock)));
		fenceColumn(block, name(fullBlock));
	}

	private void fenceColumn(Supplier<? extends FenceBlock> block, String name) {
		String baseName = name(block);
		fourWayBlock(block.get(),
				models().fencePost(baseName + "_post", texture(name)),
				models().fenceSide(baseName + "_side", texture(name)));
	}

	public void fenceGate(Supplier<? extends FenceGateBlock> block, Supplier<? extends Block> fullBlock) {
		fenceGateBlock(block.get(), texture(name(fullBlock)));
	}

	public void door(Supplier<? extends DoorBlock> block, String name) {
		doorBlockWithRenderType(block.get(), name(block), texture(name + "_door_bottom"), texture(name + "_door_top"),
				"cutout");
	}

	public void trapdoor(Supplier<? extends TrapDoorBlock> block, String name) {
		trapdoorBlockWithRenderType(block.get(), texture(name + "_trapdoor"), true, "cutout");
	}

	public void carpet(Supplier<? extends WoolCarpetBlock> block) {
		simpleBlock(block.get(), models().carpet(name(block), texture(name(block))));
	}

	public void button(Supplier<? extends ButtonBlock> block, Supplier<? extends Block> fullBlock) {
		buttonBlock(block.get(), texture(name(fullBlock)));
	}

	public void pressurePlate(Supplier<? extends PressurePlateBlock> block, Supplier<? extends Block> fullBlock) {
		pressurePlateBlock(block.get(), texture(name(fullBlock)));
	}

	public void sign(Supplier<? extends StandingSignBlock> standingBlock, Supplier<? extends WallSignBlock> wallBlock,
			String name) {
		signBlock(standingBlock.get(), wallBlock.get(), modLoc("block/" + name));
	}

	public void hangingSign(Supplier<? extends CeilingHangingSignBlock> standingBlock,
			Supplier<? extends WallHangingSignBlock> wallBlock, String name) {
		ModelFile model = models().getBuilder(name(standingBlock)).texture("particle", modLoc("block/" + name));
		simpleBlock(standingBlock.get(), model);
		simpleBlock(wallBlock.get(), model);
	}
}