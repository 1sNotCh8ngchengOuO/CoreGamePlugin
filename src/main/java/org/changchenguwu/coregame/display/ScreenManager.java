package org.changchenguwu.coregame.display;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.main;

import java.io.File;
import java.util.*;

public class ScreenManager {

    public static Map<Integer,Screen> allScreens = new HashMap<>();
    public static Map<UUID,Integer> uuid = new HashMap<>();
    public static Map<Integer,String> specialText = new HashMap<>();

    public static void loadScreens() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(main.class).getDataFolder(), "display.yml"));
        ConfigurationSection screensSection = config.getConfigurationSection("Screen");
        if (screensSection == null) {
            return;
        }
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
                double x = configurationSection.getDouble("location.x");
                double y = configurationSection.getDouble("location.y");
                double z = configurationSection.getDouble("location.z");
                Location location = new Location(world, x, y, z);
                String description = configurationSection.getString("description", "No description provided");

                String text = configurationSection.getString("text", "No text provided");
                List<String> textList = configurationSection.getStringList("textList");
                if (textList.isEmpty()) {
                    textList = Collections.singletonList(text);
                }

                String specialIndex = configurationSection.getString("special");
                assert specialIndex != null;
                String[] parts = specialIndex.split(",");
                int[] index = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    index[i] = Integer.parseInt(parts[i].trim());
                }

                String strategy = configurationSection.getString("switchStrategy", "ROUND_ROBIN");
                int interval = configurationSection.getInt("switchInterval", 100);
                int pitch = configurationSection.getInt("pitch", 0);
                int yaw = configurationSection.getInt("yaw", 0);
                String billboard = configurationSection.getString("billboard", "FIXED");
                Monitor monitor = new Monitor(id, location, description, text, pitch, yaw,billboard, strategy, interval,textList,index);
                allScreens.put(id,monitor);
            }//else if (info...)
        }
    }

    public static void loadSpecialText() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(main.class).getDataFolder(), "displayText.yml"));
        Set<String> keys = config.getKeys(false);
        for (String key : keys) {
            ConfigurationSection section = config.getConfigurationSection(key);
            if (section == null) {
                continue;
            }
            int id = Integer.parseInt(key);
            String text = section.getString("text", "");
            specialText.put(id, text);
        }
    }

    public static void unloadScreen(Player player){
        List<Entity> nearbyEntities = player.getNearbyEntities(0.5, 0.5, 0.5);
        for (Entity nearbyEntity : nearbyEntities) {
            if (nearbyEntity instanceof TextDisplay textDisplay) {
                if (uuid.containsKey(textDisplay.getUniqueId())) {
                    int id = uuid.get(textDisplay.getUniqueId());
                    player.sendMessage("卸载了荧幕 "+id);
                    allScreens.remove(id);
                    uuid.remove(textDisplay.getUniqueId());
                    textDisplay.remove();
                } else {
                    textDisplay.remove();
                    player.sendMessage("移除了一个非本插件（未被注册）的荧幕: " + textDisplay.getUniqueId());
                }
            }
        }
    }

    public static void spawnScreen(int id) {
        if (!allScreens.containsKey(id)) {
            Bukkit.getLogger().warning("尝试生成不存在的荧幕: " + id);
            return;
        }
        TextDisplay spawn = allScreens.get(id).spawn();
        uuid.put(spawn.getUniqueId(),id);
    }

    public static void updateScreen(int id, CommandSender sender,String text,List<String> textList) {
        if (!allScreens.containsKey(id)) {
            sender.sendMessage("尝试更新不存在的荧幕: " + id);
            return;
        }
        allScreens.get(id).update(text,textList);
        sender.sendMessage("荧幕 " + id + " 已更新" );
    }
}
