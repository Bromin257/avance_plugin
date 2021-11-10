package me.bromin.customcommands.commands;

import me.bromin.customcommands.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenMapSelector implements CommandExecutor {
    private final Main main;

    public OpenMapSelector(Main main) { this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("openmapselector")) {
                player.sendMessage(ChatColor.GREEN + "Map Selector Opened");
            } else {
                player.sendMessage(ChatColor.RED + "You don't have the required permission!");
            }
        } else {
            // Print out to console if not player
            main.getLogger().info("You have to be a player to execute this command");
        }
        return true;
    }
}
