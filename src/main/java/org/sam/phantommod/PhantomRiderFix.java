package org.sam.phantommod;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class PhantomRiderFix {

    public static void register() {
        // Register a server tick event
        ServerTickEvents.END_WORLD_TICK.register(PhantomRiderFix::onWorldTick);
    }

    private static void onWorldTick(ServerWorld world) {
        for (PlayerEntity player : world.getPlayers()) {
            // Check if the player is riding an entity and that entity is a Phantom
            if (player.hasVehicle() && player.getVehicle() instanceof PhantomEntity phantom) {

                // Get the direction the player is looking
                Vec3d lookVector = player.getRotationVec(1.0F);

                // Set the Phantom's velocity in the direction the player is looking
                double speed = 0.5; // Adjust this value for desired speed
                phantom.setVelocity(lookVector.x * speed, lookVector.y * speed, lookVector.z * speed);

                // Make the Phantom face the same direction as the player
                phantom.setYaw(player.getYaw());
                phantom.setPitch(-player.getPitch());

                // Ensure the phantom's head/body rotations are updated (optional, for smooth visuals)
                phantom.setHeadYaw(player.getYaw());
                phantom.bodyYaw = player.getYaw();
            }
        }
    }
}