package com.vcwdfca.thermal_parallel.mixins.thermal;

import cofh.thermalexpansion.block.machine.TileMachineBase;
import com.llamalad7.mixinextras.sugar.Local;
import com.vcwdfca.thermal_parallel.item.TePaItems;
import com.vcwdfca.thermal_parallel.utils.mixins.thermal.IMixinTileInventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;

@Mixin(value = TileMachineBase.class, remap = false)
public abstract class MixinTileMachineBase {
    @Shadow
    @Final
    protected static HashSet<String> VALID_AUGMENTS_BASE;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addToValidAugmentSet(CallbackInfo ci) {
        VALID_AUGMENTS_BASE.add(TePaItems.MACHINE_PARALLEL);
    }

    @Inject(method = "preAugmentInstall", at = @At("TAIL"))
    protected void preAddParallelLogic(CallbackInfo ci) {
        ((IMixinTileInventory) this).Thermal_parallel$setParallel(1);
    }

    @Inject(method = "installAugmentToSlot", at = @At("TAIL"), cancellable = true)
    protected void addParallelLogic(int slot, CallbackInfoReturnable<Boolean> cir, @Local(name = "id") String id) {
        if(TePaItems.MACHINE_PARALLEL.equals(id)) {
            int parallel = ((IMixinTileInventory) this).Thermal_parallel$getParallel();
            ((IMixinTileInventory) this).Thermal_parallel$setParallel(++parallel);
            cir.setReturnValue(true);
        }
    }

}
