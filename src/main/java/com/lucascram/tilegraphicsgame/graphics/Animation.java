/* (C)2024 */
package com.lucascram.tilegraphicsgame.graphics;

import com.lucascram.tilegraphicsgame.math.RandomUtility;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

    private ArrayList<AnimationFrame> frames;
    private int currFrameIndex;
    private long animTime;
    private long totalDuration;

    public Animation() {
        frames = new ArrayList<AnimationFrame>();
        totalDuration = 0;
        start();
    }

    public Animation(Animation anim) {
        this.frames = anim.frames;
        this.totalDuration = anim.totalDuration;
        this.frames = anim.frames;
        start();
    }

    public void start() {
        animTime = 0;
        currFrameIndex = 0;
    }

    public void addFrame(BufferedImage i, long duration) {
        totalDuration += duration;
        frames.add(new AnimationFrame(i, totalDuration));
    }

    public void update(long elapsedTime) {
        if (frames.size() > 1) {
            animTime += elapsedTime;

            if (animTime >= totalDuration) {
                start();
            }

            while (animTime > getFrame(currFrameIndex).endTime) {
                currFrameIndex++;
            }
        }
    }

    public BufferedImage getImage() {
        if (frames.size() == 0) {
            return null;
        }
        return getFrame(currFrameIndex).img;
    }

    public void scrambleFrameTimes(int minTime, int maxTime) {
        int newTotalDuration = 0;
        for (int i = 0; i < frames.size(); i++) {
            long time = (long) RandomUtility.randIntInRange(minTime, maxTime);
            newTotalDuration += time;
            frames.get(i).endTime = (long) newTotalDuration;
        }
    }

    private AnimationFrame getFrame(int index) {
        return frames.get(index);
    }

    private class AnimationFrame {
        BufferedImage img;
        long endTime;

        public AnimationFrame(BufferedImage img, long endTime) {
            this.img = img;
            this.endTime = endTime;
        }
    }
}
