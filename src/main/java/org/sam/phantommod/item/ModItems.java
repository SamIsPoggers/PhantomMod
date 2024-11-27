package org.sam.phantommod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.sam.phantommod.Phantommod;

public class ModItems {


    //Items
    public static final Item PHANTOM_PLATE = registerItem("phantom_plate", new Item(new Item.Settings()));

    //Tools
    public static final Item PHANTOM_PLATE_SWORD = registerItem("phantom_plate_sword", new SwordItem(ModToolMaterials.PHANTOM_PLATE, new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PHANTOM_PLATE, 3, -2.4f)).fireproof()));

    public static final Item PHANTOM_PLATE_AXE = registerItem("phantom_plate_axe", new AxeItem(ModToolMaterials.PHANTOM_PLATE, new Item.Settings()
            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.PHANTOM_PLATE, 6, -3.2f)).fireproof()));

    public static final Item PHANTOM_PLATE_PICKAXE = registerItem("phantom_plate_pickaxe", new PickaxeItem(ModToolMaterials.PHANTOM_PLATE, new Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PHANTOM_PLATE, 1, -2.8f)).fireproof()));

    public static final Item PHANTOM_PLATE_SHOVEL = registerItem("phantom_plate_shovel", new ShovelItem(ModToolMaterials.PHANTOM_PLATE, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.PHANTOM_PLATE, 1.5F, -3.0f)).fireproof()));

    public static final Item PHANTOM_PLATE_HOE = registerItem("phantom_plate_hoe", new HoeItem(ModToolMaterials.PHANTOM_PLATE, new Item.Settings()
            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.PHANTOM_PLATE, 0, -3f)).fireproof()));

    //Armor
    public static final Item PHANTOM_PLATE_HELMET = registerItem("phantom_plate_helmet",
            new ArmorItem(ModArmorMaterials.PHANTOM_PLATES_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Settings()
                            .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15)).fireproof()));
    public static final Item PHANTOM_PLATE_CHESTPLATE = registerItem("phantom_plate_chestplate",
            new ArmorItem(ModArmorMaterials.PHANTOM_PLATES_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings()
                            .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15)).fireproof()));
    public static final Item PHANTOM_PLATE_LEGGINGS = registerItem("phantom_plate_leggings",
            new ArmorItem(ModArmorMaterials.PHANTOM_PLATES_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Settings()
                            .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15)).fireproof()));
    public static final Item PHANTOM_PLATE_BOOTS = registerItem("phantom_plate_boots",
            new ArmorItem(ModArmorMaterials.PHANTOM_PLATES_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Settings()
                            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15)).fireproof()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Phantommod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Phantommod.LOGGER.info("Registering Mod Items for " + Phantommod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PHANTOM_PLATE);
        });

    }

}
