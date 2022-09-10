package com.lucascram.tilegraphicsgame.graphics;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Sprite {
	
	private int 			xPos;
	private int 			yPos;
	private int 			width;
	private int 			height;
	private Animation 		currentAnimation;
	private List<Animation> animationList;
	
	public Sprite(int xPos, int yPos, boolean absoluteCoord) {
		if(absoluteCoord) {
			this.xPos = xPos;
			this.yPos = yPos;
		} else {
			this.xPos = WorldRenderer.convertToScreenXCoord(xPos);
			this.yPos = WorldRenderer.convertToScreenYCoord(yPos);
		}
		this.width = WorldRenderer.PIXEL_WIDTH;
		this.height = WorldRenderer.PIXEL_HEIGHT;
		
		animationList = new ArrayList<Animation>();
		currentAnimation = null;
	}
	
	public Sprite(int xPos, int yPos, int width, int height, boolean absoluteCoord) {
		if(absoluteCoord) {
			this.xPos = xPos;
			this.yPos = yPos;
		} else {
			this.xPos = WorldRenderer.convertToScreenXCoord(xPos);
			this.yPos = WorldRenderer.convertToScreenYCoord(yPos);
		}
		this.width = width;
		this.height = height;
		
		animationList = new ArrayList<Animation>();
		currentAnimation = null;
	}
	
	public void setAnimation(int index) {
		if(index >= animationList.size()) {
			return;
		}
		currentAnimation = animationList.get(index);
	}
	
	public void addAnimation(Animation animation) {
		animationList.add(animation);
	}
	
	public void update(int newX, int newY) {
		xPos = WorldRenderer.convertToScreenXCoord(newX);
		yPos = WorldRenderer.convertToScreenYCoord(newY);
	}
	
	public void updateAnimation(long elapsedTime) {
		if(currentAnimation != null) {
			currentAnimation.update(elapsedTime);
		}
	}
	
	public void render(Graphics2D g2d) {
		if(currentAnimation != null) {
			g2d.drawImage(currentAnimation.getImage(), xPos, yPos, width, height, null);
		}
	}
	
	public void debugFreeRender(Graphics2D g2d, int xPos, int yPos, int width, int height) {
		if(currentAnimation != null) {
			g2d.drawImage(currentAnimation.getImage(), xPos, yPos, width, height, null);
		}
	}
	
	public Animation getCurrentAnimation() {
		return currentAnimation;
	}
}
