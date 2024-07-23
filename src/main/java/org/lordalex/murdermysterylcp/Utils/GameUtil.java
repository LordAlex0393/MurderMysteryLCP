package org.lordalex.murdermysterylcp.Utils;

import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.lordalex.murdermysterylcp.MurderMysteryLCP;

import java.util.ArrayList;

import static org.lordalex.murdermysterylcp.Utils.YmlParser.parseLocation;

public class GameUtil {
    public static int DELAY = 3;
    private static int timer = DELAY;
    public static void start(){
        MurderMysteryLCP.game.setState(GameState.STARTING);

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.getInventory().clear();
        }


        new BukkitRunnable() {
            @Override
            public void run() {

                ScoreboardManager manager = Bukkit.getScoreboardManager();
                org.bukkit.scoreboard.Scoreboard scoreboard = manager.getNewScoreboard();

                Objective objective = scoreboard.registerNewObjective(ColorUtil.getMessage("&bMurderMystery"), "Test");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);

                int online = Bukkit.getOnlinePlayers().size();

                Score s7 = objective.getScore("   ");
                Score s6 = objective.getScore("Карта: " + ChatColor.YELLOW + MurderMysteryLCP.config.getName());
                Score s5 = objective.getScore("Игроков: " + ChatColor.YELLOW + online + "/" + MurderMysteryLCP.config.getPlayersToStart());
                Score s4 = objective.getScore("  ");
                Score s3 = timer<10? objective.getScore("Начало через: " + ChatColor.YELLOW + "00:0" + timer) : objective.getScore("Начало через: " + ChatColor.YELLOW + "00:" + timer);
                Score s2 = objective.getScore(" ");
                Score s1 = objective.getScore(ColorUtil.getMessage("&a&lneVimeWorld.ru"));
                s7.setScore(7);
                s6.setScore(6);
                s5.setScore(5);
                s4.setScore(4);
                s3.setScore(3);
                s2.setScore(2);
                s1.setScore(1);

                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.setScoreboard(scoreboard);
                }
                if(MurderMysteryLCP.game.getState() != GameState.STARTING){
                    timer = DELAY;
                    //interrupt();
                    cancel();
                }
                if(timer <= 0){
                    timer = DELAY;
                    game();
                    cancel();
                }
                timer--;
            }
        }.runTaskTimer(MurderMysteryLCP.getInstance(), 0, 20);

        if(MurderMysteryLCP.game.getState() != GameState.STARTING){
            timer = DELAY;
        }
    }

    public static void stop(){
        MurderMysteryLCP.game.setState(GameState.WAITING);
        int online = Bukkit.getOnlinePlayers().size();
        for (Player all : Bukkit.getOnlinePlayers()) {
            GameUtil.updateWaitingScoreboard(all, online);
        }
    }
    public static void game(){
        MurderMysteryLCP.game.setState(GameState.GAME);
        for(Player p : Bukkit.getServer().getWorld("world").getPlayers()){
            p.sendTitle(ColorUtil.getMessage("Роль:&c Маньяк"), ColorUtil.getMessage("&eУбейте всех игроков"));
        }

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(ColorUtil.getMessage("&bMurderMystery"), "Test");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score s9 = objective.getScore("    ");
        Score s8 = objective.getScore("Ваша роль: " + ChatColor.GREEN + "Невинный");
        Score s7 = objective.getScore("Осталось мирных: " + ChatColor.YELLOW + (Bukkit.getServer().getOnlinePlayers().size()-1));
        Score s6 = objective.getScore("   ");
        Score s5 = objective.getScore(ChatColor.GREEN + "Детектив жив");
        Score s4 = objective.getScore("  ");
        Score s3 = objective.getScore("Карта: " + ChatColor.YELLOW + MurderMysteryLCP.config.getName());
        Score s2 = objective.getScore(" ");
        Score s1 = objective.getScore(ColorUtil.getMessage("&a&lneVimeWorld.ru"));
        s9.setScore(9);
        s8.setScore(8);
        s7.setScore(7);
        s6.setScore(6);
        s5.setScore(5);
        s4.setScore(4);
        s3.setScore(3);
        s2.setScore(2);
        s1.setScore(1);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(scoreboard);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if(MurderMysteryLCP.game.getState() == GameState.GAME){
                    spawnGoldResourse();
                }
                else{
                    cancel();
                }
            }
        }.runTaskTimer(MurderMysteryLCP.getInstance(), MurderMysteryLCP.config.getGoldFrequency(), MurderMysteryLCP.config.getGoldFrequency());
    }

    private static void spawnGoldResourse(){
        World world = Bukkit.getServer().getWorld("world");

        Location loc = parseLocation(world, MurderMysteryLCP.config.getGoldSpawns().get((int)(Math.random() * MurderMysteryLCP.config.getGoldSpawns().size())));
//        for(String locs : MurderMysteryLCP.config.getGoldSpawns()){
//            Location loc = parseLocation(world, locs);
        for(Entity ent : Bukkit.getServer().getWorld("world").getEntities()){
            if(ent.getLocation().getBlockX() == loc.getBlockX() && ent.getLocation().getBlockY() == loc.getBlockY() && ent.getLocation().getBlockZ() == loc.getBlockZ()){
                return;
            }
        }
        Item dropitem = world.dropItem(loc, new ItemStack(Material.GOLD_INGOT, 1));
        dropitem.setVelocity(dropitem.getVelocity().zero());
    }

    public static void updateWaitingScoreboard(Player p, int online){
        ArrayList<String> scores = new ArrayList<>();
        scores.add("  ");
        scores.add("Карта: " + ChatColor.YELLOW + MurderMysteryLCP.config.getName());
        scores.add("Игроков: " + ChatColor.YELLOW + online + "/" + MurderMysteryLCP.config.getPlayersToStart());
        Scoreboard scoreboard = CustomScoreboard.createScoreboard(scores);
        p.setScoreboard(scoreboard);
    }

    public static void updateStartingScoreboard(Player p, int online){
        ArrayList<String> scores = new ArrayList<>();
        scores.add("  ");
        scores.add("Карта: " + ChatColor.YELLOW + MurderMysteryLCP.config.getName());
        scores.add("Игроков: " + ChatColor.YELLOW + online + "/" + MurderMysteryLCP.config.getPlayersToStart());
        Scoreboard scoreboard = CustomScoreboard.createScoreboard(scores);
        p.setScoreboard(scoreboard);
    }

}
