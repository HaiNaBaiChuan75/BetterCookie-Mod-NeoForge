package com.hainabaichuan75.bettercookie.client;

import com.hainabaichuan75.bettercookie.entity.ModEntities;
import com.hainabaichuan75.bettercookie.entity.ThrownCookie;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.Random;

public class ClientEvents {

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.THROWN_COOKIE.get(), ThrownCookieRenderer::new);
    }

    public static class ThrownCookieRenderer extends EntityRenderer<ThrownCookie> {
        private final ItemRenderer itemRenderer;
        private final Map<Integer, Vec3> rotationSpeeds = new WeakHashMap<>();
        public ThrownCookieRenderer(EntityRendererProvider.Context context) {
            super(context);
            this.itemRenderer = context.getItemRenderer();

        }

        @Override
        public void render(ThrownCookie entity, float yaw, float partialTick, PoseStack poseStack,
                           MultiBufferSource buffer, int light) {
            // 获取或生成旋转速度
            Vec3 speeds = rotationSpeeds.computeIfAbsent(entity.getId(), id -> {
                Random rand = new Random(id);
                double x = randomNonZeroSpeed(rand);
                double y = randomNonZeroSpeed(rand);
                double z = randomNonZeroSpeed(rand);
                return new Vec3(x, y, z);
            });

            // 计算当前旋转角度（度）
            float age = entity.tickCount + partialTick; // 平滑插值
            float rotX = (float) (speeds.x * age);
            float rotY = (float) (speeds.y * age);
            float rotZ = (float) (speeds.z * age);

            poseStack.pushPose();
            poseStack.translate(0, entity.getBbHeight() / 2, 0);
            // 调整旋转中心（使模型绕中心翻滚，而非底部）
            poseStack.translate(0, -0.25, 0);


            // 依次旋转三个轴（顺序可调整，通常 Z→Y→X）
            poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(rotZ));
            poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(rotY));
            poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(rotX));

            ItemStack stack = entity.getCookieType();
            if (stack.isEmpty()) {
                stack = Items.COOKIE.getDefaultInstance();
            }
            itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, light, OverlayTexture.NO_OVERLAY,
                    poseStack, buffer, entity.level(), 0);
            poseStack.popPose();
        }

        // 生成速度：范围 [-60,-20] ∪ [20,60]
        private double randomNonZeroSpeed(Random rand) {
            double speed = rand.nextDouble() * 30 + 20; // 20 ~ 100
            if (rand.nextBoolean()) speed = -speed;
            // 确保绝对值 >= 20（理论上已经满足，但以防浮点误差）
            if (Math.abs(speed) < 20) speed = speed > 0 ? 20 : -20;
            return speed;
        }

        @Override
        public ResourceLocation getTextureLocation(ThrownCookie entity) {
            return ResourceLocation.parse("minecraft:textures/block/white_wool.png");
        }
    }
}

