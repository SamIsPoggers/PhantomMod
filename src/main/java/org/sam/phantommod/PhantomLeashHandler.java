package org.sam.phantommod;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class PhantomLeashHandler {

    // Register the event listener
    public static void register() {
        UseEntityCallback.EVENT.register(PhantomLeashHandler::onUseEntity);
    }

    private static ActionResult onUseEntity(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) {
        // Check if the entity is a phantom
        if (entity instanceof PhantomEntity phantom) {
            // Check if the player is holding a lead
            if (player.getStackInHand(hand).isOf(Items.LEAD)) {
                // Only proceed on the server side
                if (!world.isClient) {
                    // Check if the phantom is not already leashed
                    if (!phantom.isLeashed()) {
                        // Attach the leash
                        phantom.attachLeash(player, true);
                        // Consume one lead from the player's hand
                        player.getStackInHand(hand).decrement(1);
                        return ActionResult.SUCCESS;
                    }
                }
                return ActionResult.CONSUME; // Prevent other interactions
            }
        }
        return ActionResult.PASS; // Allow default behavior for other entities
    }

}
