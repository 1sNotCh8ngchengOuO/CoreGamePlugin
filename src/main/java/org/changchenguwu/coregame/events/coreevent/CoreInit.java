package org.changchenguwu.coregame.events.coreevent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.changchenguwu.coregame.corestate.Core;

public class CoreInit implements Listener {

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        if (Core.isIsStartup()) {
            return;
        }
        Core.initCore();
        Core.initCoreShellBlock();
    }

//    @EventHandler
//    public void onPluginDisable(PluginDisableEvent event) {
//        if (!"CoreGame".equals(event.getPlugin().getName())) {
//            return;
//        }
//        PowerOnLightingAnimation.restoreAnimation(JavaPlugin.getPlugin(main.class));
//    }
}
