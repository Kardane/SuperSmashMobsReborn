package org.karn.supersmashmobs.registry;

import eu.pb4.polymer.common.api.PolymerCommonUtils;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import static org.karn.supersmashmobs.SuperSmashMobs.MODID;

public class SSMAttributes {
    public static final RegistryEntry<EntityAttribute> HEALTH_REGEN = register("ssm_health_regen", new ClampedEntityAttribute("attribute.generic.ssm_health_regen", 1, 0.0D, 1000.0D));
    public static final RegistryEntry<EntityAttribute> KNOCKBACK_TAKEN = register("ssm_knockback_taken", new ClampedEntityAttribute("attribute.generic.ssm_knockback_taken", 100, 0.0D, 10000.0D));
    public static final RegistryEntry<EntityAttribute> PROTECTION = register("ssm_protection", new ClampedEntityAttribute("attribute.generic.ssm_protection", 100, 0.0D, 10000.0D));
    public static final RegistryEntry<EntityAttribute> ATTACK_SPEED = register("ssm_attack_speed", new ClampedEntityAttribute("attribute.generic.ssm_attack_speed", 20, 1, 10000.0D));
    public static final RegistryEntry<EntityAttribute> ATTACK_DMG = register("ssm_attack_dmg", new ClampedEntityAttribute("attribute.generic.ssm_attack_dmg", 1, 0.0D, 10000.0D));

    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.ofVanilla(id), attribute);
    }

    public static void init(){}
}
