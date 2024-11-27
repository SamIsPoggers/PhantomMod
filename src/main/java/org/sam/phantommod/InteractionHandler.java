package org.sam.phantommod;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class InteractionHandler {

    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient && entity instanceof PhantomEntity phantom && world instanceof ServerWorld serverWorld) {
                ItemStack leggings = player.getInventory().armor.get(1); // Slot 1 is for leggings
                ItemEnchantmentsComponent enchantsComponent = leggings.getEnchantments();

                // Dynamically detect the enchantment using RegistryKey and Identifier
                RegistryKey<Enchantment> phantomRiderKey = RegistryKey.of(
                        RegistryKeys.ENCHANTMENT,
                        Identifier.of("phantommod", "phantom_rider") // MOD_ID and enchantment ID
                );

                // Fetch the RegistryEntry for the enchantment
                RegistryEntry<Enchantment> phantomRiderEnchantmentEntry = serverWorld.getRegistryManager()
                        .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOrThrow(phantomRiderKey);

                // Check if the enchantment is applied
                if (enchantsComponent.getLevel(phantomRiderEnchantmentEntry) > 0) {
                    player.startRiding(phantom, true);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }
}