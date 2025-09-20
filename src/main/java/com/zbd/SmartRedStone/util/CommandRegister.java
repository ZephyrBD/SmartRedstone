package com.zbd.SmartRedStone.util;

import dev.jorel.commandapi.CommandAPICommand;
import com.zbd.SmartRedStone.SmartRedstone;

public class CommandRegister {

    private final SmartRedstone plugin;

    public CommandRegister(SmartRedstone plugin) {
        this.plugin = plugin;
    }

    public void registerCommands() {
        new CommandAPICommand("srs")
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

