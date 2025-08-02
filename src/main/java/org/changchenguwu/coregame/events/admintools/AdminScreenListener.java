package org.changchenguwu.coregame.events.admintools;

import de.rapha149.signgui.SignGUI;
import de.rapha149.signgui.exception.SignGUIVersionException;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.changchenguwu.coregame.display.ScreenManager;
import org.changchenguwu.coregame.tools.ScreenTools;

import java.util.Objects;


public class AdminScreenListener implements Listener {

    public static int id;

    @EventHandler
    public void onBlockInfoCheckClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(ScreenTools.GET_BLOCK_INFO, PersistentDataType.BYTE)) {
                return;
            }
            event.setCancelled(true);
            Location location = block.getLocation().clone();
            String locationM = "\""+ Objects.requireNonNull(location.getWorld()).getName() +"\"" +","+ location.getX() +","+ location.getY()+","+location.getZ();
            BaseComponent message = new TextComponent("§a方块信息:\n"+locationM);
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text(locationM)));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, locationM));
            player.spigot().sendMessage(message);
        }
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
            try {
                SignGUI sign = SignGUI.builder().setColor(DyeColor.BLACK).setType(Material.BAMBOO_SIGN).setHandler((p,result) -> {
                    String line = result.getLine(0).replaceAll("[^0-9]", "");
                    if(line.isEmpty()) {
                        p.sendMessage("§c荧幕序列号不能为空");
                        return null;
                    }
                    AdminScreenListener.id = Integer.parseInt(line);
                    p.sendMessage("§a设置荧幕序列号为: §f" + AdminScreenListener.id);
                    p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_USE,1,1);
                    return null;
                }).build();
                sign.open(player);
            } catch (SignGUIVersionException e) {
                player.sendMessage(ChatColor.RED+"出错了！"+ e);
            }
        }
        if (event.getRawSlot() == 7) {
            player.performCommand("loadallscreen");
            player.sendMessage("§a荧幕已重载");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
        }
    }
}
