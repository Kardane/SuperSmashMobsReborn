package org.karn.supersmashmobs.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import org.karn.supersmashmobs.kit.AbstractKit;

public class SSMRegistries {
    public static final Registry<AbstractKit> KIT = FabricRegistryBuilder.createSimple(SSMRegistryKeys.KIT).buildAndRegister();

    public static void register() {}
}
