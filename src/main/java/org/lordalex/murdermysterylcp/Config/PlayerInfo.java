package org.lordalex.murdermysterylcp.Config;

import org.bukkit.entity.Player;

public class PlayerInfo {
    private final Player player;
    private Role role;
    private int gold = 0;
    private int kills = 0;
    private int shots = 0;

    public PlayerInfo(Player player) {
        this.player = player;
        this.role = Role.DETECTIVE;
    }

    public Player getPlayer() {
        return player;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }
}
