package com.vcwdfca.thermal_parallel.proxy;

import cofh.core.render.IModelRegister;
import com.vcwdfca.thermal_parallel.init.Items;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Items.preInit();
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean addIModelRegister(IModelRegister modelRegister) {
        return false;
    }
}
