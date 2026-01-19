package com.hainabaichuan75.bettercookie.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // 配置项：是否启用原版曲奇加速进食
    public static final ModConfigSpec.BooleanValue ENABLE_VANILLA_COOKIE_FAST_EAT;
    // 配置项：加速后的食用时间（tick）
    public static final ModConfigSpec.IntValue VANILLA_COOKIE_EAT_DURATION;
    // 配置项：是否允许任何时候吃原版曲奇
    public static final ModConfigSpec.BooleanValue VANILLA_COOKIE_ALWAYS_EDIBLE;

    public static final ModConfigSpec SPEC;

    static {
        BUILDER.comment("Better Cookie 配置").push("general");

        ENABLE_VANILLA_COOKIE_FAST_EAT = BUILDER
                .comment("Once enabled, you can use the slider below to adjust the time it takes to eat cookies.")
                .comment("启用后，您可以使用下面的滑块来调整吃饼干所需的时间")
                .define("enableVanillaCookieFastEat", true);

        VANILLA_COOKIE_EAT_DURATION = BUILDER
                .comment("1 tick = 50 ms")
                .comment("1 刻 = 50 毫秒")
                .defineInRange("vanillaCookieEatDuration", 6, 2, 32);

        VANILLA_COOKIE_ALWAYS_EDIBLE = BUILDER
                .comment("Healthy eating is very important")
                .comment("健康饮食，贪吃伤身")
                .define("vanillaCookieAlwaysEdible", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}