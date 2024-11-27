package org.sam.phantommod.enchantments.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record PhantomEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {

    public static final MapCodec<PhantomEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(PhantomEnchantmentEffect::amount)
            ).apply(instance, PhantomEnchantmentEffect::new));

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (context.owner() instanceof PlayerEntity attacker && user instanceof LivingEntity target) {
            // Check if the attacker already has invisibility
            boolean hasInvisibility = attacker.hasStatusEffect(StatusEffects.INVISIBILITY);

            // Conditional logic based on enchantment level
            if ((int) this.amount.getValue(level) == 1) {
                if (!hasInvisibility) {
                    StatusEffectInstance effect = new StatusEffectInstance(
                            StatusEffects.INVISIBILITY, // The effect type
                            100,                        // Duration in ticks (100 ticks = 5 seconds)
                            0,                          // Amplifier (level of the effect, 0 = level 1)
                            false,                      // Whether particles are shown
                            false                       // Whether the effect icon is displayed
                    );
                    attacker.addStatusEffect(effect);
                }
                applyKnockback(attacker, target, 0.5f); // Apply knockback with strength 0.5
            }

            if ((int) this.amount.getValue(level) == 2) {
                if (!hasInvisibility) {
                    StatusEffectInstance effect = new StatusEffectInstance(
                            StatusEffects.INVISIBILITY, // The effect type
                            180,                        // Duration in ticks (180 ticks = 9 seconds)
                            0,                          // Amplifier (level of the effect, 0 = level 1)
                            false,                      // Whether particles are shown
                            false                       // Whether the effect icon is displayed
                    );
                    attacker.addStatusEffect(effect);
                }
                applyKnockback(attacker, target, 1.0f); // Apply knockback with strength 1.0
            }
        }
    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }

    public void applyKnockback(PlayerEntity attacker, LivingEntity target, double strength) {
        // Calculate the direction vector (from attacker to target)
        double deltaX = target.getX() - attacker.getX();
        double deltaZ = target.getZ() - attacker.getZ();

        // Normalize the direction vector
        double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        if (distance != 0) {
            deltaX /= distance;
            deltaZ /= distance;
        } else {
            // Prevent division by zero; set a small default knockback direction
            deltaX = 0.1;
            deltaZ = 0.1;
        }

        if (target instanceof PlayerEntity playerTarget) {
            // Apply knockback using addVelocity for PlayerEntity
            double velocityX = -deltaX * strength;
            double velocityZ = -deltaZ * strength;
            playerTarget.addVelocity(-velocityX, 0.1, -velocityZ); // Slight vertical component
            playerTarget.velocityModified = true; // Mark velocity as modified

            // Debugging: Log velocity for verification
            System.out.println("Applied velocity to PlayerEntity: X=" + velocityX + ", Z=" + velocityZ);
        } else {
            // Apply knockback using takeKnockback for other LivingEntity
            target.takeKnockback(strength, -deltaX, -deltaZ);

            // Debugging: Log knockback for verification
            System.out.println("Applied knockback to LivingEntity: strength=" + strength);
        }
    }


}
