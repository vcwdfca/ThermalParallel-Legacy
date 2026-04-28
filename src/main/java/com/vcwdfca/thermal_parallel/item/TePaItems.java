package com.vcwdfca.thermal_parallel.item;

import cofh.thermalexpansion.ThermalExpansion;
import cofh.thermalexpansion.item.ItemAugment;
import net.minecraft.item.ItemStack;

public class TePaItems extends ItemAugment {

    public TePaItems() {
        this.setCreativeTab(ThermalExpansion.tabUtils);
    }

    @Override
    public boolean preInit() {
        //ThermalExpansion.proxy.addIModelRegister(this);
        super.preInit();
        machineParallel = addItem(0, "machineParallel");

        return true;
    }

    public static ItemStack machineParallel;
}
