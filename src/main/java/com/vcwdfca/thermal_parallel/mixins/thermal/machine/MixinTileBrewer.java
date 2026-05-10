package com.vcwdfca.thermal_parallel.mixins.thermal.machine;

import cofh.core.fluid.FluidTankCore;
import cofh.thermalexpansion.block.machine.TileBrewer;
import cofh.thermalexpansion.block.machine.TileMachineBase;
import cofh.thermalexpansion.util.managers.machine.BrewerManager;
import com.vcwdfca.thermal_parallel.utils.ParallelUtil;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileBrewer.class, remap = false)
public class MixinTileBrewer extends TileMachineBase implements ParallelUtil {
    @Unique
    private int tp$maxParallel;

    @Shadow
    private BrewerManager.BrewerRecipe curRecipe;

    @Shadow
    private FluidTankCore outputTank;

    @Shadow
    private FluidTankCore inputTank;

    @Inject(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/fluid/FluidTankCore;fill(Lnet/minecraftforge/fluids/FluidStack;Z)I"))
    private void initParallel(CallbackInfo ci) {
        this.tp$maxParallel = this.maxParallel(
                new int[]{this.curRecipe.getInput().getCount(), this.curRecipe.getInputFluid().amount},
                new int[]{this.curRecipe.getOutputFluid().amount},
                new int[]{this.inventory[0].getCount(), this.inputTank.getFluidAmount()},
                new int[]{this.outputTank.getFluidAmount()},
                new int[]{this.outputTank.getCapacity()}
        );
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/fluid/FluidTankCore;fill(Lnet/minecraftforge/fluids/FluidStack;Z)I"), index = 0)
    private FluidStack parallelOutput(FluidStack resource) {
        FluidStack fStack = resource.copy();
        fStack.amount *= this.tp$maxParallel;
        return fStack;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/fluid/FluidTankCore;drain(IZ)Lnet/minecraftforge/fluids/FluidStack;"), index = 0)
    private int parallelInput1(int quantity) {
        return this.tp$maxParallel * quantity;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V"))
    private int parallelInput2(int quantity) {
        return this.tp$maxParallel * quantity;
    }
}
