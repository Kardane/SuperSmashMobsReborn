package org.karn.supersmashmobs.game;

import net.minecraft.network.packet.s2c.play.UpdateSelectedSlotS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.karn.supersmashmobs.registry.SSMAttributes;

public class PlayerTick {
    public static void main(ServerPlayerEntity player){
        if(player.isSpectator()) return;

        player.getHungerManager().setFoodLevel(20);

        if(player.server.getTicks()%20==0){
            player.heal((float) player.getAttributeValue(RegistryEntry.of(SSMAttributes.HEALTH_REGEN)));
        }
    }
}
