package com.vcwdfca.thermal_parallel.mixins;

import cofh.thermalexpansion.item.ItemAugment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ItemAugment.class, remap = false)
public interface MixinItemAugment {

    @Invoker("addAugmentItem")
    ItemStack addAugment(int metadata, String name);
}
