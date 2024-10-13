package org.karn.supersmashmobs.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.gjdd.batoru.job.SkillSlot;
import org.karn.supersmashmobs.SuperSmashMobsAPI;
import org.karn.supersmashmobs.game.PlayerTick;
import org.karn.supersmashmobs.kit.AbstractKit;
import org.karn.supersmashmobs.kit.NoneKit;
import org.karn.supersmashmobs.registry.SSMAttributes;
import org.karn.supersmashmobs.util.Misc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Timer;
import java.util.TimerTask;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements SuperSmashMobsAPI {
    ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
    private AbstractKit kit = new NoneKit();
    private int AttackCooldown = 0;

    @Override public AbstractKit getKit() {return this.kit;}
    @Override public void setKit(AbstractKit kit) {
        this.kit = kit;
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(kit.Health);
        player.setHealth(kit.Health);
        player.getAttributeInstance(SSMAttributes.HEALTH_REGEN).setBaseValue(kit.HealthRegen);
        //player.getAttributeInstance(SSMAttributes.KNOCKBACK_TAKEN).setBaseValue(kit.KnockbackMultiplier);
        //player.getAttributeInstance(SSMAttributes.ATTACK_SPEED).setBaseValue(kit.AttackSpeed);
        //player.getAttributeInstance(SSMAttributes.ATTACK_DMG).setBaseValue(kit.AttackDamage);
        //player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(kit.Speed);
    }


    @Inject(method = "tick", at = @At("TAIL"))
    public void mainTick(CallbackInfo ci) {
        //if(player.getJob() == null) return;
        PlayerTick.main(player);
        if(AttackCooldown > 0) AttackCooldown--;
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void SSM$addHurtValue(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(player.getJob() == null) return;
        if(source.isOf(DamageTypes.FIREWORKS)) {
            cir.setReturnValue(false);
        } else {
            if(!Misc.isVoidDamage(source) && !player.getWorld().isClient) {
                float finalAmount = (float) (amount * player.getAttributeValue(SSMAttributes.PROTECTION)/100);
                player.hurtTime = 0;
                player.damage(player.getDamageSources().generic(),finalAmount);
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void SSM$tryAttack(Entity target, CallbackInfo ci){
        if(player.getJob() == null) return;
        if(!player.getWorld().isClient && !player.isSpectator()){
            if(AttackCooldown <= 0){
                target.damage(player.getDamageSources().playerAttack(player), (float) player.getAttributeValue(SSMAttributes.ATTACK_DMG));
                AttackCooldown = (int) player.getAttributeValue(SSMAttributes.ATTACK_SPEED);
            }
            ci.cancel();
        }
    }
    /*
    @Inject(method = "dropSelectedItem", at = @At("HEAD"), cancellable = true)
    private void SSM$dropItem(boolean entireStack, CallbackInfoReturnable<Boolean> cir){
        if(player.getJob() == null) return;
        cir.cancel();
        player.getInventory().setStack(player.getInventory().selectedSlot, player.getInventory().getMainHandStack().copy());
    }*/
}
