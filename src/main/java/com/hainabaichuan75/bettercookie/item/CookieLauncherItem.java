package com.hainabaichuan75.bettercookie.item;

import com.hainabaichuan75.bettercookie.entity.ThrownCookie;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class CookieLauncherItem extends Item {
    public CookieLauncherItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack launcher = player.getItemInHand(hand);
        // 寻找弹药：优先副手，然后主背包从左到右
        ItemStack ammo = findAmmo(player);
        if (!ammo.isEmpty() && !player.getCooldowns().isOnCooldown(this)) {
            if (!level.isClientSide) {
                // 创建曲奇抛射物
                ThrownCookie cookie = new ThrownCookie(level, player);
                cookie.setCookieType(ammo);      // 记录曲奇种类（用于渲染/伤害）
                cookie.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1, 0F);
                level.addFreshEntity(cookie);
                // 消耗一个弹药
                ammo.shrink(1);
                // 损耗耐久
                launcher.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            }
            player.getCooldowns().addCooldown(this, 1); // 1秒冷却
            return InteractionResultHolder.sidedSuccess(launcher, level.isClientSide);
        }
        return InteractionResultHolder.fail(launcher);
    }

    private ItemStack findAmmo(Player player) {
        // 先检查副手
        ItemStack offhand = player.getOffhandItem();
        if (isValidAmmo(offhand)) return offhand;
        // 再检查主背包（36个槽位，0-8为快捷栏，9-35为背包）
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (isValidAmmo(stack)) return stack;
        }
        return ItemStack.EMPTY;
    }

    private boolean isValidAmmo(ItemStack stack) {
        // 判断是否为曲奇类弹药，可以改成使用标签 (Tag)
        return stack.is(Items.COOKIE) || stack.is(ModItems.BERRY_COOKIE.get());
        // 如果你没有其他曲奇，暂时只用一项测试，但必须有一个有效的弹药物品注册
    }
}
