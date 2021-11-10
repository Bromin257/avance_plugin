package me.bromin.customcommands.managers;

import org.bukkit.entity.Player;

import java.util.List;

public class PlayerInstance {
    private org.bukkit.entity.Player player;
    private List<String> friends;


    public PlayerInstance(final Player player, final List<String> friends) { this.player = player; this.friends=friends; }

    public org.bukkit.entity.Player getPlayer() { return player; }
    public List<String> getFriends() { return friends; }
}
