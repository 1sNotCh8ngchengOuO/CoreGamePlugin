package org.changchenguwu.coregame.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminStackListener implements Listener {
    @EventHandler
    public void onAdminStack(InventoryClickEvent event) {
        if (!"itemstackui".equals(event.getView().getTitle())) {
            return;
        }
        event.setCancelled(true);
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (event.getRawSlot() <= 53) {
            player.getInventory().addItem(event.getCurrentItem());
            player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
        }
    }
}
