package org.sam.phantommod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.sam.phantommod.item.ModItems;
import org.sam.phantommod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.PHANTOM_PLATE_SWORD);
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.PHANTOM_PLATE_AXE);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.PHANTOM_PLATE_PICKAXE);
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.PHANTOM_PLATE_SHOVEL);
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.PHANTOM_PLATE_HOE);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PHANTOM_PLATE_HELMET)
                .add(ModItems.PHANTOM_PLATE_CHESTPLATE)
                .add(ModItems.PHANTOM_PLATE_LEGGINGS)
                .add(ModItems.PHANTOM_PLATE_BOOTS);

        getOrCreateTagBuilder(ModTags.Items.PHANTOM_CHEST_ENCHANTABLES)
                .add(ModItems.PHANTOM_PLATE_CHESTPLATE);

        getOrCreateTagBuilder(ModTags.Items.PHANTOM_LEG_ENCHANTABLES)
                .add(ModItems.PHANTOM_PLATE_LEGGINGS);

        getOrCreateTagBuilder(ModTags.Items.PHANTOM_REPAIRABLES)
                .add(ModItems.PHANTOM_PLATE_SWORD)
                .add(ModItems.PHANTOM_PLATE_AXE)
                .add(ModItems.PHANTOM_PLATE_PICKAXE)
                .add(ModItems.PHANTOM_PLATE_SHOVEL)
                .add(ModItems.PHANTOM_PLATE_HOE)
                .add(ModItems.PHANTOM_PLATE_HELMET)
                .add(ModItems.PHANTOM_PLATE_CHESTPLATE)
                .add(ModItems.PHANTOM_PLATE_LEGGINGS)
                .add(ModItems.PHANTOM_PLATE_BOOTS);
    }
}
