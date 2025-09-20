package com.zbd.SmartRedStone;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import org.bukkit.plugin.java.JavaPlugin;
import com.zbd.SmartRedStone.listeners.BlockKicked;
import com.zbd.SmartRedStone.util.CommandRegister;

public final class SmartRedstone extends JavaPlugin {

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new BlockKicked(this), this);
        CommandAPI.onEnable();
        new CommandRegister(this).registerCommands();
        getLogger().info("SmartRedstone enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabling Redstonehelper2");
    }
}
