package org.sam.phantommod;

import net.fabricmc.api.ModInitializer;
import org.sam.phantommod.enchantments.PhantomEscapeEnchantmentInit;
import org.sam.phantommod.enchantments.PhantomRiderEnchantmentInit;
import org.sam.phantommod.item.ModItemGroups;
import org.sam.phantommod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Phantommod implements ModInitializer {

    public static final String MOD_ID = "phantommod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();

        ModItemGroups.registerItemGroups();

        PhantomEscapeEnchantmentInit.load();
        PhantomRiderEnchantmentInit.load();

        InteractionHandler.register();

        PhantomLeashHandler.register();

        VillagerCureHandler.register();

        PhantomRiderFix.register();
    }
}