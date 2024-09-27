/* (C)2024 */
package com.lucascram.tilegraphicsgame.states;

import com.lucascram.tilegraphicsgame.input.ActionEngine;
import java.awt.Graphics2D;

public abstract class AbstractGameState {

    private String stateName;
    private boolean shouldAdvance;
    private boolean shouldTerminate;
    private String nextStateName;
    private ActionEngine actionEngine;

    public AbstractGameState(String stateName, ActionEngine actionEngine) {
        this.stateName = stateName;
        this.shouldAdvance = false;
        this.shouldTerminate = false;
        this.nextStateName = null;
        this.actionEngine = actionEngine;
    }

    public abstract void onInit();

    public abstract void onResume();

    public abstract void onTerminate();

    public abstract void onSuspend();

    public abstract void update();

    public abstract void updateAnimations(long elapsedTime);

    public abstract void render(Graphics2D g2d);

    /*
     * Accessors and Mutators
     */

    public String getStateName() {
        return stateName;
    }

    public boolean shouldAdvance() {
        return shouldAdvance;
    }

    public void setShouldAdvance(boolean flag) {
        shouldAdvance = flag;
    }

    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    public void setShouldTerminate(boolean flag) {
        shouldTerminate = flag;
    }

    public String getNextStateName() {
        return nextStateName;
    }

    public void setNextStateName(String nextState) {
        nextStateName = nextState;
    }

    public ActionEngine getActionEngine() {
        return actionEngine;
    }
}
