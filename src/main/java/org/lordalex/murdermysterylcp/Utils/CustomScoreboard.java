package org.lordalex.murdermysterylcp.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.lordalex.murdermysterylcp.Config.GameState;
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
}
