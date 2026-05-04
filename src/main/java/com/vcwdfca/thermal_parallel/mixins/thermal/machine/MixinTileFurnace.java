package com.vcwdfca.thermal_parallel.mixins.thermal.machine;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.block.machine.TileFurnace;
import cofh.thermalexpansion.block.machine.TileMachineBase;
import cofh.thermalexpansion.util.managers.machine.FurnaceManager;
import com.vcwdfca.thermal_parallel.utils.mixins.thermal.IMixinTileInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = TileFurnace.class, remap = false)
public abstract class MixinTileFurnace extends TileMachineBase {
    @Shadow
    private FurnaceManager.FurnaceRecipe curRecipe;

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/util/helpers/ItemHelper;cloneStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack parallelOutput1(ItemStack stack) {
        int parallelCount = this.thermal_parallel$parallelCount(stack.getCount());
        ItemStack parallelOutput = ItemHelper.cloneStack(stack);
        parallelOutput.setCount(parallelCount);
        return parallelOutput;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;grow(I)V"))
    private int parallelOutput2(int quantity) {
        return this.thermal_parallel$parallelCount(quantity);
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V"))
    private int parallelInput(int quantity) {
        return this.thermal_parallel$parallelCount(quantity);
    }

    @Unique
    private int thermal_parallel$parallelCount(int original) {
        int parallel = ((IMixinTileInventory) this).thermal_parallel$getParallel();
        int inputCount = inventory[0].getCount();
        if(inputCount < this.curRecipe.getInput().getCount() * parallel) {
            return inputCount;
        }
        return original * parallel;
    }
}
