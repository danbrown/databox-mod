package com.example.examplemod.init;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.content.block.CoreBlock;
import com.example.examplemod.content.block.JumpBlock;
import com.example.examplemod.lib.LibBlockNames;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class DataboxBlocks {

        // @ Registering
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        ExampleMod.MOD_ID);

        public static void register(IEventBus bus) {
                BLOCKS.register(bus);
        }

        // @ Blocks
        // Adamantium
        public static final RegistryObject<Block> ADAMANTIUM_BLOCK = BLOCKS.register(LibBlockNames.ADAMANTIUM_BLOCK,
                        () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
                                        .sound(SoundType.NETHERITE_BLOCK)));

        public static final RegistryObject<Block> ADAMANTIUM_ORE = BLOCKS.register(LibBlockNames.ADAMANTIUM_ORE,
                        () -> new DropExperienceBlock(
                                        BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).sound(SoundType.BASALT)));

        public static final RegistryObject<Block> DEEPSLATE_ADAMANTIUM_ORE = BLOCKS
                        .register(LibBlockNames.DEEPSLATE_ADAMANTIUM_ORE, () -> new DropExperienceBlock(
                                        BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
                                                        .sound(SoundType.BASALT)));

        public static final RegistryObject<Block> ADAMANTIUM_DEBRIS = BLOCKS.register(LibBlockNames.ADAMANTIUM_DEBRIS,
                        () -> new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
                                        .sound(SoundType.ANCIENT_DEBRIS)));

        // // TEST
        // public static final RegistryObject<Block> DEPTHROCK =
        // BLOCKS.register("depthrock",
        // () -> new
        // Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));

        // public static final RegistryObject<Block> DEEPTURF_BLOCK =
        // BLOCKS.register("deepturf_block",
        // () -> new
        // Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.GRASS)));

        // public static final RegistryObject<Block> DEEPSOIL =
        // BLOCKS.register("deepsoil",
        // () -> new
        // Block(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.GRAVEL)));

        // public static final RegistryObject<Block> SHIVERSTONE =
        // BLOCKS.register("shiverstone",
        // () -> new
        // Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));

        // public static final RegistryObject<Block> COARSE_DEEPSOIL =
        // BLOCKS.register("coarse_deepsoil",
        // () -> new
        // Block(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT).sound(SoundType.GRAVEL)));

        // public static final RegistryObject<Block> SEDIMENT =
        // BLOCKS.register("sediment",
        // () -> new
        // Block(BlockBehaviour.Properties.copy(Blocks.SAND).sound(SoundType.SAND)));

        // public static final RegistryObject<Block> ASHEN_DEEPTURF_BLOCK =
        // BLOCKS.register("ashen_deepturf_block",
        // () -> new
        // Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.GRASS)));

        // public static final RegistryObject<Block> FROZEN_DEEPTURF_BLOCK =
        // BLOCKS.register("frozen_deepturf_block",
        // () -> new
        // Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.GRASS)));

        // Jump Block
        public static final RegistryObject<Block> JUMP_BLOCK = BLOCKS.register(LibBlockNames.JUMP_BLOCK,
                        () -> new JumpBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));

        // Core Block
        public static final RegistryObject<Block> CORE_BLOCK = BLOCKS.register(LibBlockNames.CORE_BLOCK,
                        () -> new CoreBlock(
                                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                                        .lightLevel(state -> state.getValue(CoreBlock.lit) ? 15 : 0)));

        // @ Blacklist for block items, it will not register a block item for these
        // blocks
        public static final List<RegistryObject<Block>> DONT_INCLUDE_BLOCK_ITEM = Arrays.asList();

        // @ Blacklist for creative tab, block item may still be registered
        public static final List<RegistryObject<Block>> DONT_INCLUDE_CREATIVE = Arrays.asList();
}
