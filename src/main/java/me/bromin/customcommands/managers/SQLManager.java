package me.bromin.customcommands.managers;

import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SQLManager{
    private static SQLManager _instance = new SQLManager();
    Connection conn = null;

    private SQLManager() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_players", "test", "Test123!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
     }

    public static SQLManager getInstance() { return _instance; }

    public List<String> getFriendList(Player p){
        List<String> friendList = new ArrayList<>();

        try {
            // Get User ID by Player display name
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM tbl_users WHERE username = ?");

            stmt.setString(1, p.getDisplayName());
            stmt.executeQuery();

            ResultSet rs = stmt.executeQuery();
            rs.next();
            int userId = rs.getInt("id");
            System.out.println(userId); //Debug

            // Get all friend_id by user id
            PreparedStatement stmt2 = conn.prepareStatement("SELECT friend_id FROM tbl_friends WHERE user_id = ?");

            stmt2.setInt(1, userId);
            stmt2.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();

            while (rs2.next()) {

                int friendId = rs2.getInt("friend_id");
                System.out.println(friendId); //Debug

                PreparedStatement stmt3 = conn.prepareStatement("SELECT username FROM tbl_users WHERE id = ?");
                stmt3.setInt(1, friendId);
                ResultSet rs3 = stmt3.executeQuery();
                rs3.next();
                String username = rs3.getString("username");
                System.out.println(username); //Debug

                friendList.add(username);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return friendList;
    }
}
