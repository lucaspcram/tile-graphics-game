package com.lucascram.tilegraphicsgame.world;

import com.lucascram.tilegraphicsgame.math.RandomUtility;
import com.lucascram.tilegraphicsgame.tiles.AntHillTile;
import com.lucascram.tilegraphicsgame.tiles.DirtTile;
import com.lucascram.tilegraphicsgame.tiles.GrassTile;
import com.lucascram.tilegraphicsgame.tiles.WaterTile;


//WARNING: This class contains lots of horrid, ugly code. Read at your own risk!

public class MapGenerator {
	
	//DON'T TOUCH THESE
	private static final int TILE_UP = 0;
	private static final int TILE_DOWN = 1;
	private static final int TILE_LEFT = 2;
	private static final int TILE_RIGHT = 3;
	private static final int TILE_UP_RIGHT = 4;
	private static final int TILE_DOWN_RIGHT = 5;
	private static final int TILE_UP_LEFT = 6;
	private static final int TILE_DOWN_LEFT = 7;
	
	/*****************************/
	/*****************************/
	/*World Generation Parameters*/
	/*****************************/
	/*****************************/
	
	/*
	 *  
	 */
	
	//0 - no grass; 100 - max grass  						DEFAULT: 7
	private static final double GRASS_SEED_VALUE = 8; 		
	
	//grass fill parameter floor							DEFAULT: 0
	private static final int 	GRASS_FILL_FLOOR = 0; 		
	
	//MUST exceed GRASS_FILL_FLOOR in value					DEFAULT: 4
	private static final int 	GRASS_FILL_CEILING = 4; 	
	
	//0 - no water; 100 - max water							DEFAULT: 1
	private static final double WATER_SEED_VALUE = 2; 		
	
	//0 - no fill; 100 - max fill							DEFAULT: 80
	private static final double WATER_FILL_VALUE = 60; 		
	
	//becomes water if adj. to this many water  			DEFAULT: 7 (< 0 means no filling occurs)
	private static final int	WATER_ADJ_FILL_BOUND = 5;	
	
	//grass dies if not adj. to this many grass tiles		DEFAULT:8
	private static final int DIRT_ADJ_TRIM_BOUND = 8;
	
	//0 - no anthills; 100 - max anthills					DEFAULT: 1.5
	private static final double ANTHILL_SEED_VALUE = 3;		
	
	//minimum number of possible anthills					DEFAULT: 5
	private static final int	MIN_ANTHILLS = 9;
	
	//maximum number of possible anthills					DEFAULT: 10
	private static final int	MAX_ANTHILLS = 16;
				
	
	private World		world;
	
	public MapGenerator(World world) {
		this.world = world;
	}
	
	public void generateMap(boolean absoluteCoord) {
		clearMap();
		
		generateBase(absoluteCoord);
		generateBushes(absoluteCoord);
		generatePuddles(absoluteCoord);
		generateAntHills(absoluteCoord);
	}
	
	private void clearMap() {
		for(int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
			for(int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
				world.getWorldMap().setTileAt(x, y, null);
			}
		}
	}
	
	private void generateBase(boolean absoluteCoord) {
		for(int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
			for(int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
				DirtTile dirtTile = new DirtTile(x, y, absoluteCoord);
				world.getWorldMap().setTileAt(x, y, dirtTile);
			}
		}
	}
	
	private void generateBushes(boolean absoluteCoord) {
		for(int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
			for(int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
				if(!world.getWorldMap().isEdgeTile(x, y) && RandomUtility.percentChance(GRASS_SEED_VALUE)) {
					GrassTile grassTile = new GrassTile(x, y, absoluteCoord);
					world.getWorldMap().setTileAt(x, y, grassTile);
				}
			}
		}
		
		for(int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
			for(int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
				if(world.getWorldMap().isTileXYGrass(x, y)) {
					fillOutBush(x, y, absoluteCoord);
				}
			}
		}
		
		trimExcessGrass(absoluteCoord);
	}
	
	private void fillOutBush(int startX, int startY, boolean absoluteCoord) {
		int width = RandomUtility.randIntInRange(GRASS_FILL_FLOOR, GRASS_FILL_CEILING) + startX;
		int height = RandomUtility.randIntInRange(GRASS_FILL_FLOOR, GRASS_FILL_CEILING) + startY;
		
		for(int x = startX; x < width; x++) {
			for(int y = startY; y < height; y++) {
				if(!world.getWorldMap().isValidTile(x, y)) {
					continue;
				}
				GrassTile tile = new GrassTile(x, y, absoluteCoord);
				world.getWorldMap().setTileAt(x, y, tile);
			}
		}
	}
	
	private void trimExcessGrass(boolean absoluteCoord) {
		for(int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
			for(int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
				if(countDirtAdjacency(x, y) >= DIRT_ADJ_TRIM_BOUND) {
					DirtTile tile = new DirtTile(x, y, absoluteCoord);
					world.getWorldMap().setTileAt(x, y, tile);
				}
			}
		}
	}
	
