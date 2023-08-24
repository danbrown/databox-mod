package com.example.examplemod.init;

import com.example.examplemod.ExampleMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataboxCreativeTabs {
  // @ Registering
  public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister
      .create(Registries.CREATIVE_MODE_TAB, ExampleMod.MOD_ID);

  public static void register(IEventBus bus) {
    TABS.register(bus);
  }

  // Creative mode tab for databox items
  public static final RegistryObject<CreativeModeTab> DATABOX_TAB = TABS.register("undergarden_group",
      () -> CreativeModeTab.builder()
          .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
          .title(Component.translatable("itemGroup.undergarden_group"))
          .icon(() -> new ItemStack(DataboxItems.ADAMANTIUM_INGOT.get()))
          .displayItems((parameters, output) -> {

            // Block Items
            DataboxItems.BLOCK_ITEMS.getEntries().forEach(item -> {
              Block block = Block.byItem(item.get());
              RegistryObject<Block> blockRegistry = DataboxBlocks.BLOCKS.getEntries()
                  .stream()
                  .filter(entry -> entry.get() == block)
                  .findFirst()
                  .orElse(null);

              if (blockRegistry != null && !DataboxBlocks.DONT_INCLUDE_CREATIVE.contains(blockRegistry)) {
                output.accept(new ItemStack(item.get()));
              }
            });

            // Items
            DataboxItems.ITEMS.getEntries().forEach(item -> {
              if (!DataboxItems.DONT_INCLUDE_CREATIVE.contains(item)) {
                output.accept(item.get());
              }
            });

            // Enchanted books
            DataboxEnchantments.ENCHANTMENTS.getEntries().forEach(enchantment -> {
              if (!DataboxEnchantments.DONT_INCLUDE_CREATIVE.contains(enchantment)) {
                output.accept(EnchantedBookItem.createForEnchantment(
                    new EnchantmentInstance(enchantment.get(), enchantment.get().getMaxLevel())));
              }
            });

            // Spawn eggs?

          }).build());

  // This will register items into the vanilla creative tabs
  @SubscribeEvent
  public static void addVanillaCreativeTabs(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
      event.accept(DataboxItems.ADAMANTIUM_INGOT);
    }
  }
}
