package org.changchenguwu.coregame.variable;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "coregame";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Changcheng";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if ("group".equalsIgnoreCase(params)) {
            return getGroup(player);
        }
        return null;
    }

    public String getGroup(OfflinePlayer player) {
        Player p = Bukkit.getPlayer(player.getUniqueId());
        if(p == null) {
            return null;
        }
        Team entryTeam = p.getScoreboard().getEntryTeam(p.getName());
        if (entryTeam != null) {
            return switch (entryTeam.getName()) {
                case "scientist" -> "§b科学家";
                case "security" -> "§7安保";
                case "engineer" -> "§6工程师";
                default -> "";
            };
        }
        return "";
    }
}
