package org.changchenguwu.coregame.display;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class PluginEventForScreenListener implements Listener {

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        ScreenManager.allScreens.values().stream()
            .filter(screen -> screen instanceof Monitor)
            .filter(monitor -> ((Monitor) monitor).isState())
            .forEach(Screen::spawn);
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        ScreenManager.allScreens.values().forEach(Screen::remove);
    }
}
