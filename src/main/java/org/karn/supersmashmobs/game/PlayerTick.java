package org.karn.supersmashmobs.game;

import net.minecraft.network.packet.s2c.play.UpdateSelectedSlotS2CPacket;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.joml.Vector3f;
import org.karn.supersmashmobs.registry.SSMAttributes;

public class PlayerTick {
    public static void main(ServerPlayerEntity player){
        if(player.isSpectator()) return;

        if(player.isOnGround() && !player.getAbilities().allowFlying){
            player.getAbilities().allowFlying = true;
            player.sendAbilitiesUpdate();
        } else if(player.getAbilities().flying){
            player.getAbilities().flying = false;
            player.getAbilities().allowFlying = false;
            player.sendAbilitiesUpdate();
            player.setVelocity(player.getRotationVector().x/3,100/player.getAttributeValue(SSMAttributes.KNOCKBACK_TAKEN),player.getRotationVector().z/3);
            player.velocityModified = true;
            player.getWorld().playSound(player.getX(),player.getY(),player.getZ(),SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.MASTER,1,1,true);
            player.getServerWorld().spawnParticles(new DustParticleEffect(new Vector3f(1,1,1),1),player.getX(),player.getY(),player.getZ(),30,0.5,0.5,0.5,0);
        }

        if(player.getInventory().selectedSlot != 4){
            player.getInventory().selectedSlot = 4;
            player.networkHandler.sendPacket(new UpdateSelectedSlotS2CPacket(4));
        }

        if(player.server.getTicks()%20==0){
            player.heal((float) player.getAttributeValue(SSMAttributes.HEALTH_REGEN));
            player.getHungerManager().setFoodLevel(20);
        }
    }
}
