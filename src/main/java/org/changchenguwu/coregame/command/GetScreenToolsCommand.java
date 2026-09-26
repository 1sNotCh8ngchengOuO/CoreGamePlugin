package org.changchenguwu.coregame.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.changchenguwu.coregame.item.ScreenToolItems;

import java.util.List;

public class GetScreenToolsCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.isOp()) {
                player.openInventory(ScreenToolItems.createScreenToolsChest());
            }else {
                sender.sendMessage(ChatColor.RED+"请在游戏中使用此命令");
            }
        }else {
            sender.sendMessage(ChatColor.RED+"无权限");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return List.of("stools");
    }
}
