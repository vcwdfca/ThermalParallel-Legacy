package com.vcwdfca.thermal_parallel.mixins.thermal.machine;

import cofh.core.fluid.FluidTankCore;
import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.block.machine.TileCentrifuge;
import cofh.thermalexpansion.block.machine.TileMachineBase;
import cofh.thermalexpansion.util.managers.machine.CentrifugeManager;
import com.vcwdfca.thermal_parallel.utils.ParallelUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(value = TileCentrifuge.class, remap = false)
public abstract class MixinTileCentrifuge extends TileMachineBase implements ParallelUtil {
    @Unique
    private int thermal_parallel$maxParallel;

    @Shadow
    private CentrifugeManager.CentrifugeRecipe curRecipe;

    @Shadow
    private FluidTankCore tank;

    @Inject(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/thermalexpansion/util/managers/machine/CentrifugeManager$CentrifugeRecipe;getOutput()Ljava/util/List;"))
    private void initParallel(CallbackInfo ci) {
        this.thermal_parallel$maxParallel = this.maxParallel(
                new int[]{curRecipe.getInput().getCount()},
                thermal_parallel$initRecipeOutCount(),
                new int[]{this.inventory[0].getCount()},
                new int[]{this.inventory[1].getCount(), this.inventory[2].getCount(), this.inventory[3].getCount(), this.inventory[4].getCount(), this.tank.getFluidAmount()}
        );
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/util/helpers/ItemHelper;cloneStack(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/item/ItemStack;"), index = 1)
    private int parallelOutput1(int quantity) {
        return this.thermal_parallel$maxParallel * quantity;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/util/helpers/ItemHelper;cloneStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack parallelOutput2(ItemStack resource) {
        ItemStack parallelOutput = ItemHelper.cloneStack(resource);
        parallelOutput.setCount(this.thermal_parallel$maxParallel * parallelOutput.getCount());
        return parallelOutput;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;grow(I)V"))
    private int parallelOutput3(int quantity) {
        return this.thermal_parallel$maxParallel * quantity;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lcofh/core/fluid/FluidTankCore;fill(Lnet/minecraftforge/fluids/FluidStack;Z)I"))
    private FluidStack parallelOutput4(FluidStack resource) {
        FluidStack fStack = resource.copy();
        fStack.amount *= this.thermal_parallel$maxParallel;
        return fStack;
    }

    @ModifyArg(method = "processFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V"))
    private int parallelInput(int quantity) {
        return this.thermal_parallel$maxParallel * quantity;
    }

    @Unique
    private int[] thermal_parallel$initRecipeOutCount() {
        List<ItemStack> stacks = this.curRecipe.getOutput();
        int[] recipeOutCount = new int[5];
        Arrays.fill(recipeOutCount, 0);
        for(int i = 0; i < 5; i++) {
            recipeOutCount[i] = stacks.get(i).getCount();
        }

        recipeOutCount[4] = 4000;
        return recipeOutCount;
    }
}
