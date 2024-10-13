package org.karn.supersmashmobs.kit;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Util;
import org.gjdd.batoru.job.Job;
import org.jetbrains.annotations.Nullable;
import org.karn.supersmashmobs.registry.SSMRegistries;

public abstract class AbstractKit {
    public EntityType disguiseType;
    public String jobID;
    public String skill1ID;
    public String skill2ID;
    public String skill3ID;
    public String skill4ID;
    public ItemStack mainhandItem;

    public Integer Health;
    public Integer HealthRegen;
    public Integer KnockbackMultiplier;
    public Float AttackSpeed;
    public Float AttackDamage;
    public Float Speed;

    private @Nullable String translationKey;

    public AbstractKit(EntityType disguiseType, String jobID,
                       String skill1ID, String skill2ID, String skill3ID, String skill4ID,
                       ItemStack mainhandItem,
                       Integer health, Integer healthRegen, Integer knockbackMultiplier,
                       Float attackSpeed, Float attackDamage, Float speed) {
        this.disguiseType = disguiseType;
        this.jobID = jobID;
        this.skill1ID = skill1ID;
        this.skill2ID = skill2ID;
        this.skill3ID = skill3ID;
        this.skill4ID = skill4ID;
        this.mainhandItem = mainhandItem;
        this.Health = health;
        this.HealthRegen = healthRegen;
        this.KnockbackMultiplier = knockbackMultiplier;
        this.AttackSpeed = attackSpeed;
        this.AttackDamage = attackDamage;
        this.Speed = speed;
    }

    public String getTranslationKey() {
        if (translationKey == null)
            translationKey = Util.createTranslationKey("kit", SSMRegistries.KIT.getId(this));
        return translationKey;
    }
}
