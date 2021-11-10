package me.bromin.customcommands.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.*;

import java.util.List;

public class FriendInstance {


    String name;
    ItemStack head;

    public FriendInstance(final String name) { this.name = name; this.head = createHead();}

    public ItemStack getPlayerHead() { return head; }

    private ItemStack createHead() {

       head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner(name);
        head.setItemMeta(meta);

        return head;
    }

}

