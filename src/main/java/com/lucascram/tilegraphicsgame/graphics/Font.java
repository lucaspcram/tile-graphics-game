/* (C)2024 */
package com.lucascram.tilegraphicsgame.graphics;

import com.lucascram.tilegraphicsgame.resource.ResourceManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Font {

    private static final int NUM_CHARS = 36;
    private static final String characterSet = "012456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // offset used to get the correct letter suffix on font*.png
    private static final int ASCII_OFFSET = 55;

    private BufferedImage[] characterImages;
    private ResourceManager resourceManager;

    public Font() {
        characterImages = new BufferedImage[NUM_CHARS];
        resourceManager = new ResourceManager();
        loadFont();
    }

    private void loadFont() {
        for (int i = 0; i < NUM_CHARS; i++) {
            if (i >= 0 && i <= 9) {
                resourceManager.loadImage("res/font/font" + i + ".png", "Font" + i);
                characterImages[i] = resourceManager.getImageResource("Font" + i).getImage();
            } else {
                char letter = (char) (i + ASCII_OFFSET);
                resourceManager.loadImage("res/font/font" + letter + ".png", "Font" + letter);
                characterImages[i] = resourceManager.getImageResource("Font" + letter).getImage();
            }
        }
    }

    // Precondition: Parameter 'String message' contains only spaces (ASCII 32), capital letters A-Z
    // and/or digits 0-9
    public void render(Graphics2D g2d, String message, int xPos, int yPos, int sizeScale) {
        if (sizeScale <= 0) {
            return;
        }

        if (message != null) {
            message = message.toUpperCase();
        }

        int startX = xPos;
        int startY = yPos;

        int width = 1 * sizeScale;
        int height = 1 * sizeScale;

        for (int i = 0; i < message.length(); i++) {
            int index = 0;
            if (message.charAt(i) >= '0' && message.charAt(i) <= '9') {
                index = Integer.parseInt(message.charAt(i) + "");
            } else if (message.charAt(i) == ' ') {
                continue;
            } else if (message.charAt(i) >= 'A' && message.charAt(i) <= 'Z') {
                index = message.charAt(i) - ASCII_OFFSET;
            } else {
                index = 0;
            }
            g2d.drawImage(
                    characterImages[index], startX + (i * width), startY, width, height, null);
        }
    }
}
