package com.example.examplemod.init;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.lib.LibUtils;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DataboxSoundEvents {
  // @ Register
  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
      ExampleMod.MOD_ID);

  public static void register(IEventBus bus) {
    SOUNDS.register(bus);
  }

  // @ Sounds
  public static final RegistryObject<SoundEvent> CORE_BLOCK_BREAK = registerSound("block.core_block.break");

  // @ Utils
  private static RegistryObject<SoundEvent> registerSound(String name) {
    return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(LibUtils.resourceLocation(name)));
  }
}
