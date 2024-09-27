/* (C)2024 */
package com.lucascram.tilegraphicsgame.states;

import com.lucascram.tilegraphicsgame.graphics.Font;
import com.lucascram.tilegraphicsgame.graphics.GameWindowManager;
import com.lucascram.tilegraphicsgame.input.ActionEngine;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class ControlState extends AbstractGameState {

    private Font font;

    public ControlState(String stateName, ActionEngine actionEngine) {
        super(stateName, actionEngine);
    }

    public void onInit() {
        font = new Font();

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
    }

    public void update() {
        if (getActionEngine().getGameAction(ActionEngine.ADVANCE).isUnhandled()) {
            getActionEngine().getGameAction(ActionEngine.ADVANCE).handle();
            setNextStateName(GameStateManager.MAIN_MENU_STATE_NAME);
            setShouldAdvance(true);
        }
    }

    public void updateAnimations(long elapsedTime) {}

    public void render(Graphics2D g2d) {
        g2d.setColor(
                Color.getHSBColor(0.1F, 1.0F, 0.2F)); // 0.1, 1.0, 0.2 in HSB is an earthy brown
        g2d.fillRect(0, 0, GameWindowManager.WINWIDTH, GameWindowManager.WINHEIGHT);
        font.render(g2d, "CONTROLS", 100, 100, 40);
    }
}
