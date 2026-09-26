package org.changchenguwu.coregame.effect;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.changchenguwu.coregame.core.Core;
import org.changchenguwu.coregame.event.CoreShutdownEvent;
import org.changchenguwu.coregame.event.temperature.*;

public class CoreShellChangeListener implements Listener {

    @EventHandler
    public void onNormal(TemperatureNormalEvent event) {
        Block[] coreShellBlock = Core.getCoreShellBlock();
        if (coreShellBlock == null) {
            return;
        }
        if (!Core.isIsStartup()) {
            return;
        }
        for (Block block : coreShellBlock) {
            block.setType(Material.WHITE_STAINED_GLASS);
        }
    }

    @EventHandler
    public void onOverHeat(TemperatureOverheatEvent event) {
        Block[] coreShellBlock = Core.getCoreShellBlock();
        if (coreShellBlock == null) {
            return;
        }
        for (Block block : coreShellBlock) {
            block.setType(Material.YELLOW_STAINED_GLASS);
        }
    }

    @EventHandler
    public void onCritical(TemperatureCriticalEvent event) {
        Block[] coreShellBlock = Core.getCoreShellBlock();
        if (coreShellBlock == null) {
            return;
        }
        for (Block block : coreShellBlock) {
            block.setType(Material.ORANGE_STAINED_GLASS);
        }
    }

    @EventHandler
    public void onMeltDown(TemperatureMeltdownEvent event) {
        Block[] coreShellBlock = Core.getCoreShellBlock();
        if (coreShellBlock == null) {
            return;
        }
        for (Block block : coreShellBlock) {
            block.setType(Material.RED_STAINED_GLASS);
        }
    }

    @EventHandler
    public void onSuperCool(TemperatureSupercoolEvent event) {
        Block[] coreShellBlock = Core.getCoreShellBlock();
        if (coreShellBlock == null) {
            return;
        }
        for (Block block : coreShellBlock) {
            block.setType(Material.CYAN_STAINED_GLASS);
        }
    }

    @EventHandler
    public void onFreeze(TemperatureFreezeEvent event) {
        Block[] coreShellBlock = Core.getCoreShellBlock();
        if (coreShellBlock == null) {
            return;
        }
        for (Block block : coreShellBlock) {
            block.setType(Material.BLUE_STAINED_GLASS);
        }
    }

    @EventHandler
    public void onShutdown(CoreShutdownEvent event) {
        Block[] coreShellBlock = Core.getCoreShellBlock();
        if (coreShellBlock == null) {
            return;
        }
        for (Block block : coreShellBlock) {
            block.setType(Material.TINTED_GLASS);
        }
    }
}
