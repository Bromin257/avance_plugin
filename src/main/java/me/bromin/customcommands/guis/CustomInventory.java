package me.bromin.customcommands.guis;

import me.bromin.customcommands.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomInventory implements Listener {


    // When a player joins
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        // Get config.yml file
        FileConfiguration config = Main.getCustomConfig();

        Player p = event.getPlayer();

        // Clear player's inventory on join
        Inventory inv = p.getInventory();
        inv.clear();

        // For every item listed in config.yml generate the item and add it to the inventory
        for (String key: config.getConfigurationSection("custominventory").getKeys(false)) {

            String i = config.getConfigurationSection("custominventory").getString(key+ ".itemtype");
            String e = config.getConfigurationSection("custominventory").getString(key+ ".enchantment");
            String d = config.getConfigurationSection("custominventory").getString(key+ ".displayname");
            String c = config.getConfigurationSection("custominventory").getString(key + ".namecolor");
            int s = config.getConfigurationSection("custominventory").getInt(key + ".slot");
            int m = config.getConfigurationSection("custominventory").getInt(key + ".custommodeldata");

            ItemStack item = new ItemStack(Material.valueOf(i), 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.valueOf(c) + d);
            meta.addEnchant(Enchantment.getByName(e), 1, true);
            meta.setCustomModelData(m);
            item.setItemMeta(meta);
            inv.setItem(s, item);
        }
    }

    // Prevent player for dropping items (Cancel drop event)
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {

        event.setCancelled(true);
    }
}
