package org.lordalex.murdermysterylcp;

import org.lordalex.murdermysterylcp.Config.BowOwner;
import org.lordalex.murdermysterylcp.Config.GameState;
import org.lordalex.murdermysterylcp.Utils.CustomScoreboard;

public class Game {
    private final MurderMysteryLCP plugin;
    private GameState state;
    private BowOwner bowOwner;
    public Game(MurderMysteryLCP plugin, GameState state) {
        this.plugin = plugin;
        this.state = state;
        bowOwner = BowOwner.DETECTIVE;
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
}
