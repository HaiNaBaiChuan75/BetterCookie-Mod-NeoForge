package com.hainabaichuan75.bettercookie.event;

import com.hainabaichuan75.bettercookie.config.ModConfig;
import com.hainabaichuan75.bettercookie.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;


//食物进食事件处理
public class FoodEatEventHandler {

    //事件侦听，玩家进食状态
    public static void register(IEventBus eventBus) {
        NeoForge.EVENT_BUS.addListener(FoodEatEventHandler::onStartEating);
        NeoForge.EVENT_BUS.addListener(FoodEatEventHandler::onRightClickItem);
    }
    //私有的，静态的，“开始进食”方法，实体使用物品事件，开始事件
    private static void onStartEating(LivingEntityUseItemEvent.Start event) {
        ItemStack stack = event.getItem();
        // 只处理原版曲奇和你的模组曲奇
        if (stack.is(Items.COOKIE) || stack.is(ModItems.BERRY_COOKIES)) {
            try {
                boolean fast = ModConfig.ENABLE_VANILLA_COOKIE_FAST_EAT.get();
                if (fast) {
                    int duration = ModConfig.VANILLA_COOKIE_EAT_DURATION.get();
                    event.setDuration(duration);
                }
            } catch (Exception e) {
                // 配置读取失败时的保底值
                event.setDuration(6);
            }
        }
    }
    private static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        // 只处理原版曲奇或模组曲奇
        if (!stack.is(Items.COOKIE) && !stack.is(ModItems.BERRY_COOKIES)) return;

        // 如果玩家不需要食物（即饱食度满或不需要进食）
        if (!player.getFoodData().needsFood()) {
            // 获取玩家使用的手（主手或副手）
            InteractionHand hand = event.getHand();
            // 手动开始使用物品（这会触发 LivingEntityUseItemEvent.Start）
            player.startUsingItem(hand);
            // 取消原版默认的事件处理，避免重复或冲突
            event.setCanceled(true);
        }
    }
}
