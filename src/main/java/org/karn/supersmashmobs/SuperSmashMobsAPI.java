package org.karn.supersmashmobs;

import org.karn.supersmashmobs.kit.AbstractKit;

public interface SuperSmashMobsAPI {
    AbstractKit getKit();

    void setKit(AbstractKit kit);
}
