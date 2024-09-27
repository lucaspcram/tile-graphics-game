/* (C)2024 */
package com.lucascram.tilegraphicsgame.gamecore;

import com.lucascram.tilegraphicsgame.graphics.*;
import com.lucascram.tilegraphicsgame.input.*;
import com.lucascram.tilegraphicsgame.io.*;
import com.lucascram.tilegraphicsgame.states.*;
import java.awt.Graphics2D;

public class GameCore {

    private boolean isRunning;
    private ActionEngine actionEngine;
    private GameWindowManager gameWindowManager;
    private GameStateManager gameStateManager;

    public void init() {
        isRunning = true;

        actionEngine = new ActionEngine();

        gameWindowManager = new GameWindowManager();
        gameWindowManager.initWindow(actionEngine);

        gameStateManager = new GameStateManager(actionEngine);
        gameStateManager.advanceState(GameStateManager.LOADING_SCREEN_STATE_NAME);

        update();
    }

    public void update() {
        long startTime = System.currentTimeMillis();
        long currentTime = startTime;

        while (isRunning) {
            long elapsedTime = System.currentTimeMillis() - currentTime;
            currentTime += elapsedTime;

            gameStateManager.getCurrentState().update();
            gameStateManager.getCurrentState().updateAnimations(elapsedTime);

            Graphics2D screenGraphics = gameWindowManager.getWindowGraphics();
            if (screenGraphics != null) {
                render(screenGraphics);
            }

            gameWindowManager.updateWindow();

            AbstractGameState currentState = gameStateManager.getCurrentState();
            if (currentState.shouldTerminate()) {
                currentState.setShouldTerminate(false);
                gameStateManager.resumePreviousState();
            } else if (gameStateManager.getCurrentState().shouldAdvance()) {
                currentState.setShouldAdvance(false);
                gameStateManager.advanceState(currentState.getNextStateName());
            }

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                DebugDumper.handleException(
                        null, DebugDumper.ERR_MESSAGE_GENERIC, e.getStackTrace(), e);
            }
        }
    }

    public void render(Graphics2D g2d) {
        gameStateManager.getCurrentState().render(g2d);
    }
}
