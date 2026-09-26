package org.changchenguwu.coregame.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.changchenguwu.coregame.effect.ScreenShakeEffect;

import org.bukkit.plugin.java.JavaPlugin;

public class ScreenShakeCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public ScreenShakeCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("你没有权限使用此命令。");
            return true;
        }
        if (!(sender instanceof Player player)) {
            sender.sendMessage("只有玩家才能使用此命令。");
            return true;
        }

        if (args.length == 3 && "start".equalsIgnoreCase(args[0])) {
            try {
                double intensity = Double.parseDouble(args[1]);
                long durationSeconds = Long.parseLong(args[2]);
                ScreenShakeEffect.startScreenShake(plugin, player, intensity, durationSeconds * 20L); // 20 ticks per second
                player.sendMessage("屏幕抖动已开始，强度: " + intensity + ", 持续时间: " + durationSeconds + "秒。");
            } catch (NumberFormatException e) {
                player.sendMessage("无效的强度或持续时间。用法: /screenshake start <强度> <持续时间(秒)>");
            }
            return true;
        } else if (args.length == 1 && "stop".equalsIgnoreCase(args[0])) {
            ScreenShakeEffect.stopScreenShake(player);
            player.sendMessage("屏幕抖动已停止。");
            return true;
        } else {
            player.sendMessage("用法: /screenshake start <强度> <持续时间(秒)> 或 /screenshake stop");
            return true;
        }
    }
}