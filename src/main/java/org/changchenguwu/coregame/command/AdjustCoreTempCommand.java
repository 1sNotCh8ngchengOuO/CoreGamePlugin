package org.changchenguwu.coregame.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.changchenguwu.coregame.core.CoreTemperature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class AdjustCoreTempCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "权限不足");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "用法: /coretemp <add|reduce> <数值>");
            return true;
        }

        try {
            int value = Integer.parseInt(args[1]);
            switch (args[0].toLowerCase()) {
                case "add":
                    CoreTemperature.addTemperature(value);
                    break;
                case "reduce":
                    CoreTemperature.reduceTemperature(value);
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "无效操作，请使用 add 或 reduce");
                    return true;
            }
            sender.sendMessage(ChatColor.GREEN +CoreTemperature.getCoreTemperatureFormat());
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "无效的数值格式");
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("add", "reduce");
        }
        return null;
    }
}