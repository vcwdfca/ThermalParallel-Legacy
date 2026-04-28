package com.vcwdfca.thermal_parallel;

import net.minecraftforge.common.config.Config;

@Config(modid = ThermalParallel.MOD_ID)
public final class ThermalParallelConfig {
    @Config.Name("Balance")
    public static final Balance BALANCE = new Balance();

    @Config.Name("Storage Modify")
    public static final StorageModify STORAGE_MODIFY = new StorageModify();

    public static class Balance {
        @Config.Comment({
                "如果设置为true，则更高等级的并行插件会被加进创造模式物品栏。默认：false。",
                "注：更高级的并行插件没有默认配方并且会略微超模，如果你不是整合包作者请不要把此项打开。",
                "If true, higher tiers of parallel upgrades will be added to creative tab. Default false.",
                "Note that those parallel upgrades don't have a recipe and is relatively op, remain this false once you are not a modpack developer."
        })
        @Config.Name("addExtraParallelAugmentsToTab")
        public boolean ADD_EXTRA_PARALLEL_AUGMENTS_TO_TAB = false;

        @Config.Comment({
                "如果设置为true，则机器的并行数量会被整合组件带来的基础倍率增幅。默认：false。",
                "如果你不在乎平衡性可以把这个设置成true然后加上别的热力附属，享受极致的速率。",
                "If true, parallel value will be multiplied by Scale Factor. Default false.",
                "If you want to make parallel augment op, set this to true."
        })
        @Config.Name("baseModAffectParallel")
        public boolean BASE_MOD_AFFECT_PARALLEL = false;

        @Config.Comment({
                "如果设置为true，则机器的能耗会随并行增多而成倍增加。默认：true。",
                "并行的配方处理带来的额外能量需求会在处理过程结束的瞬间消耗（遗憾的是目前没有找到一个更好的方法来处理并行能耗的问题）。", //TODO: 找到更好的办法处理并行耗能
                "If true, energy consumption will be multiplied by parallel value. Default true.",
                "The energy consumed by parallel tasks will be consumed by the end of a process."
        })
        @Config.Name("parallelIncreaseEnergyConsumption")
        public boolean PARALLEL_INCREASE_ENERGY_CONSUMPTION = false;
    }

    public static class StorageModify {
        @Config.Comment({
                "如果设置为true，则并行升级会同步提高机器的能量存储上限。默认：true。",
                "If true, parallel upgrades will increase energy storage capacity for a machine. Default true."
        })
        @Config.Name("parallelIncreaseEnergyCapacity")
        public boolean PARALLEL_INCREASE_ENERGY_CAPACITY = true;

        @Config.Comment({
                "如果设置为true，则并行升级会同步提高机器的能量传输上限。默认：true。",
                "If true, parallel upgrades will increase energy transfer for a machine. Default true."
        })
        @Config.Name("parallelIncreaseEnergyCapacity")
        public boolean PARALLEL_INCREASE_ENERGY_TRANSFER = true;

        @Config.Comment({
                "如果设置为true，则并行升级会同步提高机器的流体存储上限。默认：true。",
                "If true, parallel upgrades will increase fluid storage capacity for a machine. Default true."
        })
        @Config.Name("parallelIncreaseEnergyCapacity")
        public boolean PARALLEL_INCREASE_FLUID_CAPACITY = true;

        @Config.Comment({
                "如果设置为true，则并行升级会同步提高机器进行自动提取/弹出流体的速率上限。默认：true。",
                "If true, parallel upgrades will increase max fluid auto input/output speed for a machine. Default true."
        })
        @Config.Name("parallelIncreaseEnergyCapacity")
        public boolean PARALLEL_INCREASE_FLUID_TRANSFER = true;

        @Config.Comment({
                "如果设置为true，则并行升级会同步提高机器的输入/输出物品存储上限。默认：true。",
                "If true, parallel upgrades will increase item input/output storage capacity for a machine. Default true."
        })
        @Config.Name("parallelIncreaseEnergyCapacity")
        public boolean PARALLEL_INCREASE_ITEM_CAPACITY = true;

        @Config.Comment({
                "如果设置为true，则并行升级会同步提高机器进行自动提取/弹出物品的速率上限。默认：true。",
                "If true, parallel upgrades will increase max item auto input/output speed for a machine. Default true."
        })
        @Config.Name("parallelIncreaseEnergyCapacity")
        public boolean PARALLEL_INCREASE_ITEM_TRANSFER = true;
    }
}
