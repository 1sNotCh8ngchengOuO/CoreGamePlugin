package org.changchenguwu.coregame.event.commonevent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakPreventListener implements Listener {
    private static boolean breakPrevent = false;//TODO:默认为true,暂时不防止

    public static void setBreakPrevent(boolean breakPrevent) {
        BreakPreventListener.breakPrevent = breakPrevent;
    }

    public static boolean isBreakPrevent() {
        return breakPrevent;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(breakPrevent);
    }
}
