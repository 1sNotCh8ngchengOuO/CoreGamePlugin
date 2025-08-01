package org.changchenguwu.coregame.variable;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.corestate.TemperatureStatus;
import org.changchenguwu.coregame.events.myevents.temperature.*;
import org.changchenguwu.coregame.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CoreTemperature {
    private static int coreTemperature = 30;
    public static int heatUpTemp;
    private static final Map<TemperatureStatus, Integer> TEMPERATURE_THRESHOLDS = new HashMap<>();
    private static TemperatureStatus lastTemperatureStatus = null;

    public static void init() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(main.class).getDataFolder(), "CoreSetting.yml"));
        ConfigurationSection statusRangeSection = config.getConfigurationSection("CoreStatusRange");
        if (statusRangeSection != null) {
            TEMPERATURE_THRESHOLDS.put(TemperatureStatus.FREEZE, statusRangeSection.getInt("freeze"));
            TEMPERATURE_THRESHOLDS.put(TemperatureStatus.SUPERCOOL, statusRangeSection.getInt("supercool"));
            TEMPERATURE_THRESHOLDS.put(TemperatureStatus.NORMAL, statusRangeSection.getInt("normal"));
            TEMPERATURE_THRESHOLDS.put(TemperatureStatus.OVERHEAT, statusRangeSection.getInt("overheat"));
            TEMPERATURE_THRESHOLDS.put(TemperatureStatus.CRITICAL, statusRangeSection.getInt("critical"));
        }
    }

    public static void addTemperature(int temperature) {
        coreTemperature += temperature;
    }

    public static void reduceTemperature(int temperature) {
        coreTemperature -= temperature;
    }

    public static String getCoreTemperatureFormat() {
        return coreTemperature + "°C";
    }

    public static int getCoreTemperature() {
        return coreTemperature;
    }

    public static TemperatureStatus getTemperatureStatusAndCallEvent() {
        TemperatureStatus currentStatus;
        if (coreTemperature <= TEMPERATURE_THRESHOLDS.getOrDefault(TemperatureStatus.FREEZE, -273)) {
            currentStatus = TemperatureStatus.FREEZE;
        } else if (coreTemperature <= TEMPERATURE_THRESHOLDS.getOrDefault(TemperatureStatus.SUPERCOOL, 0)) {
            currentStatus = TemperatureStatus.SUPERCOOL;
        } else if (coreTemperature <= TEMPERATURE_THRESHOLDS.getOrDefault(TemperatureStatus.NORMAL, 3000)) {
            currentStatus = TemperatureStatus.NORMAL;
        } else if (coreTemperature <= TEMPERATURE_THRESHOLDS.getOrDefault(TemperatureStatus.OVERHEAT, 6000)) {
            currentStatus = TemperatureStatus.OVERHEAT;
        } else if (coreTemperature <= TEMPERATURE_THRESHOLDS.getOrDefault(TemperatureStatus.CRITICAL, 10000)) {
            currentStatus = TemperatureStatus.CRITICAL;
        } else {
            currentStatus = TemperatureStatus.MELTDOWN;
        }

        if (currentStatus != lastTemperatureStatus) {
            switch (currentStatus) {
                case FREEZE -> Bukkit.getPluginManager().callEvent(new TemperatureFreezeEvent());
                case SUPERCOOL -> Bukkit.getPluginManager().callEvent(new TemperatureSupercoolEvent());
                case NORMAL -> Bukkit.getPluginManager().callEvent(new TemperatureNormalEvent());
                case OVERHEAT -> Bukkit.getPluginManager().callEvent(new TemperatureOverheatEvent());
                case CRITICAL -> Bukkit.getPluginManager().callEvent(new TemperatureCriticalEvent());
                case MELTDOWN -> Bukkit.getPluginManager().callEvent(new TemperatureMeltdownEvent());
            }
            lastTemperatureStatus = currentStatus;
        }
        return currentStatus;
    }
}
