package org.karn.supersmashmobs.kit;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.gjdd.batoru.skill.Skill;
import org.gjdd.batoru.skill.SkillAction;
import org.karn.supersmashmobs.util.SSMRegistry;

public class NoneKit extends AbstractKit {
    public static final EntityType disguiseType = EntityType.DROWNED;
    public static final String JOBID = "none";
    public static final String SKILL1_ID = "none_a";
    public static final String SKILL2_ID = "none_b";
    public static final String SKILL3_ID = "none_c";
    public static final String SKILL4_ID = "none_d";
    public static final ItemStack MAINHANDITEM = Items.IRON_SWORD.getDefaultStack();

    public static final Integer HEALTH = 20;
    public static final Integer HEALTHREGEN = 1;
    public static final Integer KNOCKBACKMULTIPLIER = 100;
    public static final Float ATTACKSPEED = 20f;
    public static final Float ATTACKDAMAGE = 1f;
    public static final Float SPEED = 0.1f;

    private static final Skill NONESKILL1 = Skill.builder()
            .action(
                    SkillAction.builder()
                            .useWithSuccess(context -> {
                                context.source().velocityModified = true;
                                context.source().setVelocity(context.source().getRotationVector());
                                context.source().setSkillCooldown(context.skill(), 10);
                            }).build()
            ).build();

    public static void register() {
        SSMRegistry.registerSkill(SKILL1_ID, NONESKILL1);
        SSMRegistry.registerJob(JOBID,
                NONESKILL1, NONESKILL1, NONESKILL1, NONESKILL1,
                MAINHANDITEM
        );
    }
}
