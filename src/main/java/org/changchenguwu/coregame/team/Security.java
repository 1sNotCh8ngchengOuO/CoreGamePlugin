package org.changchenguwu.coregame.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class Security implements ITeam{
    Scoreboard manager = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
    Team team;
    @Override
    public void registerTeam() {
        team = manager.registerNewTeam("security");
        team.setAllowFriendlyFire(false);
        team.setPrefix(ChatColor.DARK_GRAY + "[安全]");
    }

    @Override
    public void joinTeam(Player player) {
        team.addEntry(player.getName());
    }

    @Override
    public void quitTeam(Player player) {
        team.removeEntry(player.getName());
    }
}
