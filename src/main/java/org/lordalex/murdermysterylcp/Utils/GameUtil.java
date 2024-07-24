package org.lordalex.murdermysterylcp.Utils;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.lordalex.murdermysterylcp.Config.GameState;
import org.lordalex.murdermysterylcp.Config.PlayerInfo;
import org.lordalex.murdermysterylcp.Config.Role;
import org.lordalex.murdermysterylcp.MurderMysteryLCP;

import java.util.ArrayList;

import static org.lordalex.murdermysterylcp.Config.YmlParser.parseLocation;
import static org.lordalex.murdermysterylcp.Utils.CustomScoreboard.*;

public class GameUtil {
    public static int DELAY = 5;
    private static int timer = DELAY;

    public static void start() {
        MurderMysteryLCP.game.setState(GameState.STARTING);

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.getInventory().clear();
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    setStartingScoreboard(all, Bukkit.getOnlinePlayers().size(), timer);
                }
                if (MurderMysteryLCP.game.getState() != GameState.STARTING) {
                    timer = DELAY;
                    interrupt();
                    cancel();
                }
                else if (timer <= 0) {
                    timer = DELAY;
                    game();
                    cancel();
                }
                timer--;
            }
        }.runTaskTimer(MurderMysteryLCP.getInstance(), 0, 20);
    }
    public static void interrupt() {
        MurderMysteryLCP.game.setState(GameState.WAITING);
        for (Player all : Bukkit.getOnlinePlayers()) {
            setWaitingScoreboard(all, Bukkit.getOnlinePlayers().size());
        }
    }

    public static void stop() {
        MurderMysteryLCP.game.setState(GameState.STARTING);
    }

    public static void game() {
        MurderMysteryLCP.game.setState(GameState.GAME);
        for(Player all : Bukkit.getServer().getWorld("world").getPlayers()){
            MurderMysteryLCP.players.add(new PlayerInfo(all));
        }
        for(PlayerInfo pi : MurderMysteryLCP.players){
            pi.setRole(Role.MURDER);
            break;
        }

        for(PlayerInfo pi : MurderMysteryLCP.players){

            if(pi.getRole() == Role.INNOCENT){
                pi.getPlayer().sendTitle(ColorUtil.getMessage("Роль:&a Невинный"), ColorUtil.getMessage("&eОставайтесь в живых как можно дольше"));
            }
            else if(pi.getRole() == Role.DETECTIVE){
                pi.getPlayer().sendTitle(ColorUtil.getMessage("Роль:&c Маньяк"), ColorUtil.getMessage("&eНайдите и убейте маньяка"));
            }
            else if(pi.getRole() == Role.MURDER){
                pi.getPlayer().sendTitle(ColorUtil.getMessage("Роль:&c Маньяк"), ColorUtil.getMessage("&eУбейте всех игроков"));
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        ItemStack swordStack = new ItemStack(Material.IRON_SWORD, 1);
                        swordStack.setDurability((short)999);
                        pi.getPlayer().getInventory().setItem(1, swordStack);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.sendTitle(ColorUtil.getMessage("&c&l Маньяк получил оружие"), ColorUtil.getMessage(" "));
                        }
                    }
                }.runTaskLater(MurderMysteryLCP.getInstance(), 100);
            }
            pi.getPlayer().setCustomName("Игрок");
            for (Player on : Bukkit.getServer().getOnlinePlayers()) {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "nick " + on.getName() + " off");
            }
            setGamingScoreboard(pi);
        }



        new BukkitRunnable() {
            @Override
            public void run() {
                if (MurderMysteryLCP.game.getState() == GameState.GAME) {
                    spawnGoldResourse();
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(MurderMysteryLCP.getInstance(), MurderMysteryLCP.config.getGoldFrequency(), MurderMysteryLCP.config.getGoldFrequency());
    }

    private static void spawnGoldResourse() {
        World world = Bukkit.getServer().getWorld("world");

        Location loc = parseLocation(world, MurderMysteryLCP.config.getGoldSpawns().get((int) (Math.random() * MurderMysteryLCP.config.getGoldSpawns().size())));
//        for(String locs : MurderMysteryLCP.config.getGoldSpawns()){
//            Location loc = parseLocation(world, locs);
        for (Entity ent : Bukkit.getServer().getWorld("world").getEntities()) {
            if (ent.getLocation().getBlockX() == loc.getBlockX() && ent.getLocation().getBlockY() == loc.getBlockY() && ent.getLocation().getBlockZ() == loc.getBlockZ()) {
                return;
            }
        }
        Item dropitem = world.dropItem(loc, new ItemStack(Material.GOLD_INGOT, 1));
        dropitem.setVelocity(dropitem.getVelocity().zero());
    }

    public static void updateStartingScoreboard(Player p, int online) {
        ArrayList<String> scores = new ArrayList<>();
        scores.add("  ");
        scores.add("Карта: " + ChatColor.YELLOW + MurderMysteryLCP.config.getName());
        scores.add("Игроков: " + ChatColor.YELLOW + online + "/" + MurderMysteryLCP.config.getPlayersToStart());
        Scoreboard scoreboard = CustomScoreboard.createScoreboard(scores);
        p.setScoreboard(scoreboard);
    }

}
