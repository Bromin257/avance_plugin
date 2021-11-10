package me.bromin.customcommands.guis;

import me.bromin.customcommands.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GamemodeList implements Listener {



    // Open maps according to the map type given by
    public static void openGamemodeList(Player p) {

        int mapType;
        Player player;
        Inventory gui;

        // Get config.yml file
        FileConfiguration config = Main.getCustomConfig();

        gui = Bukkit.createInventory(null, InventoryType.HOPPER, "Maps");

        for (String key: config.getConfigurationSection("gamemodes").getKeys(false)) {
            String m = config.getConfigurationSection("gamemodes").getString(key + ".itemtype");
            String n = config.getConfigurationSection("gamemodes").getString(key + ".displayname");
            String c = config.getConfigurationSection("gamemodes").getString(key + ".namecolor");
            int l = config.getConfigurationSection("gamemodes").getInt(key + ".custommodeldata");
            int i = config.getConfigurationSection("gamemodes").getInt(key + ".index");


            ItemStack item = new ItemStack(Material.valueOf(m), 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.valueOf(c) + n);
            meta.setCustomModelData(l);
            item.setItemMeta(meta);

            gui.setItem(i, item);
        }
        p.openInventory(gui);
    }


/*
    public void gameGUI(int i) {

        Inventory gui;

        gui = Bukkit.createInventory(null, InventoryType.CHEST, "Adventure Maps");

        player.openInventory(gui);

    }
*/

}
