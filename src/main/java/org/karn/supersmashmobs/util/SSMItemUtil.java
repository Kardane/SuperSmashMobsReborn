package org.karn.supersmashmobs.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import org.gjdd.batoru.component.BatoruDataComponentTypes;
import org.gjdd.batoru.job.Job;

public class SSMItemUtil {
    public static ItemStack setBaseComponent(ItemStack item, Job job) {
        item.set(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(false));
        item.set(DataComponentTypes.MAX_STACK_SIZE, 1);
        item.set(BatoruDataComponentTypes.USABLE_JOB, RegistryEntry.of(job));
        return item;
    }
}
