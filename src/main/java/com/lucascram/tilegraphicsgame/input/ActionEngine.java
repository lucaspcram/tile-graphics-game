/* (C)2024 */
package com.lucascram.tilegraphicsgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ActionEngine implements KeyListener {

    public static final String TITLE_START_GAME = "StartGame";

    public static final String MENU_UP = "MenuUp";
    public static final String MENU_DOWN = "MenuDown";
    public static final String MENU_SELECT = "MenuSelect";

    public static final String BEETLE_UP = "BeetleUp";
    public static final String BEETLE_DOWN = "BeetleDown";
    public static final String BEETLE_LEFT = "BeetleLeft";
    public static final String BEETLE_RIGHT = "BeetleRight";
    public static final String BEETLE_REST = "BeetleWait";
    public static final String BEETLE_DECOY = "BeetleDecoy";
    public static final String SKIP_LEVEL = "SkipLevel";

    public static final String ADVANCE = "Advance";

    private List<GameAction> actionList;

    public ActionEngine() {
        actionList = new ArrayList<GameAction>();
    }

    public void bindKey(String actionName, int mapping) {
        GameAction action = new GameAction(mapping, actionName);
        actionList.add(action);
    }

    public void clearBindings() {
        actionList.clear();
    }

    public void handleActions() {
        for (int i = 0; i < actionList.size(); i++) {
            actionList.get(i).handle();
        }
    }

    public int getActionMapping(String actionName) {
        int mapping = -1;

        for (int i = 0; i < actionList.size(); i++) {
            if (actionList.get(i).getName().equals(actionName)) {
                mapping = actionList.get(i).getMapping();
                break;
            }
        }
        return mapping;
    }

    public String getActionName(int mapping) {
        String actionName = "UnboundMapping";

        for (int i = 0; i < actionList.size(); i++) {
            if (actionList.get(i).getMapping() == mapping) {
                actionName = actionList.get(i).getName();
                break;
            }
        }
        return actionName;
    }

    public GameAction getGameAction(int mapping) {
        GameAction action = null;
        for (int i = 0; i < actionList.size(); i++) {
            if (actionList.get(i).getMapping() == mapping) {
                action = actionList.get(i);
                break;
            }
        }
        return action;
    }

    public GameAction getGameAction(String actionName) {
        GameAction action = null;
        for (int i = 0; i < actionList.size(); i++) {
            if (actionList.get(i).getName().equals(actionName)) {
                action = actionList.get(i);
                break;
            }
        }
        return action;
    }

    private boolean allActionsHandled() {
        boolean flag = true;

        for (int i = 0; i < actionList.size(); i++) {
            if (actionList.get(i).isUnhandled()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private void trackAction(KeyEvent event) {
        GameAction action = getGameAction(event.getKeyCode());
        if (action != null) {
            action.setUnhandled();
        }
    }

    public void keyPressed(KeyEvent event) {
        if (allActionsHandled()) {
            trackAction(event);
        }
    }

    public void keyReleased(KeyEvent event) {
        ;
    }

    public void keyTyped(KeyEvent event) {
        ;
    }
}
