package me.itskev.teleporttostaff;

import me.itskev.teleporttostaff.commands.*;
import me.itskev.teleporttostaff.util.WriteConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Main class of the TeleportToStuff plugin
 *
 * @author ItsKev
 */
public class TeleportToStaff extends JavaPlugin {

    private static TeleportToStaff instance;

    public static TeleportToStaff getInstance() {
        return instance;
    }

    private Map<UUID, UUID> activeTeleportRequests;

    @Override
    public void onEnable() {
        instance = this;
        activeTeleportRequests = Collections.synchronizedMap(new HashMap<>());

        WriteConfig.getInstance();
        this.registerCommands();
    }

    @Override
    public void onDisable() {
    }

    public Map<UUID, UUID> getActiveTeleportRequests() {
        return this.activeTeleportRequests;
    }

    private void registerCommands() {
        this.getCommand("tpTo").setExecutor(new TeleportToCommand());
        this.getCommand("tpTo").setTabCompleter(new TeleportTabCompleter());
        this.getCommand("tpAccept").setExecutor(new TeleportAcceptCommand());
        this.getCommand("tpAccept").setTabCompleter(new TabCompleter());
        this.getCommand("tpAdd").setExecutor(new TeleportAddCommand());
        this.getCommand("tpAdd").setTabCompleter(new TabCompleter());
    }
}
