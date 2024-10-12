package org.karn.supersmashmobs.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import org.gjdd.batoru.job.Job;
import org.gjdd.batoru.job.SkillSlot;
import org.gjdd.batoru.registry.BatoruRegistries;
import org.gjdd.batoru.skill.Skill;
import org.karn.supersmashmobs.kit.NoneKit;

import java.util.HashMap;

import static org.karn.supersmashmobs.SuperSmashMobs.MODID;

public class SSMRegistry {

    public static void registerSkill(String id, Skill skill) {
        Registry.register(BatoruRegistries.SKILL, MODID +":" +id, skill);
    }

    public static void registerJob(String id, Skill skill1, Skill skill2, Skill skill3, Skill skill4, ItemStack mainhand) {
        var skillmap = new HashMap<SkillSlot, RegistryEntry<Skill>>();
        skillmap.put(SkillSlot.NORMAL_1, RegistryEntry.of(skill1));
        skillmap.put(SkillSlot.NORMAL_2, RegistryEntry.of(skill2));
        skillmap.put(SkillSlot.NORMAL_3, RegistryEntry.of(skill3));
        skillmap.put(SkillSlot.ULTIMATE, RegistryEntry.of(skill4));

        Job job = Job.builder()
                .skillMap(skillmap)
                .equipStack(EquipmentSlot.MAINHAND, mainhand)
                .build();
        Registry.register(BatoruRegistries.JOB, MODID +":" +id, job);
    }

    public static void registerKits() {
        NoneKit.register();
    }
}
