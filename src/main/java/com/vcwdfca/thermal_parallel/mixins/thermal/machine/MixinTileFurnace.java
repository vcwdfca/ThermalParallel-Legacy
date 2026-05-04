package com.vcwdfca.thermal_parallel.mixins.thermal.machine;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.block.machine.TileFurnace;
import cofh.thermalexpansion.block.machine.TileMachineBase;
import cofh.thermalexpansion.util.managers.machine.FurnaceManager;
import com.vcwdfca.thermal_parallel.utils.mixins.thermal.IMixinTileInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = TileFurnace.class, remap = false)
public class MixinTileFurnace extends TileMachineBase {
    @Shadow
    private FurnaceManager.FurnaceRecipe curRecipe;

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/util/helpers/ItemHelper;cloneStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack parallelOutput1(ItemStack stack) {
        int parallel = ((IMixinTileInventory) this).Thermal_parallel$getParallel();
        int inputCount = inventory[0].getCount();
        if(inputCount < this.curRecipe.getInput().getCount() * parallel) {
            return stack;
        }
        int parallelCount = stack.getCount() * parallel;
        ItemStack parallelOutput = ItemHelper.cloneStack(stack);
        parallelOutput.setCount(parallelCount);
        return parallelOutput;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;grow(I)V"))
    private int parallelOutput2(int quantity) {
        int parallel = ((IMixinTileInventory) this).Thermal_parallel$getParallel();
        int inputCount = inventory[0].getCount();
        if(inputCount < this.curRecipe.getInput().getCount() * parallel) {
            return quantity;
        }
        return quantity * parallel;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V"))
    private int parallelInput(int quantity) {
        int parallel = ((IMixinTileInventory) this).Thermal_parallel$getParallel();
        int inputCount = inventory[0].getCount();
        if(inputCount < this.curRecipe.getInput().getCount() * parallel) {
            return quantity;
        }
        return quantity * parallel;
    }
}
