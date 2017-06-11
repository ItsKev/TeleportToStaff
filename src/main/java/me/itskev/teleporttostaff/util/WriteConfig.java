package me.itskev.teleporttostaff.util;

import me.itskev.teleporttostaff.TeleportToStaff;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Write the staff list to the config.
 */
public class WriteConfig {

    private static WriteConfig instance;

    public static WriteConfig getInstance() {
        return instance == null ? instance = new WriteConfig() : instance;
    }

    private JavaPlugin plugin;

    private WriteConfig() {
        this.plugin = TeleportToStaff.getInstance();
        this.plugin.getConfig().options().copyDefaults(true);
        this.plugin.saveDefaultConfig();
        this.plugin.saveConfig();
    }

    public void addToStaffList(String playerName) {
        FileConfiguration config = this.plugin.getConfig();

        List<String> staff = config.getStringList("Staff");

        if (staff == null) {
            staff = new ArrayList<>();
        }

        staff.add(playerName);

        config.set("Staff", staff);
        this.plugin.saveConfig();
        ReadConfig.getInstance().readStaffList();
    }
}
