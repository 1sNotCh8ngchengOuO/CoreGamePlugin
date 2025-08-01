package org.changchenguwu.coregame.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaySoundCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("只有玩家才能使用此命令。");
            return true;
        }

        if (!player.isOp()) {
            player.sendMessage("你没有权限使用此命令。");
            return true;
        }

        if (args.length < 4) {
            player.sendMessage("用法: /debugsound <声音名称> <音量> <音调> <世界名称>,<x>,<y>,<z>");
            return true;
        }

        try {
            Sound sound = Sound.valueOf(args[0].toUpperCase());
            float volume = Float.parseFloat(args[1]);
            float pitch = Float.parseFloat(args[2]);

            String[] locationParts = args[3].split(",");
            if (locationParts.length != 4) {
                player.sendMessage("无效的位置格式。请使用 <世界名称>,<x>,<y>,<z>");
                return true;
            }

            String worldName = locationParts[0];
            double x = Double.parseDouble(locationParts[1]);
            double y = Double.parseDouble(locationParts[2]);
            double z = Double.parseDouble(locationParts[3]);

            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                player.sendMessage("世界 '" + worldName + "' 不存在。");
                return true;
            }

            Location location = new Location(world, x, y, z);
            player.playSound(location, sound, volume, pitch);
            player.sendMessage("已在指定位置播放声音: " + sound.name());

        } catch (IllegalArgumentException e) {
            player.sendMessage("无效的声音名称、音量、音调或位置格式。错误: " + e.getMessage());
            return true;
        }

        return true;
    }
}