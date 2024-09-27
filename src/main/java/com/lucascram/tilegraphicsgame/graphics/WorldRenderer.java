/* (C)2024 */
package com.lucascram.tilegraphicsgame.graphics;

import com.lucascram.tilegraphicsgame.entities.AbstractEntity;
import com.lucascram.tilegraphicsgame.tiles.GrassTile;
import com.lucascram.tilegraphicsgame.world.World;
import java.awt.Graphics2D;

public class WorldRenderer {

    public static final int RENDER_START_X = 25;
    public static final int RENDER_START_Y = 31;

    public static final int PIXEL_WIDTH = 30;
    public static final int PIXEL_HEIGHT = 30;

    private World world;

    public WorldRenderer(World world) {
        this.world = world;
    }

    public void renderMap(Graphics2D g2d) {
        for (int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
            for (int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
                if (world.getWorldMap().getTileAt(x, y) != null) {
                    world.getWorldMap().getTileAt(x, y).getSprite().render(g2d);
                }
            }
        }
    }

    public void renderMapSkeleton(Graphics2D g2d) {
        for (int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
            for (int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
                if (!world.getWorldMap().isTileXYDirt(x, y)) {
                    world.getWorldMap().getTileAt(x, y).getSprite().render(g2d);
                }
            }
        }
    }

    public void renderEntity(Graphics2D g2d, AbstractEntity entity) {
        entity.getSprite().render(g2d);
    }

    public void renderGrassCovers(Graphics2D g2d) {
        for (int x = 0; x < world.getWorldMap().getWorldWidth(); x++) {
            for (int y = 0; y < world.getWorldMap().getWorldHeight(); y++) {
                if (world.getWorldMap().isTileXYGrass(x, y)) {
                    GrassTile tile = (GrassTile) world.getWorldMap().getTileAt(x, y);
                    tile.getTallGrassSprite().render(g2d);
                }
            }
        }
    }

    public static int convertToScreenXCoord(int worldXCoord) {
        return (worldXCoord * PIXEL_WIDTH) + RENDER_START_X;
    }

    public static int convertToScreenYCoord(int worldYCoord) {
        return (worldYCoord * PIXEL_HEIGHT) + RENDER_START_Y;
    }
}
