package com.vcwdfca.thermal_parallel.proxy;

import cofh.core.render.IModelRegister;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public final class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        for(IModelRegister register : modelList) {
            register.registerModels();
        }
    }

    public boolean addIModelRegister(IModelRegister modelRegister) {
        return modelList.add(modelRegister);
    }

    private static final ArrayList<IModelRegister> modelList = new ArrayList<>();
}
