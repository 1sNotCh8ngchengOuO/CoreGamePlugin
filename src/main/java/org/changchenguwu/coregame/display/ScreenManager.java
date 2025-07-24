package org.changchenguwu.coregame.display;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ScreenManager {

    public static List<Screen> allScreens = new ArrayList<>();
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(main.class).getDataFolder(), "display.yml"));
    public static ConfigurationSection screensSection = config.getConfigurationSection("Screen");

    public static void loadScreens() {
        List<Screen> screensTemp = new ArrayList<>();
        Set<String> keys = screensSection.getKeys(false);
        for (String key : keys) {
            ConfigurationSection configurationSection = screensSection.getConfigurationSection(key);
            if (configurationSection == null) {
                return;
            }
            int id = Integer.parseInt(key);

            String type = configurationSection.getString("type");
            if ("Monitor".equals(type)){
                World world = Bukkit.getWorld(Objects.requireNonNull(configurationSection.getString("location.world")));
                int x = configurationSection.getInt("location.x");
                int y = configurationSection.getInt("location.y");
                int z = configurationSection.getInt("location.z");
                Location location = new Location(world, x, y, z);
                String description = configurationSection.getString("description", "No description provided");
                String text = configurationSection.getString("text", "No text provided");
                screensTemp.add(new Monitor(id, location, description, text));
            }
        }
        for (Screen screen : screensTemp) {
            screen.spawn();
        }
        allScreens.addAll(screensTemp);
        screensTemp.clear();
    }

    //clear时记得把allScreens的相同内容清空、删除AdminScreenListener的clear
}
