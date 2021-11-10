package me.bromin.customcommands;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.sql.*;
import java.util.UUID;


public class NewUser implements Listener {

    static FileConfiguration config;

    // When new user joins
    @EventHandler
    public void onNewUserjoin (PlayerJoinEvent event) throws SQLException {

        Player p = event.getPlayer();
        UUID u = p.getUniqueId();

        // Establish connection to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_players", "test", "Test123!");

        // Insert new user details into tbl_users table
        PreparedStatement stmt = conn.prepareStatement("INSERT IGNORE INTO tbl_users(username, uuid) VALUES (?, ?)");

        // Set username and uuid in SQL syntax
        stmt.setString(1, p.getDisplayName());
        stmt.setString(2, String.valueOf(u));

        stmt.executeUpdate();

    }
}
