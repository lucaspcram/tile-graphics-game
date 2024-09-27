/* (C)2024 */
package com.lucascram.tilegraphicsgame.entities;

import com.lucascram.tilegraphicsgame.io.AnimationLoader;
import com.lucascram.tilegraphicsgame.world.World;

public class GoalEntity extends AbstractEntity {

    public static final int GOAL_DEFAULT = 0;

    public GoalEntity(int xPos, int yPos, boolean isAlive) {
        super(xPos, yPos, isAlive);
        loadGoalAnimations();
    }

    private void loadGoalAnimations() {
        getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.GOAL));
        getSprite().setAnimation(GOAL_DEFAULT);
    }

    public void update(int newXPos, int newYPos, World world) {
        // do nothing, goal should not move
    }
}
