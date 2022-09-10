package com.lucascram.tilegraphicsgame.io;

import java.awt.image.BufferedImage;

import com.lucascram.tilegraphicsgame.resource.ResourceManager;

public class ImageLoader {
	
	public static final String TITLE_IMG = "title_img";
	public static final String STAMINA_IMG = "StaminaImg";
	public static final String DECOY_IMG = "DecoyImg";
	
	private static ResourceManager resourceManager;
	 
	public static void loadImages() {
		resourceManager = new ResourceManager();
		
		//load the title image
		resourceManager.loadImage("res/img/gfx/title_logo.png", TITLE_IMG);
		
		//load the stamina bar img
		resourceManager.loadImage("res/img/gfx/stamina.png", STAMINA_IMG);
		
		//load the decoy bar img
		resourceManager.loadImage("res/img/entities/decoy1.png", DECOY_IMG);
	}
	
	public static BufferedImage getImage(String imageName) {
		BufferedImage img = null;
		
		try {
			img = resourceManager.getImageResource(imageName).getImage();
		} catch(NullPointerException e) {
			DebugDumper.handleException(null, DebugDumper.ERR_MESSAGE_RESOURCE, e.getStackTrace(), e);
		}
		
		return img;
	}
}
