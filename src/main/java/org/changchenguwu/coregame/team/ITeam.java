package org.changchenguwu.coregame.team;

import org.bukkit.entity.Player;

public interface ITeam {
    void registerTeam();

    void joinTeam(Player player);

    void quitTeam(Player player);
}
