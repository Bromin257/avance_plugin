package me.bromin.customcommands;

import me.bromin.customcommands.commands.*;
import me.bromin.customcommands.guis.CustomInventory;
import me.bromin.customcommands.guis.GUISelector;
import me.bromin.customcommands.managers.PlayerManager;
import me.bromin.customcommands.managers.SQLManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    static FileConfiguration config;

    @Override
    public void onEnable() {

        config = getConfig();

        getServer().getPluginCommand("friend").setExecutor(new AddFriend(this));
        getServer().getPluginCommand("maps").setExecutor(new OpenMapSelector(this));
        getServer().getPluginCommand("friends").setExecutor(new OpenFriendList(this));

        getServer().getPluginManager().registerEvents(new GUISelector(), this);
        getServer().getPluginManager().registerEvents(new CustomInventory(), this);
        getServer().getPluginManager().registerEvents(new NewUser(), this);
        getServer().getPluginManager().registerEvents(new Test(), this);
        getServer().getPluginManager().registerEvents(new PlayerManager(), this);


        config.options().copyDefaults(true);
        saveConfig();

        System.out.println("PLUGIN STARTED");
    }

    public static FileConfiguration getCustomConfig() { return config; }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
