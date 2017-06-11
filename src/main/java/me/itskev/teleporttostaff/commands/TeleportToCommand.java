package me.itskev.teleporttostaff.commands;

import me.itskev.teleporttostaff.TeleportToStaff;
import me.itskev.teleporttostaff.util.ReadConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Teleport to staff command handler
 */
public class TeleportToCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 0) {
            List<String> staff = ReadConfig.getInstance().getStaff();
            String name = args[0];
            FileConfiguration config = TeleportToStaff.getInstance().getConfig();

            Player playerTo = Bukkit.getPlayer(name);

            if (!staff.contains(name) || playerTo == null) {
                String playerNotFound = ChatColor.translateAlternateColorCodes('&',
                        config.getString("PlayerNotFound"));
                player.sendMessage(playerNotFound);
                return true;
            }

            TeleportToStaff.getInstance().getActiveTeleportRequests().put(playerTo.getUniqueId(), player.getUniqueId());
            String teleportRequest = ChatColor.translateAlternateColorCodes('&',
                    config.getString("TeleportRequest"));
            String message = teleportRequest.replace("[Name]", player.getName());
            playerTo.sendMessage(message);

            this.removeUnansweredRequestLater(playerTo.getUniqueId());
            return true;
        }


        return false;
    }

    private void removeUnansweredRequestLater(UUID uniqueId) {
        new BukkitRunnable() {

            @Override
            public void run() {
                Map<UUID, UUID> teleportRequests = TeleportToStaff.getInstance().getActiveTeleportRequests();
                if (teleportRequests.containsKey(uniqueId)) {
                    teleportRequests.remove(uniqueId);
                }
            }
        }.runTaskLaterAsynchronously(TeleportToStaff.getInstance(), 15 * 20);
    }
}
