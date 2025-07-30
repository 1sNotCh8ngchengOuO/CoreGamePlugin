package org.changchenguwu.coregame.variable;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
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
            return "成员组(制作中)";
        }
        return null;
    }
}
