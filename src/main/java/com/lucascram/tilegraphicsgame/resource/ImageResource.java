package com.lucascram.tilegraphicsgame.resource;

import java.awt.image.BufferedImage;

public class ImageResource extends AbstractResource {

	private BufferedImage		image;
	
	public ImageResource(String path, String name, BufferedImage image) {
		super(path, name);
		this.image = image;
	}
	
	
	/*
	 *  Accessors and Mutators
	 */
	
	public BufferedImage getImage() {
		return image;
	}
}
