package org.changchenguwu.coregame.corestate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.main;

import java.io.File;

public class Core {

    private static Location coreLocation;
    private static boolean isStartup = false;
    private static boolean couldStart = false;
    private static Block[] coreShellBlock;

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
            World world = Bukkit.getWorld(locationSection.getString("world","world"));
            double x = locationSection.getDouble("x");
            double y = locationSection.getDouble("y");
            double z = locationSection.getDouble("z");
            coreLocation = new Location(world,x,y,z);
            Bukkit.getServer().broadcastMessage(coreLocation.toString());
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

    public static void initCoreShellBlock() {
        Block[] block = new Block[14];
        block[0] = coreLocation.getBlock().getRelative(1, 0, 0);
        block[1] = coreLocation.getBlock().getRelative(1, 1, 0);
        block[2] = coreLocation.getBlock().getRelative(1, -1, 0);

        block[3] = coreLocation.getBlock().getRelative(-1, 0, 0);
        block[4] = coreLocation.getBlock().getRelative(-1, 1, 0);
        block[5] = coreLocation.getBlock().getRelative(-1, -1, 0);

        block[6] = coreLocation.getBlock().getRelative(0, 0, 1);
        block[7] = coreLocation.getBlock().getRelative(0, 1, 1);
        block[8] = coreLocation.getBlock().getRelative(0, -1, 1);

        block[9] = coreLocation.getBlock().getRelative(0, 0, -1);
        block[10] = coreLocation.getBlock().getRelative(0, 1, -1);
        block[11] = coreLocation.getBlock().getRelative(0, -1, -1);

        block[12] = coreLocation.getBlock().getRelative(0, 2, 0);
        block[13] = coreLocation.getBlock().getRelative(0, -2, 0);

        coreShellBlock = block;
    }
}