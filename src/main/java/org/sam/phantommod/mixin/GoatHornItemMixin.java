package org.sam.phantommod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GoatHornItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GoatHornItem.class)
public class GoatHornItemMixin {
    @Inject(method = "use", at = @At("RETURN"), cancellable = true)
    private void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (!world.isClient) { // Ensure this runs on the server side
            ServerWorld serverWorld = (ServerWorld) world;

            // Get the player's position
            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();

            // Define the range (100x100 blocks)
            double range = 50.0; // Radius is half of the area (100x100)

            // Iterate over entities within the bounding box
            for (Entity entity : serverWorld.getEntitiesByClass(Entity.class, user.getBoundingBox().expand(range), e -> e.hasPassengers())) {
                // If the entity is a phantom and has passengers
                if (entity.getType().toString().contains("phantom")) {
                    // Remove all passengers except the user
                    entity.getPassengerList().stream()
                            .filter(passenger -> passenger instanceof PlayerEntity && !passenger.equals(user))
                            .forEach(Entity::stopRiding);
                }
            }
        }
    }
}
