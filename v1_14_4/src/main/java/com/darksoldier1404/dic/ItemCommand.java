package com.darksoldier1404.dic;

import com.darksoldier1404.dic.commands.DICCommand;
import com.darksoldier1404.dic.events.DICEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class ItemCommand extends JavaPlugin {
    private static ItemCommand plugin;
    public static final String prefix = "§f[ §aDIC §f] ";
    public static final HashSet<String> cooldown = new HashSet<>();

    public static ItemCommand getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        plugin.getServer().getPluginManager().registerEvents(new DICEvent(), plugin);
        getCommand("dic").setExecutor(new DICCommand());
    }
}
