/* (C)2024 */
package com.lucascram.tilegraphicsgame.tiles;

import com.lucascram.tilegraphicsgame.io.AnimationLoader;

public class DirtTile extends AbstractTile {

    public static final String TILE_TYPE_NAME = "DirtTile";
    public static final int DEFAULT_ANIM = 0;

    public DirtTile() {
        super();
    }

    public DirtTile(int xPos, int yPos) {
        super(xPos, yPos);
        loadTileAnimations();
    }

    public DirtTile(int xPos, int yPos, boolean absolute) {
        super(xPos, yPos, absolute);
        loadTileAnimations();
    }

    private void loadTileAnimations() {
        getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.DIRT_TILE));
        getSprite().setAnimation(DEFAULT_ANIM);
    }

    public void update() {}

    public String getTileTypeName() {
        return TILE_TYPE_NAME;
    }
}
