package org.changchenguwu.coregame.display;

import org.bukkit.entity.TextDisplay;

public class PluginEventForScreen {

    public static void spawnAllScreens() {
        ScreenManager.allScreens.values().stream()
                .filter(screen -> screen instanceof Monitor)
                .filter(monitor -> ((Monitor) monitor).isState())
                .forEach(screen -> {
                    TextDisplay spawnedDisplay = screen.spawn();
                    ScreenManager.uuid.put(spawnedDisplay.getUniqueId(), screen.getId());
                });
    }
}