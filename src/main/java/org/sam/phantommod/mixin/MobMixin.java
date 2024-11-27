package org.sam.phantommod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobMixin {
    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    public void setTarget(@Nullable LivingEntity target, CallbackInfo callback) {
        if (target != null && target.getVehicle() == (MobEntity)(Object)this) {
            // Cancel targeting if the target is riding this mob
            callback.cancel();
        }
    }
}
