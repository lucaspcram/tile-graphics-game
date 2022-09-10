package com.lucascram.tilegraphicsgame.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.lucascram.tilegraphicsgame.graphics.Font;
import com.lucascram.tilegraphicsgame.graphics.Sprite;
import com.lucascram.tilegraphicsgame.input.ActionEngine;
import com.lucascram.tilegraphicsgame.io.AnimationLoader;

public class GameOverState extends AbstractGameState {
	
	private Sprite panel1;
	private Sprite panel2;
	
	private Font font;
	
	public GameOverState(String stateName, ActionEngine actionEngine) {
		super(stateName, actionEngine);
	}

	
	public void onInit() {
		panel1 = new Sprite(265, 90, 780, 100, true);
		panel1.addAnimation(AnimationLoader.getAnimation(AnimationLoader.BG_PANEL));
		panel1.setAnimation(0);
		
		panel2 = new Sprite(445, 570, 390, 90, true);
		panel2.addAnimation(AnimationLoader.getAnimation(AnimationLoader.BG_PANEL));
		panel2.setAnimation(0);
		
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
		if(getActionEngine().getGameAction(ActionEngine.ADVANCE).isUnhandled()) {
			getActionEngine().getGameAction(ActionEngine.ADVANCE).handle();
			setNextStateName(GameStateManager.SCORE_SCREEN_STATE_NAME);
			setShouldAdvance(true);
		}
	}

	public void updateAnimations(long elapsedTime) {
		
	}

	public void render(Graphics2D g2d) {
		panel1.render(g2d);
		panel2.render(g2d);
		
		font.render(g2d, "GAME OVER", 280, 100, 80);
		font.render(g2d, "PRESS ENTER", 480, 600, 30);
	}

}
