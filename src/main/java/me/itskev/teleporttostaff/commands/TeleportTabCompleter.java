package me.itskev.teleporttostaff.commands;

import me.itskev.teleporttostaff.util.ReadConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Teleport to staff tab completer
 */
public class TeleportTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> completions = new ArrayList<>();

        if (!(sender instanceof Player)) {
            return Collections.emptyList();
        }

        if (args.length < 2) {
            List<String> staff = ReadConfig.getInstance().getStaff();
            List<String> availableCompletions = new ArrayList<>(staff);

            StringUtil.copyPartialMatches(args[0], availableCompletions, completions);
            Collections.sort(completions);

            return completions;
        }
        return Collections.emptyList();
    }
}
