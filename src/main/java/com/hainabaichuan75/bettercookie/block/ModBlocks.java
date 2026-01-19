package com.hainabaichuan75.bettercookie.block;

import com.hainabaichuan75.bettercookie.bettercookie;
import com.hainabaichuan75.bettercookie.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(bettercookie.MOD_ID);

    public static final DeferredBlock<Block> COOKIE_BLOCK =
            registerBlock("cookie_block",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.1F).sound(SoundType.GRASS)));

    private static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static  <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> blocks = BLOCKS.register(name, block);
        registerBlockItems(name, blocks);
        return blocks;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
