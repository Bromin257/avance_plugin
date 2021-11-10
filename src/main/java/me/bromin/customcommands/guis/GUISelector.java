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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GUISelector implements Listener {
    // GUI
    private Inventory gui;

    // Get config.yml file
    FileConfiguration config = Main.getCustomConfig();

    @EventHandler
    public void loadFriendList(PlayerJoinEvent e) {

        Player p = e.getPlayer();

    }

    // Method to open the required gui (h = type of GUI)
    public void openNewGui(Player p, int h) {

        if (h == 1) {
            gui = Bukkit.createInventory(null, InventoryType.HOPPER, "Maps");

            for (String key: config.getConfigurationSection("gamemodes").getKeys(false)) {
                String m = config.getConfigurationSection("gamemodes").getString(key+ ".itemtype");
                String n = config.getConfigurationSection("gamemodes").getString(key+ ".displayname");
                String c = config.getConfigurationSection("gamemodes").getString(key+ ".namecolor");
                int l = config.getConfigurationSection("gamemodes").getInt(key+ ".custommodeldata");
                int i = config.getConfigurationSection("gamemodes").getInt(key+ ".index");


                ItemStack item = new ItemStack(Material.valueOf(m), 1);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.valueOf(c) + n);
                meta.setCustomModelData(l);
                item.setItemMeta(meta);

                gui.setItem(i, item);
            }

            p.openInventory(gui);

        } else if (h == 2) {


        }
    }

    // Event handler for player interaction with inventory items
    @EventHandler
    public void openGuiEvent(PlayerInteractEvent e) throws SQLException {
        Player p = e.getPlayer();
        Action a = e.getAction();
        PlayerInventory i = p.getInventory();
        int h = Objects.requireNonNull(i.getItemInMainHand().getItemMeta()).getCustomModelData();


        // Only if right click on air or block
        if ((a == Action.RIGHT_CLICK_AIR) || (a == Action.RIGHT_CLICK_BLOCK)) {
            if (!(h == 0)) {
                openNewGui(e.getPlayer(), h);
            }
        }
    }

    // Event handler for player interaction with map selector items
    @EventHandler
    public void openMapList(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        int i = Objects.requireNonNull(e.getCurrentItem().getItemMeta().getCustomModelData());


        MapSelector map = new MapSelector();
        map.mapType = i;
        map.player = p;
        map.openMaps();
    }


}
