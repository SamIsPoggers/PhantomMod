package org.sam.phantommod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import org.sam.phantommod.enchantments.PhantomEscapeEnchantmentInit;
import org.sam.phantommod.enchantments.PhantomRiderEnchantmentInit;
import org.sam.phantommod.enchantments.effects.PhantomEnchantmentEffect;
import org.sam.phantommod.enchantments.effects.PhantomRiderEnchantmentEffect;
import org.sam.phantommod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class PhantomModEnchantmentGenerator extends FabricDynamicRegistryProvider {

    public PhantomModEnchantmentGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        RegistryWrapper<Item> itemLookup = wrapperLookup.getWrapperOrThrow(RegistryKeys.ITEM);

        register(entries, PhantomEscapeEnchantmentInit.PHANTOM_ESCAPE_KEY, Enchantment.builder(
                Enchantment.definition(
                        itemLookup.getOrThrow(ModTags.Items.PHANTOM_CHEST_ENCHANTABLES),
                        1, //probability of showing on ench table
                        2, //max level
                        Enchantment.leveledCost(1,10),
                        Enchantment.leveledCost(1,15),
                        7, //anvil cost
                        AttributeModifierSlot.CHEST
                ))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.VICTIM,
                        EnchantmentEffectTarget.ATTACKER,
                        new PhantomEnchantmentEffect(
                                EnchantmentLevelBasedValue.linear(1.0f, 1.0f)
                        )
                )
        );

        register(entries, PhantomRiderEnchantmentInit.PHANTOM_RIDER_KEY, Enchantment.builder(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ModTags.Items.PHANTOM_LEG_ENCHANTABLES),
                                1, //probability of showing on ench table
                                1, //max level
                                Enchantment.leveledCost(1,10),
                                Enchantment.leveledCost(1,15),
                                7, //anvil cost
                                AttributeModifierSlot.LEGS
                        ))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.VICTIM,
                        EnchantmentEffectTarget.ATTACKER,
                        new PhantomRiderEnchantmentEffect(
                                EnchantmentLevelBasedValue.linear(1.0f, 1.0f)
                        )
                )
        );
    }

    private static void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }


    @Override
    public String getName() {
        return "Enchantment Generator";
    }
}
