package com.lucascram.tilegraphicsgame.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.lucascram.tilegraphicsgame.graphics.Font;
import com.lucascram.tilegraphicsgame.graphics.GameWindowManager;
import com.lucascram.tilegraphicsgame.input.ActionEngine;
import com.lucascram.tilegraphicsgame.io.AnimationLoader;
import com.lucascram.tilegraphicsgame.io.DebugDumper;
import com.lucascram.tilegraphicsgame.io.ImageLoader;
import com.lucascram.tilegraphicsgame.sound.SoundPlayer;

public class LoadingScreenState extends AbstractGameState {
	
	private Font font;

	public LoadingScreenState(String stateName, ActionEngine actionEngine) {
		super(stateName, actionEngine);
	}
	
	public void onInit() {
		font = new Font();
	}
	
	public void onResume() {
		
	}
	
	public void onTerminate() {
		
	}
	
	public void onSuspend() {
		ImageLoader.loadImages();
		//SoundPlayer.loadMusic();
		AnimationLoader.loadAnimations();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			DebugDumper.handleException(null, DebugDumper.ERR_MESSAGE_GENERIC, e.getStackTrace(), e);
		}
	}
	
	public void update() {
		setNextStateName(GameStateManager.TITLE_SCREEN_STATE_NAME);
		setShouldAdvance(true);
	}
	
	public void updateAnimations(long elapsedTime) {
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GameWindowManager.WINWIDTH, GameWindowManager.WINHEIGHT);
		
		font.render(g2d, "LOADING", 485, 330, 40);
	}
}
