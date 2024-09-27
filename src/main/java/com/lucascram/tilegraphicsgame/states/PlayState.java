/* (C)2024 */
package com.lucascram.tilegraphicsgame.states;

import com.lucascram.tilegraphicsgame.global.NameContainer;
import com.lucascram.tilegraphicsgame.graphics.Font;
import com.lucascram.tilegraphicsgame.graphics.GUIRenderer;
import com.lucascram.tilegraphicsgame.graphics.GameWindowManager;
import com.lucascram.tilegraphicsgame.graphics.WorldRenderer;
import com.lucascram.tilegraphicsgame.highscore.HighscoreManager;
import com.lucascram.tilegraphicsgame.input.ActionEngine;
import com.lucascram.tilegraphicsgame.io.ImageLoader;
import com.lucascram.tilegraphicsgame.world.World;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class PlayState extends AbstractGameState {

    private World world;
    private WorldRenderer worldRenderer;
    private GUIRenderer guiRenderer;

    private Font testFont;
    private HighscoreManager scoreManager;

    public PlayState(String stateName, ActionEngine actionEngine) {
        super(stateName, actionEngine);
    }

    public void onInit() {
        // SoundPlayer.stopTrack(SoundPlayer.TITLE_MUS);

        world = new World(NameContainer.name);
        worldRenderer = new WorldRenderer(world);
        guiRenderer = new GUIRenderer();

        testFont = new Font();
        scoreManager = new HighscoreManager();

        world.generateWorld(false);

        onResume();
    }

    public void onResume() {
        getActionEngine().bindKey(ActionEngine.BEETLE_UP, KeyEvent.VK_W);
        getActionEngine().bindKey(ActionEngine.BEETLE_DOWN, KeyEvent.VK_S);
        getActionEngine().bindKey(ActionEngine.BEETLE_LEFT, KeyEvent.VK_A);
        getActionEngine().bindKey(ActionEngine.BEETLE_RIGHT, KeyEvent.VK_D);
        getActionEngine().bindKey(ActionEngine.BEETLE_REST, KeyEvent.VK_E);
        getActionEngine().bindKey(ActionEngine.BEETLE_DECOY, KeyEvent.VK_Q);
        getActionEngine().bindKey(ActionEngine.SKIP_LEVEL, KeyEvent.VK_ENTER);
    }

    public void onTerminate() {}

    public void onSuspend() {
        getActionEngine().clearBindings();
    }

    public void update() {

        if (world.getBeetleEntity().isAlive()) {
            if (getActionEngine().getGameAction(ActionEngine.BEETLE_UP).isUnhandled()) {
                getActionEngine().getGameAction(ActionEngine.BEETLE_UP).handle();
                world.updateWorld(
                        world.getBeetleEntity().getXPos(), world.getBeetleEntity().getYPos() - 1);
            }
            if (getActionEngine().getGameAction(ActionEngine.BEETLE_DOWN).isUnhandled()) {
                getActionEngine().getGameAction(ActionEngine.BEETLE_DOWN).handle();
                world.updateWorld(
                        world.getBeetleEntity().getXPos(), world.getBeetleEntity().getYPos() + 1);
            }
            if (getActionEngine().getGameAction(ActionEngine.BEETLE_LEFT).isUnhandled()) {
                getActionEngine().getGameAction(ActionEngine.BEETLE_LEFT).handle();
                world.updateWorld(
                        world.getBeetleEntity().getXPos() - 1, world.getBeetleEntity().getYPos());
            }
            if (getActionEngine().getGameAction(ActionEngine.BEETLE_RIGHT).isUnhandled()) {
                getActionEngine().getGameAction(ActionEngine.BEETLE_RIGHT).handle();
                world.updateWorld(
                        world.getBeetleEntity().getXPos() + 1, world.getBeetleEntity().getYPos());
            }
            if (getActionEngine().getGameAction(ActionEngine.BEETLE_REST).isUnhandled()) {
                getActionEngine().getGameAction(ActionEngine.BEETLE_REST).handle();
                world.updateWorld(
                        world.getBeetleEntity().getXPos(), world.getBeetleEntity().getYPos());
            }
            if (getActionEngine().getGameAction(ActionEngine.BEETLE_DECOY).isUnhandled()) {
                getActionEngine().getGameAction(ActionEngine.BEETLE_DECOY).handle();
                world.getBeetleEntity().deployDecoy(world);
            }
        }

        if (getActionEngine().getGameAction(ActionEngine.SKIP_LEVEL).isUnhandled()) {
            getActionEngine().getGameAction(ActionEngine.SKIP_LEVEL).handle();
            world.decrementScore();
            world.generateWorld(false);
        }
    }

    public void updateAnimations(long elapsedTime) {
        world.updateEntityAnimations(elapsedTime);
        world.updateTileAnimations(elapsedTime);

        if (!world.getBeetleEntity().isAlive()) {
            scoreManager.addScore(world.getName(), world.getScore());
            scoreManager.writeScoresToFile();
            setNextStateName(GameStateManager.GAME_OVER_STATE_NAME);
            setShouldAdvance(true);
        }
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(
                Color.getHSBColor(0.1F, 1.0F, 0.2F)); // 0.1, 1.0, 0.2 in HSB is an earthy brown
        g2d.fillRect(0, 0, GameWindowManager.WINWIDTH, GameWindowManager.WINHEIGHT);

        worldRenderer.renderMap(g2d);
        worldRenderer.renderEntity(g2d, world.getGoalEntity());
        if (world.getBeetleEntity().isDecoyDeployed()) {
            worldRenderer.renderEntity(g2d, world.getBeetleEntity().getDecoy());
        }
        for (int i = 0; i < world.getAntEntityList().size(); i++) {
            worldRenderer.renderEntity(g2d, world.getAntEntityList().get(i));
        }
        worldRenderer.renderEntity(g2d, world.getBeetleEntity());
        worldRenderer.renderGrassCovers(g2d);

        guiRenderer.renderGUIPanel(g2d);

        testFont.render(g2d, world.getScore() + "", 1150, 675, 30);

        renderStaminaBar(g2d);
        renderDecoyBar(g2d);
    }

    private void renderStaminaBar(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(400, 672, 465, 40);
        for (int i = 0; i < world.getBeetleEntity().getCurrentStamina(); i++) {
            g2d.drawImage(
                    ImageLoader.getImage(ImageLoader.STAMINA_IMG),
                    410 + (30 * i),
                    677,
                    30,
                    30,
                    null);
        }
        // testFont.render(g2d, world.getBeetleEntity().getCurrentStamina() + "", 350, 680, 25);
    }

    private void renderDecoyBar(Graphics2D g2d) {
        for (int i = 0; i < world.getBeetleEntity().getNumDecoys(); i++) {
            g2d.drawImage(
                    ImageLoader.getImage(ImageLoader.DECOY_IMG), 50 + (40 * i), 670, 40, 40, null);
        }
    }
}
