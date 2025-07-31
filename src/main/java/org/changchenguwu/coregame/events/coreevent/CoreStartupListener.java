package org.changchenguwu.coregame.events.coreevent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.changchenguwu.coregame.corestate.Core;
import org.changchenguwu.coregame.display.ScreenManager;
import org.changchenguwu.coregame.events.myevents.CoreStartupEvent;
import org.changchenguwu.coregame.main;

import java.io.File;
import java.util.Objects;

public class CoreStartupListener implements Listener {


    @EventHandler
    public void startupCore(PlayerInteractEvent event) {
        if(event.getHand() == EquipmentSlot.HAND) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            Location playerLocation = player.getLocation();
            World world = playerLocation.getWorld();
            if(world == null) {
                return;
            }
            world.getNearbyEntities(playerLocation, 1.1, 1.1, 1).forEach(entity -> {
                if (entity instanceof TextDisplay textDisplay) {
                    if (ScreenManager.uuid.containsKey(textDisplay.getUniqueId())) {
                        int screenId = ScreenManager.uuid.get(textDisplay.getUniqueId());
                        if (screenId == 3) {
                            Bukkit.getPluginManager().callEvent(new CoreStartupEvent(player));
                        }
                    }
                }
            });
        }
    }

    @EventHandler
    public void onCoreStartup(CoreStartupEvent event) {
        Player player = event.getPlayer();
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(main.class).getDataFolder(),"message.yml"));
        if (!Core.isCouldStart()) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            player.sendMessage(Objects.requireNonNull(config.getString("core_could_not_start")));
            return;
        }
        if (Core.isIsStartup()) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            player.sendMessage(Objects.requireNonNull(config.getString("core_already_start")));
            return;
        }
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 1);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 2);
            }
        }.runTaskLater(JavaPlugin.getPlugin(main.class), 10L);
        player.sendMessage(Objects.requireNonNull(config.getString("core_start_success")));
        Core.setIsStartup(true);
    }
}
