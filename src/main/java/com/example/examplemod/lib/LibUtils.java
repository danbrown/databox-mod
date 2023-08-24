package com.example.examplemod.lib;

import com.example.examplemod.ExampleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.versions.forge.ForgeVersion;

public class LibUtils {
  public static ResourceLocation resourceLocation(String path) {
    return new ResourceLocation(ExampleMod.MOD_ID, path);
  }

  public static ResourceLocation forgeResourceLocation(String path) {
    return new ResourceLocation(ForgeVersion.MOD_ID, path);
  }

  public static Item.Properties defaultItemProps() {
    return new Item.Properties();
  }
}
