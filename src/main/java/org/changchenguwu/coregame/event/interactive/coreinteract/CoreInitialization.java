package org.changchenguwu.coregame.event.interactive.coreinteract;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.changchenguwu.coregame.CoreGame;
import org.changchenguwu.coregame.core.Core;
import org.changchenguwu.coregame.effect.PowerOnLightingAnimation;
import org.changchenguwu.coregame.event.interactive.InteractEntityDetect;

public class CoreInitialization implements Listener {

    @EventHandler
    public void readyPanelClick(PlayerInteractEvent event) {
        if(event.getHand() == EquipmentSlot.HAND) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(InteractEntityDetect.InteractEntityRangeDetect(event, 4)) {
                if (!Core.isCouldStart()) {

                    PowerOnLightingAnimation.animation(JavaPlugin.getPlugin(CoreGame.class));
                    Player player = event.getPlayer();
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
}
