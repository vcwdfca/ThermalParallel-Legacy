package com.vcwdfca.thermal_parallel.item;

import cofh.thermalexpansion.item.ItemAugment;
import com.vcwdfca.thermal_parallel.ThermalParallel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;

public class TePaItems extends ItemAugment {

    public TePaItems() {
        modName = ThermalParallel.MOD_NAME;
        this.setTranslationKey("augment");
        this.setCreativeTab(new CreativeTabs("Parallel") {
            @Override
            @Nonnull
            public ItemStack createIcon() {
                return machineParallel1;
            }
        });
    }

    @Override
    public boolean preInit() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("augment"));
        ThermalParallel.proxy.addIModelRegister(this);

        machineParallel1 = addItem(0, "machineParallel1");
        machineParallel2 = addItem(1, "machineParallel2");
        machineParallel3 = addItem(2, "machineParallel3");

        return true;
    }

    public static ItemStack machineParallel1;
    public static ItemStack machineParallel2;
    public static ItemStack machineParallel3;
}
