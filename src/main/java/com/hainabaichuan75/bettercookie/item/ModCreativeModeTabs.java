package com.hainabaichuan75.bettercookie.item;

import com.hainabaichuan75.bettercookie.bettercookie;
import com.hainabaichuan75.bettercookie.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, bettercookie.MOD_ID);

    public static final Supplier<CreativeModeTab> FHSCOOKIER_TAB =
            CREATIVE_MODE_TABS.register("bettercookie_tab",() ->CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.COOKIE_PIECES.asItem()))
                    .title(Component.translatable("itemGrop.bettercookie_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.COOKIE_PIECES);
                        output.accept(ModBlocks.COOKIE_BLOCK);
                    }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
