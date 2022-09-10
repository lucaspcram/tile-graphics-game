package com.lucascram.tilegraphicsgame.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AbstractPanel {
	
	private int 			screenXPos;
	private int 			screenYPos;
	private int 			pixelWidth;
	private int 			pixelHeight;
	private BufferedImage 	panelImage;
	
	public AbstractPanel(int screenXPos, int screenYPos, int pixelWidth, int pixelHeight) {
		this.screenXPos = screenXPos;
		this.screenYPos = screenYPos;
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;
		panelImage = new BufferedImage(pixelWidth, pixelHeight, BufferedImage.TYPE_3BYTE_BGR);
	}
	
	public int getScreenXPos() {
		return screenXPos;
	}
	
	public int getScreenYPos() {
		return screenYPos;
	}
	
	public int getPixelWidth() {
		return pixelWidth;
	}
	
	public int getPixelHeight() {
		return pixelHeight;
	}
	
	public void renderPanel(Graphics2D g2d) {
		g2d.drawImage(panelImage, screenXPos, screenYPos, pixelWidth, pixelHeight, null);
	}
	
	public Graphics2D getPanelGraphics() {
		return panelImage.createGraphics();
	}
}
