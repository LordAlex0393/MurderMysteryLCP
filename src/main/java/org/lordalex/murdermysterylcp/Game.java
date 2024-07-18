package org.lordalex.murdermysterylcp;

import org.lordalex.murdermysterylcp.Utils.CustomScoreboard;
import org.lordalex.murdermysterylcp.Utils.GameState;

public class Game {
    private final MurderMysteryLCP plugin;
    private GameState state;
    private CustomScoreboard scoreboard;


    public Game(MurderMysteryLCP plugin, GameState state) {
        this.plugin = plugin;
        this.state = state;
    }
    public MurderMysteryLCP getPlugin() {
        return plugin;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public CustomScoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(CustomScoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

}
