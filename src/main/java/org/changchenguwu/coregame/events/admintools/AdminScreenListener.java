package org.changchenguwu.coregame.events.admintools;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.changchenguwu.coregame.display.ScreenManager;
import org.changchenguwu.coregame.tools.ScreenTools;


public class AdminScreenListener implements Listener {

    public static int id;

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        ScreenManager.loadScreens();
    }

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
        if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
            return;
        }
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getHand() != EquipmentSlot.HAND) {return;}
            if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(ScreenTools.NEW_SCREEN_KEY, PersistentDataType.BYTE)) {
                event.setCancelled(true);
                ScreenManager.spawnScreen(id);
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(ScreenTools.NEW_SCREEN_KEY, PersistentDataType.BYTE)){
                event.setCancelled(true);
                ScreenManager.unloadScreen(player);
            }
        }
    }

    @EventHandler
    public void onPlayerShiftClickTools(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (!event.getPlayer().isSneaking()) {
                return;
            }
            ItemStack currentItem = event.getPlayer().getInventory().getItemInMainHand();
            if (currentItem.getItemMeta() == null || !currentItem.getItemMeta().getPersistentDataContainer().has(ScreenTools.NEW_SCREEN_KEY, PersistentDataType.BYTE)) {
                return;
            }
            event.getPlayer().closeInventory();
            event.getPlayer().openInventory(ScreenTools.toolsSettingChest());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onToolsSettingChestClick(InventoryClickEvent event) {
        if(!"STC设置".equals(event.getView().getTitle())) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if (event.getRawSlot() == 8) {
            player.openInventory(ScreenTools.toolsSettingChest());
        }
        if (event.getRawSlot() == 0) {
            BlockState blockState = player.getWorld().getBlockAt(-1388, -56, 333).getState();
            Sign sign = (Sign) blockState;
            player.openSign(sign);
        }
        if (event.getRawSlot() == 7) {
            player.performCommand("loadallscreen");
            player.sendMessage("§a荧幕已重载");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
        }
    }

    @EventHandler
    public void onSignEvent(SignChangeEvent event) {
        try {
            if (event.getBlock().getLocation().equals(new Location(event.getBlock().getWorld(), -1388, -56, 333))) {
                event.setCancelled(true);
                String line = event.getLine(0);
                if(line == null || line.isEmpty()) {
                    event.getPlayer().sendMessage("§c荧幕序列号不能为空");
                    return;
                }
                AdminScreenListener.id = Integer.parseInt(line);
                event.getPlayer().sendMessage("§a设置荧幕序列号为: §f" + AdminScreenListener.id);
                event.getPlayer().playSound(event.getPlayer().getLocation(),Sound.BLOCK_ANVIL_USE,1,1);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
