/* (C)2024 */
package com.lucascram.tilegraphicsgame.states;

import com.lucascram.tilegraphicsgame.global.NameContainer;
import com.lucascram.tilegraphicsgame.graphics.Font;
import com.lucascram.tilegraphicsgame.graphics.GameWindowManager;
import com.lucascram.tilegraphicsgame.graphics.Sprite;
import com.lucascram.tilegraphicsgame.gui.TextEntryField;
import com.lucascram.tilegraphicsgame.input.ActionEngine;
import com.lucascram.tilegraphicsgame.io.AnimationLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class NameEntryState extends AbstractGameState {

    private TextEntryField textField;
    private Font font;

    private Sprite dec1;
    private Sprite dec2;

    private boolean textFlicker = true;
    private int textFlickerTimer = 0;

    public NameEntryState(String stateName, ActionEngine actionEngine) {
        super(stateName, actionEngine);
    }

    public void onInit() {
        textField = new TextEntryField();
        font = new Font();

        dec1 = new Sprite(0, 0, 0, 0, true);
        dec1.addAnimation(AnimationLoader.getAnimation(AnimationLoader.BEETLE_ALIVE));
        dec1.setAnimation(0);

        dec2 = new Sprite(0, 0, 0, 0, true);
        dec2.addAnimation(AnimationLoader.getAnimation(AnimationLoader.ANT_ALIVE));
        dec2.setAnimation(0);

        if (!NameContainer.name.equals("BEETLE")) {
            textField.setContents(NameContainer.name);
            for (int i = 0; i < NameContainer.name.length(); i++) {
                textField.advancePosition();
            }
        }

        onResume();
    }

    public void onResume() {
        getActionEngine().bindKey(ActionEngine.ADVANCE, KeyEvent.VK_ENTER);
        getActionEngine().bindKey("BACK", KeyEvent.VK_BACK_SPACE);
        getActionEngine().bindKey("LEFT", KeyEvent.VK_LEFT);
        getActionEngine().bindKey("RIGHT", KeyEvent.VK_RIGHT);

        for (int i = 0; i < 26; i++) {
            getActionEngine().bindKey('A' + i + "", KeyEvent.VK_A + i);
        }
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

            if (textField.toString().equals("")) {
                NameContainer.name = "BEETLE";
            } else if (textField.toString() == null) {
                NameContainer.name = "FAIL";
            } else {
                NameContainer.name = textField.toString();
            }

            setNextStateName(GameStateManager.PLAY_STATE_NAME);
            setShouldAdvance(true);
        }

        if (getActionEngine().getGameAction("BACK").isUnhandled()) {
            getActionEngine().getGameAction("BACK").handle();
            textField.deleteCharacter();
        }

        if (getActionEngine().getGameAction("LEFT").isUnhandled()) {
            getActionEngine().getGameAction("LEFT").handle();
            textField.retreatPosition();
        }

        if (getActionEngine().getGameAction("RIGHT").isUnhandled()) {
            getActionEngine().getGameAction("RIGHT").handle();
            textField.advancePosition();
        }

        for (int i = 0; i < 26; i++) {
            if (getActionEngine().getGameAction('A' + i + "").isUnhandled()) {
                getActionEngine().getGameAction('A' + i + "").handle();
                textField.typeCharacter((char) getActionEngine().getActionMapping('A' + i + ""));
            }
        }
    }

    public void updateAnimations(long elapsedTime) {
        dec1.updateAnimation(elapsedTime);
        dec2.updateAnimation(elapsedTime);
        textFlickerTimer += elapsedTime;
        if (textFlickerTimer > 500) {
            textFlickerTimer = 0;
            textFlicker = !textFlicker;
        }
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(
                Color.getHSBColor(0.1F, 1.0F, 0.2F)); // 0.1, 1.0, 0.2 in HSB is an earthy brown
        g2d.fillRect(0, 0, GameWindowManager.WINWIDTH, GameWindowManager.WINHEIGHT);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(350, 289, 579, 60);

        g2d.setColor(Color.RED);
        g2d.drawRect(350, 289, 579, 60);

        dec1.debugFreeRender(g2d, 40, 250, 250, 250);
        dec2.debugFreeRender(g2d, 960, 270, 250, 250);

        if (textFlicker) {
            if (textField.getSize() == 0) {
                g2d.fill3DRect(352, 298, 5, 42, false);
            } else {
                g2d.fill3DRect(363 + (textField.getPosition() * 40), 298, 5, 42, false);
            }
        }

        font.render(g2d, "ENTER YOUR NAME", 270, 100, 50);
        font.render(g2d, "PRESS ENTER TO CONTINUE", 460, 600, 15);
        font.render(g2d, "DEFAULT NAME IS BEETLE", 465, 650, 15);

        textField.render(g2d, 365, 300, 40);
    }
}
