package org.sam.phantommod;

import net.minecraft.entity.passive.VillagerEntity;

public interface StuffTimerAccess {
    void phantomMod_setTimer(long ticksUntilSomething, VillagerEntity villager);
}
