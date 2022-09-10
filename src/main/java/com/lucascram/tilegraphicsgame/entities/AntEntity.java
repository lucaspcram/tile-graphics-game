package com.lucascram.tilegraphicsgame.entities;

import com.lucascram.tilegraphicsgame.io.AnimationLoader;
import com.lucascram.tilegraphicsgame.world.World;

public class AntEntity extends AbstractEntity {

	public static final int ANT_DEFAULT = 0;
	
	private AntEntityAI aiBrain;
	
	public AntEntity(int xPos, int yPos, boolean isAlive) {
		super(xPos, yPos, isAlive);
		aiBrain = new AntEntityAI();
		loadAntAnimations();
	}
	
	private void loadAntAnimations() {
		getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.ANT_ALIVE));
		getSprite().setAnimation(ANT_DEFAULT);
	}
	
	public void update(World world) {
		int beetleXPos = world.getBeetleEntity().getApparentXPos();
		int beetleYPos = world.getBeetleEntity().getApparentYPos();
		
		int bestMove = aiBrain.getBestMove(getXPos(), getYPos(), beetleXPos, beetleYPos, world);
		
		setXPos(getXPos() + aiBrain.getAntXMove(bestMove));
		setYPos(getYPos() + aiBrain.getAntYMove(bestMove));
		
		super.update(world);
	}
}
