package org.changchenguwu.coregame.events.admintools;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.changchenguwu.coregame.tools.ScreenTools;

import java.util.List;


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

    @EventHandler
    public void onNewScreenClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getHand() != EquipmentSlot.HAND) {return;}
        if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(ScreenTools.NEW_SCREEN_KEY, PersistentDataType.BYTE)) {
                Location loc = event.getClickedBlock().getLocation();
                if (loc.getWorld() == null){return;}
                loc.getWorld().spawn(loc, TextDisplay.class, entity -> {
                    entity.setTextOpacity((byte)0);
                    entity.setText("Hello, World!");
                    entity.setBillboard(Display.Billboard.FIXED);
                    entity.setShadowed(false);
                });
            }
        } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(ScreenTools.NEW_SCREEN_KEY, PersistentDataType.BYTE)){
                event.setCancelled(true);
                List<Entity> nearbyEntities = player.getNearbyEntities(0.5, 0.5, 0.5);
                for (Entity nearbyEntity : nearbyEntities) {
                    if (nearbyEntity instanceof TextDisplay textDisplay) {
                        textDisplay.remove();
                        player.sendMessage("删除了一个荧幕");
                    }
                }
            }
        }
    }
}
