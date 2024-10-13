package org.karn.supersmashmobs.registry;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.karn.supersmashmobs.kit.AbstractKit;

import static org.karn.supersmashmobs.SuperSmashMobs.MODID;

public final class SSMRegistryKeys {
    public static final RegistryKey<Registry<AbstractKit>> KIT = RegistryKey.ofRegistry(Identifier.of(MODID, "kit"));

    public static void register() {}
}
