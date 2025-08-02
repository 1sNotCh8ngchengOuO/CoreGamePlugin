package org.changchenguwu.coregame.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.changchenguwu.coregame.nametag.NameTagManager;
import org.jetbrains.annotations.NotNull;

public class SetNameTagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "只有玩家可以使用此命令！");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "用法: /setnametag <前缀> [后缀] 或 /setnametag reset");
            return true;
        }

        // 重置NameTag
        if ("reset".equalsIgnoreCase(args[0])) {
            NameTagManager.resetPlayerNameTag(player);
            player.sendMessage(ChatColor.GREEN + "已重置你的自定义NameTag");
            
            // 重新应用团队设置
            Scoreboard scoreboard = player.getScoreboard();
            Team team = scoreboard.getEntryTeam(player.getName());
            if (team != null) {
                // 重新加入团队以刷新NameTag
                team.removeEntry(player.getName());
                team.addEntry(player.getName());
            }
            return true;
        }

        // 设置前缀和后缀
        String prefix = args[0];
        String suffix = args.length > 1 ? args[1] : "";

        // 检查颜色代码格式
        if (!isValidColorCode(prefix) || !isValidColorCode(suffix)) {
            player.sendMessage(ChatColor.RED + "无效的颜色代码格式！请使用 §+颜色代码，例如 §a§l");
            return true;
        }

        // 保存并应用NameTag
        NameTagManager.setPlayerNameTag(player, prefix, suffix);
        player.sendMessage(ChatColor.GREEN + "已设置你的自定义NameTag");

        // 重新应用团队设置
        Scoreboard scoreboard = player.getScoreboard();
        Team team = scoreboard.getEntryTeam(player.getName());
        if (team != null) {
            // 应用自定义NameTag
            NameTagManager.applyNameTag(player, team);
        }

        return true;
    }

    private boolean isValidColorCode(String text) {
        // 允许空字符串
        if (text.isEmpty()) {
            return true;
        }
        
        // 检查是否包含有效的颜色代码
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '§') {
                char colorChar = text.charAt(i + 1);
                // 检查是否是有效的颜色代码字符
                if (!((colorChar >= '0' && colorChar <= '9') || 
                      (colorChar >= 'a' && colorChar <= 'f') || 
                      (colorChar >= 'k' && colorChar <= 'r'))) {
                    return false;
                }
            }
        }
        return true;
    }
}