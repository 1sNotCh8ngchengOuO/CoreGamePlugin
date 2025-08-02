package org.changchenguwu.coregame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.commands.*;
import org.changchenguwu.coregame.corestate.Core;
import org.changchenguwu.coregame.display.PluginEventForScreen;
import org.changchenguwu.coregame.display.Screen;
import org.changchenguwu.coregame.display.ScreenManager;
import org.changchenguwu.coregame.effects.CoreShellChangeListener;
import org.changchenguwu.coregame.effects.PowerOnLightingAnimation;
import org.changchenguwu.coregame.events.admintools.AdminScreenListener;
import org.changchenguwu.coregame.events.coreevent.CoreReadyListener;
import org.changchenguwu.coregame.events.coreevent.CoreStartupListener;
import org.changchenguwu.coregame.events.team.SelectTeamListener;
import org.changchenguwu.coregame.events.team.TeamRegisterListener;
import org.changchenguwu.coregame.nametag.NameTagManager;
import org.changchenguwu.coregame.variable.CoreTemperature;
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
        saveResource("message.yml", false);
        saveResource("nametags.yml", false);
        
        // 初始化NameTagManager
        NameTagManager.init();

        Core.initCore();
        Core.initCoreShellBlock();
        CoreTemperature.init();
        PowerOnLightingAnimation.init();
        ScreenManager.loadScreens();
        ScreenManager.loadSpecialText();
        PluginEventForScreen.spawnAllScreens();

        Objects.requireNonNull(Bukkit.getPluginCommand("stools")).setExecutor(new GetScreenToolsCommand());
        Bukkit.getPluginManager().registerEvents(new AdminScreenListener(),this);
        Objects.requireNonNull(Bukkit.getPluginCommand("loadallscreen")).setExecutor(new LoadAllScreenCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("us")).setExecutor(new UpdateDisplayTestCommand());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder().register();
        }
        Objects.requireNonNull(Bukkit.getPluginCommand("changeteam")).setExecutor(new ChangeTeamCommand());
        Bukkit.getPluginManager().registerEvents(new TeamRegisterListener(),this);
        Bukkit.getPluginManager().registerEvents(new SelectTeamListener(),this);
        Bukkit.getPluginManager().registerEvents(new CoreStartupListener(),this);

        Objects.requireNonNull(Bukkit.getPluginCommand("startupcore")).setExecutor(new CoreStartupCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("coretemp")).setExecutor(new AdjustCoreTempCommand());
        Bukkit.getPluginManager().registerEvents(new SelectTeamListener() , this);
        Bukkit.getPluginManager().registerEvents(new CoreReadyListener(),this);
        Objects.requireNonNull(Bukkit.getPluginCommand("screenshake")).setExecutor(new ScreenShakeCommand(this));
        Objects.requireNonNull(Bukkit.getPluginCommand("restorelightanimation")).setExecutor(new RestoreLightAnimationCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("lightanimation")).setExecutor(new LightAnimationCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("debugsound")).setExecutor(new PlaySoundCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("setnametag")).setExecutor(new SetNameTagCommand());
        Bukkit.getPluginManager().registerEvents(new CoreShellChangeListener(),this);

        Bukkit.getLogger().info(ChatColor.GREEN+"CoreGame插件已启动");
    }

    @Override
    public void onDisable() {
        ScreenManager.allScreens.values().forEach(Screen::remove);
        Bukkit.getScheduler().cancelTasks(this);

        Bukkit.getLogger().info(ChatColor.RED+"CoreGame插件已关闭");
    }
}
