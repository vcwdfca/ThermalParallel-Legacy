package com.vcwdfca.thermal_parallel.item;

import cofh.thermalexpansion.item.ItemAugment;
import com.vcwdfca.thermal_parallel.ThermalParallel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Map;

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
    public void registerModels() {
        for(Map.Entry<Integer, ItemEntry> entry : this.itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(
                    this,
                    entry.getKey(),
                    new ModelResourceLocation(
                            new ResourceLocation(ThermalParallel.MOD_ID, "augment"),
                            "type=" + entry.getValue().name
                    )
            );
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
