package org.sam.phantommod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.sam.phantommod.Phantommod;

public class ModItemGroups {

    public static final ItemGroup PHANTOM_PLATE_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Phantommod.MOD_ID, "phantom_plate_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.PHANTOM_PLATE))
                    .displayName(Text.translatable("itemgroup.phantommod.phantom_plate_items"))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.PHANTOM_PLATE);

                        entries.add(ModItems.PHANTOM_PLATE_AXE);
                        entries.add(ModItems.PHANTOM_PLATE_HOE);
                        entries.add(ModItems.PHANTOM_PLATE_SHOVEL);
                        entries.add(ModItems.PHANTOM_PLATE_PICKAXE);
                        entries.add(ModItems.PHANTOM_PLATE_SWORD);

                        entries.add(ModItems.PHANTOM_PLATE_HELMET);
                        entries.add(ModItems.PHANTOM_PLATE_CHESTPLATE);
                        entries.add(ModItems.PHANTOM_PLATE_LEGGINGS);
                        entries.add(ModItems.PHANTOM_PLATE_BOOTS);

                    }).build());


    public static void registerItemGroups() {
        Phantommod.LOGGER.info("Registering Item Groups for " + Phantommod.MOD_ID);

    }

}
