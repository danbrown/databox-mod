package com.dannbrown.databox.item;

import java.util.function.Supplier;

import com.dannbrown.databox.DataboxMod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DataboxBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            DataboxMod.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> ADAMANTIUM_ORE = registerBlock("adamantium_ore",
            () -> new DropExperienceBlock(Block.Properties
                    .of(Material.STONE)),
            DataboxCreativeTab.TAB_DATABOX);

    public static final RegistryObject<Block> DEEPSLATE_ADAMANTIUM_ORE = registerBlock("deepslate_adamantium_ore",
            () -> new DropExperienceBlock(Block.Properties
                    .of(Material.STONE)),
            DataboxCreativeTab.TAB_DATABOX);

    public static final RegistryObject<Block> ADAMANTIUM_DEBRIS = registerBlock("adamantium_debris",
            () -> new Block(Block.Properties
                    .of(Material.STONE)),
            DataboxCreativeTab.TAB_DATABOX);

    public static final RegistryObject<Block> ADAMANTIUM_BLOCK = registerBlock("adamantium_block",
            () -> new Block(Block.Properties
                    .of(Material.STONE)),
            DataboxCreativeTab.TAB_DATABOX);

    // Generics for easy block registration
    private static <T extends Block> RegistryObject<Block> registerBlock(
            String name,
            Supplier<T> block,
            CreativeModeTab tab) {
        // Register block
        RegistryObject<Block> toReturn = BLOCKS.register(name, block);

        // Register block item
        DataboxItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(tab)));

        return toReturn; // Return block registry object
    }

    // Register
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
