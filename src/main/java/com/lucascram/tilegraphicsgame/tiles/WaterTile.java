package com.lucascram.tilegraphicsgame.tiles;

import com.lucascram.tilegraphicsgame.io.AnimationLoader;

public class WaterTile extends AbstractTile {
	
	public static final String TILE_TYPE_NAME = "WaterTile";
	public static final int	DEFAULT_ANIM = 0;
	
	public WaterTile() {
		super();
	}
	
	public WaterTile(int xPos, int yPos) {
		super(xPos, yPos);
		loadTileAnimations();
	}
	
	public WaterTile(int xPos, int yPos, boolean absolute) {
		super(xPos, yPos, absolute);
		loadTileAnimations();
	}
	
	private void loadTileAnimations() {
		getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.WATER_TILE));
		getSprite().setAnimation(DEFAULT_ANIM);
	}
	
	public void update() {
		
	}
	
	public String getTileTypeName() {
		return TILE_TYPE_NAME;
	}
}
