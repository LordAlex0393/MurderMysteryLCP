package org.lordalex.murdermysterylcp;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.lordalex.murdermysterylcp.Utils.Config;
import org.lordalex.murdermysterylcp.Utils.Events;
import org.lordalex.murdermysterylcp.Utils.YmlParser;

import java.io.File;

public final class MurderMysteryLCP extends JavaPlugin {
    private static Plugin instance;
    public static Config config;
    public static Game game;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        File file = new File("plugins\\MurderMysteryLCP\\config.yml");
        config = YmlParser.parseMapConfig(file);
        System.out.println(config.getLobby());


        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(World world : Bukkit.getServer().getWorlds()) {
                    world.setThundering(false);
                    world.setStorm(false);
                    world.setTime(3000);
                }
            }
        }, 0L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance(){
        return instance;
    }
}
