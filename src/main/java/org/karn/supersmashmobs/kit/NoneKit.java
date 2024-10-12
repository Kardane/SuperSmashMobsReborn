package org.karn.supersmashmobs.kit;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.gjdd.batoru.skill.Skill;
import org.gjdd.batoru.skill.SkillAction;
import org.karn.supersmashmobs.util.SSMRegistry;

public class NoneKit {
    public static final EntityType DISGUISEMOB = EntityType.DROWNED;
    public static final String JOBID = "none";
    public static final String NAME = "None";
    public static final String SKILL1_ID = "noneskilla";
    public static final String SKILL2_ID = "noneskillb";
    public static final String SKILL3_ID = "noneskillc";
    public static final String SKILL4_ID = "noneskilld";
    public static final ItemStack MAINHANDITEM = Items.IRON_SWORD.getDefaultStack();
    public static final ItemStack OFFHABDITEM = Items.AIR.getDefaultStack();
    public static final ItemStack HEADITEM = Items.AIR.getDefaultStack();
    public static final ItemStack CHESTITEM = Items.AIR.getDefaultStack();
    public static final ItemStack LEGSITEM = Items.AIR.getDefaultStack();
    public static final ItemStack FEETITEM = Items.AIR.getDefaultStack();

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
                MAINHANDITEM, OFFHABDITEM, HEADITEM, CHESTITEM, LEGSITEM, FEETITEM
        );
    }
}
