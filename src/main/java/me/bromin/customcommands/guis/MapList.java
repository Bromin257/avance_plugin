package me.bromin.customcommands.guis;

import me.bromin.customcommands.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

public class MapList implements Listener {

    Inventory gui;
    public static void openMapList(Player player) {
        System.out.println("openMapList DONE");
        FileConfiguration config = Main.getCustomConfig();

        Inventory gui = Bukkit.createInventory(null, 54, "Maps");
        int i = 0;

        for (String key: config.getConfigurationSection("maps").getKeys(false)) {
            String name = config.getConfigurationSection("maps").getString(key + ".name");
            String gamemode = config.getConfigurationSection("maps").getString(key + ".gamemode");
            String description = config.getConfigurationSection("maps").getString(key + ".description");
            String difficulty = config.getConfigurationSection("maps").getString(key + ".difficulty");
            String itemtype = config.getConfigurationSection("maps").getString(key + ".itemtype");
            String namecolor = config.getConfigurationSection("maps").getString((key + ".namecolor"));

            ItemStack item = new ItemStack(Material.valueOf(itemtype), 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.valueOf(namecolor) + name);
            item.setItemMeta(meta);
            i++; // Slot index

            gui.setItem(i, item);
        }
        System.out.println("Maplist gui opened");
        player.openInventory(gui);


    }

    @EventHandler
    public static void selectMap(InventoryClickEvent e) {
        String invName = e.getView().getTitle();
        if(invName.equals("Maps")) {
            System.out.println("selectmap DONE");
            Player player = (Player) e.getWhoClicked();
            Location loc = new Location(Bukkit.getWorld("parkour_spiral"), 0, 53, -126);
            player.teleport(loc);
        }
    }
}
