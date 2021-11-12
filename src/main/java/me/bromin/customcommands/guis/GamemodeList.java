package me.bromin.customcommands.guis;

import me.bromin.customcommands.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GamemodeList implements Listener {

    // Open maps according to the map type given by
    public static void openGamemodeList(Player p) {

        int mapType;
        Inventory gui;

        // Get config.yml file
        FileConfiguration config = Main.getCustomConfig();

        gui = Bukkit.createInventory(null, InventoryType.HOPPER, "Gamemodes");

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

            System.out.println("Custom MD is:" + l);

            gui.setItem(i, item);
        }
        p.openInventory(gui);
    }


    @EventHandler
    public void openMapList(InventoryClickEvent e) {
        String invName = e.getView().getTitle();

        if (invName.equals("Gamemodes")) {
            Player p = (Player) e.getWhoClicked();
            int i = e.getCurrentItem().getItemMeta().getCustomModelData();

            MapList.openMapList(p, i);
        }

    }



}
