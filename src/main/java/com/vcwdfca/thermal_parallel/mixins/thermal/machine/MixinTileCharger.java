package com.vcwdfca.thermal_parallel.mixins.thermal.machine;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.block.machine.TileCharger;
import cofh.thermalexpansion.block.machine.TileMachineBase;
import cofh.thermalexpansion.util.managers.machine.ChargerManager;
import com.llamalad7.mixinextras.sugar.Local;
import com.vcwdfca.thermal_parallel.utils.ParallelUtil;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileCharger.class, remap = false)
public class MixinTileCharger extends TileMachineBase implements ParallelUtil {
    @Unique
    private int tp$maxParallel;

    @Unique
    private ChargerManager.ChargerRecipe tp$curRecipe;

    @Inject(method = "processStart", at = @At(value = "INVOKE", target = "Lcofh/thermalexpansion/util/managers/machine/ChargerManager$ChargerRecipe;getEnergy()I", ordinal = 1))
    private void initParallel1(CallbackInfo ci, @Local(name = "recipe") ChargerManager.ChargerRecipe recipe) {
        this.tp$curRecipe = recipe;
        this.tp$maxParallel = this.maxParallel(
                this.tp$curRecipe.getInput().getCount(),
                this.tp$curRecipe.getOutput().getCount(),
                this.inventory[0].getCount(),
                this.inventory[1].getCount()
        );
    }

    @ModifyArg(method = "processStart", at = @At(value = "INVOKE", target = "Lcofh/core/util/helpers/ItemHelper;cloneStack(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/item/ItemStack;"), index = 1)
    private int preParallelInput1(int stackSize) {
        return this.tp$maxParallel * stackSize;
    }

    @ModifyArg(method = "processStart", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V"))
    private int preParallelInput2(int quantity) {
        return this.tp$maxParallel * quantity;
    }

    @Inject(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/thermalexpansion/util/managers/machine/ChargerManager$ChargerRecipe;getOutput()Lnet/minecraft/item/ItemStack;"))
    private void initParallel2(CallbackInfo ci, @Local(name = "recipe") ChargerManager.ChargerRecipe recipe) {
        this.tp$curRecipe = recipe;
        this.tp$maxParallel = this.maxParallel(
                this.tp$curRecipe.getInput().getCount(),
                this.tp$curRecipe.getOutput().getCount(),
                this.inventory[1].getCount(),
                this.inventory[2].getCount()
        );
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/util/helpers/ItemHelper;cloneStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack parallelOutput1(ItemStack stack) {
        ItemStack parallelOutput = ItemHelper.cloneStack(stack);
        parallelOutput.setCount(this.tp$maxParallel * parallelOutput.getCount());
        return parallelOutput;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;grow(I)V"))
    private int parallelOutput2(int quantity) {
        return this.tp$maxParallel * quantity;
    }
}
