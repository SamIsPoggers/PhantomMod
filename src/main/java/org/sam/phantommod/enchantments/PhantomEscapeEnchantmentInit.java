package org.sam.phantommod.enchantments;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKeys;
import org.sam.phantommod.Phantommod;
import org.sam.phantommod.enchantments.effects.PhantomEnchantmentEffect;

public class PhantomEscapeEnchantmentInit {

    public static final Identifier IDENTIFIER = Identifier.of(Phantommod.MOD_ID, "phantom_escape");
    public static final RegistryKey<Enchantment> PHANTOM_ESCAPE_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT, IDENTIFIER);

    public static final MapCodec<PhantomEnchantmentEffect> PHANTOM_EFFECT = register("phantom_escape", PhantomEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String name, MapCodec<T> codec){
        Identifier IDENTIFIER = Identifier.of(Phantommod.MOD_ID, name);
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, IDENTIFIER, codec);
    }

    public static void load () {
    }

}
