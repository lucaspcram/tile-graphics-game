package com.lucascram.tilegraphicsgame.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;

import com.lucascram.tilegraphicsgame.graphics.Font;
import com.lucascram.tilegraphicsgame.graphics.GUIRenderer;
import com.lucascram.tilegraphicsgame.graphics.GameWindowManager;
import com.lucascram.tilegraphicsgame.graphics.Sprite;
import com.lucascram.tilegraphicsgame.graphics.WorldRenderer;
import com.lucascram.tilegraphicsgame.input.ActionEngine;
import com.lucascram.tilegraphicsgame.io.AnimationLoader;
import com.lucascram.tilegraphicsgame.io.ImageLoader;
import com.lucascram.tilegraphicsgame.math.RandomUtility;
import com.lucascram.tilegraphicsgame.sound.SoundPlayer;

public class MainMenuState extends AbstractGameState {
	
	private static final int PLAY_OPTION = 0;
	private static final int CONTROL_OPTION = 1;
	private static final int EXIT_OPTION = 2;
	
	private int 			menuState;
	private GUIRenderer		guiRenderer;
	
	private Font font;
	private Sprite[][] background;
	private Sprite panel1;
	private Sprite selectorSprite;
	
	private final int BG_WIDTH = 80;
	private final int BG_HEIGHT = 40;
	
	public MainMenuState(String stateName, ActionEngine actionEngine) {
		super(stateName, actionEngine);
	}

	public void onInit() {
		guiRenderer = new GUIRenderer();
		
		font = new Font();
		background = new Sprite[BG_WIDTH][BG_HEIGHT];
		panel1 = new Sprite(0, 0, 0, 0, true);
		panel1.addAnimation(AnimationLoader.getAnimation(AnimationLoader.BG_PANEL_2));
		panel1.setAnimation(0);
		selectorSprite = new Sprite(0, 0, 0, 0, true);
		selectorSprite.addAnimation(AnimationLoader.getAnimation(AnimationLoader.BEETLE_ALIVE));
		selectorSprite.setAnimation(0);


		
		//generate dirt and grass
		for(int i = 0; i < BG_WIDTH; i++) {
			for(int j = 0; j < BG_HEIGHT; j++) {
				if(RandomUtility.percentChance(40)) {
					background[i][j] = new Sprite(i * WorldRenderer.PIXEL_WIDTH, j * WorldRenderer.PIXEL_HEIGHT, true);
					background[i][j].addAnimation(AnimationLoader.getAnimation(AnimationLoader.GRASS_TILE));
					background[i][j].setAnimation(0);
				} else {
					background[i][j] = new Sprite(i * WorldRenderer.PIXEL_WIDTH, j * WorldRenderer.PIXEL_HEIGHT, true);
					background[i][j].addAnimation(AnimationLoader.getAnimation(AnimationLoader.DIRT_TILE));
					background[i][j].setAnimation(0);
				}
			}
		}
				
		//generate some water and anthills
		for(int i = 0; i < BG_WIDTH; i++) {
			for(int j = 0; j < BG_HEIGHT; j++) {
				if(RandomUtility.percentChance(10)) {
					background[i][j] = new Sprite(i * WorldRenderer.PIXEL_WIDTH, j * WorldRenderer.PIXEL_HEIGHT, true);
					background[i][j].addAnimation(AnimationLoader.getAnimation(AnimationLoader.WATER_TILE));
					background[i][j].setAnimation(0);
				}
						
				if(RandomUtility.percentChance(1)) {
					background[i][j] = new Sprite(i * WorldRenderer.PIXEL_WIDTH, j * WorldRenderer.PIXEL_HEIGHT, true);
					background[i][j].addAnimation(AnimationLoader.getAnimation(AnimationLoader.ANT_HILL_TILE));
					background[i][j].setAnimation(0);
				} 
			}
		}
				
		//generate grass for contrast
		for(int i = 3; i < 40; i++) {
			for(int j = 2; j < 11; j++) {
				background[i][j] = new Sprite(i * WorldRenderer.PIXEL_WIDTH, j * WorldRenderer.PIXEL_HEIGHT, true);
				background[i][j].addAnimation(AnimationLoader.getAnimation(AnimationLoader.GRASS_TILE));
				background[i][j].setAnimation(0);
			}
		}
				
		//generate water tiles for contrast
		for(int i = 16; i < 27; i++) {
			for(int j = 19; j < 22; j++) {
				background[i][j] = new Sprite(i * WorldRenderer.PIXEL_WIDTH, j * WorldRenderer.PIXEL_HEIGHT, true);
				background[i][j].addAnimation(AnimationLoader.getAnimation(AnimationLoader.WATER_TILE));
				background[i][j].setAnimation(0);
			}
		}
		
		onResume();
	}
	
