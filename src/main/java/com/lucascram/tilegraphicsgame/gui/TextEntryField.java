/* (C)2024 */
package com.lucascram.tilegraphicsgame.gui;

import com.lucascram.tilegraphicsgame.graphics.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class TextEntryField {

    private List<Character> contents;
    private Font font;
    private int position;

    public TextEntryField() {
        contents = new ArrayList<Character>();
        font = new Font();
        position = 0;
    }

    public void typeCharacter(char input) {
        if (contents.size() < 14) {
            contents.add(position, input);
            advancePosition();
        }
    }

    public void deleteCharacter() {
        try {
            contents.remove(position - 1);
            retreatPosition();
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < contents.size(); i++) {
            builder.append(contents.get(i));
        }

        return builder.toString();
    }

    public int getSize() {
        return contents.size();
    }

    public void render(Graphics2D g2d, int xPos, int yPos, int sizeScale) {
        String toRender = this.toString();
        if (toRender != null) {
            font.render(g2d, toRender.toUpperCase(), xPos, yPos, sizeScale);
        }
    }

    public void setContents(String text) {
        contents.clear();
        for (int i = 0; i < text.length(); i++) {
            contents.add(text.charAt(i));
        }
    }

    public void advancePosition() {
        if (position < contents.size()) {
            position++;
        }
    }

    public void retreatPosition() {
        if (position > 0) {
            position--;
        }
    }

    public int getPosition() {
        return position;
    }
}
