package com.lucascram.tilegraphicsgame.world;

import com.lucascram.tilegraphicsgame.tiles.AbstractTile;
import com.lucascram.tilegraphicsgame.tiles.AntHillTile;
import com.lucascram.tilegraphicsgame.tiles.DirtTile;
import com.lucascram.tilegraphicsgame.tiles.GrassTile;
import com.lucascram.tilegraphicsgame.tiles.WaterTile;

public class Map {
	
	private AbstractTile[][] 	tileMap;
	private int					worldWidth;
	private int					worldHeight;
	
	public Map(int worldWidth, int worldHeight) {
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		tileMap = new AbstractTile[worldWidth][worldHeight];
	}
	
	public AbstractTile getTileAt(int xPos, int yPos) {
		try {
			return tileMap[xPos][yPos];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void setTileAt(int xPos, int yPos, AbstractTile tile) {
		try {
			tileMap[xPos][yPos] = tile;
		} catch(ArrayIndexOutOfBoundsException e) {
			;
		}
	}
	
	public boolean isEdgeTile(int xPos, int yPos) {
		if(xPos == 0 || xPos == World.WORLD_WIDTH - 1) {
			return true; 
		}
		if(yPos == 0 || yPos == World.WORLD_HEIGHT - 1) {
			return true;
		}
		return false;
	}
	
	public boolean isValidTile(int xPos, int yPos) {
		if(xPos < 0 || xPos > World.WORLD_WIDTH - 1) {
			return false;
		}
		if(yPos < 0 || yPos > World.WORLD_HEIGHT - 1) {
			return false;
		}
		return true;
	}
	
	public boolean isTileXYWater(int xPos, int yPos) {
		if(!isValidTile(xPos, yPos)) {
			return false;
		}
		return getTileAt(xPos, yPos).getTileTypeName().equals(WaterTile.TILE_TYPE_NAME);
	}
	
	public boolean isTileXYGrass(int xPos, int yPos) {
		if(!isValidTile(xPos, yPos)) {
			return false;
		}
		return getTileAt(xPos, yPos).getTileTypeName().equals(GrassTile.TILE_TYPE_NAME);
	}
	
	public boolean isTileXYDirt(int xPos, int yPos) {
		if(!isValidTile(xPos, yPos)) {
			return false;
		}
		return getTileAt(xPos, yPos).getTileTypeName().equals(DirtTile.TILE_TYPE_NAME);
	}
	
	public boolean isTileXYAntHill(int xPos, int yPos) {
		if(!isValidTile(xPos, yPos)) {
			return false;
		}
		return getTileAt(xPos, yPos).getTileTypeName().equals(AntHillTile.TILE_TYPE_NAME);
	}
	
	public void updateTileAnimations(long elapsedTime) {
		for(int x = 0; x < worldWidth; x++) {
			for(int y = 0; y < worldHeight; y++) {
				getTileAt(x, y).updateAnimation(elapsedTime);
			}
		}
	}
	
	public int getWorldWidth() {
		return worldWidth;
	}
	
	public int getWorldHeight() {
		return worldHeight;
	}
}
