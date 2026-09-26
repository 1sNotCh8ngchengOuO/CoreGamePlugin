package org.changchenguwu.coregame.event.interactive.coreinteract;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.changchenguwu.coregame.event.coreevent.CoreStartupEvent;
import org.changchenguwu.coregame.event.interactive.InteractEntityDetect;

public class CoreStart implements Listener {
    @EventHandler
    public void startPanelClick(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (InteractEntityDetect.InteractEntityRangeDetect(event, 3)) {
                Bukkit.getPluginManager().callEvent(new CoreStartupEvent(event.getPlayer()));
            }
        }
    }
}
