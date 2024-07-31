package com.altoflora;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.altoflora.utils.Axios;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.OfflinePlayer;

public final class Kore extends JavaPlugin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName().toLowerCase()) {
            case "micuenta":
                return handleMiCuentaCommand(sender, args);
            case "playerinfo":
                return handlePlayerInfoCommand(sender, args);
            case "playeroffline":
                return handlePlayerOfflineCommand(sender, args);
            default:
                return false;
        }
    }

    private boolean handleMiCuentaCommand(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Uso correcto: /micuenta <nombre>");
            return false;
        }

        Map<String, String> jsonBody = Map.of("key", args[0]);
        Map<String, String> headers = Map.of(
                "Accept", "application/json",
                "Content-Type", "application/json",
                "Authorization", "Bearer 123"
        );

        String jsonBodyString = new Gson().toJson(jsonBody);
        Axios axios = new Axios();

        axios.put("https://webhook.site/d7f02b67-52fa-4702-8433-72109a83a2d8", jsonBodyString, headers)
                .thenAccept(response -> {
                    System.out.println("PUT Response: " + response);
                    sender.sendMessage("Response: " + response);
                }).join();

        sender.sendMessage("Tu cuenta es " + args[0]);
        return true;
    }

    private boolean handlePlayerInfoCommand(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Uso correcto: /playerinfo <nombre>");
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            sender.sendMessage("El jugador " + args[0] + " no está en línea.");
            return false;
        }

        Map<String, Object> playerInfo = Map.of(
                "nombre", targetPlayer.getName(),
                "vida", targetPlayer.getHealth(),
                "comida", targetPlayer.getFoodLevel(),
                "nivel", targetPlayer.getLevel()
        );

        String playerInfoJson = new Gson().toJson(playerInfo);
        sender.sendMessage(playerInfoJson);
        return true;
    }

    private boolean handlePlayerOfflineCommand(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Uso correcto: /playeroffline <nombre>");
            return false;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
        if (!offlinePlayer.hasPlayedBefore()) {
            sender.sendMessage("El jugador " + args[0] + " no existe.");
            return false;
        }

        Map<String, Object> playerInfo = Map.of(
                "nombre", offlinePlayer.getName(),
                "uuid", offlinePlayer.getUniqueId().toString(),
                "online", offlinePlayer.isOnline(),
                "banned", offlinePlayer.isBanned(),
                "whitelisted", offlinePlayer.isWhitelisted(),
                "op", offlinePlayer.isOp(),
                "firstPlayed", offlinePlayer.getFirstPlayed(),
                "lastPlayed", offlinePlayer.getLastPlayed(),
                "timePlayed", offlinePlayer.getStatistic(org.bukkit.Statistic.PLAY_ONE_MINUTE),
                "kills", offlinePlayer.getStatistic(org.bukkit.Statistic.PLAYER_KILLS)
        );

        String playerInfoJson = new Gson().toJson(playerInfo);
        sender.sendMessage(playerInfoJson);
        return true;
    }
}