package org.sam.phantommod.mixin;

import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.SystemDetails;
import net.minecraft.village.TradeOffer;
import org.sam.phantommod.Phantommod;
import org.sam.phantommod.StuffTimerAccess;
import org.sam.phantommod.VillagerCureHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class) // ServerWorld, MinecraftServer, etc
public abstract class StuffTimer implements StuffTimerAccess {

    @Shadow public abstract SystemDetails addSystemDetails(SystemDetails details);

    @Unique
    private long ticksUntilSomething;
    @Unique
    private VillagerEntity villagerEntity;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) { // Fix parameters as needed
        if (--this.ticksUntilSomething == 0L) {
            if (alreadyOffersEnchants(this.villagerEntity)) {
                System.out.println("This villager already offers a trade for Phantom Rider or Phantom Escape!");
            } else {
                System.out.println("No matching enchantment trades found, trying one...");
                VillagerCureHandler.setTrade(this.villagerEntity);
            }
            // If you want to repeat this, reset ticksUntilSomething here.
        }
    }

    @Override
    public void phantomMod_setTimer(long ticksUntilSomething, VillagerEntity villager) {
        this.ticksUntilSomething = ticksUntilSomething;
        this.villagerEntity = villager;
    }

    private static boolean alreadyOffersEnchants(VillagerEntity villager) {

        // Dynamically detect the enchantment using RegistryKey and Identifier
        RegistryKey<Enchantment> phantomRiderKey = RegistryKey.of(
                RegistryKeys.ENCHANTMENT,
                Identifier.of("phantommod", "phantom_rider") // MOD_ID and enchantment ID
        );

        // Fetch the RegistryEntry for the enchantment
        RegistryEntry<Enchantment> phantomRiderEnchantmentEntry = villager.getWorld().getRegistryManager()
                .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(phantomRiderKey);

        // Dynamically detect the enchantment using RegistryKey and Identifier
        RegistryKey<Enchantment> phantomEscapeKey = RegistryKey.of(
                RegistryKeys.ENCHANTMENT,
                Identifier.of("phantommod", "phantom_escape") // MOD_ID and enchantment ID
        );

        // Fetch the RegistryEntry for the enchantment
        RegistryEntry<Enchantment> phantomEscapeEnchantmentEntry = villager.getWorld().getRegistryManager()
                .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(phantomEscapeKey);

        for (TradeOffer offers : villager.getOffers()) {

            ItemStack tradedItemStack = offers.getSellItem();

            Phantommod.LOGGER.info("Item " + tradedItemStack);
            Phantommod.LOGGER.info("Enchantments: " + EnchantmentHelper.getEnchantments(tradedItemStack));

            if (EnchantmentHelper.getEnchantments(tradedItemStack).toString().contains("phantommod:phantom_escape")
                    || EnchantmentHelper.getEnchantments(tradedItemStack).toString().contains("phantommod:phantom_rider")) {
                return true;
            }
        }
        return false;
    }

}