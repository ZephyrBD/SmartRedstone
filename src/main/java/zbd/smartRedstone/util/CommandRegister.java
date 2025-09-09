package zbd.smartRedstone.util;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.configuration.file.FileConfiguration;
import zbd.smartRedstone.SmartRedstone;

public class CommandRegister {

    private final SmartRedstone plugin;
    private final FileConfiguration config;

    public CommandRegister(SmartRedstone plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void registerCommands() {
        new CommandAPICommand("smartredstone")
                .withPermission("smartredstone.admin")
                .withSubcommand(new CommandAPICommand("reload")
                        .executes((sender, args) -> {
                            plugin.reloadConfig();
                            String msg = plugin.getConfig().getString("translation.reload", "Config reloaded!");
                            sender.sendMessage(msg);
                        }))
                .register();
    }
}
