package org.sam.phantommod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import org.sam.phantommod.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE, 1)
                .input(Items.PHANTOM_MEMBRANE)
                .input(Items.PHANTOM_MEMBRANE)
                .criterion(hasItem(Items.PHANTOM_MEMBRANE), conditionsFromItem(Items.PHANTOM_MEMBRANE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE_SWORD)
                .pattern(" P ")
                .pattern(" P ")
                .pattern(" B ")
                .input('P', ModItems.PHANTOM_PLATE)
                .input('B', Items.BONE)
                .criterion(hasItem(ModItems.PHANTOM_PLATE), conditionsFromItem(ModItems.PHANTOM_PLATE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE_PICKAXE)
                .pattern("PPP")
                .pattern(" B ")
                .pattern(" B ")
                .input('P', ModItems.PHANTOM_PLATE)
                .input('B', Items.BONE)
                .criterion(hasItem(ModItems.PHANTOM_PLATE), conditionsFromItem(ModItems.PHANTOM_PLATE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE_AXE)
                .pattern(" PP")
                .pattern(" BP")
                .pattern(" B ")
                .input('P', ModItems.PHANTOM_PLATE)
                .input('B', Items.BONE)
                .criterion(hasItem(ModItems.PHANTOM_PLATE), conditionsFromItem(ModItems.PHANTOM_PLATE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE_SHOVEL)
                .pattern(" P ")
                .pattern(" B ")
                .pattern(" B ")
                .input('P', ModItems.PHANTOM_PLATE)
                .input('B', Items.BONE)
                .criterion(hasItem(ModItems.PHANTOM_PLATE), conditionsFromItem(ModItems.PHANTOM_PLATE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE_HOE)
                .pattern(" PP")
                .pattern(" B ")
                .pattern(" B ")
                .input('P', ModItems.PHANTOM_PLATE)
                .input('B', Items.BONE)
                .criterion(hasItem(ModItems.PHANTOM_PLATE), conditionsFromItem(ModItems.PHANTOM_PLATE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE_HELMET)
                .pattern("PPP")
                .pattern("P P")
                .pattern("   ")
                .input('P', ModItems.PHANTOM_PLATE)
                .criterion(hasItem(ModItems.PHANTOM_PLATE), conditionsFromItem(ModItems.PHANTOM_PLATE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE_CHESTPLATE)
                .pattern("P P")
                .pattern("PPP")
                .pattern("PPP")
                .input('P', ModItems.PHANTOM_PLATE)
                .criterion(hasItem(ModItems.PHANTOM_PLATE), conditionsFromItem(ModItems.PHANTOM_PLATE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE_LEGGINGS)
                .pattern("PPP")
                .pattern("P P")
                .pattern("P P")
                .input('P', ModItems.PHANTOM_PLATE)
                .criterion(hasItem(ModItems.PHANTOM_PLATE), conditionsFromItem(ModItems.PHANTOM_PLATE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PHANTOM_PLATE_BOOTS)
                .pattern("   ")
                .pattern("P P")
                .pattern("P P")
                .input('P', ModItems.PHANTOM_PLATE)
                .criterion(hasItem(ModItems.PHANTOM_PLATE), conditionsFromItem(ModItems.PHANTOM_PLATE))
                .offerTo(recipeExporter);
    }
}
