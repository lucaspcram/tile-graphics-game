package com.lucascram.tilegraphicsgame.tiles;

import com.lucascram.tilegraphicsgame.io.AnimationLoader;

public class AntHillTile extends AbstractTile {
	
	public static final String TILE_TYPE_NAME = "AntHillTile";
	public static final int	DEFAULT_ANIM = 0;
	
	public AntHillTile() {
		super();
	}
	
	public AntHillTile(int xPos, int yPos) {
		super(xPos, yPos);
		loadTileAnimations();
	}
	
	public AntHillTile(int xPos, int yPos, boolean absolute) {
		super(xPos, yPos, absolute);
		loadTileAnimations();
	}
	
	private void loadTileAnimations() {
		getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.ANT_HILL_TILE));
		getSprite().setAnimation(DEFAULT_ANIM);
	}
	
	public void update() {
		
	}
	
	public String getTileTypeName() {
		return TILE_TYPE_NAME;
	}
}
