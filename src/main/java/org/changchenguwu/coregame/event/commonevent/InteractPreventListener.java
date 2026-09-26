package org.changchenguwu.coregame.event.commonevent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractPreventListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (BreakPreventListener.isBreakPrevent()) {
            event.setCancelled(true);
        }
    }
}
