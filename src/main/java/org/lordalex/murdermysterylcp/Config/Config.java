package org.lordalex.murdermysterylcp.Config;

import java.util.List;

public class Config {
    private String name;
    private String world;
    private String lobby;
    private int playersToStart;
    private int goldFrequency;
    private List<String> goldSpawns;
    private List<String> playerSpawns;
    private List<String> extraLoc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getLobby() {
        return lobby;
    }

    public void setLobby(String lobby) {
        this.lobby = lobby;
    }

    public int getPlayersToStart() {
        return playersToStart;
    }

    public void setPlayersToStart(int playersToStart) {
        this.playersToStart = playersToStart;
    }

    public int getGoldFrequency() {
        return goldFrequency;
    }

    public void setGoldFrequency(int goldFrequency) {
        this.goldFrequency = goldFrequency;
    }

    public List<String> getGoldSpawns() {
        return goldSpawns;
    }

    public void setGoldSpawns(List<String> goldSpawns) {
        this.goldSpawns = goldSpawns;
    }

    public List<String> getPlayerSpawns() {
        return playerSpawns;
    }

    public void setPlayerSpawns(List<String> playerSpawns) {
        this.playerSpawns = playerSpawns;
    }

    public List<String> getExtraLoc() {
        return extraLoc;
    }

    public void setExtraLoc(List<String> extraLoc) {
        this.extraLoc = extraLoc;
    }
}
