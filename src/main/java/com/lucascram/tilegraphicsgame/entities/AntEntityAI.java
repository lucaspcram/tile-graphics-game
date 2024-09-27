/* (C)2024 */
package com.lucascram.tilegraphicsgame.entities;

import com.lucascram.tilegraphicsgame.math.Vector2D;
import com.lucascram.tilegraphicsgame.world.World;

public class AntEntityAI {

    public static final int UP_MOVE = 0;
    public static final int DOWN_MOVE = 1;
    public static final int RIGHT_MOVE = 2;
    public static final int LEFT_MOVE = 3;
    public static final int UP_RIGHT_MOVE = 4;
    public static final int DOWN_RIGHT_MOVE = 5;
    public static final int UP_LEFT_MOVE = 6;
    public static final int DOWN_LEFT_MOVE = 7;
    public static final int NO_MOVE = 8;

    private static final double IMPOSSIBLE_MOVE = Double.MAX_VALUE;

    public AntEntityAI() {}

    public int getBestMove(int antXPos, int antYPos, int beetleXPos, int beetleYPos, World world) {
        final int numPossibleMoves = 8;

        Vector2D[] possibleMoves = new Vector2D[numPossibleMoves];
        double[] moveValues = new double[numPossibleMoves];

        if (antXPos == beetleXPos && antYPos == beetleYPos) {
            return NO_MOVE;
        }

        for (int i = UP_MOVE; i < DOWN_LEFT_MOVE + 1; i++) {
            int xMove = getAntXMove(i);
            int yMove = getAntYMove(i);

            if (world.getWorldMap().isValidTile(antXPos + xMove, antYPos + yMove)) {
                if (world.getWorldMap().isTileXYWater(antXPos + xMove, antYPos + yMove)) {
                    possibleMoves[i] = new Vector2D(); // zero vector
                    moveValues[i] = IMPOSSIBLE_MOVE;
                } else {
                    possibleMoves[i] =
                            new Vector2D(beetleXPos, beetleYPos, antXPos + xMove, antYPos + yMove);
                    moveValues[i] = possibleMoves[i].getMagnitude();
                }
            } else {
                possibleMoves[i] = new Vector2D(); // zero vector
                moveValues[i] = IMPOSSIBLE_MOVE;
            }
        }

        return getMinIndex(moveValues);
    }

    private int getMinIndex(double[] array) {
        double minVal = Double.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < minVal) {
                minVal = array[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public int getAntXMove(int index) {
        if (index == UP_MOVE) {
            return 0;
        } else if (index == DOWN_MOVE) {
            return 0;
        } else if (index == LEFT_MOVE) {
            return -1;
        } else if (index == RIGHT_MOVE) {
            return 1;
        } else if (index == UP_RIGHT_MOVE) {
            return 1;
        } else if (index == DOWN_RIGHT_MOVE) {
            return 1;
        } else if (index == UP_LEFT_MOVE) {
            return -1;
        } else if (index == DOWN_LEFT_MOVE) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getAntYMove(int index) {
        if (index == UP_MOVE) {
            return -1;
        } else if (index == DOWN_MOVE) {
            return 1;
        } else if (index == LEFT_MOVE) {
            return 0;
        } else if (index == RIGHT_MOVE) {
            return 0;
        } else if (index == UP_RIGHT_MOVE) {
            return -1;
        } else if (index == DOWN_RIGHT_MOVE) {
            return 1;
        } else if (index == UP_LEFT_MOVE) {
            return -1;
        } else if (index == DOWN_LEFT_MOVE) {
            return 1;
        } else {
            return 0;
        }
    }
}
