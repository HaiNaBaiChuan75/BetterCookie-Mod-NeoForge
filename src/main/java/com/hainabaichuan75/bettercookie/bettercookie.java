package com.hainabaichuan75.bettercookie;

import com.hainabaichuan75.bettercookie.block.ModBlocks;
import com.hainabaichuan75.bettercookie.event.FoodEatEventHandler;
import com.hainabaichuan75.bettercookie.item.ModCreativeModeTabs;
import com.hainabaichuan75.bettercookie.item.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.fml.config.ModConfig.Type;

@Mod(bettercookie.MOD_ID)
public class bettercookie {
    public static final String MOD_ID = "bettercookie";
    public static final Logger LOGGER = LogUtils.getLogger();

    public bettercookie(IEventBus modEventBus, ModContainer modContainer) {
        // 只注册你自己的配置类，删除 Config.SPEC
        modContainer.registerConfig(Type.COMMON, com.hainabaichuan75.bettercookie.config.ModConfig.SPEC);

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        ModItems.register(modEventBus);
        FoodEatEventHandler.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);

        System.out.println("FHSCookier Mod 已加载！");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // 简化commonSetup，暂时不记录配置信息
        LOGGER.info("FHSCookier Mod 初始化完成");

        // 如果你想要保留这些日志，可以硬编码或者使用你的ModConfig
        LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
    }

    // Add the hainabaichuan75 block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // 可以在这里添加你的物品到创造模式标签页
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("FHSCookier Mod 服务器启动");
    }
}