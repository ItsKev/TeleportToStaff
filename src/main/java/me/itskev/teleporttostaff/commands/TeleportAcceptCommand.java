package me.itskev.teleporttostaff.commands;

import me.itskev.teleporttostaff.TeleportToStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

/**
 * Teleport accept command handler
 */
public class TeleportAcceptCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 0) {
            Map<UUID, UUID> activeTeleportRequests = TeleportToStaff.getInstance().getActiveTeleportRequests();
            FileConfiguration config = TeleportToStaff.getInstance().getConfig();
            String name = args[0];

            Player playerTeleportRequested = Bukkit.getPlayer(name);

            if (playerTeleportRequested == null) {
                String playerNotFound = ChatColor.translateAlternateColorCodes('&',
                        config.getString("PlayerNotFound"));
                player.sendMessage(playerNotFound);
                return true;
            }

            if (!activeTeleportRequests.containsKey(player.getUniqueId()) ||
                    !activeTeleportRequests.get(player.getUniqueId()).equals(playerTeleportRequested.getUniqueId())) {
                String noRequestPending = ChatColor.translateAlternateColorCodes('&',
                        config.getString("NoRequestPending"));
                player.sendMessage(noRequestPending);
                return true;
            }

            playerTeleportRequested.teleport(player.getLocation());

            if (activeTeleportRequests.containsKey(player.getUniqueId())) {
                activeTeleportRequests.remove(player.getUniqueId());
            }
            return true;
        }
        return false;
    }
}
