package org.changchenguwu.coregame.nametag;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;
import org.changchenguwu.coregame.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class NameTagManager {
    private static final Map<UUID, NameTagData> PLAYER_NAME_TAGS = new HashMap<>();
    private static File nameTagFile;
    private static YamlConfiguration nameTagConfig;

    public static void init() {
        // 初始化配置文件
        nameTagFile = new File(JavaPlugin.getPlugin(main.class).getDataFolder(), "nametags.yml");
        if (!nameTagFile.exists()) {
            JavaPlugin.getPlugin(main.class).saveResource("nametags.yml", false);
        }
        nameTagConfig = YamlConfiguration.loadConfiguration(nameTagFile);
        loadNameTags();
    }

    private static void loadNameTags() {
        PLAYER_NAME_TAGS.clear();
        if (nameTagConfig.contains("players")) {
            for (String uuidStr : Objects.requireNonNull(nameTagConfig.getConfigurationSection("players")).getKeys(false)) {
                UUID uuid = UUID.fromString(uuidStr);
                String prefix = nameTagConfig.getString("players." + uuidStr + ".prefix", "");
                String suffix = nameTagConfig.getString("players." + uuidStr + ".suffix", "");
                PLAYER_NAME_TAGS.put(uuid, new NameTagData(prefix, suffix));
            }
        }
    }

    public static void saveNameTags() {
        // 清除现有配置
        nameTagConfig.set("players", null);
        
        // 保存所有玩家的NameTag数据
        for (Map.Entry<UUID, NameTagData> entry : PLAYER_NAME_TAGS.entrySet()) {
            String uuidStr = entry.getKey().toString();
            NameTagData data = entry.getValue();
            nameTagConfig.set("players." + uuidStr + ".prefix", data.prefix());
            nameTagConfig.set("players." + uuidStr + ".suffix", data.suffix());
        }
        
        try {
            nameTagConfig.save(nameTagFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPlayerNameTag(Player player, String prefix, String suffix) {
        UUID uuid = player.getUniqueId();
        PLAYER_NAME_TAGS.put(uuid, new NameTagData(prefix, suffix));
        saveNameTags();
    }

    public static void resetPlayerNameTag(Player player) {
        UUID uuid = player.getUniqueId();
        PLAYER_NAME_TAGS.remove(uuid);
        saveNameTags();
    }

    public static NameTagData getPlayerNameTag(Player player) {
        return PLAYER_NAME_TAGS.getOrDefault(player.getUniqueId(), new NameTagData("", ""));
    }

    public static void applyNameTag(Player player, Team team) {
        NameTagData nameTagData = getPlayerNameTag(player);
        String teamPrefix = team.getPrefix();

        // 应用前缀和后缀
        if (!nameTagData.prefix().isEmpty()) {
            team.setPrefix(nameTagData.prefix() + " " + teamPrefix);
        }
        
        if (!nameTagData.suffix().isEmpty()) {
            team.setSuffix(" " + nameTagData.suffix());
        }
    }
}