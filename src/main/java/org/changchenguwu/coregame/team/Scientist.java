package org.changchenguwu.coregame.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class Scientist{
    static Team team;

    public static void uninit() {
        if (team != null) {
            team.unregister(); // 注销队伍
            team = null;
        }
    }

    public static void init() {
        Scoreboard mainScoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        team = mainScoreboard.getTeam("scientist");
        if (team == null) {
            team = mainScoreboard.registerNewTeam("scientist");
            team.setPrefix(ChatColor.DARK_AQUA + "[科学家]");
            team.setColor(ChatColor.DARK_AQUA);
            team.setAllowFriendlyFire(false);
        }
    }

    public static void joinScientistTeam(Player player) {
        Scoreboard scoreboard = player.getScoreboard();

        Team currentTeam = scoreboard.getEntryTeam(player.getName());
        if (currentTeam != null) {
            currentTeam.removeEntry(player.getName());
        }

        team.addEntry(player.getName());
    }
}
