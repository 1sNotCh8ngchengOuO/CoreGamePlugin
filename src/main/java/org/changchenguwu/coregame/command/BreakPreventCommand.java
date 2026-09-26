package org.changchenguwu.coregame.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.changchenguwu.coregame.event.commonevent.BreakPreventListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BreakPreventCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(args.length == 1)) {
            return false;
        }
        String booleanStr = args[0];
        if (!"true".equals(booleanStr) && !"false".equals(booleanStr)) {
            return false;
        }else{
            BreakPreventListener.setBreakPrevent("true".equals(booleanStr));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("true", "false");
        }
        return null;
    }
}
