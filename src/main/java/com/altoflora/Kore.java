package com.altoflora;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.altoflora.utils.Axios;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public final class Kore extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("micuenta").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("micuenta")) return false;

        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("key", args[0]);
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer 123");

        String jsonBodyString = new Gson().toJson(jsonBody);

        Axios axios = new Axios();

        axios.put("https://webhook.site/d7f02b67-52fa-4702-8433-72109a83a2d8", jsonBodyString, headers)
                .thenAccept(response -> {
                    System.out.println("PUT Response: " + response);
                    sender.sendMessage("Response: " + response);
                }).join();

        if (args.length == 1) {
            sender.sendMessage("Tu cuenta es " + args[0]);
            return true;
        } else {
            sender.sendMessage("Uso correcto: /micuenta <nombre>");
            return false;
        }
    }
}