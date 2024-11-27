package org.sam.phantommod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.sam.phantommod.Phantommod;

public class ModTags {

    public static class Blocks {

        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Phantommod.MOD_ID, name));
        }

    }

    public static class Items {

        public static final TagKey<Item> PHANTOM_CHEST_ENCHANTABLES = createTag("phantom_chest_ench");

        public static final TagKey<Item> PHANTOM_LEG_ENCHANTABLES = createTag("phantom_leg_ench");

        public static final TagKey<Item> PHANTOM_REPAIRABLES = createTag("phantom_repairables");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Phantommod.MOD_ID, name));
        }

    }

}
