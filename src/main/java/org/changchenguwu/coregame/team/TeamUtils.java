package org.changchenguwu.coregame.team;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.changchenguwu.coregame.nametag.NameTagData;
import org.changchenguwu.coregame.nametag.NameTagManager;

public class TeamUtils {
    
    /**
     * 将玩家添加到指定团队，并应用自定义NameTag
     * 
     * @param player 要添加的玩家
     * @param team 目标团队
     * @param defaultPrefix 团队默认前缀
     */
    public static void addPlayerToTeam(Player player, Team team, String defaultPrefix) {
        // 先从当前团队中移除玩家
        Team currentTeam = player.getScoreboard().getEntryTeam(player.getName());
        if (currentTeam != null) {
            currentTeam.removeEntry(player.getName());
        }
        // 添加玩家到团队
        team.addEntry(player.getName());
        
        // 应用自定义NameTag
        NameTagData nameTagData = NameTagManager.getPlayerNameTagData(player);
        if (!nameTagData.prefix().isEmpty() || !nameTagData.suffix().isEmpty()) {
            // 如果有自定义NameTag，则应用
            if (!nameTagData.prefix().isEmpty()) {
                team.setPrefix(nameTagData.prefix() + " " + defaultPrefix);
            }
            
            if (!nameTagData.suffix().isEmpty()) {
                team.setSuffix(" " + nameTagData.suffix());
            }
        }
    }
}