/* (C)2024 */
package com.lucascram.tilegraphicsgame.world;

import com.lucascram.tilegraphicsgame.entities.AntEntity;
import com.lucascram.tilegraphicsgame.entities.BeetleEntity;
import com.lucascram.tilegraphicsgame.entities.GoalEntity;
import com.lucascram.tilegraphicsgame.math.RandomUtility;
import java.util.ArrayList;

public class World {

    public static final int WORLD_WIDTH = 41;
    public static final int WORLD_HEIGHT = 21;

    private Map map;
    private MapGenerator mapGenerator;

    private BeetleEntity beetleEntity;
    private GoalEntity goalEntity;
    private ArrayList<AntEntity> antEntityList;

    private int score;
    private String name;

    public World() {
        map = new Map(WORLD_WIDTH, WORLD_HEIGHT);
        mapGenerator = new MapGenerator(this);
        antEntityList = new ArrayList<AntEntity>();
        score = 0;
        name = null;
    }

    public World(String name) {
        map = new Map(WORLD_WIDTH, WORLD_HEIGHT);
        mapGenerator = new MapGenerator(this);
        antEntityList = new ArrayList<AntEntity>();
        score = 0;
        this.name = name;
    }

    public void generateWorld(boolean absoluteCoord) {
        int decoys = BeetleEntity.MAX_DECOYS;

        if (beetleEntity != null) {
            decoys = beetleEntity.getNumDecoys();
        }

        mapGenerator.generateMap(absoluteCoord);
        spawnBeetle();
        beetleEntity.setNumDecoy(decoys);

        antEntityList.clear();
        spawnAnts();

        spawnGoal();
    }

    private void spawnBeetle() {
        while (true) {
            int x = RandomUtility.randIntInRange(0, WORLD_WIDTH);
            int y = RandomUtility.randIntInRange(0, WORLD_HEIGHT);
            if (map.isValidTile(x, y)) {
                if (!map.isTileXYAntHill(x, y) || !map.isTileXYWater(x, y)) {
                    beetleEntity = new BeetleEntity(x, y, true);
                    break;
                }
            }
        }
    }

    private void spawnAnts() {
        for (int x = 0; x < map.getWorldWidth(); x++) {
            for (int y = 0; y < map.getWorldHeight(); y++) {
                if (map.isTileXYAntHill(x, y)) {
                    AntEntity ant = new AntEntity(x, y, true);
                    antEntityList.add(ant);
                }
            }
        }
    }

    private void spawnGoal() {
        while (true) {
            int x = RandomUtility.randIntInRange(0, WORLD_WIDTH);
            int y = RandomUtility.randIntInRange(0, WORLD_HEIGHT);
            if (map.isValidTile(x, y)) {
                if (map.isTileXYDirt(x, y)
                        && x != beetleEntity.getXPos()
                        && y != beetleEntity.getYPos()) {
                    goalEntity = new GoalEntity(x, y, true);
                    break;
                }
            }
        }
    }

    public BeetleEntity getBeetleEntity() {
        return beetleEntity;
    }

    public GoalEntity getGoalEntity() {
        return goalEntity;
    }

    public ArrayList<AntEntity> getAntEntityList() {
        return antEntityList;
    }

    public void updateWorld(int newBeetleX, int newBeetleY) {

        // update beetle and goal
        beetleEntity.update(newBeetleX, newBeetleY, this);
        goalEntity.update(this);

        // update ants
        if (!map.isTileXYGrass(beetleEntity.getApparentXPos(), beetleEntity.getApparentYPos())) {
            for (int i = 0; i < antEntityList.size(); i++) {
                antEntityList.get(i).update(this);
            }
        }

        // check for win
        if (beetleEntity.getXPos() == goalEntity.getXPos()
                && beetleEntity.getYPos() == goalEntity.getYPos()) {
            incrementScore();
            generateWorld(false);
            beetleEntity.setNumDecoy(BeetleEntity.MAX_DECOYS);
        }

        // kill beetle if necessary
        for (int i = 0; i < antEntityList.size(); i++) {
            AntEntity a = antEntityList.get(i);
            if (a.getXPos() == beetleEntity.getXPos() && a.getYPos() == beetleEntity.getYPos()) {
                beetleEntity.getSprite().setAnimation(BeetleEntity.BEETLE_DEAD);
                beetleEntity.kill();
            }
        }
    }

    public void updateTileAnimations(long elapsedTime) {
        map.updateTileAnimations(elapsedTime);
    }

    public void updateEntityAnimations(long elapsedTime) {
        beetleEntity.updateAnimation(elapsedTime);
        goalEntity.updateAnimation(elapsedTime);
        for (int i = 0; i < antEntityList.size(); i++) {
            antEntityList.get(i).updateAnimation(elapsedTime);
        }
        if (beetleEntity.isDecoyDeployed()) {
            beetleEntity.updateDecoyAnimation(elapsedTime);
        }
    }

    public Map getWorldMap() {
        return map;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void incrementScore() {
        score++;
    }

    public void decrementScore() {
        if (score == 0) {
            return;
        } else {
            score--;
        }
    }
}
