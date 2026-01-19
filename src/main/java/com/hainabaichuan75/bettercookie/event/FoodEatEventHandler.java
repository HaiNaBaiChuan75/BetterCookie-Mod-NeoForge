package com.hainabaichuan75.bettercookie.event;

import com.hainabaichuan75.bettercookie.config.ModConfig;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.IEventBus;

public class FoodEatEventHandler {

    public static void register(IEventBus eventBus) {
        NeoForge.EVENT_BUS.addListener(FoodEatEventHandler::onStartEating);
    }

    private static void onStartEating(LivingEntityUseItemEvent.Start event) {
        ItemStack stack = event.getItem();

        try {
            if (stack.is(Items.COOKIE)) {
                // 尝试读取配置，如果失败则使用默认值
                boolean enableFastEat = ModConfig.ENABLE_VANILLA_COOKIE_FAST_EAT.get();
                if (enableFastEat) {
                    int duration = ModConfig.VANILLA_COOKIE_EAT_DURATION.get();
                    event.setDuration(duration);
                }
            }
        } catch (Exception e) {
            // 如果配置读取失败，使用硬编码值
            if (stack.is(Items.COOKIE)) {
                event.setDuration(6);
            }
        }
    }
}