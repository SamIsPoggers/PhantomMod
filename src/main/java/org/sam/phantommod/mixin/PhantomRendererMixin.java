package org.sam.phantommod.mixin;

import net.minecraft.client.render.entity.PhantomEntityRenderer;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.util.Identifier;
import org.sam.phantommod.Phantommod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PhantomEntityRenderer.class)
public abstract class PhantomRendererMixin {

    private static final Identifier NETHER_PHANTOM_TEXTURE = Identifier.of(Phantommod.MOD_ID, "textures/entity/nether_phantom.png");
    private static final Identifier OVERWORLD_PHANTOM_TEXTURE = Identifier.of("minecraft", "textures/entity/phantom.png");

    @Inject(method = "getTexture(Lnet/minecraft/entity/mob/PhantomEntity;)Lnet/minecraft/util/Identifier;",
            at = @At("HEAD"),
            cancellable = true)
    private void injectGetTexture(PhantomEntity phantomEntity, CallbackInfoReturnable<Identifier> cir) {
        if (phantomEntity.getWorld().getRegistryKey() == net.minecraft.world.World.NETHER) {
            cir.setReturnValue(NETHER_PHANTOM_TEXTURE);
        } else {
            cir.setReturnValue(OVERWORLD_PHANTOM_TEXTURE);
        }
    }
}


