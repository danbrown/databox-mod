package com.example.examplemod.init;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.content.item.CustomItem;
import com.example.examplemod.lib.LibItemNames;
import com.example.examplemod.lib.LibUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class DataboxItems {
  // @ Utils
  private static final Item.Properties defaultProps = LibUtils.defaultItemProps();

  // @ Registry
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);
  public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      ExampleMod.MOD_ID);

  public static void register(IEventBus bus) {
    ITEMS.register(bus);
    BLOCK_ITEMS.register(bus);
  }

  static {
    registerBlockItems();
  }

  private static void registerBlockItems() {
    DataboxBlocks.BLOCKS.getEntries().forEach(item -> {
      if (!DataboxBlocks.DONT_INCLUDE_BLOCK_ITEM.contains(item)) {
        BLOCK_ITEMS.register(item.getId().getPath(), () -> new BlockItem(item.get(), defaultProps));
      }
    });
  }

  // @ Items
  // adamantium
  public static final RegistryObject<Item> ADAMANTIUM_INGOT = ITEMS.register(LibItemNames.ADAMANTIUM_INGOT,
      () -> new Item(defaultProps));
  public static final RegistryObject<Item> ADAMANTIUM_FRAGMENT = ITEMS.register(LibItemNames.ADAMANTIUM_FRAGMENT,
      () -> new Item(defaultProps));

  // custom items
  public static final RegistryObject<Item> CUSTOM_ITEM = ITEMS.register(LibItemNames.CUSTOM_ITEM,
      () -> new CustomItem(defaultProps));

  // @ Blacklist for creative tab
  public static final List<RegistryObject<Item>> DONT_INCLUDE_CREATIVE = new ArrayList<>();
  static {
    DONT_INCLUDE_CREATIVE.add(ADAMANTIUM_INGOT);
    DONT_INCLUDE_CREATIVE.add(CUSTOM_ITEM);
  }
}
