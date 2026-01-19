package com.hainabaichuan75.bettercookie.item;

import com.hainabaichuan75.bettercookie.bettercookie;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(bettercookie.MOD_ID);

    // 自定义快速食用物品类
    public static class FastEatFood extends Item {
        public FastEatFood(Properties properties) {
            super(properties);
        }

        @Override
        public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
            return 2; // 6 tick = 0.3秒，比.fast()的16 tick更快
        }

        @Override
        public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
            return UseAnim.EAT;
        }
    }

    // 注册使用自定义类
    public static final DeferredItem<Item> COOKIE_PIECES =
            ITEMS.register("cookie_pieces", () -> new FastEatFood(
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(0)
                                    .saturationModifier(0.25F)
                                    .fast()
                                    .alwaysEdible()
                                    .build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}