package com.hainabaichuan75.bettercookie.client;

import com.hainabaichuan75.bettercookie.entity.ModEntities;
import com.hainabaichuan75.bettercookie.entity.ThrownCookie;
import com.hainabaichuan75.bettercookie.entity.CookiePiece;
import com.hainabaichuan75.bettercookie.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ClientEvents {

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.THROWN_COOKIE.get(), ThrownCookieRenderer::new);
        event.registerEntityRenderer(ModEntities.COOKIE_PIECE.get(), CookiePieceRenderer::new);
    }

    public static class ThrownCookieRenderer extends EntityRenderer<ThrownCookie> {
        private final ItemRenderer itemRenderer;

        public ThrownCookieRenderer(EntityRendererProvider.Context context) {
            super(context);
            this.itemRenderer = context.getItemRenderer();
        }

        @Override
        public void render(ThrownCookie entity, float yaw, float partialTick, PoseStack poseStack,
                           MultiBufferSource buffer, int light) {
            poseStack.pushPose();
            poseStack.translate(0, entity.getBbHeight() / 2, 0);
            ItemStack stack = entity.getCookieType();
            if (stack.isEmpty()) {
                stack = ModItems.COOKIE_PIECES.get().getDefaultInstance();
            }
            itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, light, OverlayTexture.NO_OVERLAY,
                    poseStack, buffer, entity.level(), 0);
            poseStack.popPose();
        }

        @Override
        public ResourceLocation getTextureLocation(ThrownCookie entity) {
            return ResourceLocation.parse("minecraft:textures/block/white_wool.png");
        }
    }

    public static class CookiePieceRenderer extends EntityRenderer<CookiePiece> {
        private final ItemRenderer itemRenderer;

        public CookiePieceRenderer(EntityRendererProvider.Context context) {
            super(context);
            this.itemRenderer = context.getItemRenderer();
        }

        @Override
        public void render(CookiePiece entity, float yaw, float partialTick, PoseStack poseStack,
                           MultiBufferSource buffer, int light) {
            poseStack.pushPose();
            poseStack.translate(0, entity.getBbHeight() / 2, 0);
            ItemStack stack = ModItems.COOKIE_PIECES.get().getDefaultInstance();
            itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, light, OverlayTexture.NO_OVERLAY,
                    poseStack, buffer, entity.level(), 0);
            poseStack.popPose();
        }

        @Override
        public ResourceLocation getTextureLocation(CookiePiece entity) {
            return ResourceLocation.parse("minecraft:textures/block/white_wool.png");
        }
    }
}

