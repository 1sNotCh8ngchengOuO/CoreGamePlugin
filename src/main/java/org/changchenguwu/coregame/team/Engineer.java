package org.changchenguwu.coregame.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class Engineer {
    static Team team;

    public static void init() {
        Scoreboard mainScoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        team = mainScoreboard.getTeam("engineer");
        if (team == null) {
            team = mainScoreboard.registerNewTeam("engineer");
            team.setPrefix(ChatColor.GOLD + "[工程师]");
            team.setColor(ChatColor.GOLD);
            team.setAllowFriendlyFire(false);
        }
    }

    public static void uninit() {
        if (team != null) {
            team.unregister();
            team = null;
        }
    }

    public static void joinEngineerTeam(Player player) {
        // 使用TeamUtils添加玩家到团队并应用自定义NameTag
        TeamUtils.addPlayerToTeam(player, team, ChatColor.GOLD + "[工程师]");
    }
}