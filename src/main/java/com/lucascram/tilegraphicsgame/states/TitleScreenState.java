package com.lucascram.tilegraphicsgame.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.lucascram.tilegraphicsgame.graphics.Font;
import com.lucascram.tilegraphicsgame.graphics.GameWindowManager;
import com.lucascram.tilegraphicsgame.graphics.Sprite;
import com.lucascram.tilegraphicsgame.graphics.WorldRenderer;
import com.lucascram.tilegraphicsgame.input.ActionEngine;
import com.lucascram.tilegraphicsgame.io.AnimationLoader;
import com.lucascram.tilegraphicsgame.io.ImageLoader;
import com.lucascram.tilegraphicsgame.math.RandomUtility;
import com.lucascram.tilegraphicsgame.sound.SoundPlayer;

public class TitleScreenState extends AbstractGameState {
	
	private Font font;
	private Sprite[][] background;
	private Sprite panel;
	
	private final int BG_WIDTH = 80;
	private final int BG_HEIGHT = 40;
	
	public TitleScreenState(String stateName, ActionEngine actionEngine) {
		super(stateName, actionEngine);
	}
	
	public void onInit() {
		//SoundPlayer.loopTrack(SoundPlayer.TITLE_MUS);
		//SoundPlayer.modifyTrackVolume(SoundPlayer.TITLE_MUS, 1F);
		
		font = new Font();
		background = new Sprite[BG_WIDTH][BG_HEIGHT];
		panel = new Sprite(15*30, 19*30, 390, 90, true);
		panel.addAnimation(AnimationLoader.getAnimation(AnimationLoader.BG_PANEL));
		panel.setAnimation(0);
		
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
		getActionEngine().bindKey(ActionEngine.TITLE_START_GAME, KeyEvent.VK_ENTER);
	}
	
	public void onTerminate() {
		onSuspend();
	}
	
	public void onSuspend() {
		getActionEngine().clearBindings();
	}
	
	public void update() {
		if(getActionEngine().getGameAction(ActionEngine.TITLE_START_GAME).isUnhandled()) {
			getActionEngine().getGameAction(ActionEngine.TITLE_START_GAME).handle();
			setNextStateName(GameStateManager.MAIN_MENU_STATE_NAME);
			setShouldAdvance(true);
		}
	}
	
	public void updateAnimations(long elapsedTime) {
		for(int i = 0; i < BG_WIDTH; i++) {
			for(int j = 0; j < BG_HEIGHT; j++) {
				background[i][j].updateAnimation(elapsedTime);
			}
		}
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.getHSBColor(0.1F, 1.0F, 0.2F)); //0.1, 1.0, 0.2 in HSB is an earthy brown
		g2d.fillRect(0, 0, GameWindowManager.WINWIDTH, GameWindowManager.WINHEIGHT);
		
		for(int i = 0; i < BG_WIDTH; i++) {
			for(int j = 0; j < BG_HEIGHT; j++) {
				background[i][j].render(g2d);
			}
		}
		
		panel.render(g2d);
		
		g2d.drawImage(ImageLoader.getImage(ImageLoader.TITLE_IMG), 160, 80, 1000, 190, null);
		font.render(g2d, "PRESS ENTER", 480, 600, 30);
	}
}
