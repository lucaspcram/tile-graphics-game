package com.lucascram.tilegraphicsgame.tiles;

import com.lucascram.tilegraphicsgame.graphics.Sprite;
import com.lucascram.tilegraphicsgame.io.AnimationLoader;

public class GrassTile extends AbstractTile {
	
	public static final String TILE_TYPE_NAME = "GrassTile";
	public static final int DEFAULT_ANIM = 0;
	public static final int TALL_DEFAULT_ANIM = 0;
	
	private Sprite		tallGrassSprite;
	
	public GrassTile() {
		super();
		tallGrassSprite = new Sprite(0, 0, false);
	}
	
	public GrassTile(int xPos, int yPos) {
		super(xPos, yPos);
		tallGrassSprite = new Sprite(xPos, yPos, false);
		loadTileAnimations();
	}
	
	public GrassTile(int xPos, int yPos, boolean absolute) {
		super(xPos, yPos, absolute);
		tallGrassSprite = new Sprite(xPos, yPos, absolute);
		loadTileAnimations();
	}
	
	private void loadTileAnimations() {
		getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.GRASS_TILE));
		getSprite().setAnimation(DEFAULT_ANIM);
		
		tallGrassSprite.addAnimation(AnimationLoader.getAnimation(AnimationLoader.GRASS_COVER));
		tallGrassSprite.setAnimation(TALL_DEFAULT_ANIM);
	}
	
	public void update() {
		
	}
	
	public void updateAnimation(long elapsedTime) {
		tallGrassSprite.updateAnimation(elapsedTime);
		super.updateAnimation(elapsedTime);
	}
	
	public Sprite getTallGrassSprite() {
		return tallGrassSprite;
	}
	
	public String getTileTypeName() {
		return TILE_TYPE_NAME;
	}
}
