package org.sam.phantommod.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import org.sam.phantommod.util.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    private static final float TOTAL_HEAL_TIME = 120.0f;  // Total time for 300 durability (120 seconds)
    private static final int BASE_MAX_DURABILITY = 300;  // Base max durability for scaling
    private static final float HEALING_SPEED_MULTIPLIER = 0.25f;  // Slow down the healing (0.5 will halve the healing speed)

    /**
     * Inject our custom logic into the regular tick method
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;

        // Ensure we are on the server side
        if (!(itemEntity.getWorld() instanceof ServerWorld)) {
            return;  // If we're not on the server, exit early
        }

        // Cast to ServerWorld as we're on the server side
        ServerWorld serverWorld = (ServerWorld) itemEntity.getWorld();

        // Get the current position as a Vec3d
        Vec3d posVec = itemEntity.getPos();

        // Convert Vec3d to BlockPos by flooring the coordinates to integers
        BlockPos pos = new BlockPos((int) Math.floor(posVec.x), (int) Math.floor(posVec.y), (int) Math.floor(posVec.z));

        // Get the block state at the position of the item entity
        BlockState blockState = serverWorld.getBlockState(pos);

        // Check if the item is part of the PHANTOM_REPAIRABLES tag
        if (!itemEntity.getStack().isIn(ModTags.Items.PHANTOM_REPAIRABLES)) {
            return; // Do nothing if item is not in the tag
        }

        // Check if the block at the item's position is fire
        if (!blockState.isOf(Blocks.FIRE)) {
            return; // Exit if it's not fire
        }

        // Check if the block below the fire is Netherrack
        BlockPos blockBelowPos = pos.down();  // Position below the fire block
        BlockState blockBelow = serverWorld.getBlockState(blockBelowPos);

        if (!blockBelow.isOf(Blocks.NETHERRACK)) {
            return; // Exit if the block below the fire is not Netherrack
        }

        // Get the current item stack and its properties
        ItemStack itemStack = itemEntity.getStack();
        int currentDurability = itemStack.getDamage();
        int maxDurability = itemStack.getMaxDamage();

        // Calculate healing rate based on the item's max durability
        // We want the item to heal over the same period (e.g., 120 seconds for 300 durability)
        float healingRate = (float) maxDurability / BASE_MAX_DURABILITY * (TOTAL_HEAL_TIME / 20) * HEALING_SPEED_MULTIPLIER;  // Heal per tick (each tick is 1/20 of a second)

        // Heal the item (don't exceed max durability)
        if (currentDurability > 0) {
            int healingAmount = (int) healingRate;  // Calculate how much durability to heal each tick
            int newDurability = Math.max(0, currentDurability - healingAmount);  // Ensure durability doesn't go below 0
            itemStack.setDamage(newDurability);
        }
    }
}
