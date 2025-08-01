package org.changchenguwu.coregame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.effects.PowerOnLightingAnimation;
import org.changchenguwu.coregame.main;

public class RestoreLightAnimationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("只有玩家才能使用此命令。");
            return true;
        }

        if (!sender.isOp()) {
            sender.sendMessage("你没有权限使用此命令。");
            return true;
        }

        PowerOnLightingAnimation.restoreAnimation(JavaPlugin.getPlugin(main.class));
        sender.sendMessage("动画恢复已启动。");
        return true;
    }
}