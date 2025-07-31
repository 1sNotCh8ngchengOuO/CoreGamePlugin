package org.changchenguwu.coregame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.commands.*;
import org.changchenguwu.coregame.display.PluginEventForScreenListener;
import org.changchenguwu.coregame.events.admintools.AdminScreenListener;
import org.changchenguwu.coregame.events.coreevent.CoreInit;
import org.changchenguwu.coregame.events.coreevent.CoreStartupListener;
import org.changchenguwu.coregame.events.team.TeamRegisterListener;
import org.changchenguwu.coregame.events.interactive.TextDisplayInteractionListener;
import org.changchenguwu.coregame.variable.Placeholder;

import java.util.Objects;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveResource("config.yml",false);
        saveResource("display.yml", false);
        saveResource("displayText.yml", false);
        saveResource("CoreSetting.yml", false);
        saveResource("InteractiveBlock.yml", false);

        Objects.requireNonNull(Bukkit.getPluginCommand("stools")).setExecutor(new GetScreenToolsCommand());
        Bukkit.getPluginManager().registerEvents(new AdminScreenListener(),this);
        Objects.requireNonNull(Bukkit.getPluginCommand("loadallscreen")).setExecutor(new LoadAllScreenCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("us")).setExecutor(new UpdateDisplayTestCommand());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder().register();
        }
        Objects.requireNonNull(Bukkit.getPluginCommand("changeteam")).setExecutor(new ChangeTeamCommand());
        Bukkit.getPluginManager().registerEvents(new TeamRegisterListener(),this);
        Bukkit.getPluginManager().registerEvents(new CoreStartupListener(),this);
        Bukkit.getPluginManager().registerEvents(new CoreInit(),this);
        Objects.requireNonNull(Bukkit.getPluginCommand("startupcore")).setExecutor(new CoreStartupCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("coretemp")).setExecutor(new AdjustCoreTempCommand());
        Bukkit.getPluginManager().registerEvents(new PluginEventForScreenListener(),this);
        Bukkit.getPluginManager().registerEvents(new TextDisplayInteractionListener(),this);

        Bukkit.getLogger().info(ChatColor.GREEN+"CoreGame插件已启动");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);

        Bukkit.getLogger().info(ChatColor.RED+"CoreGame插件已关闭");
    }
}
