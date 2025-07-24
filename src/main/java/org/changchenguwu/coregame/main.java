package org.changchenguwu.coregame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.commands.ScreenTest;
import org.changchenguwu.coregame.events.admintools.AdminScreenListener;

import java.util.Objects;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveConfig();
        Objects.requireNonNull(Bukkit.getPluginCommand("stools")).setExecutor(new ScreenTest());
        Bukkit.getPluginManager().registerEvents(new AdminScreenListener(),this);
        Bukkit.getLogger().info(ChatColor.GREEN+"CoreGame插件已启动");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED+"CoreGame插件已关闭");
    }
}
