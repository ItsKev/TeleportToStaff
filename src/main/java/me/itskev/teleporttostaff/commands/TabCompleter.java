package me.itskev.teleporttostaff.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TabCompleter
 */
public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> completions = new ArrayList<>();

        if (!(sender instanceof Player)) {
            return Collections.emptyList();
        }

        if (args.length < 2) {
            List<String> availableCompletions = new ArrayList<>();

            for (Player player : Bukkit.getOnlinePlayers()) {
                availableCompletions.add(player.getName());
            }

            StringUtil.copyPartialMatches(args[0], availableCompletions, completions);
            Collections.sort(completions);

            return completions;
        }
        return Collections.emptyList();
    }
}