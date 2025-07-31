package org.changchenguwu.coregame.events.interactive;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Player;
import org.changchenguwu.coregame.display.ScreenManager;

public class TextDisplayInteractionListener implements Listener {

    @EventHandler
    public void onPlayerInteractAtTextDisplay(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof TextDisplay textDisplay) {
            Player player = event.getPlayer();
            
            if (ScreenManager.uuid.containsKey(textDisplay.getUniqueId())) {
                int screenId = ScreenManager.uuid.get(textDisplay.getUniqueId());
                player.sendMessage("你与ID为 " + screenId + " 的TextDisplay进行了交互！");

            }
        }
    }
}