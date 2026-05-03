package com.vcwdfca.thermal_parallel.mixins.thermal;

import cofh.api.item.IAugmentItem;
import cofh.thermalexpansion.item.ItemAugment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ItemAugment.class, remap = false)
public interface IMixinItemAugment {
    @Invoker("addAugmentEntry")
    void addAugmentEntryINVOKE(int metadata, IAugmentItem.AugmentType type, String identifier);
}
