package org.changchenguwu.coregame.corestate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.main;

import java.io.File;

public class Core {

    private static Location coreLocation;
    private static boolean isStartup = false;
    private static boolean couldStart = false;

    public static boolean isIsStartup() {
        return isStartup;
    }

    public static void setIsStartup(boolean isStartup) {
        Core.isStartup = isStartup;
    }

    public static void initCore() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(main.class).getDataFolder(), "CoreSetting.yml"));
        ConfigurationSection locationSection = config.getConfigurationSection("location");
        if (locationSection != null) {
            World world = Bukkit.getWorld(locationSection.getString("location.world","world"));
            double x = locationSection.getDouble("location.x");
            double y = locationSection.getDouble("location.y");
            double z = locationSection.getDouble("location.z");
            coreLocation = new Location(world,x,y,z);
        }
    }

    public static Location getCoreLocation() {
        return coreLocation;
    }

    public static boolean isCouldStart() {
        return couldStart;
    }

    public static void setCouldStart(boolean couldStart) {
        Core.couldStart = couldStart;
    }

    public static String getCoreStartupInfo() {
        if (!couldStart) {
            return ChatColor.RED + "" + ChatColor.BOLD + ChatColor.ITALIC + "DISABLE";
        }else {
            if (!isStartup){
                return ChatColor.YELLOW + "" + ChatColor.BOLD + ChatColor.ITALIC + "READY";
            }else {
                return ChatColor.GREEN + "" + ChatColor.BOLD + ChatColor.ITALIC + "STARTED";
            }
        }
    }
}
