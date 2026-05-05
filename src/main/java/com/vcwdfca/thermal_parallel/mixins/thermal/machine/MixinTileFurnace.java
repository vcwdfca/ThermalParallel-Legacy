package com.vcwdfca.thermal_parallel.mixins.thermal.machine;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.block.machine.TileFurnace;
import cofh.thermalexpansion.block.machine.TileMachineBase;
import cofh.thermalexpansion.util.managers.machine.FurnaceManager;
import com.vcwdfca.thermal_parallel.utils.ParallelUtil;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileFurnace.class, remap = false)
public abstract class MixinTileFurnace extends TileMachineBase implements ParallelUtil {
    @Unique
    private int thermal_parallel$maxParallel;

    @Shadow
    private FurnaceManager.FurnaceRecipe curRecipe;

    @Inject(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/thermalexpansion/util/managers/machine/FurnaceManager$FurnaceRecipe;getOutput()Lnet/minecraft/item/ItemStack;"))
    private void initParallel(CallbackInfo ci) {
        this.thermal_parallel$maxParallel = this.maxParallel(
                curRecipe.getInput().getCount(),
                curRecipe.getOutput().getCount(),
                this.inventory[0].getCount(),
                this.inventory[1].getCount()
        );
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/util/helpers/ItemHelper;cloneStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack parallelOutput1(ItemStack stack) {
        ItemStack parallelOutput = ItemHelper.cloneStack(stack);
        parallelOutput.setCount(this.thermal_parallel$maxParallel * parallelOutput.getCount());
        return parallelOutput;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;grow(I)V"))
    private int parallelOutput2(int quantity) {
        return this.thermal_parallel$maxParallel * quantity;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fluids/FluidStack;<init>(Lnet/minecraftforge/fluids/Fluid;I)V"), index = 1)
    private int parallelOutPut3(int quantity) {
        return this.thermal_parallel$maxParallel * quantity;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V"))
    private int parallelInput(int quantity) {
        return this.thermal_parallel$maxParallel * quantity;
    }
}
