package com.vcwdfca.thermal_parallel.mixins.thermal.machine;

import cofh.thermalexpansion.block.machine.TileCrafter;
import cofh.thermalexpansion.block.machine.TileMachineBase;
import com.vcwdfca.thermal_parallel.utils.ParallelUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = TileCrafter.class, remap = false)
public class MixinTileCrafter extends TileMachineBase implements ParallelUtil {
    @Unique
    private int thermal_parallel$maxParallel;

    @Shadow
    private TileCrafter.CrafterRecipe craftRecipe;
}
