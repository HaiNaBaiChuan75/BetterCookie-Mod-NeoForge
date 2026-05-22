package com.hainabaichuan75.bettercookie.entity;

import com.hainabaichuan75.bettercookie.bettercookie;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, bettercookie.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownCookie>> THROWN_COOKIE =
            ENTITIES.register("thrown_cookie", () -> EntityType.Builder.<ThrownCookie>of(ThrownCookie::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_cookie"));

}