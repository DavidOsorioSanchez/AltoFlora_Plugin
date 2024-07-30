package com.altoflora.utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

public class Minecraft {

    public static String getPlayerInfo(Player player) {
        return "Name: " + player.getName() + ", UUID: " + player.getUniqueId();
    }

    public static String getPlayerInventory(Player player) {
        return player.getInventory().toString();
    }

    public static String getPlayerLocation(Player player) {
        return player.getLocation().toString();
    }

    public static double getPlayerHealth(Player player) {
        return player.getHealth();
    }

    public static String getPlayerStats(Player player) {
        return "Health: " + getPlayerHealth(player) + ", Location: " + getPlayerLocation(player);
    }

    public static void sendCommand(CommandSender sender, String command) {
        Bukkit.dispatchCommand(sender, command);
    }
}