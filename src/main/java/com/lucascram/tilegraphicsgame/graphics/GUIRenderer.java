package com.lucascram.tilegraphicsgame.graphics;

import java.awt.Graphics2D;

import com.lucascram.tilegraphicsgame.io.AnimationLoader;

public class GUIRenderer {
	
	private Sprite guiPanel;
	
	public GUIRenderer() {
		guiPanel = new Sprite(0, 660, 1280, 60, true);
		guiPanel.addAnimation(AnimationLoader.getAnimation(AnimationLoader.GUI_PANEL));
		guiPanel.setAnimation(0);
	}
	
	public void renderBackground(Graphics2D g2d) {
		
	}
	
	public void renderGUIPanel(Graphics2D g2d) {
		guiPanel.render(g2d);
	}
}
