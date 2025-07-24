package org.changchenguwu.coregame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveConfig();
        Bukkit.getLogger().info(ChatColor.GREEN+"CoreGame插件已启动");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED+"CoreGame插件已关闭");
    }
}
