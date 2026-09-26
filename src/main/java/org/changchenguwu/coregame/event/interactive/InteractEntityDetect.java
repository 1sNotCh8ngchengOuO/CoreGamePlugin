package org.changchenguwu.coregame.event.interactive;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.player.PlayerInteractEvent;
import org.changchenguwu.coregame.screen.ScreenManager;
import org.jetbrains.annotations.NotNull;

public class InteractEntityDetect {

    public static boolean InteractEntityRangeDetect(@NotNull PlayerInteractEvent event , int screenId) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        World world = playerLocation.getWorld();
        if (world == null) {
            return false;
        }
        for (Entity entity : world.getNearbyEntities(playerLocation, 1.1, 1.1, 1)) {
            if (entity instanceof TextDisplay textDisplay
                    && ScreenManager.uuid.containsKey(textDisplay.getUniqueId())
                    && ScreenManager.uuid.get(textDisplay.getUniqueId()) == screenId) {
                return true;
            }
        }
        return false;
    }
}
