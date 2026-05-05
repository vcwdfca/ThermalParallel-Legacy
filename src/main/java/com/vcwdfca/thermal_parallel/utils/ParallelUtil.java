package com.vcwdfca.thermal_parallel.utils;

import cofh.core.block.TileInventory;
import com.vcwdfca.thermal_parallel.utils.mixins.thermal.IMixinTileInventory;

public interface ParallelUtil {
    default int maxParallel(int recipeIn, int recipeOut, int inCount, int outCount) {
        if(recipeIn <= 0 || recipeOut <= 0) {
            return 0;
        }
        int outSpace = ((TileInventory) this).getInventoryStackLimit() - outCount;
        if(outSpace <= 0) {
            return 0;
        }

        int maxByInput = inCount / recipeIn;
        int maxByOutput = outSpace / recipeOut;
        return Math.min(((IMixinTileInventory) this).thermal_parallel$getParallel(), Math.min(maxByInput, maxByOutput));
    }
}
