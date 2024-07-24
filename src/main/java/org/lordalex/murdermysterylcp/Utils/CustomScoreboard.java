package org.lordalex.murdermysterylcp.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.lordalex.murdermysterylcp.Config.GameState;
import org.lordalex.murdermysterylcp.Config.PlayerInfo;
import org.lordalex.murdermysterylcp.Config.Role;
import org.lordalex.murdermysterylcp.MurderMysteryLCP;

import java.util.ArrayList;

public class CustomScoreboard {
    public static Scoreboard createScoreboard(ArrayList<String> scores) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(ColorUtil.getMessage("&bMurderMystery"), "Test");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int i = 2 + scores.size();
        for(String str : scores){
            objective.getScore(str).setScore(i);
            i--;
        }
        objective.getScore(" ").setScore(2);
        objective.getScore(ColorUtil.getMessage("&a&lneVimeWorld.ru")).setScore(1);

        return scoreboard;
    }

    public static void setWaitingScoreboard(Player p, int online){
        ArrayList<String> scores = new ArrayList<>();
        scores.add("  ");
        scores.add("Карта: " + ChatColor.YELLOW + MurderMysteryLCP.config.getName());
        scores.add("Игроков: " + ChatColor.YELLOW + online + "/" + MurderMysteryLCP.config.getPlayersToStart());
        Scoreboard scoreboard = CustomScoreboard.createScoreboard(scores);
        p.setScoreboard(scoreboard);
    }
    public static void setStartingScoreboard(Player p, int online, int timer){
        ArrayList<String> scores = new ArrayList<>();
        scores.add("   ");
        scores.add("Карта: " + ChatColor.YELLOW + MurderMysteryLCP.config.getName());
        scores.add("Игроков: " + ChatColor.YELLOW + online + "/" + MurderMysteryLCP.config.getPlayersToStart());
        scores.add("  ");

        String timerString = "Начало через: " + ChatColor.YELLOW;
        timerString += timer < 10 ? "00:0" + timer : "00:" + timer;
        scores.add(timerString);

        Scoreboard scoreboard = CustomScoreboard.createScoreboard(scores);
        p.setScoreboard(scoreboard);
    }

    public static void setGamingScoreboard(PlayerInfo pi){
        ArrayList<String> scores = new ArrayList<>();
        scores.add("    ");

        if(pi.getRole() == Role.INNOCENT){
            scores.add("Ваша роль: " + ChatColor.GREEN + "Невинный");
        }
        else if(pi.getRole() == Role.DETECTIVE){
            scores.add("Ваша роль: " + ChatColor.AQUA + "Детектив");
        }
        else if(pi.getRole() == Role.MURDER){
            scores.add("Ваша роль: " + ChatColor.RED + "Маньяк");
        }

        scores.add("Осталось мирных: " + ChatColor.YELLOW + (Bukkit.getServer().getOnlinePlayers().size() - 1));
        scores.add("   ");
        scores.add(ChatColor.GREEN + "Детектив жив");
        scores.add("  ");
        scores.add("Карта: " + ChatColor.YELLOW + MurderMysteryLCP.config.getName());

        Scoreboard scoreboard = CustomScoreboard.createScoreboard(scores);
        pi.getPlayer().setScoreboard(scoreboard);
    }
}
