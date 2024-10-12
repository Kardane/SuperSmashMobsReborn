package org.karn.supersmashmobs.kit;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;

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

    public EntityType getDisguiseMob(){return disguiseType;};
    public String getJobID(){return jobID;};
    public String getSkill1ID(){return skill1ID;};
    public String getSkill2ID(){return skill2ID;};
    public String getSkill3ID(){return skill3ID;};
    public String getSkill4ID(){return skill4ID;};
    public ItemStack getMainhandItem(){return mainhandItem;}

    public Integer getHealth(){return Health;};
    public Integer getHealthRegen(){return HealthRegen;};
    public Integer getKnockbackMultiplier(){return KnockbackMultiplier;};
    public Float getAttackSpeed(){return AttackSpeed;};
    public Float getAttackDamage(){return AttackDamage;};
    public Float getSpeed(){return Speed;};

}
