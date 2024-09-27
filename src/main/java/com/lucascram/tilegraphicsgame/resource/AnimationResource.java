/* (C)2024 */
package com.lucascram.tilegraphicsgame.resource;

import com.lucascram.tilegraphicsgame.graphics.Animation;

public class AnimationResource extends AbstractResource {

    private Animation animation;

    public AnimationResource(String path, String name, Animation animation) {
        super(path, name);
        this.animation = animation;
    }

    public Animation getAnimation() {
        return animation;
    }

    public String getPath() {
        return "AnimationResource: path not defined";
    }
}
