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
        if (stack.is(Items.COOKIE) || stack.is(ModItems.BERRY_COOKIE)) {
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
        if (!stack.is(Items.COOKIE) && !stack.is(ModItems.BERRY_COOKIE)) return;

        // 从配置读取是否允许饱腹吃（默认 true）
        boolean alwaysEdible = ModConfig.VANILLA_COOKIE_ALWAYS_EDIBLE.get();
        if (alwaysEdible && !player.getFoodData().needsFood()) {
            InteractionHand hand = event.getHand();
            player.startUsingItem(hand);
            event.setCanceled(true);
        }
    }
}
/*
private static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
    Player player = event.getEntity();
    ItemStack stack = event.getItemStack();
    if (!stack.is(Items.COOKIE) && !stack.is(ModItems.BERRY_COOKIE)) return;

    // 从配置读取是否允许饱腹吃（默认 true）
    boolean alwaysEdible = ModConfig.VANILLA_COOKIE_ALWAYS_EDIBLE.get();
    if (alwaysEdible && !player.getFoodData().needsFood()) {
        InteractionHand hand = event.getHand();
        player.startUsingItem(hand);
        event.setCanceled(true);
    }
}
* */