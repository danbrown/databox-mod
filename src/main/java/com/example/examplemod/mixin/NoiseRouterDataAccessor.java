package com.example.examplemod.mixin;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(NoiseRouterData.class)
public class NoiseRouterDataAccessor {

    public NoiseRouterDataAccessor() {
        //
    }

    @Invoker("getFunction")
    public static DensityFunction getFunction(HolderGetter<DensityFunction> p_256312_,
            ResourceKey<DensityFunction> p_256077_) {
        return new DensityFunctions.HolderHolder(p_256312_.getOrThrow(p_256077_));
    }

    @Shadow
    @Final
    private static final ResourceKey<DensityFunction> Y = createKey("y");

    @Shadow
    @Final
    private static final ResourceKey<DensityFunction> SHIFT_X = createKey("shift_x");

    @Shadow
    @Final
    private static final ResourceKey<DensityFunction> SHIFT_Z = createKey("shift_z");

    public final ResourceKey<DensityFunction> getShiftX() {
        return SHIFT_X;
    }

    public final ResourceKey<DensityFunction> getShiftZ() {
        return SHIFT_Z;
    }

    public final ResourceKey<DensityFunction> getY() {
        return Y;
    }

    private static ResourceKey<DensityFunction> createKey(String p_209537_) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(p_209537_));
    }

}