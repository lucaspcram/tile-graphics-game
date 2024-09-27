/* (C)2024 */
package com.lucascram.tilegraphicsgame.entities;

import com.lucascram.tilegraphicsgame.io.AnimationLoader;
import com.lucascram.tilegraphicsgame.world.World;

public class DecoyEntity extends AbstractEntity {

    public static final int MAX_HEALTH = 10;

    private int health;

    public DecoyEntity(int xPos, int yPos, boolean isAlive) {
        super(xPos, yPos, isAlive);
        health = MAX_HEALTH;
        loadDecoyAnimations();
    }

    private void loadDecoyAnimations() {
        getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.DECOY));
        getSprite().setAnimation(0);
    }

    public void update(int newXPos, int newYPos, World world) {
        if (isAlive()) {
            health--;
            if (health <= 0) {
                health = MAX_HEALTH;
                kill();
                setXPos(newXPos);
                setYPos(newYPos);
                super.update(world);
            }
        } else {
            setXPos(newXPos);
            setYPos(newYPos);
            super.update(world);
        }
    }
}
