package com.hainabaichuan75.bettercookie.entity;

import com.hainabaichuan75.bettercookie.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.item.ItemEntity;
import java.util.Random;

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
            // 造成直接伤害（可选，因为爆炸也会有伤害，可以保留一个小伤或取消）
            result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 4.0F);
            // 在命中位置触发爆炸效果
            spawnExplosionAndDrops(result.getLocation());
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (!level().isClientSide) {
            // 同样触发爆炸和掉落（如果撞到方块，也可以产生效果）
            spawnExplosionAndDrops(result.getLocation());
            this.discard();
        }
    }
    private void spawnExplosionAndDrops(Vec3 pos) {
        Level level = this.level();
        if (level.isClientSide) return;

        // 1. 产生爆炸伤害（威力 1.2，不破坏方块）
        level.explode(this, pos.x, pos.y, pos.z, 1.0f, false, Level.ExplosionInteraction.NONE);
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // 不需要同步额外数据
    }

    @Override
    public void tick() {
        super.tick();
        // 重力影响
        this.setDeltaMovement(this.getDeltaMovement().add(0, -0.2, 0));
        // 移动并检查碰撞
        HitResult hit = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hit.getType() != HitResult.Type.MISS) {
            this.onHit(hit);
        }
        this.setPos(this.getX() + this.getDeltaMovement().x, this.getY() + this.getDeltaMovement().y, this.getZ() + this.getDeltaMovement().z);
        // 超时消失
        if (this.tickCount > 2000) this.discard();
    }
}