	private int countDirtAdjacency(int x, int y) {
		int count = 0;
		for(int i = TILE_UP; i < TILE_DOWN_LEFT + 1; i++) {
			int xPos = x + getXOffset(i);
			int yPos = y + getYOffset(i);
			if(world.getWorldMap().isValidTile(xPos, yPos)) {
				if(world.getWorldMap().isTileXYDirt(xPos, yPos)) {
					count++;
				}
			}
		}
		return count;
	}
	
	private void generatePuddles(boolean absoluteCoord) {
		for(int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
			for(int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
				if(!world.getWorldMap().isEdgeTile(x, y) && world.getWorldMap().isTileXYDirt(x, y)) {
					if(isAdjacentToWater(x, y)) {
						if(RandomUtility.percentChance(WATER_FILL_VALUE)) {
							WaterTile tile = new WaterTile(x, y, absoluteCoord);
							world.getWorldMap().setTileAt(x, y, tile);
						}
					} else {
						if(RandomUtility.percentChance(WATER_SEED_VALUE)) {
							WaterTile tile = new WaterTile(x, y, absoluteCoord);
							world.getWorldMap().setTileAt(x, y, tile);
						}
					}
				}
			}
		}
		
		if(WATER_ADJ_FILL_BOUND >= 0) {
			fillPuddles(absoluteCoord);
		}
	}
	
	private void fillPuddles(boolean absoluteCoord) {
		for(int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
			for(int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
				if(countWaterAdjacency(x, y) >= WATER_ADJ_FILL_BOUND) {
					WaterTile tile = new WaterTile(x, y, absoluteCoord);
					world.getWorldMap().setTileAt(x, y, tile);
				}
			}
		}
	}
	
	private int countWaterAdjacency(int x, int y) {
		int count = 0;
		for(int i = TILE_UP; i < TILE_DOWN_LEFT + 1; i++) {
			int xPos = x + getXOffset(i);
			int yPos = y + getYOffset(i);
			if(world.getWorldMap().isValidTile(xPos, yPos)) {
				if(world.getWorldMap().isTileXYWater(xPos, yPos)) {
					count++;
				}
			}
		}
		return count;
	}
	
	private boolean isAdjacentToWater(int x, int y) {
		
		for(int i = TILE_UP; i < TILE_DOWN_LEFT + 1; i++) {
			int xPos = x + getXOffset(i);
			int yPos = y + getYOffset(i);
			if(world.getWorldMap().isValidTile(xPos, yPos)) {
				if(world.getWorldMap().isTileXYWater(xPos, yPos)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void generateAntHills(boolean absoluteCoord) {
		int minHills = MIN_ANTHILLS;
		int maxHills = MAX_ANTHILLS;
		
		int numGenSoFar = 0;
		int numAntHills = RandomUtility.randIntInRange(MIN_ANTHILLS, MAX_ANTHILLS + 1);
		int numPasses = 0;
		int maxPasses = World.WORLD_WIDTH * World.WORLD_HEIGHT * 10;
		
		while(true) {
			numPasses++;
			
			int x = RandomUtility.randIntInRange(0, World.WORLD_WIDTH);
			int y = RandomUtility.randIntInRange(0, World.WORLD_HEIGHT);
			
			if(world.getWorldMap().isTileXYDirt(x, y)) {
				AntHillTile tile = new AntHillTile(x, y, absoluteCoord);
				world.getWorldMap().setTileAt(x, y, tile);
				numGenSoFar++;
			}
			
			if(numGenSoFar == numAntHills) {
				return;
			}
			if(numPasses > maxPasses) {
				return;
			}
		}
	}
	
	private int getXOffset(int index) {
		if(index == TILE_UP) {
			return 0;
		} else if(index == TILE_DOWN) {
			return 0;
		} else if(index == TILE_LEFT) {
			return -1;
		} else if(index == TILE_RIGHT) {
			return 1;
		} else if(index == TILE_UP_RIGHT) {
			return 1;
		} else if(index == TILE_DOWN_RIGHT) {
			return 1;
		} else if(index == TILE_UP_LEFT) {
			return -1;
		} else if(index == TILE_DOWN_LEFT) {
			return -1;
		} else {
			return 0;
		}
	}
	
	private int getYOffset(int index) {
		if(index == TILE_UP) {
			return -1;
		} else if(index == TILE_DOWN) {
			return 1;
		} else if(index == TILE_LEFT) {
			return 0;
		} else if(index == TILE_RIGHT) {
			return 0;
		} else if(index == TILE_UP_RIGHT) {
			return -1;
		} else if(index == TILE_DOWN_RIGHT) {
			return 1;
		} else if(index == TILE_UP_LEFT) {
			return -1;
		} else if(index == TILE_DOWN_LEFT) {
			return 1;
		} else {
			return 0;
		}
	}
}
