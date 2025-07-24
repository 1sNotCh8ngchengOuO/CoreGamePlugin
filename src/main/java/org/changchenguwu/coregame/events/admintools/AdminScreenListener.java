package org.changchenguwu.coregame.events.admintools;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class AdminScreenListener implements Listener {

    @EventHandler
    public void onAdminScreenChestClick(InventoryClickEvent event) {
        if (!"STC".equals(event.getView().getTitle())) {
            return;
        }
        event.setCancelled(true);
        ItemStack currentItem = event.getCurrentItem();
        if (currentItem == null || currentItem.getItemMeta() == null) {
            return;
        }
        ItemStack clone = currentItem.clone();
        if (event.getWhoClicked() instanceof Player player) {
            if (event.getRawSlot() >= 9) {
                return;
            }
            player.getInventory().addItem(clone);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP,1,1);
        }
    }
}
