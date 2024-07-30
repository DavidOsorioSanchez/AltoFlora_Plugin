package com.altoflora;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.altoflora.utils.Minecraft;

public final class Kore extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("kore_player").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                if (args.length > 0) {
                    Player player = getServer().getPlayer(args[0]);
                    if (player != null) {
                        sender.sendMessage(Minecraft.getPlayerStats(player));
                    } else {
                        sender.sendMessage("Player not found.");
                    }
                    return true;
                }
                sender.sendMessage("Usage: /koreplayer <name>");
                return false;
            }
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}