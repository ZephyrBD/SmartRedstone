package zbd.smartRedstone.util;

import dev.jorel.commandapi.CommandAPICommand;
import zbd.smartRedstone.SmartRedstone;

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
                            plugin.reloadConfig(); // 重新读取磁盘
                            String msg = plugin.getConfig().getString("translation.reload", "Config reloaded!");
                            sender.sendMessage(msg);
                        }))
                .register();
    }
}

