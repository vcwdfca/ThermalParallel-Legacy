package com.vcwdfca.thermal_parallel.mixins.thermal;

import cofh.thermalexpansion.block.machine.TileMachineBase;
import com.vcwdfca.thermal_parallel.item.TePaItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
}
