package com.dannbrown.databox.init;

import com.dannbrown.databox.DataboxMod;
import com.dannbrown.databox.item.CustomItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DataboxItems {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DataboxMod.MOD_ID);

  // create a new item
  public static final RegistryObject<Item> ADAMANTIUM_INGOT = ITEMS.register(
      "adamantium_ingot",
      () -> new Item(new Item.Properties()
          .tab(DataboxCreativeTab.TAB_DATABOX)));

  public static final RegistryObject<Item> ADAMANTIUM_FRAGMENT = ITEMS.register(
      "adamantium_fragment",
      () -> new Item(new Item.Properties()
          .tab(DataboxCreativeTab.TAB_DATABOX)));

  public static final RegistryObject<Item> CUSTOM_ITEM = ITEMS.register(
      "custom_item",
      () -> new CustomItem(new Item.Properties()
          .tab(DataboxCreativeTab.TAB_DATABOX)
          .stacksTo(1)));

  // Register
  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
