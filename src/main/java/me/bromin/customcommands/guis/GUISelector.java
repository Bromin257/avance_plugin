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

import java.sql.*;
import java.util.Objects;

public class GUISelector implements Listener {
    // GUI
    private Inventory gui;

    // Get config.yml file
    FileConfiguration config = Main.getCustomConfig();

    //! Find if it is used
    @EventHandler
    public void loadFriendList(PlayerJoinEvent e) {
        Player p = e.getPlayer();
    }

    // Method to open the required gui (h = type of GUI)
    public void openNewGui(Player p, int h) {

        if (h == 1) {
            MapList.openMaps(p);
        } else if (h == 2) {

            FriendList.openFriendSelector(p);
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
                System.out.println("item clicked" + h);
            }
        }
    }

    // Event handler for player interaction with map selector items
    /*
    @EventHandler
    public void openMapList(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        int i = Objects.requireNonNull(e.getCurrentItem().getItemMeta().getCustomModelData());


        MapList map = new MapList();
        map.mapType = i;
        map.player = p;
        map.openMaps();
    }

     */


}
