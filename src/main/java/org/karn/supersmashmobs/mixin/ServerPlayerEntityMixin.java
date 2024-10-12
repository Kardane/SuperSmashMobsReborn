package org.karn.supersmashmobs.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.gjdd.batoru.job.SkillSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;


    @Inject(method = "tick", at = @At("TAIL"))
    public void mainTick(CallbackInfo ci) {
        if(player.getJob() == null) return;
        var job = player.getJob().value();
        player.sendMessage(
                Text.empty().append((String.valueOf(player.getSkillCooldown(job.getSkillMap().get(SkillSlot.NORMAL_1)))))
                        .append(" | ")
                        .append((String.valueOf(player.getSkillCooldown(job.getSkillMap().get(SkillSlot.NORMAL_2)))))
                        .append(" | ")
                        .append((String.valueOf(player.getSkillCooldown(job.getSkillMap().get(SkillSlot.NORMAL_3)))))
                        .append(" | ")
                        .append((String.valueOf(player.getSkillCooldown(job.getSkillMap().get(SkillSlot.ULTIMATE)))))
                ,true);
    }
}
