/* (C)2024 */
package com.lucascram.tilegraphicsgame.math;

import java.util.Random;

public class RandomUtility {

    public static boolean percentChance(double percent) {
        Random random = new Random();

        if (percent < 0 || percent > 100) {
            return false;
        }

        double dec = percent / 100.0;
        if (random.nextFloat() < dec) {
            return true;
        }
        return false;
    }

    // floor inclusive, ceiling exclusive
    public static int randIntInRange(int floor, int ceiling) {
        Random random = new Random();

        if (floor > ceiling) {
            return -1;
        }

        return random.nextInt(ceiling - floor) + floor;
    }
}
