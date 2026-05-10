package com.vcwdfca.thermal_parallel.utils;

import cofh.core.block.TileInventory;
import com.vcwdfca.thermal_parallel.utils.mixins.thermal.IMixinTileInventory;

import java.util.Arrays;

public interface ParallelUtil {
    default int computeMaxParallel(int recipeIn, int recipeOut, int inCount, int outCount) {
        return computeMaxParallel(new int[]{recipeIn}, new int[]{recipeOut}, new int[]{inCount}, new int[]{outCount});
    }

    default int computeMaxParallel(int recipeIn, int[] recipeOut, int inCount, int[] outCount) {
        return computeMaxParallel(new int[]{recipeIn}, recipeOut, new int[]{inCount}, outCount);
    }

    default int computeMaxParallel(int[] recipeIn, int recipeOut, int[] inCount, int outCount) {
        return computeMaxParallel(recipeIn, new int[]{recipeOut}, inCount, new int[]{outCount});
    }

    default int computeMaxParallel(int[] recipeIn, int[] recipeOut, int[] inCount, int[] outCount) {
        int inventoryStackLimit = ((TileInventory) this).getInventoryStackLimit();
        int[] outSlotLimits = new int[recipeOut == null ? 0 : recipeOut.length];
        Arrays.fill(outSlotLimits, inventoryStackLimit);
        return computeMaxParallel(recipeIn, recipeOut, inCount, outCount, outSlotLimits);
    }

    default int computeMaxParallel(int[] recipeIn, int[] recipeOut, int[] inCount, int[] outCount, int[] outSlotLimits) {
        if (recipeIn == null || recipeOut == null || inCount == null || outCount == null || outSlotLimits == null) {
            return 0;
        }
        if (recipeIn.length != inCount.length || recipeOut.length != outCount.length || recipeOut.length != outSlotLimits.length) {
            return 0;
        }

        int parallelLimit = ((IMixinTileInventory) this).tp$getParallel();
        if (parallelLimit <= 0) {
            return 0;
        }

        int maxByInput = parallelLimit;
        int maxByOutput = parallelLimit;

        for (int i = 0; i < recipeIn.length; i++) {
            int recipeCount = recipeIn[i];
            if (recipeCount <= 0) {
                continue;
            }
            maxByInput = Math.min(maxByInput, inCount[i] / recipeCount);
            if (maxByInput == 0) {
                return 0;
            }
        }

        for (int i = 0; i < recipeOut.length; i++) {
            int recipeCount = recipeOut[i];
            if (recipeCount <= 0) {
                continue;
            }
            int outSpace = outSlotLimits[i] - outCount[i];
            if (outSpace <= 0) {
                return 0;
            }
            maxByOutput = Math.min(maxByOutput, outSpace / recipeCount);
            if (maxByOutput == 0) {
                return 0;
            }
        }

        return Math.min(parallelLimit, Math.min(maxByInput, maxByOutput));
    }
}
