package net.countercraft.movecraft.combat.event;

import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;


public class CombatStartEvent extends CombatEvent {
    public CombatStartEvent(@NotNull Player player) {
        super(player);
    }
}