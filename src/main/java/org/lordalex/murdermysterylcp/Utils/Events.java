package org.lordalex.murdermysterylcp.Utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.lordalex.murdermysterylcp.MurderMysteryLCP;

import static org.lordalex.murdermysterylcp.Utils.GameUtil.updateWaitingScoreboard;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerLoginEvent e) {
        if(MurderMysteryLCP.game.getState() == GameState.STARTING){
            e.setKickMessage(ColorUtil.getMessage("&c" + e.getHostname()));
            e.disallow(PlayerLoginEvent.Result.KICK_FULL, ColorUtil.getMessage("Идет отсчёт до начала игры"));
        }
        else if(MurderMysteryLCP.game.getState() == GameState.ENDING){
            e.setKickMessage(ColorUtil.getMessage("&c" + e.getHostname()));
            e.disallow(PlayerLoginEvent.Result.KICK_FULL, ColorUtil.getMessage("Идет завершение игры"));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setHealth(20);
        p.setFoodLevel(20);
        Location loc = YmlParser.parseLocation(p.getWorld(), MurderMysteryLCP.config.getLobby());
        p.teleport(loc);

        if(MurderMysteryLCP.game.getState() == GameState.WAITING){
            p.setGameMode(GameMode.ADVENTURE);
            int online = Bukkit.getOnlinePlayers().size();
            e.setJoinMessage(ColorUtil.getMessage("[" + online + "/" + MurderMysteryLCP.config.getPlayersToStart() + "] &e=> &fИгрок " + p.getName() + " подключился"));

            if (Bukkit.getOnlinePlayers().size() >= MurderMysteryLCP.config.getPlayersToStart()) {
                GameUtil.start();
            }

            ItemStack compassStack = new ItemStack(Material.COMPASS, 1);
            ItemMeta compassMeta = compassStack.getItemMeta();
            compassMeta.setDisplayName(ColorUtil.getMessage("&f >>&e&l Вернуться в лобби&f <<"));
            compassStack.setItemMeta(compassMeta);
            p.getInventory().setItem(8, compassStack);

            for (Player all : Bukkit.getOnlinePlayers()) {
                GameUtil.updateWaitingScoreboard(all, online);
            }
        }
        else if(MurderMysteryLCP.game.getState() == GameState.GAME){
            p.setGameMode(GameMode.SPECTATOR);
            p.setPlayerListName(null);
            e.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
            e.setCancelled(true);
        }
    }
}
