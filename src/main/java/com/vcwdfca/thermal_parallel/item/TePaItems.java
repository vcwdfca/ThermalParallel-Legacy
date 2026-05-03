package com.vcwdfca.thermal_parallel.item;

import cofh.thermalexpansion.item.ItemAugment;
import com.vcwdfca.thermal_parallel.ThermalParallel;
import com.vcwdfca.thermal_parallel.mixins.thermal.IMixinItemAugment;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;

public class TePaItems extends ItemAugment {

    public TePaItems() {
        this.modName = ThermalParallel.MOD_NAME;
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

        machineParallel1 = this.registerAugmentParallel(0, "machineParallel1");
        machineParallel2 = this.registerAugmentParallel(1, "machineParallel2");
        machineParallel3 = this.registerAugmentParallel(2, "machineParallel3");

        return true;
    }

    private ItemStack registerAugmentParallel(int metadata, String name) {
        return this.registerItem(metadata, name, MACHINE_PARALLEL);
    }

    @SuppressWarnings("SameParameterValue")
    private ItemStack registerItem(int metadata, String name, String identifier) {
        ((IMixinItemAugment) this).addAugmentEntryINVOKE(metadata, AugmentType.BASIC, identifier);
        return this.addItem(metadata, name);
    }

    public static final String MACHINE_PARALLEL = "machineParallel";

    public static ItemStack machineParallel1;
    public static ItemStack machineParallel2;
    public static ItemStack machineParallel3;
}
