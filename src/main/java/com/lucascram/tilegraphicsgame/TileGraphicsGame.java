package com.lucascram.tilegraphicsgame;

import com.lucascram.tilegraphicsgame.gamecore.GameCore;

public class TileGraphicsGame {
    public static final String TITLE = "Survive!";
    public static final String VERSION = "alpha2.0";
    public static final String AUTHOR = "Lucas Cram";
    public static void main(String[] args) {
        new GameCore().init();
    }
}
