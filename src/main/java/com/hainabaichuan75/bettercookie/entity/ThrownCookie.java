package com.hainabaichuan75.bettercookie.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ThrownCookie extends Projectile {
    private ItemStack cookieType = ItemStack.EMPTY;  // 记录哪种曲奇

    public ThrownCookie(EntityType<? extends ThrownCookie> type, Level level) {
        super(type, level);
    }

    public ThrownCookie(Level level, LivingEntity shooter) {
        super(ModEntities.THROWN_COOKIE.get(), level);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
    }

    public void setCookieType(ItemStack stack) {
        this.cookieType = stack.copyWithCount(1);
    }

    public ItemStack getCookieType() {
        return cookieType;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!level().isClientSide) {
            // 造成伤害（根据曲奇种类可不同，暂固定5点）
            result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 5.0F);
            // 分裂成曲奇碎
            spawnPieces();
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (!level().isClientSide) {
            spawnPieces();
            this.discard();
        }
    }

    private void spawnPieces() {
        // 生成3~6个曲奇碎
        int count = random.nextInt(4) + 3;
        for (int i = 0; i < count; i++) {
            CookiePiece piece = new CookiePiece(ModEntities.COOKIE_PIECE.get(), level());
            piece.setPos(this.getX(), this.getY(), this.getZ());
            // 随机方向，小速度
            Vec3 motion = new Vec3(
                    (random.nextDouble() - 0.5) * 0.8,
                    random.nextDouble() * 0.6 + 0.2,
                    (random.nextDouble() - 0.5) * 0.8
            );
            piece.setDeltaMovement(motion);
            piece.setOwner(this.getOwner());
            level().addFreshEntity(piece);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // 不需要同步额外数据
    }

    @Override
    public void tick() {
        super.tick();
        // 重力影响
        this.setDeltaMovement(this.getDeltaMovement().add(0, -0.03, 0));
        // 移动并检查碰撞
        HitResult hit = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hit.getType() != HitResult.Type.MISS) {
            this.onHit(hit);
        }
        this.setPos(this.getX() + this.getDeltaMovement().x, this.getY() + this.getDeltaMovement().y, this.getZ() + this.getDeltaMovement().z);
        // 超时消失
        if (this.tickCount > 200) this.discard();
    }
}