package com.vcwdfca.thermal_parallel;

import com.vcwdfca.thermal_parallel.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class ThermalParallel {

    public static final String MOD_ID = Tags.MOD_ID;
    public static final String MOD_NAME = Tags.MOD_NAME;
    public static final String CLIENT_PROXY = "com.vcwdfca.thermal_parallel.proxy.ClientProxy";
    public static final String COMMON_PROXY = "com.vcwdfca.thermal_parallel.proxy.CommonProxy";

    private static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy = null;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
}
