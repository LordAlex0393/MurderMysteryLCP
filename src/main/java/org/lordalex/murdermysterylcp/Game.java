package org.lordalex.murdermysterylcp;

import org.lordalex.murdermysterylcp.Config.BowOwner;
import org.lordalex.murdermysterylcp.Config.GameState;
import org.lordalex.murdermysterylcp.Utils.CustomScoreboard;

public class Game {
    private final MurderMysteryLCP plugin;
    private GameState state;
    private BowOwner bowOwner;
    private boolean ALLOW_WARP;
    private int GAME_DURATION;
    private float GOLD_RATE;
    private int MAX_GOLD;
    private float BOW_RECHARGE;
    private int SHOT_COST;
    private float SWORD_RECHARGE;
    private float GIVE_WEAPON_DELAY;
    private int MURDER_COUNT;
    private int MURDER_SPEED;
    private int DETECTIVE_SPEED;
    private int INNOCENT_SPEED;

    public Game(MurderMysteryLCP plugin, GameState state) {
        this.plugin = plugin;
        this.state = state;
        bowOwner = BowOwner.DETECTIVE;

        ALLOW_WARP = true;
        GAME_DURATION = 5;
        GOLD_RATE = 1;
        MAX_GOLD = 16;
        BOW_RECHARGE = 5;
        SHOT_COST = 10;
        SWORD_RECHARGE = 5;
        GIVE_WEAPON_DELAY = 15;
        MURDER_COUNT = 1;
        MURDER_SPEED = 1;
        DETECTIVE_SPEED = 1;
        INNOCENT_SPEED = 1;
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

    public BowOwner getBowOwner() {
        return bowOwner;
    }

    public void setBowOwner(BowOwner bowOwner) {
        this.bowOwner = bowOwner;
    }

    public boolean isALLOW_WARP() {
        return ALLOW_WARP;
    }

    public void setALLOW_WARP(boolean ALLOW_WARP) {
        this.ALLOW_WARP = ALLOW_WARP;
    }

    public int getGAME_DURATION() {
        return GAME_DURATION;
    }

    public void setGAME_DURATION(int GAME_DURATION) {
        this.GAME_DURATION = GAME_DURATION;
    }

    public float getGOLD_RATE() {
        return GOLD_RATE;
    }

    public void setGOLD_RATE(float GOLD_RATE) {
        this.GOLD_RATE = GOLD_RATE;
    }

    public int getMAX_GOLD() {
        return MAX_GOLD;
    }

    public void setMAX_GOLD(int MAX_GOLD) {
        this.MAX_GOLD = MAX_GOLD;
    }

    public float getBOW_RECHARGE() {
        return BOW_RECHARGE;
    }

    public void setBOW_RECHARGE(float BOW_RECHARGE) {
        this.BOW_RECHARGE = BOW_RECHARGE;
    }

    public int getSHOT_COST() {
        return SHOT_COST;
    }

    public void setSHOT_COST(int SHOT_COST) {
        this.SHOT_COST = SHOT_COST;
    }

    public float getSWORD_RECHARGE() {
        return SWORD_RECHARGE;
    }

    public void setSWORD_RECHARGE(float SWORD_RECHARGE) {
        this.SWORD_RECHARGE = SWORD_RECHARGE;
    }

    public float getGIVE_WEAPON_DELAY() {
        return GIVE_WEAPON_DELAY;
    }

    public void setGIVE_WEAPON_DELAY(float GIVE_WEAPON_DELAY) {
        this.GIVE_WEAPON_DELAY = GIVE_WEAPON_DELAY;
    }

    public int getMURDER_COUNT() {
        return MURDER_COUNT;
    }

    public void setMURDER_COUNT(int MURDER_COUNT) {
        this.MURDER_COUNT = MURDER_COUNT;
    }

    public int getMURDER_SPEED() {
        return MURDER_SPEED;
    }

    public void setMURDER_SPEED(int MURDER_SPEED) {
        this.MURDER_SPEED = MURDER_SPEED;
    }

    public int getDETECTIVE_SPEED() {
        return DETECTIVE_SPEED;
    }

    public void setDETECTIVE_SPEED(int DETECTIVE_SPEED) {
        this.DETECTIVE_SPEED = DETECTIVE_SPEED;
    }

    public int getINNOCENT_SPEED() {
        return INNOCENT_SPEED;
    }

    public void setINNOCENT_SPEED(int INNOCENT_SPEED) {
        this.INNOCENT_SPEED = INNOCENT_SPEED;
    }
}
