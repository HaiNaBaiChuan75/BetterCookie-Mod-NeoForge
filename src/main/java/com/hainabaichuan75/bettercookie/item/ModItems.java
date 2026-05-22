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

//公共的类，模组物品
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

    // 曲奇碎
    public static final DeferredItem<Item> COOKIE_PIECES =
            ITEMS.register("cookie_pieces", () -> new FastEatFood(
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(0)
                                    .saturationModifier(0.25F)
                                    .fast()
                                    .alwaysEdible()
                                    .build())));
    //浆果曲奇碎
    public static final DeferredItem<Item> BERRY_COOKIE_PIECES =
            ITEMS.register("berry_cookie_pieces", () -> new FastEatFood(
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(0)
                                    .saturationModifier(0.25F)
                                    .fast()
                                    .alwaysEdible()
                                    .build())));

    //浆果曲奇
    public static final DeferredItem<Item> BERRY_COOKIE =
            ITEMS.register("berry_cookie", () -> new Item(
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationModifier(0.6F)
                                    .fast()
                                    .alwaysEdible()
                                    .build())));

    //曲奇发射器
    public static final DeferredItem<Item> COOKIE_LAUNCHER =
            ITEMS.register("cookie_launcher", () -> new CookieLauncherItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(384)));

    //测试弹药
    public static final DeferredItem<Item> COOKIE_AMMO = ITEMS.register("cookie_ammo",
            () -> new Item(new Item.Properties()));

    /*
    public static void 公共的，静态的，无返回值
    register 意为“注册”
    IEventBus eventBus 是方法的参
    ITEMS 是一个实例，携带注册方法register
    */
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}

