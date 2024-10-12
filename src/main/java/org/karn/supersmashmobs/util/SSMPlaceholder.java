package org.karn.supersmashmobs.util;

import eu.pb4.placeholders.api.PlaceholderResult;
import eu.pb4.placeholders.api.Placeholders;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.karn.supersmashmobs.SuperSmashMobsAPI;

import static org.karn.supersmashmobs.SuperSmashMobs.MODID;

public class SSMPlaceholder {
    public static void register() {
        Placeholders.register(Identifier.of(MODID, "type"), (ctx, arg) -> {
            if(!ctx.hasPlayer()) return PlaceholderResult.invalid("No Player");
            var sp = (SuperSmashMobsAPI) ctx.player();
            return PlaceholderResult.value(sp.getKit().disguiseType.getName());
        });
    }
}