	public void onResume() {
		getActionEngine().bindKey(ActionEngine.MENU_UP, KeyEvent.VK_UP);
		getActionEngine().bindKey(ActionEngine.MENU_DOWN, KeyEvent.VK_DOWN);
		getActionEngine().bindKey(ActionEngine.MENU_SELECT, KeyEvent.VK_ENTER);
		
		menuState = PLAY_OPTION;
	}

	public void onTerminate() {
		onSuspend();
	}
	
	public void onSuspend() {
		getActionEngine().clearBindings();
		getActionEngine().handleActions();
	}
	
	public void update() {
		if(getActionEngine().getGameAction(ActionEngine.MENU_UP).isUnhandled()) {
			getActionEngine().getGameAction(ActionEngine.MENU_UP).handle();
			menuState--;
			if(menuState < PLAY_OPTION) {
				menuState = 2;
			}
		}
		
		if(getActionEngine().getGameAction(ActionEngine.MENU_DOWN).isUnhandled()) {
			getActionEngine().getGameAction(ActionEngine.MENU_DOWN).handle();
			menuState++;
			if(menuState > EXIT_OPTION) {
				menuState = 0;
			}
		}
		
		if(getActionEngine().getGameAction(ActionEngine.MENU_SELECT).isUnhandled()) {
			getActionEngine().getGameAction(ActionEngine.MENU_SELECT).handle();
			if(menuState == PLAY_OPTION) {
				setNextStateName(GameStateManager.NAME_ENTRY_STATE_NAME);
				setShouldAdvance(true);
			} else if(menuState == CONTROL_OPTION) {
				setNextStateName(GameStateManager.CONTROL_STATE_NAME);
				setShouldAdvance(true);
			} else if(menuState == EXIT_OPTION) {
				System.exit(0);
			}
		}
	}
	
	public void updateAnimations(long elapsedTime) {
		for(int i = 0; i < BG_WIDTH; i++) {
			for(int j = 0; j < BG_HEIGHT; j++) {
				background[i][j].updateAnimation(elapsedTime);
			}
		}
		selectorSprite.updateAnimation(elapsedTime);
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.getHSBColor(0.1F, 1.0F, 0.2F)); //0.1, 1.0, 0.2 in HSB is an earthy brown
		g2d.fillRect(0, 0, GameWindowManager.WINWIDTH, GameWindowManager.WINHEIGHT);
		
		for(int i = 0; i < BG_WIDTH; i++) {
			for(int j = 0; j < BG_HEIGHT; j++) {
				background[i][j].render(g2d);
			}
		}
		g2d.drawImage(ImageLoader.getImage(ImageLoader.TITLE_IMG), 160, 80, 1000, 190, null);
		
		panel1.debugFreeRender(g2d, 420, 330, 450, 350);
		//panel2.debugFreeRender(g2d, 300, 300, 100, 100);
		
		renderMenuOptions(g2d, menuState);
	}
	
	private void renderMenuOptions(Graphics2D g2d, int menuState) {
		
		if(menuState == PLAY_OPTION) {
			selectorSprite.debugFreeRender(g2d, 440, 395, 50, 50);
			selectorSprite.debugFreeRender(g2d, 660, 395, 50, 50);
			font.render(g2d, "PLAY", 500, 400, 40);
			font.render(g2d, "CONTROLS", 505, 500, 20);
			font.render(g2d, "EXIT", 505, 600, 20);
		} else if(menuState == CONTROL_OPTION) {
			selectorSprite.debugFreeRender(g2d, 440, 495, 50, 50);
			selectorSprite.debugFreeRender(g2d, 800, 495, 50, 50);
			font.render(g2d, "PLAY", 505, 400, 20);
			font.render(g2d, "CONTROLS", 485, 500, 40);
			font.render(g2d, "EXIT", 505, 600, 20);
		} else if(menuState == EXIT_OPTION) {
			selectorSprite.debugFreeRender(g2d, 440, 595, 50, 50);
			selectorSprite.debugFreeRender(g2d, 660, 595, 50, 50);
			font.render(g2d, "PLAY", 505, 400, 20);
			font.render(g2d, "CONTROLS", 505, 500, 20);
			font.render(g2d, "EXIT", 500, 600, 40);
		}
	}
	
	private void printAvailabeFonts() {
		System.out.println("****************************");
        System.out.println("**Listing Fonts Using Method From GraphicsEnvironment Class**");
        GraphicsEnvironment ge= null;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
 
        String[] fontnames = ge.getAvailableFontFamilyNames();
 
        for (int i = 0; i < fontnames.length; i++) {
            System.out.println(fontnames[i]);
        }
	}
}
