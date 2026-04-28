package com.vcwdfca.thermal_parallel.init;

import cofh.core.util.core.IInitializer;
import com.vcwdfca.thermal_parallel.item.TePaItems;
import net.minecraftforge.common.MinecraftForge;

public class Items {
    public static final Items INSTANCE = new Items();

    public static void preInit() {
        itemParallel = new TePaItems();

        IInitializer init = itemParallel;
        init.preInit();

        MinecraftForge.EVENT_BUS.register(INSTANCE);
    }

    public static TePaItems itemParallel;
}
