/* (C)2024 */
package com.lucascram.tilegraphicsgame.io;

import com.lucascram.tilegraphicsgame.graphics.Animation;
import com.lucascram.tilegraphicsgame.resource.ResourceManager;
import java.awt.image.BufferedImage;

public class AnimationLoader {

    public static final String TITLE_BG = "TitleBG";
    public static final String DIRT_TILE = "DirtTile";
    public static final String GRASS_TILE = "GrassTile";
    public static final String GRASS_COVER = "GrassCover";
    public static final String ANT_HILL_TILE = "AntHillTile";
    public static final String WATER_TILE = "WaterTile";
    public static final String BEETLE_ALIVE = "BeetleAlive";
    public static final String BEETLE_DEAD = "BeetleDead";
    public static final String BEETLE_DROWNED = "BeetleDrowned";
    public static final String DECOY = "Decoy";
    public static final String ANT_ALIVE = "AntAlive";
    public static final String GOAL = "Goal";
    public static final String GUI_PANEL = "GUIPanel";
    public static final String BG_PANEL = "BGPanel";
    public static final String BG_PANEL_2 = "BGPanel2";

    private static ResourceManager resourceManager;

    public static void loadAnimations() {
        resourceManager = new ResourceManager();

        // load the animation for DirtTile
        final int NUM_DIRTTILE_FRAMES = 1;
        BufferedImage[] dirtTile = new BufferedImage[NUM_DIRTTILE_FRAMES];
        long[] timesDirtTile = {2000};
        resourceManager.loadImage("res/img/tiles/dirt1.png", "DirtTileImg1");
        dirtTile[0] = resourceManager.getImageResource("DirtTileImg1").getImage();
        resourceManager.loadAnimation(dirtTile, timesDirtTile, DIRT_TILE);

        // load the animation for GrassTile
        final int NUM_GRASSTILE_FRAMES = 1;
        BufferedImage[] grassTile = new BufferedImage[NUM_GRASSTILE_FRAMES];
        long[] timesGrassTile = {2000};
        resourceManager.loadImage("res/img/tiles/grass1.png", "GrassTileImg1");
        grassTile[0] = resourceManager.getImageResource("GrassTileImg1").getImage();
        resourceManager.loadAnimation(grassTile, timesGrassTile, GRASS_TILE);

        // load the animation for TallGrassCover
        final int NUM_TALLGRASS_FRAMES = 4;
        BufferedImage[] tallGrass = new BufferedImage[NUM_TALLGRASS_FRAMES];
        long[] timesTallGrass = {500, 500, 500, 500};
        for (int i = 0; i < tallGrass.length; i++) {
            resourceManager.loadImage(
                    "res/img/tiles/grasscover" + (i + 1) + ".png", "GrassCoverImg" + i);
            tallGrass[i] = resourceManager.getImageResource("GrassCoverImg" + i).getImage();
        }
        resourceManager.loadAnimation(tallGrass, timesTallGrass, GRASS_COVER);

        // load the animation for AntHill
        final int NUM_ANTHILL_FRAMES = 4;
        BufferedImage[] antHill = new BufferedImage[NUM_ANTHILL_FRAMES];
        long[] timesAntHill = new long[NUM_ANTHILL_FRAMES];
        for (int i = 0; i < antHill.length; i++) {
            resourceManager.loadImage("res/img/tiles/anthill" + (i + 1) + ".png", "AntHill" + i);
            antHill[i] = resourceManager.getImageResource("AntHill" + i).getImage();
            timesAntHill[i] = 300;
        }
        resourceManager.loadAnimation(antHill, timesAntHill, ANT_HILL_TILE);

        // load the animation for Water
        final int NUM_WATER_FRAMES = 3;
        BufferedImage[] water = new BufferedImage[NUM_WATER_FRAMES];
        long[] timesWater = new long[NUM_WATER_FRAMES];
        for (int i = 0; i < water.length; i++) {
            resourceManager.loadImage("res/img/tiles/water" + (i + 1) + ".png", "Water" + i);
            water[i] = resourceManager.getImageResource("Water" + i).getImage();
            timesWater[i] = 500;
        }
        resourceManager.loadAnimation(water, timesWater, WATER_TILE);

        // load the animation for a beetle
        final int NUM_BEETLE_FRAMES = 4;
        BufferedImage[] beetle = new BufferedImage[NUM_BEETLE_FRAMES];
        long[] timesBeetle = new long[NUM_BEETLE_FRAMES];
        for (int i = 0; i < beetle.length; i++) {
            resourceManager.loadImage(
                    "res/img/entities/beetle" + (i + 1) + ".png", "BeetleImg" + i);
            beetle[i] = resourceManager.getImageResource("BeetleImg" + i).getImage();
            timesBeetle[i] = 500;
        }
        resourceManager.loadAnimation(beetle, timesBeetle, BEETLE_ALIVE);

        // load animation for dead beetle
        final int NUM_BEETLEDEAD_FRAMES = 1;
        BufferedImage[] beetleDead = new BufferedImage[NUM_BEETLEDEAD_FRAMES];
        long[] timesBeetleDead = new long[NUM_BEETLEDEAD_FRAMES];
        for (int i = 0; i < beetleDead.length; i++) {
            resourceManager.loadImage("res/img/entities/beetle5.png", "BeetleImg5");
            beetleDead[i] = resourceManager.getImageResource("BeetleImg5").getImage();
            timesBeetleDead[i] = 500;
        }
        resourceManager.loadAnimation(beetleDead, timesBeetleDead, BEETLE_DEAD);

        // load animation for drowned beetle
        final int NUM_BEETLEDROWNED_FRAMES = 1;
        BufferedImage[] beetleDrowned = new BufferedImage[NUM_BEETLEDROWNED_FRAMES];
        long[] timesBeetleDrowned = new long[NUM_BEETLEDROWNED_FRAMES];
        for (int i = 0; i < beetleDrowned.length; i++) {
            resourceManager.loadImage("res/img/entities/beetle6.png", "BeetleImg6");
            beetleDrowned[i] = resourceManager.getImageResource("BeetleImg6").getImage();
            timesBeetleDrowned[i] = 500;
        }
        resourceManager.loadAnimation(beetleDrowned, timesBeetleDrowned, BEETLE_DROWNED);

        // load animation for decoy
        final int NUM_DECOY_FRAMES = 1;
        BufferedImage[] decoy = new BufferedImage[NUM_DECOY_FRAMES];
        long[] timesDecoy = new long[NUM_DECOY_FRAMES];
        for (int i = 0; i < decoy.length; i++) {
            resourceManager.loadImage("res/img/entities/decoy1.png", "DecoyImg1");
            decoy[i] = resourceManager.getImageResource("DecoyImg1").getImage();
            timesDecoy[i] = 500;
        }
        resourceManager.loadAnimation(decoy, timesDecoy, DECOY);

        // load animation for ant
        final int NUM_ANT_FRAMES = 2;
        BufferedImage[] ant = new BufferedImage[NUM_ANT_FRAMES];
        long[] timesAnt = new long[NUM_ANT_FRAMES];
        for (int i = 0; i < ant.length; i++) {
            resourceManager.loadImage("res/img/entities/ant" + (i + 1) + ".png", "Ant" + i);
            ant[i] = resourceManager.getImageResource("Ant" + i).getImage();
            timesAnt[i] = 200;
        }
        resourceManager.loadAnimation(ant, timesAnt, ANT_ALIVE);

        // load animation for the goal
        final int NUM_GOAL_FRAMES = 2;
        BufferedImage[] goal = new BufferedImage[NUM_GOAL_FRAMES];
        long[] timesGoal = new long[NUM_GOAL_FRAMES];
        for (int i = 0; i < goal.length; i++) {
            resourceManager.loadImage("res/img/entities/goal" + (i + 1) + ".png", "Goal" + i);
            goal[i] = resourceManager.getImageResource("Goal" + i).getImage();
            timesGoal[i] = 200;
        }
        resourceManager.loadAnimation(goal, timesGoal, GOAL);

        // load animation for GUIpanel
        final int NUM_PANEL_FRAMES = 1;
        BufferedImage[] guipanel = new BufferedImage[NUM_PANEL_FRAMES];
        long[] timesPanel = {2000};
        resourceManager.loadImage("res/img/gfx/guipanel.png", "GUIPanelImg1");
        guipanel[0] = resourceManager.getImageResource("GUIPanelImg1").getImage();
        resourceManager.loadAnimation(guipanel, timesPanel, GUI_PANEL);

        // load animation for BGPanel
        final int NUM_BGPANEL_FRAMES = 1;
        BufferedImage[] bgpanel = new BufferedImage[NUM_BGPANEL_FRAMES];
        long[] timesBGPanel = {2000};
        resourceManager.loadImage("res/img/gfx/bgpanel.png", "BGPanelImg1");
        bgpanel[0] = resourceManager.getImageResource("BGPanelImg1").getImage();
        resourceManager.loadAnimation(bgpanel, timesBGPanel, BG_PANEL);

        // load animation for BGPanel2
        final int NUM_BGPANEL2_FRAMES = 1;
        BufferedImage[] bgpanel2 = new BufferedImage[NUM_BGPANEL2_FRAMES];
        long[] timesBGPanel2 = {2000};
        resourceManager.loadImage("res/img/gfx/bgpanel2.png", "BGPanel2Img1");
        bgpanel2[0] = resourceManager.getImageResource("BGPanel2Img1").getImage();
        resourceManager.loadAnimation(bgpanel2, timesBGPanel2, BG_PANEL_2);
    }

    public static Animation getAnimation(String animationName) {
        Animation anim = null;
        try {
            anim =
                    new Animation(
                            resourceManager.getAnimationResource(animationName).getAnimation());
        } catch (NullPointerException e) {
            DebugDumper.handleException(
                    null, DebugDumper.ERR_MESSAGE_RESOURCE, e.getStackTrace(), e);
        }
        return anim;
    }
}
