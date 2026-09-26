package org.changchenguwu.coregame.core;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.changchenguwu.coregame.screen.ScreenManager;
import org.changchenguwu.coregame.effect.PowerOnLightingAnimation;
import org.changchenguwu.coregame.CoreGame;

public class CoreReadyListener implements Listener {

    @EventHandler
    public void readyPanelClick(PlayerInteractEvent event) {
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
                        if (screenId == 4) {
                            if (!Core.isCouldStart()) {

                                PowerOnLightingAnimation.animation(JavaPlugin.getPlugin(CoreGame.class));

                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 1);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 2);
                                    }
                                }.runTaskLater(JavaPlugin.getPlugin(CoreGame.class), 10L);
                            }
                        }
                    }
                }
            });
        }
    }
}
