/* (C)2024 */
package com.lucascram.tilegraphicsgame.states;

import com.lucascram.tilegraphicsgame.graphics.Font;
import com.lucascram.tilegraphicsgame.graphics.GameWindowManager;
import com.lucascram.tilegraphicsgame.graphics.Sprite;
import com.lucascram.tilegraphicsgame.highscore.HighscoreManager;
import com.lucascram.tilegraphicsgame.highscore.Scorecard;
import com.lucascram.tilegraphicsgame.input.ActionEngine;
import com.lucascram.tilegraphicsgame.io.AnimationLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class ScoreScreenState extends AbstractGameState {

    private HighscoreManager scoreManager;
    private Font font;

    private Sprite dec1;
    private Sprite dec2;
    private Sprite dec3;

    public ScoreScreenState(String stateName, ActionEngine actionEngine) {
        super(stateName, actionEngine);
    }

    public void onInit() {
        scoreManager = new HighscoreManager();

        font = new Font();

        dec1 = new Sprite(0, 0, 0, 0, true);
        dec1.addAnimation(AnimationLoader.getAnimation(AnimationLoader.BEETLE_ALIVE));
        dec1.setAnimation(0);

        dec2 = new Sprite(0, 0, 0, 0, true);
        dec2.addAnimation(AnimationLoader.getAnimation(AnimationLoader.ANT_ALIVE));
        dec2.setAnimation(0);

        onResume();
    }

    public void onResume() {
        getActionEngine().bindKey(ActionEngine.ADVANCE, KeyEvent.VK_ENTER);
    }

    public void onTerminate() {
        onSuspend();
    }

    public void onSuspend() {
        getActionEngine().clearBindings();
        // SoundPlayer.loopTrack(SoundPlayer.TITLE_MUS);
    }

    public void update() {
        if (getActionEngine().getGameAction(ActionEngine.ADVANCE).isUnhandled()) {
            getActionEngine().getGameAction(ActionEngine.ADVANCE).handle();
            setNextStateName(GameStateManager.MAIN_MENU_STATE_NAME);
            setShouldAdvance(true);
        }
    }

    public void updateAnimations(long elapsedTime) {
        dec1.updateAnimation(elapsedTime);
        dec2.updateAnimation(elapsedTime);
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(
                Color.getHSBColor(0.1F, 1.0F, 0.2F)); // 0.1, 1.0, 0.2 in HSB is an earthy brown
        // g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, GameWindowManager.WINWIDTH, GameWindowManager.WINHEIGHT);

        dec1.debugFreeRender(g2d, 40, 250, 250, 250);
        dec2.debugFreeRender(g2d, 960, 270, 250, 250);

        font.render(g2d, "TOP SCORES", 230, 100, 80);
        font.render(g2d, "PRESS ENTER TO RETURN TO MENU", 410, 680, 15);

        for (int i = 0; i < 5; i++) {
            Scorecard score = scoreManager.getScorecard(i);
            if (score != null) {
                String toRenderName = score.getPlayerName();
                String toRenderScore = score.getScore() + "";
                font.render(g2d, toRenderName, 350, (70 * i) + 250, 30);
                font.render(g2d, toRenderScore, 850, (70 * i) + 250, 30);
            }
        }
    }
}
