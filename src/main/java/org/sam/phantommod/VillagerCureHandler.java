package org.sam.phantommod;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class VillagerCureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(VillagerCureHandler.class);

    public static void register() {
        ServerLivingEntityEvents.MOB_CONVERSION.register((previous, converted, world) -> {
            LOGGER.info("Mob conversion event triggered.");
            if (previous instanceof ZombieVillagerEntity && converted instanceof VillagerEntity villager) {
                LOGGER.info("Converted entity is a VillagerEntity.");
                handleVillagerCure((ZombieVillagerEntity) previous, villager, previous);
            } else {
                LOGGER.info("Converted entity is not a VillagerEntity.");
            }
        });
    }

    private static void handleVillagerCure(ZombieVillagerEntity zombieVillager, VillagerEntity villager, LivingEntity old) {
        LOGGER.info("Zombie villager was cured!");

        // Check if the cured villager is a librarian
        if (zombieVillager.getVillagerData().getProfession() == VillagerProfession.LIBRARIAN) {

            MinecraftServer minecraftServer = villager.getServer();
            ((StuffTimerAccess) minecraftServer).phantomMod_setTimer(100L, villager); // do something after 100 ticks

        } else {
            LOGGER.info("Cured villager is not a librarian.");
        }
    }

    private static void addCustomTradeForLibrarian(VillagerEntity librarian, World World) {

        if (librarian.getRandom().nextBoolean()){

            int outcome = librarian.getRandom().nextInt(3);

            switch (outcome){

                case 0:
                    Phantommod.LOGGER.info("Random is 0, adding phantomRider...");
                    // Dynamically detect the enchantment using RegistryKey and Identifier
                    RegistryKey<Enchantment> phantomRiderKey = RegistryKey.of(
                            RegistryKeys.ENCHANTMENT,
                            Identifier.of("phantommod", "phantom_rider") // MOD_ID and enchantment ID
                    );

                    // Fetch the RegistryEntry for the enchantment
                    RegistryEntry<Enchantment> phantomRiderEnchantmentEntry = World.getRegistryManager()
                            .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                            .getOrThrow(phantomRiderKey);

                    ItemStack enchantedBook = EnchantedBookItem.forEnchantment(
                            new EnchantmentLevelEntry(phantomRiderEnchantmentEntry, 1)
                    );

                    TradeOffer customTrade = new TradeOffer(
                            new TradedItem(Items.EMERALD, 5), // Buy 5 emeralds
                            enchantedBook, // Get enchanted book
                            10, // Max uses
                            1, // Experience
                            0.05f // Price multiplier
                    );

                    // Add the custom trade to the librarian
                    librarian.getOffers().add(customTrade);
                    LOGGER.info("Custom trade added to librarian.");
                    break;
                case 1:
                    Phantommod.LOGGER.info("Random is 1, adding Phantom Escape Level 1");
                    // Dynamically detect the enchantment using RegistryKey and Identifier
                    RegistryKey<Enchantment> phantomEscapeKey = RegistryKey.of(
                            RegistryKeys.ENCHANTMENT,
                            Identifier.of("phantommod", "phantom_escape") // MOD_ID and enchantment ID
                    );

                    // Fetch the RegistryEntry for the enchantment
                    RegistryEntry<Enchantment> phantomEscapeEnchantmentEntry = World.getRegistryManager()
                            .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                            .getOrThrow(phantomEscapeKey);

                    ItemStack enchantedBook2 = EnchantedBookItem.forEnchantment(
                            new EnchantmentLevelEntry(phantomEscapeEnchantmentEntry, 1)
                    );

                    TradeOffer customTrade2 = new TradeOffer(
                            new TradedItem(Items.EMERALD, 5), // Buy 5 emeralds
                            enchantedBook2, // Get enchanted book
                            10, // Max uses
                            1, // Experience
                            0.05f // Price multiplier
                    );

                    // Add the custom trade to the librarian
                    librarian.getOffers().add(customTrade2);
                    LOGGER.info("Custom trade added to librarian.");
                    break;
                case 2:
                    Phantommod.LOGGER.info("Random is 2, adding Phantom Escape Level 2");
                    // Dynamically detect the enchantment using RegistryKey and Identifier
                    RegistryKey<Enchantment> phantomEscapeKey2 = RegistryKey.of(
                            RegistryKeys.ENCHANTMENT,
                            Identifier.of("phantommod", "phantom_escape") // MOD_ID and enchantment ID
                    );

                    // Fetch the RegistryEntry for the enchantment
                    RegistryEntry<Enchantment> phantomEscapeEnchantmentEntry2 = World.getRegistryManager()
                            .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                            .getOrThrow(phantomEscapeKey2);

                    ItemStack enchantedBook3 = EnchantedBookItem.forEnchantment(
                            new EnchantmentLevelEntry(phantomEscapeEnchantmentEntry2, 2)
                    );

                    TradeOffer customTrade3 = new TradeOffer(
                            new TradedItem(Items.EMERALD, 5), // Buy 5 emeralds
                            enchantedBook3, // Get enchanted book
                            10, // Max uses
                            1, // Experience
                            0.05f // Price multiplier
                    );

                    // Add the custom trade to the librarian
                    librarian.getOffers().add(customTrade3);
                    LOGGER.info("Custom trade added to librarian.");
                    break;
            }

        } else {
            Phantommod.LOGGER.info("50% Chance Failed, no new trade added");
        }
    }

    public static void setTrade(VillagerEntity villager){
        LOGGER.info("Adding custom trade to cured librarian.");
        addCustomTradeForLibrarian(villager, villager.getWorld());
    }
}