package org.changchenguwu.coregame.core;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.changchenguwu.coregame.CoreGame;
import org.changchenguwu.coregame.event.coreevent.CoreStartupEvent;
import org.changchenguwu.coregame.event.temperature.TemperatureNormalEvent;

import java.io.File;
import java.util.Objects;
import java.util.Random;

public class CoreStartupListener implements Listener {

    private static int refreshRate;
    private static int min;
    private static int max;


    @EventHandler
    public void onCoreStartup(CoreStartupEvent event) {
        Player player = event.getPlayer();

        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(CoreGame.class).getDataFolder(),"message.yml"));
        if (!Core.isCouldStart()) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            player.sendMessage(Objects.requireNonNull(config.getString("core_could_not_start")));
            return;
        }
        if (Core.isIsStartup()) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            player.sendMessage(Objects.requireNonNull(config.getString("core_already_start")));
            return;
        }
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 1);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 2);
            }
        }.runTaskLater(JavaPlugin.getPlugin(CoreGame.class), 10L);
        player.sendMessage(Objects.requireNonNull(config.getString("core_start_success")));
        Core.setIsStartup(true);



        new BukkitRunnable() {
            @Override
            public void run() {
                calculateTemp();
            }
        }.runTaskTimer(JavaPlugin.getPlugin(CoreGame.class), 10L,refreshRate);
        Bukkit.getPluginManager().callEvent(new TemperatureNormalEvent());
    }

    @EventHandler
    public void randomTemp(CoreStartupEvent event) {
        Random random = new Random();

        new BukkitRunnable() {
            @Override
            public void run() {
                CoreTemperature.heatUpTemp = random.nextInt(max - min + 1) + min;
                //TODO:未来添加更多温度变化逻辑
            }
        }.runTaskTimer(JavaPlugin.getPlugin(CoreGame.class), 20L, 10L);
    }

    public static void initTempMaxMin() {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(CoreGame.class).getDataFolder(), "CoreSetting.yml"));
        ConfigurationSection configurationSection = configuration.getConfigurationSection("CoreTemperatureAddition");
        if (configurationSection != null) {
            min = configurationSection.getInt("min",5);
            max = configurationSection.getInt("max", 300);
            refreshRate = Objects.requireNonNull(configuration.getConfigurationSection("CoreTemperatureRefreshRate")).getInt("rate");
        }
    }

    public static void calculateTemp(){
        CoreTemperature.addTemperature(CoreTemperature.heatUpTemp);
    }
}
