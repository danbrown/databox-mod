package com.dannbrown.databox.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
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

  @Inject(method = { "onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V" }, at = {
      @At("TAIL") }, cancellable = true, require = 1)
  public void endHitEntity(EntityHitResult res, CallbackInfo ci) {
    Level world = this.level;
    ItemStack tridentItem = this.getPickupItem();

    if (!world.isClientSide() && !this.hitSomething && res.getEntity() instanceof LivingEntity target) {
      // check trident enchantments
      if (tridentItem.getEnchantmentLevel(DataboxEnchantments.BLINKING_STRIKE.get()) > 0) {
        hitSomething = true;

        // teleport to the target
        this.getOwner().teleportTo(target.getX(), target.getY(), target.getZ());

        // hurt the owner by half a heart
        this.getOwner().hurt(DamageSource.thrown(this, this.getOwner()), 1.0F);

        // emit many of portal particles
        for (int i = 0; i < 32; ++i) {
          world.addParticle(ParticleTypes.PORTAL, this.getOwner().getX(),
              this.getOwner().getY() + this.random.nextDouble() * 2.0D, this.getOwner().getZ(),
              this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
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

    if (!world.isClientSide() && !this.hitSomething) {

      // check trident enchantments
      if (tridentItem.getEnchantmentLevel(DataboxEnchantments.BLINKING_STRIKE.get()) > 0) {
        hitSomething = true;

        // teleport to above the block
        this.getOwner().teleportTo(hitPos.getX(), hitPos.getY() + 1, hitPos.getZ());

        // hurt the owner by half a heart
        this.getOwner().hurt(DamageSource.thrown(this, this.getOwner()), 1.0F);

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
