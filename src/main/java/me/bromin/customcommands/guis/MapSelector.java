package me.bromin.customcommands.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class MapSelector implements Listener {

    int mapType;
    Player player;

    // Open maps according to the map type given by
    public void openMaps() {
        switch (mapType) {
            case 1: {
                gameGUI(mapType);
                break;
            }
            case 2: {
                gameGUI(mapType);
                break;
            }
            case 3: {
                gameGUI(mapType);
                break;
            }
            case 4: {
                gameGUI(mapType);
                break;
            }
        }
    }

    public void gameGUI(int i) {

        Inventory gui;



        gui = Bukkit.createInventory(null, InventoryType.CHEST, "Adventure Maps");

        player.openInventory(gui);

    }



}
