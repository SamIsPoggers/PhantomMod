package org.sam.phantommod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import org.sam.phantommod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PHANTOM_PLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.PHANTOM_PLATE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PHANTOM_PLATE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PHANTOM_PLATE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PHANTOM_PLATE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PHANTOM_PLATE_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PHANTOM_PLATE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PHANTOM_PLATE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PHANTOM_PLATE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PHANTOM_PLATE_BOOTS));
    }
}
