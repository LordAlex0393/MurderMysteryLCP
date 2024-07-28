package org.lordalex.murdermysterylcp.Events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.lordalex.murdermysterylcp.Config.YmlParser;
import org.lordalex.murdermysterylcp.MurderMysteryLCP;
import org.lordalex.murdermysterylcp.Config.GameState;
import org.lordalex.murdermysterylcp.Utils.ColorUtil;
import org.lordalex.murdermysterylcp.Utils.GameUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static org.lordalex.murdermysterylcp.Utils.CustomScoreboard.setWaitingScoreboard;

public class Events implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
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
        p.setCustomName(p.getName());
        p.setCustomNameVisible(true);
        Location loc = YmlParser.parseLocation(p.getWorld(), MurderMysteryLCP.config.getLobby());
        p.teleport(loc);

        if(MurderMysteryLCP.game.getState() == GameState.WAITING){
            p.setGameMode(GameMode.ADVENTURE);
            int online = Bukkit.getOnlinePlayers().size();
            e.setJoinMessage(ColorUtil.getMessage("[" + online + "/" + MurderMysteryLCP.config.getPlayersToStart() + "] &e=> &fИгрок " + p.getName() + " подключился"));

            ItemStack compassStack = new ItemStack(Material.COMPASS, 1);
            ItemMeta compassMeta = compassStack.getItemMeta();
            compassMeta.setDisplayName(ColorUtil.getMessage("&f >>&e&l Вернуться в лобби&f <<"));
            compassStack.setItemMeta(compassMeta);
            p.getInventory().setItem(8, compassStack);

            if (Bukkit.getOnlinePlayers().size() >= MurderMysteryLCP.config.getPlayersToStart()) {
                GameUtil.start();
            }

            for (Player all : Bukkit.getOnlinePlayers()) {
                setWaitingScoreboard(all,online);
            }
        }
        else if(MurderMysteryLCP.game.getState() == GameState.GAME){
            p.setGameMode(GameMode.SPECTATOR);
            p.setPlayerListName(null);
            e.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.SPECTATOR) {
            e.setQuitMessage(null);
        }
        else {
            int online = Bukkit.getOnlinePlayers().size();
            e.setQuitMessage(ColorUtil.getMessage("[" + (online-1) + "/" + MurderMysteryLCP.config.getPlayersToStart() + "] &e<= &fИгрок " + p.getName() + " вышел"));
        }
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();
        e.setCancelled(true);
        if (p.getName().equals("_Lord_Alex_")) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage(ColorUtil.getMessage("&3[Dev] " + p.getName() + ": &f" + msg));
            }
        } else {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage(ColorUtil.getMessage("&7" + p.getName() + ": &f" + msg));
            }
        }
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent e) {
        if (e == null || e.getItem() == null) return;
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getItem().getType() == Material.COMPASS) {
            e.getPlayer().sendMessage(ChatColor.RED +  "Ты куда собрался?");
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
            Player player = ((Player) event.getEntity()).getPlayer();
            player.sendMessage(ChatColor.RED +  "Ай!");
            event.setCancelled(true);
        }
        //event.setDamage(20000);
    }

    @EventHandler
    public void PickupItem(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();

        if (e.getItem().getItemStack().getType() == Material.GOLD_INGOT){
            if(!player.getInventory().contains(Material.GOLD_INGOT)){
                player.getInventory().setItem(8, new ItemStack(Material.GOLD_INGOT, 1));
                e.setCancelled(true);
            }
            else{
                //player.getInventory().setItem(8, new ItemStack(Material.GOLD_INGOT, player.getInventory().getItem(8).getAmount()+1));
            }
            player.sendMessage(String.valueOf(player.getInventory().getItem(8).getAmount()));

            if(player.getInventory().getItem(8).getAmount()==9){
                if(!player.getInventory().contains(Material.BOW)){
                    ItemStack bowStack = new ItemStack(Material.BOW, 1);
                    ItemMeta bowMeta = bowStack.getItemMeta();
                    bowMeta.setDisplayName(ChatColor.GREEN + "Лук");
                    List<String> bowList = new ArrayList<>();
                    bowList.add(ChatColor.GRAY + "Используйте лук, чтобы убить маньяка");
                    bowMeta.setLore(bowList);
                    bowMeta.spigot().setUnbreakable(true);
                    bowStack.setItemMeta(bowMeta);
                    player.getInventory().setItem(1, bowStack);
                }
                ItemStack arrowStack = new ItemStack(Material.ARROW, 1);
                player.getInventory().setItem(2, arrowStack);
            }
        }
    }


    @EventHandler
    public void projectileCreating(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null) return;
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getItem().getType() == Material.IRON_SWORD) {
            p.launchProjectile(Snowball.class);
        }
        if (e.getItem().getType() == Material.FIREBALL) {
            p.launchProjectile(Fireball.class);
        }
        if (e.getItem().getType() == Material.BLAZE_ROD) {
            p.launchProjectile(WitherSkull.class);
        }
        if (e.getItem().getType() == Material.DIAMOND_SWORD) {
            p.launchProjectile(Arrow.class);
        }
    }

}
