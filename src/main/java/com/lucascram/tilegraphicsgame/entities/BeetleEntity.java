/* (C)2024 */
package com.lucascram.tilegraphicsgame.entities;

import com.lucascram.tilegraphicsgame.io.AnimationLoader;
import com.lucascram.tilegraphicsgame.world.World;

public class BeetleEntity extends AbstractEntity {

    public static final int BEETLE_ALIVE = 0;
    public static final int BEETLE_DEAD = 1;
    public static final int BEETLE_DROWNED = 2;

    public static final int MAX_STAMINA = 15;
    public static final int MAX_DECOYS = 3;

    private int currentStamina;
    private DecoyEntity decoy;
    private int decoysRemaining;

    public BeetleEntity(int xPos, int yPos, boolean isAlive) {
        super(xPos, yPos, isAlive);
        currentStamina = MAX_STAMINA;
        decoysRemaining = MAX_DECOYS;
        decoy = new DecoyEntity(xPos, yPos, false);
        loadBeetleAnimations();
    }

    private void loadBeetleAnimations() {
        getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.BEETLE_ALIVE));
        getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.BEETLE_DEAD));
        getSprite().addAnimation(AnimationLoader.getAnimation(AnimationLoader.BEETLE_DROWNED));
        getSprite().setAnimation(BEETLE_ALIVE);
    }

    public void kill(boolean drowned) {
        super.kill();
    }

    public void update(int newXPos, int newYPos, World world) {
        boolean isRestingThisUpdate = false;

        if (!isAlive()) {
            return;
        }

        decoy.update(newXPos, newYPos, world);

        if (newXPos == getXPos() && newYPos == getYPos()) {
            isRestingThisUpdate = true;
        }

        if (isRestingThisUpdate) {
            replenishStamina();
        }

        if (world.getWorldMap().isValidTile(newXPos, newYPos)) {
            if (!world.getWorldMap().isTileXYAntHill(newXPos, newYPos) && currentStamina > 0) {
                setXPos(newXPos);
                setYPos(newYPos);

                // updates the sprite
                super.update(world);
            }
        }

        if (!isRestingThisUpdate) {
            depleteStamina();
        }

        if (currentStamina == 0 && world.getWorldMap().isTileXYWater(getXPos(), getYPos())) {
            getSprite().setAnimation(BEETLE_DROWNED);
            this.kill();
        }
    }

    public int getCurrentStamina() {
        return currentStamina;
    }

    public void deployDecoy(World world) {
        if (decoy.isAlive()
                || world.getWorldMap().isTileXYGrass(getXPos(), getYPos())
                || decoysRemaining <= 0) {
            return;
        } else {
            decoy.setAlive();
            decoysRemaining--;
        }
    }

    public boolean isDecoyDeployed() {
        return decoy.isAlive();
    }

    public DecoyEntity getDecoy() {
        return decoy;
    }

    public void updateDecoyAnimation(long elapsedTime) {
        decoy.updateAnimation(elapsedTime);
    }

    public int getApparentXPos() {
        if (isDecoyDeployed()) {
            return decoy.getXPos();
        } else {
            return getXPos();
        }
    }

    public int getApparentYPos() {
        if (isDecoyDeployed()) {
            return decoy.getYPos();
        } else {
            return getYPos();
        }
    }

    public int getNumDecoys() {
        return decoysRemaining;
    }

    public void setNumDecoy(int newAmt) {
        decoysRemaining = newAmt;
    }

    private void replenishStamina() {
        currentStamina++;
        if (currentStamina > MAX_STAMINA) {
            currentStamina = MAX_STAMINA;
        }
    }

    private void depleteStamina() {
        currentStamina--;
        if (currentStamina < 0) {
            currentStamina = 0;
        }
    }
}
