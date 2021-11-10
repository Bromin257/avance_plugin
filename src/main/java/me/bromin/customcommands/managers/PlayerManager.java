package me.bromin.customcommands.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.bromin.customcommands.managers.SQLManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements Listener {
    private final List<PlayerInstance> onlinePlayers = new ArrayList<>();

    // When new player joins ass Player + Friends to the list of online players
    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        List<String> friendList = SQLManager.getInstance().getFriendList(p);
        PlayerInstance currentPLayer = new PlayerInstance(p, friendList);
        onlinePlayers.add(currentPLayer);

        System.out.println("Player" + p.getDisplayName() + "has joined the server!"); // Debug
    }


    // When player leaves, remove from online player list
    @EventHandler
    public void playerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        onlinePlayers.removeIf(x -> x.getPlayer().getUniqueId().equals(p.getUniqueId()));

        System.out.println("Player" + p.getDisplayName() + "has left the server!"); // Debug

    }

}
