package org.karn.supersmashmobs;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.karn.supersmashmobs.command.GameCommand;
import org.karn.supersmashmobs.util.SSMRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuperSmashMobs implements ModInitializer {
    public static final String MODID = "supersmashmobs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    @Override
    public void onInitialize() {
        //명령어
        CommandRegistrationCallback.EVENT.register((dispatcher, commandRegistryAccess, ignored1) -> {
            GameCommand.register(dispatcher);
        });

        SSMRegistry.registerKits();
        PolymerResourcePackUtils.addModAssets(MODID);
        PolymerResourcePackUtils.markAsRequired();
        LOGGER.info("SuperSmashMobs Online!");
    }
}
