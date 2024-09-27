/* (C)2024 */
package com.lucascram.tilegraphicsgame.resource;

import com.lucascram.tilegraphicsgame.graphics.Font;

public class FontResource extends AbstractResource {

    private Font font;

    public FontResource(String path, String name, Font font) {
        super(path, name);
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}
