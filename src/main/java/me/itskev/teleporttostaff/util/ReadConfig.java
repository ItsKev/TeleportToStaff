package me.itskev.teleporttostaff.util;

import me.itskev.teleporttostaff.TeleportToStaff;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Read the staff list
 */
public class ReadConfig {

    private static ReadConfig instance;

    public static ReadConfig getInstance() {
        return instance == null ? instance = new ReadConfig() : instance;
    }

    private List<String> staff;

    private ReadConfig() {
        this.staff = new ArrayList<>();
        this.readStaffList();
    }

    public List<String> getStaff() {
        return this.staff;
    }

    public void readStaffList() {
        FileConfiguration config = TeleportToStaff.getInstance().getConfig();

        this.staff = config.getStringList("Staff");
        if (this.staff == null) {
            this.staff = new ArrayList<>();
        }
    }


}
