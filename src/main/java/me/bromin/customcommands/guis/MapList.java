package me.bromin.customcommands.guis;

import me.bromin.customcommands.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class MapList {
    public void openMapList(String gamemodeType) {
        FileConfiguration config = Main.getCustomConfig();

        Inventory gui = Bukkit.createInventory(null, 54, "Maps");



    }
}
