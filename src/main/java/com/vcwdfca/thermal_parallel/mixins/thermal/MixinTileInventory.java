package com.vcwdfca.thermal_parallel.mixins.thermal;

import cofh.core.block.TileInventory;
import com.vcwdfca.thermal_parallel.utils.mixins.thermal.IMixinTileInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = TileInventory.class, remap = false)
public class MixinTileInventory implements IMixinTileInventory {
    @Unique
    private int tp$parallel = 1;

    @Override
    public int tp$getParallel() {
        return tp$parallel;
    }

    @Override
    public void tp$setParallel(int parallel) {
        tp$parallel = parallel;
    }
}
