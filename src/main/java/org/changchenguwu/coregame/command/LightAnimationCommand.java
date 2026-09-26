package org.changchenguwu.coregame.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.effect.PowerOnLightingAnimation;
import org.changchenguwu.coregame.CoreGame;

public class LightAnimationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        if (!player.isOp()) {
            player.sendMessage("You do not have permission to use this command.");
            return true;
        }

        PowerOnLightingAnimation.animation(JavaPlugin.getPlugin(CoreGame.class));
        player.sendMessage("PowerOnLightingAnimation started.");
        return true;
    }
}