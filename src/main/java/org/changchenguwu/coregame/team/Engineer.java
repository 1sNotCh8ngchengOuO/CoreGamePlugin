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
        Scoreboard scoreboard = player.getScoreboard();
        Team currentTeam = scoreboard.getEntryTeam(player.getName());
        if (currentTeam != null) {
            currentTeam.removeEntry(player.getName());
        }
        team.addEntry(player.getName());
    }
}