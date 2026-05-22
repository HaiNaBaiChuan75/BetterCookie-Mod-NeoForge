package com.hainabaichuan75.bettercookie.entity;

import com.hainabaichuan75.bettercookie.item.ModItems;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;

public class CookiePiece extends Projectile {
    private int life = 600;

    public CookiePiece(EntityType<? extends CookiePiece> type, Level level) {
        super(type, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!level().isClientSide) {
            result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 2.0F);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        // 插在地上：停止运动，取消重力
        this.setDeltaMovement(Vec3.ZERO);
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            life--;
            if (life <= 0) {
                this.discard();
                return;
            }

            // 如果已经落地（noGravity 为 true）且稳定后，尝试让附近玩家拾取
            if (this.isNoGravity() && this.tickCount > 5) {
                AABB aabb = this.getBoundingBox().inflate(1.0);
                Player nearestPlayer = level().getNearestPlayer(this.getX(), this.getY(), this.getZ(), 1.0, false);
                if (nearestPlayer != null && !nearestPlayer.isSpectator()) {
                    ItemStack stack = new ItemStack(ModItems.COOKIE_PIECES.get());
                    if (nearestPlayer.getInventory().add(stack)) {
                        this.discard();
                        nearestPlayer.playSound(SoundEvents.ITEM_PICKUP, 0.2F, 1.0F);
                    }
                }
            }

            // 如果没有落地，受重力影响
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            }
        }

        // 移动实体
        this.setPos(this.getX() + this.getDeltaMovement().x,
                this.getY() + this.getDeltaMovement().y,
                this.getZ() + this.getDeltaMovement().z);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // 无需同步数据
    }
}