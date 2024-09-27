/* (C)2024 */
package com.lucascram.tilegraphicsgame.tiles;

import com.lucascram.tilegraphicsgame.graphics.Sprite;
import java.awt.Graphics2D;

public abstract class AbstractTile {

    private int xPos;
    private int yPos;
    private Sprite sprite;

    public AbstractTile() {
        this.xPos = 0;
        this.yPos = 0;
        this.sprite = null;
    }

    public AbstractTile(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.sprite = new Sprite(xPos, yPos, false);
    }

    public AbstractTile(int xPos, int yPos, boolean absolute) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.sprite = new Sprite(xPos, yPos, absolute);
    }

    public abstract void update();

    public abstract String getTileTypeName();

    public void render(Graphics2D g2d) {
        sprite.render(g2d);
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

    public int getYPos() {
        return yPos;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
