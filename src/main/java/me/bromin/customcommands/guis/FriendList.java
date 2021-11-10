package me.bromin.customcommands.guis;

import me.bromin.customcommands.managers.FriendInstance;
import me.bromin.customcommands.managers.PlayerInstance;
import me.bromin.customcommands.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class FriendList {

    public static void openFriendSelector(Player p) {
        PlayerInstance player = PlayerManager.findPlayerInstance(p);


        Inventory gui = Bukkit.createInventory(null, 54, "Friends");
        ItemStack item = null;
        int i = 0;

        for(FriendInstance friend : player.getFriends()) {

            gui.setItem(i, friend.getPlayerHead());
            i++;
        }


        System.out.println("Friend Lsit opened");
        p.openInventory(gui);

    }
}
