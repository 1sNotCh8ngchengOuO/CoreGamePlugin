package org.changchenguwu.coregame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.commands.GetScreenToolsCommand;
import org.changchenguwu.coregame.commands.LoadAllScreenCommand;
import org.changchenguwu.coregame.commands.UpdateDisplayTestCommand;
import org.changchenguwu.coregame.events.admintools.AdminScreenListener;

import java.util.Objects;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveConfig();
        saveResource("display.yml", false);
        Objects.requireNonNull(Bukkit.getPluginCommand("stools")).setExecutor(new GetScreenToolsCommand());
        Bukkit.getPluginManager().registerEvents(new AdminScreenListener(),this);
        Bukkit.getLogger().info(ChatColor.GREEN+"CoreGame插件已启动");
        Objects.requireNonNull(Bukkit.getPluginCommand("loadallscreen")).setExecutor(new LoadAllScreenCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("us")).setExecutor(new UpdateDisplayTestCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED+"CoreGame插件已关闭");
    }
}
