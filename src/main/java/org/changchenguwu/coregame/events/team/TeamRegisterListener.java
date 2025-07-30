package org.changchenguwu.coregame.events.team;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.changchenguwu.coregame.team.Scientist;
import org.changchenguwu.coregame.team.Security;

public class TeamRegisterListener implements Listener {
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        Scientist.init();
        Security.init();
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        Scientist.uninit();
        Security.uninit();
    }
}
