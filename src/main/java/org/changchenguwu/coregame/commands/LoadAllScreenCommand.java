package org.changchenguwu.coregame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.changchenguwu.coregame.display.Screen;
import org.changchenguwu.coregame.display.ScreenManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class LoadAllScreenCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("§c无权限执行此命令");
            return true;
        }
        ScreenManager.loadScreens();
        sender.sendMessage("所有屏幕已加载");
        Map<Integer, Screen> allScreens = ScreenManager.allScreens;
        StringBuilder screenList = new StringBuilder("已加载的屏幕ID: ");
        for(Integer key: allScreens.keySet()){
            String string = key.toString();
            screenList.append("[").append(string).append("] ");
        }
        sender.sendMessage(screenList.toString());
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of("loadallscreen");
    }
}
