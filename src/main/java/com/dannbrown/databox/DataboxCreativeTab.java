package com.dannbrown.databox;

import com.dannbrown.databox.item.DataboxItems;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DataboxCreativeTab {
  public static final CreativeModeTab TAB_DATABOX = new CreativeModeTab("databox") {

    @Override
    public ItemStack makeIcon() {
      return new ItemStack(DataboxItems.ADAMANTIUM_FRAGMENT.get());
    }
  };
}
