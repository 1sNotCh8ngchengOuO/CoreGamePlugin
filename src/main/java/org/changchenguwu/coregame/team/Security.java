package org.changchenguwu.coregame.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class Security{
    static Team team;

    public static void uninit() {
        if (team != null) {
            team.unregister();
            team = null;
        }
    }

    public static void init() {
        Scoreboard mainScoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        team = mainScoreboard.getTeam("security");
        if (team == null) {
            team = mainScoreboard.registerNewTeam("security");
            team.setPrefix(ChatColor.DARK_GRAY + "[安保]");
            team.setColor(ChatColor.DARK_GRAY);
            team.setAllowFriendlyFire(false);
        }
    }

    public static void joinSecurityTeam(Player player) {
        Scoreboard scoreboard = player.getScoreboard();

        Team currentTeam = scoreboard.getEntryTeam(player.getName());
        if (currentTeam != null) {
            currentTeam.removeEntry(player.getName());
        }

        team.addEntry(player.getName());
    }
}
