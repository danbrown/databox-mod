package com.dannbrown.databox.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.dannbrown.databox.init.DataboxEnchantments;

@Mixin(ThrownTrident.class)
public abstract class ThrownTridentMixin extends AbstractArrow {

  protected ThrownTridentMixin(EntityType<? extends AbstractArrow> entityType, Level level) {
    super(entityType, level);
  }

  boolean hitSomething = false;

  @Accessor
  public abstract ItemStack getTridentItem();

  @Inject(method = { "onHitEntity" }, at = {
      @At("TAIL") }, cancellable = true, require = 1)
  public void endHitEntity(EntityHitResult res, CallbackInfo ci) {
    Level world = this.level;
    ItemStack tridentItem = this.getPickupItem();
    Entity owner = this.getOwner();

    // if the world is not client side and the trident has not hit something yet
    if (!world.isClientSide() && !this.hitSomething && res.getEntity() instanceof LivingEntity target) {
      // check trident enchantments
      if (tridentItem.getEnchantmentLevel(DataboxEnchantments.BLINKING_STRIKE.get()) > 0) {
        // check if the owner exists, its not the target, and its on the same level of
        // the trident and the target
        if (owner != null && owner != target && owner.getLevel() == target.getLevel() && owner.getLevel() == world) {

          hitSomething = true;

          // teleport to the target if
          owner.teleportTo(target.getX(), target.getY(), target.getZ());

          // hurt the owner by half a heart without knockback
          owner.hurt(DamageSource.thrown(this, owner).bypassArmor().bypassMagic(), 1.0F);

          // emit many of portal particles
          for (int i = 0; i < 32; ++i) {
            world.addParticle(ParticleTypes.PORTAL, owner.getX(),
                owner.getY() + this.random.nextDouble() * 2.0D, owner.getZ(),
                this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
          }

        }
      }
    }
  }

  @Override
  protected void onHitBlock(BlockHitResult res) {
    super.onHitBlock(res);

    Level world = this.level;
    BlockPos hitPos = res.getBlockPos();
    ItemStack tridentItem = this.getPickupItem();
    Entity owner = this.getOwner();

    // if the world is not client side and the trident has not hit something yet
    if (!world.isClientSide() && !this.hitSomething) {
      // check trident enchantments
      if (tridentItem.getEnchantmentLevel(DataboxEnchantments.BLINKING_STRIKE.get()) > 0) {
        // check if the owner exists, its on the same level of the trident
        if (owner != null && owner.getLevel() == world) {

          hitSomething = true;

          // teleport to above the block
          owner.teleportTo(hitPos.getX(), hitPos.getY() + 1, hitPos.getZ());

          // hurt the owner by half a heart without knockback
          owner.hurt(DamageSource.thrown(this, owner).bypassArmor().bypassMagic(), 1.0F);

          // emit many of portal particles
          for (int i = 0; i < 32; ++i) {
            world.addParticle(ParticleTypes.PORTAL, hitPos.getX(),
                hitPos.getY() + this.random.nextDouble() * 2.0D, hitPos.getZ(),
                this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
          }
        }
      }
    }
  }
}
