package org.changchenguwu.coregame.events.coreevent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.changchenguwu.coregame.corestate.Core;
import org.changchenguwu.coregame.events.myevents.CoreStartupEvent;

public class CoreStartupListener implements Listener {

    @EventHandler
    public void onCoreStartup(CoreStartupEvent event) {
        if (Core.isIsStartup()) {
            return;
        }
        Core.setIsStartup(true);
        Bukkit.getServer().broadcastMessage(ChatColor.GREEN+"Core Startup!");
    }
}
