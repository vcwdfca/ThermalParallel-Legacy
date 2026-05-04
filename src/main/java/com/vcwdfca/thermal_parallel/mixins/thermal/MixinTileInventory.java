package com.vcwdfca.thermal_parallel.mixins.thermal;

import cofh.core.block.TileInventory;
import com.vcwdfca.thermal_parallel.utils.mixins.thermal.IMixinTileInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = TileInventory.class, remap = false)
public class MixinTileInventory implements IMixinTileInventory {
    @Unique
    private int thermal_parallel$parallel = 1;

    @Override
    public int thermal_parallel$getParallel() {
        return thermal_parallel$parallel;
    }

    @Override
    public void thermal_parallel$setParallel(int parallel) {
        thermal_parallel$parallel = parallel;
    }
}
