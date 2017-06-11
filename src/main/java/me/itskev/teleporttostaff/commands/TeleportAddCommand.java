package me.itskev.teleporttostaff.commands;

import me.itskev.teleporttostaff.TeleportToStaff;
import me.itskev.teleporttostaff.util.ReadConfig;
import me.itskev.teleporttostaff.util.WriteConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Adds a player to the staff
 */
public class TeleportAddCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("teleport.Add")) {
            return true;
        }

        if (args.length > 0) {
            List<String> staff = ReadConfig.getInstance().getStaff();
            String name = args[0];
            FileConfiguration config = TeleportToStaff.getInstance().getConfig();

            Player staffPlayer = Bukkit.getPlayer(name);

            if (staffPlayer == null) {
                String playerNotFound = ChatColor.translateAlternateColorCodes('&',
                        config.getString("PlayerNotFound"));
                player.sendMessage(playerNotFound);
                return true;
            }

            if (staff.contains(staffPlayer.getName())) {
                String playerIsAlreadyStaff = ChatColor.translateAlternateColorCodes('&',
                        config.getString("PlayerIsAlreadyStaff"));
                player.sendMessage(playerIsAlreadyStaff);
                return true;
            }

            WriteConfig.getInstance().addToStaffList(staffPlayer.getName());
            return true;
        }
        return false;
    }
}
