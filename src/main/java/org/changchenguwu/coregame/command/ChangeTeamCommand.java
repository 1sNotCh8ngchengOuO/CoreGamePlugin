package org.changchenguwu.coregame.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.changchenguwu.coregame.team.Engineer;
import org.changchenguwu.coregame.team.Scientist;
import org.changchenguwu.coregame.team.Security;

import java.util.List;

public class ChangeTeamCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("你没有权限执行该命令");
            return true;
        }
        if (sender instanceof Player player) {
            if (args.length == 1) {
                if ("scientist".equals(args[0])) {
                    Scientist.joinScientistTeam(player);
                } else if ("security".equals(args[0])) {
                    Security.joinSecurityTeam(player);
                } else if ("engineer".equals(args[0])) {
                    Engineer.joinEngineerTeam(player);
                } else {
                    sender.sendMessage("未知的团队名称");
                }
            }
        }else {
            sender.sendMessage("你必须是玩家才能执行该命令");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return List.of("scientist", "security", "engineer");
    }
}
