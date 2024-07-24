package org.lordalex.murdermysterylcp;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.lordalex.murdermysterylcp.Commands.GameCommand;
import org.lordalex.murdermysterylcp.Config.Config;
import org.lordalex.murdermysterylcp.Config.GameState;
import org.lordalex.murdermysterylcp.Events.Events;
import org.lordalex.murdermysterylcp.Config.YmlParser;

import java.io.File;

public final class MurderMysteryLCP extends JavaPlugin {
    private static Plugin instance;
    public static Config config;
    public static Game game;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        getCommand("game").setExecutor(new GameCommand());
        File file = new File("config.yml");
        config = YmlParser.parseMapConfig(file);

        game = new Game(this, GameState.WAITING);


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
