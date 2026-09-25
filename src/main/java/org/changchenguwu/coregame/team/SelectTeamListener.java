package org.changchenguwu.coregame.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scoreboard.Team;
import org.changchenguwu.coregame.team.Engineer;
import org.changchenguwu.coregame.team.Scientist;
import org.changchenguwu.coregame.team.Security;

public class SelectTeamListener implements Listener {

//    @EventHandler
//    public void onCloseInv(InventoryCloseEvent event) {
//        if (event.getPlayer() instanceof Player p
//                && "选择队伍".equals(event.getView().getTitle())
//                && p.getScoreboard().getEntryTeam(p.getName()) == null) {
//            openTeamGui(p);
//        }
//    }

    @EventHandler
    public void onJoinServer(PlayerJoinEvent event) {
        openTeamGui(event.getPlayer());
    }

    @EventHandler
    public void onQuitServer(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Team currentTeam = player.getScoreboard().getEntryTeam(player.getName());
        if (currentTeam != null) {
            currentTeam.removeEntry(player.getName());
        }
    }

    private static void openTeamGui(Player player) {
        if (player.getScoreboard().getEntryTeam(player.getName()) == null) {
            player.openInventory(teamSelectGui());
        }
    }

    public static Inventory teamSelectGui() {
        Inventory inventory = Bukkit.createInventory(null, 9, "选择队伍");

        ItemStack scientificTeam = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta scientificTeamItemMeta = scientificTeam.getItemMeta();
        assert scientificTeamItemMeta != null;
        scientificTeamItemMeta.setDisplayName(ChatColor.AQUA+"科学家");
        LeatherArmorMeta scientificTeamLeatherArmorMeta = (LeatherArmorMeta) scientificTeamItemMeta;
        scientificTeamLeatherArmorMeta.setColor(Color.AQUA);
        scientificTeam.setItemMeta(scientificTeamItemMeta);

        ItemStack medicalTeam = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta medicalTeamItemMeta = medicalTeam.getItemMeta();
        assert medicalTeamItemMeta != null;
        medicalTeamItemMeta.setDisplayName(ChatColor.GOLD+"工程师");
        LeatherArmorMeta medicalTeamLeatherArmorMeta = (LeatherArmorMeta) medicalTeamItemMeta;
        medicalTeamLeatherArmorMeta.setColor(Color.ORANGE);
        medicalTeam.setItemMeta(medicalTeamItemMeta);

        ItemStack securityTeam = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta securityTeamItemMeta = securityTeam.getItemMeta();
        assert securityTeamItemMeta != null;
        securityTeamItemMeta.setDisplayName(ChatColor.GRAY+"安保");
        LeatherArmorMeta securityTeamLeatherArmorMeta = (LeatherArmorMeta) securityTeamItemMeta;
        securityTeamLeatherArmorMeta.setColor(Color.GRAY);
        securityTeam.setItemMeta(securityTeamItemMeta);

        inventory.setItem(3, scientificTeam);
        inventory.setItem(4, medicalTeam);
        inventory.setItem(5, securityTeam);
        return inventory;
    }

    @EventHandler
    public void onSelectTeam(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (player.getScoreboard().getEntryTeam(player.getName()) != null) {
                return;
            }
        }
        if ("选择队伍".equals(event.getView().getTitle())) {
            event.setCancelled(true);
        }
        if (event.getSlot() == 3) {
            Player player = (Player) event.getWhoClicked();
            player.closeInventory();
            player.sendMessage(ChatColor.GREEN+"你选择了科学家队伍");
            Scientist.joinScientistTeam(player);
        }
        if (event.getSlot() == 4) {
            Player player = (Player) event.getWhoClicked();
            player.closeInventory();
            player.sendMessage(ChatColor.GREEN+"你选择了工程师队伍");
            Engineer.joinEngineerTeam(player);
        }
        if (event.getSlot() == 5) {
            Player player = (Player) event.getWhoClicked();
            player.closeInventory();
            player.sendMessage(ChatColor.GREEN+"你选择了安保队伍");
            Security.joinSecurityTeam(player);
        }
    }
}
