package me.bromin.customcommands.commands;

import me.bromin.customcommands.Main;
import me.bromin.customcommands.managers.FriendInstance;
import me.bromin.customcommands.managers.PlayerInstance;
import me.bromin.customcommands.managers.PlayerManager;
import me.bromin.customcommands.managers.SQLManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

public class AddFriend implements CommandExecutor {

    public AddFriend(Main main) {
    }

    // On friend command execution
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)  {
        Player p = (Player) sender;
        String action = args[0];
        String user = args[1];

        // Connection object
        Connection conn = null;

        // Define the executer and argument user
        int executerUser = 0;
        int argsUser = 0;

        // Get the id of the executer and argument users
        // Try Connecting to the DB and
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_players", "test", "Test123!");

            // Prep statement for SELECT query
            PreparedStatement stmt = conn.prepareStatement("SELECT id, username FROM tbl_users WHERE username = ? OR username = ?");

            // Define sql query arguments
            stmt.setString(1, p.getDisplayName());
            stmt.setString(2, user);

            // Get result set from results
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString("username"));
                if(rs.getString("username").equals(p.getName())) {
                    executerUser = rs.getInt("id");

                }
                else if (rs.getString("username").equals(user)) {
                    argsUser = rs.getInt("id");
                }
                else {
                    p.sendMessage(ChatColor.RED + "Enter a valid username");
                }
            }
            // Close data table
            rs.close();
        } catch (SQLException e) {
            //Throws exeception
            e.printStackTrace();
        }

        // If friend ADD argument
        if (action.equals("add")) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_players", "test", "Test123!");

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO tbl_friends(user_id , friend_id) VALUES (?, ?)");

                stmt.setInt(1, executerUser);
                stmt.setInt(2, argsUser);

                stmt.executeUpdate();

                p.sendMessage(ChatColor.GREEN + "You have added " + user + " as a friend! Wait for him to accept.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (action.equals("remove")) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_players", "test", "Test123!");

                PreparedStatement stmt = conn.prepareStatement("DELETE FROM tbl_friends WHERE user_id = ? AND friend_id = ?");

                stmt.setInt(1, executerUser);
                stmt.setInt(2, argsUser);

                stmt.executeUpdate();

                p.sendMessage(ChatColor.GREEN + "You have removed " + user + " from you friend list!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (action.equals("accept")) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_players", "test", "Test123!");

                PreparedStatement stmt = conn.prepareStatement("UPDATE tbl_friends SET status = accepted WHERE user_id = ? AND friend_id = ?");

                stmt.setInt(1, executerUser);
                stmt.setInt(2, argsUser);

                stmt.executeUpdate();

                p.sendMessage(ChatColor.GREEN + "Friend request accepted from:" + argsUser);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (action.equals("deny")) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_players", "test", "Test123!");

                PreparedStatement stmt = conn.prepareStatement("UPDATE tbl_friends SET status = denied WHERE user_id = ? AND friend_id = ?");

                stmt.setInt(1, executerUser);
                stmt.setInt(2, argsUser);

                stmt.executeUpdate();

                p.sendMessage(ChatColor.GREEN + "Friend request from" + argsUser + "has been denied!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            p.sendMessage(ChatColor.RED + "Usage: /friend <action> <username> ");
        }


        // if sender is not player
        if (!(sender instanceof Player)) {
            p.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return true;
        }
        else {
            p.sendMessage(ChatColor.GREEN + "You have added XYZ as a friend!");
        }

        return true;
    }
}
