package org.changchenguwu.coregame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.changchenguwu.coregame.display.ScreenManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpdateDisplayTestCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp()) {
            if (args.length != 2) {
                return true;
            }
            int id = Integer.parseInt(args[0]);
            String text = args[1];
            ScreenManager.updateScreen(id,sender, text);
        }else {
            sender.sendMessage("§c你没有权限使用此命令");
            return true;
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 1) {
            return null;
        }
        return List.of("us");
    }
}
