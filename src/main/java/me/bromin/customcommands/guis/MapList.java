package me.bromin.customcommands.guis;

import me.bromin.customcommands.Main;
import me.bromin.customcommands.managers.PlayerInstance;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class MapList implements Listener {

    Inventory gui;
    static FileConfiguration config = Main.getCustomConfig();

    public static void openMapList(Player player, int mapType) {
        System.out.println("openMapList DONE");
        System.out.println(mapType);

        String gamemode = null;

        /*
        for (String key: config.getConfigurationSection("gamemodes").getKeys(false)) {
            int cmd = config.getConfigurationSection("gamemodes").getInt(key + ".custommodeldata");
            if (cmd == mapType) {
                gamemode = key;
                System.out.println("Selected gamemode is:" + gamemode);
            }
        }


        for (String key: config.getConfigurationSection("maps").getKeys(false)) {
            String maptype = config.getConfigurationSection("maps").getString(key + ".gamemode");

            System.out.println("Selected maptype is:" + maptype);
            if (maptype.equals(gamemode)) {
            */
                Inventory gui = Bukkit.createInventory(null, 54, "Maps");
                int i = 0;

                for (String key2: config.getConfigurationSection("maps").getKeys(false)) {
                    String name = config.getConfigurationSection("maps").getString(key2 + ".name");
                    String description = config.getConfigurationSection("maps").getString(key2 + ".description");
                    String difficulty = config.getConfigurationSection("maps").getString(key2 + ".difficulty");
                    String itemtype = config.getConfigurationSection("maps").getString(key2 + ".itemtype");
                    String namecolor = config.getConfigurationSection("maps").getString((key2 + ".namecolor"));
                    int modeldata = config.getConfigurationSection("maps").getInt((key2 + ".custommodeldata"));

                    ItemStack item = new ItemStack(Material.valueOf(itemtype), 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.valueOf(namecolor) + name);
                    meta.setCustomModelData(modeldata);
                    item.setItemMeta(meta);

                    gui.setItem(i, item);
                    i++; // Slot index
                }
                System.out.println("Maplist gui opened");
                player.openInventory(gui);
            //}
        //}

    }

    @EventHandler
    public static void selectMap(InventoryClickEvent e) {
        String invName = e.getView().getTitle();

        // Execute only if the clicked item is within the Maps GUI
        if(invName.equals("Maps")) {
            System.out.println("selectmap DONE"); // Debug
            Player player = (Player) e.getWhoClicked();
            int i = e.getCurrentItem().getItemMeta().getCustomModelData();
            System.out.println("Custom Model Data: " + i);
            String worldname = "world";

            for (String key: config.getConfigurationSection("maps").getKeys(false)) {
                int custommodeldata = config.getConfigurationSection("maps").getInt(key + ".custommodeldata");

                if (custommodeldata == i) {
                    worldname = config.getConfigurationSection("maps").getString(key + ".worldname");
                    System.out.println("worldname: " + worldname);
                }

            }



            double x = config.getConfigurationSection("maps").getDouble((worldname + ".spawnpoint_x"));
            double y = config.getConfigurationSection("maps").getDouble((worldname + ".spawnpoint_y"));
            double z = config.getConfigurationSection("maps").getDouble((worldname + ".spawnpoint_z"));


            System.out.println("x:" + x + " y:" + y + " z:" + z);
            Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
            player.teleport(loc);
        }
    }
}
