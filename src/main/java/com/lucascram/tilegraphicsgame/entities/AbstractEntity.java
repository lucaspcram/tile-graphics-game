/* (C)2024 */
package com.lucascram.tilegraphicsgame.entities;

import com.lucascram.tilegraphicsgame.graphics.Sprite;
import com.lucascram.tilegraphicsgame.world.World;

public abstract class AbstractEntity {

    private int xPos; // represents position in world space, not screen space
    private int yPos;
    private boolean isAlive;
    private Sprite sprite;

    public AbstractEntity() {
        this.xPos = 0;
        this.yPos = 0;
        this.isAlive = true;
        this.sprite = null;
    }

    public AbstractEntity(int xPos, int yPos, boolean isAlive) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isAlive = isAlive;
        sprite = new Sprite(xPos, yPos, false);
    }

    public void update(World world) {
        sprite.update(xPos, yPos);
    }

    public void updateAnimation(long elapsedTime) {
        sprite.updateAnimation(elapsedTime);
    }

    public void setAnimation(int index) {
        sprite.setAnimation(index);
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int newXPos) {
        xPos = newXPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int newYPos) {
        yPos = newYPos;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }

    public void setAlive() {
        isAlive = true;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
