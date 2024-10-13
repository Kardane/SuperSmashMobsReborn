package org.karn.supersmashmobs.kit;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import org.gjdd.batoru.skill.Skill;
import org.gjdd.batoru.skill.SkillAction;
import org.karn.supersmashmobs.registry.SSMJobRegistryUtil;
import org.karn.supersmashmobs.registry.SSMRegistries;

public class NoneKit extends AbstractKit {
    public static final EntityType disguiseType = EntityType.DROWNED;
    public static final String JOBID = "none";
    public static final String SKILL1_ID = "none_a";
    public static final String SKILL2_ID = "none_b";
    public static final String SKILL3_ID = "none_c";
    public static final String SKILL4_ID = "none_d";
    public static final ItemStack MAINHANDITEM = Items.IRON_SWORD.getDefaultStack();

    public static final Integer HEALTH = 100;
    public static final Integer HEALTHREGEN = 1;
    public static final Integer KNOCKBACKMULTIPLIER = 100;
    public static final Float ATTACKSPEED = 20F;
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

    public NoneKit() {
        super(disguiseType, JOBID,
                SKILL1_ID, SKILL2_ID, SKILL3_ID, SKILL4_ID,
                MAINHANDITEM,
                HEALTH, HEALTHREGEN, KNOCKBACKMULTIPLIER, ATTACKSPEED, ATTACKDAMAGE, SPEED);
    }

    public static void register() {
        SSMJobRegistryUtil.registerSkill(SKILL1_ID, NONESKILL1);
        SSMJobRegistryUtil.registerJob(JOBID,
                NONESKILL1, NONESKILL1, NONESKILL1, NONESKILL1,
                MAINHANDITEM
        );
        Registry.register(SSMRegistries.KIT, JOBID, new NoneKit());
    }
}
