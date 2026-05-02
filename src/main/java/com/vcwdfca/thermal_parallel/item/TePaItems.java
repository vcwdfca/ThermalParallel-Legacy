package com.vcwdfca.thermal_parallel.item;

import cofh.thermalexpansion.item.ItemAugment;
import com.vcwdfca.thermal_parallel.ThermalParallel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;
import java.util.Objects;

public class TePaItems extends ItemAugment {

    public TePaItems() {
        name = "Augment";
        modName = ThermalParallel.MOD_NAME;

        this.setCreativeTab(new CreativeTabs("Parallel") {
            @Override
            public ItemStack createIcon() {
                return machineParallel1;
            }
        });
    }

    @Override
    public void registerModels() {
        for(Map.Entry<Integer, ItemEntry> entry : this.itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this, (Integer)entry.getKey(), new ModelResourceLocation(Objects.requireNonNull(this.getRegistryName()), "inventory"));
        }
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
