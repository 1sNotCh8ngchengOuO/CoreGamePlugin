package org.changchenguwu.coregame.display;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

public class PluginEventForScreenListener implements Listener {

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        ScreenManager.allScreens.values().forEach(Screen::remove);
    }
}
