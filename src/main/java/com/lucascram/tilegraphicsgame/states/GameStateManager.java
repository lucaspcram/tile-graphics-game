package com.lucascram.tilegraphicsgame.states;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import com.lucascram.tilegraphicsgame.input.ActionEngine;
import com.lucascram.tilegraphicsgame.io.DebugDumper;

public class GameStateManager {
	
	public static final String LOADING_SCREEN_STATE_NAME = "LoadingScreenStateName";
	public static final String TITLE_SCREEN_STATE_NAME = "TitleScreenState";
	public static final String MAIN_MENU_STATE_NAME = "MainMenuState";
	public static final String PLAY_STATE_NAME = "PlayState";
	public static final String CONTROL_STATE_NAME = "ControlState";
	public static final String NAME_ENTRY_STATE_NAME = "NameEntryState";
	public static final String GAME_OVER_STATE_NAME = "GameOverStateName";
	public static final String SCORE_SCREEN_STATE_NAME = "ScoreScreenStateName";
	
	private static int 			NUMGAMESTATES = 0;
	
	private List<AbstractGameState>		gameStates;
	private Stack<AbstractGameState>	gameStateStack;
	
	public GameStateManager(ActionEngine actionEngine) {
		gameStates = new ArrayList<AbstractGameState>();
		gameStateStack = new Stack<AbstractGameState>();
		
		initStates(actionEngine);
	}
	
	private void initStates(ActionEngine actionEngine) {
		gameStates.add(NUMGAMESTATES++, new LoadingScreenState(LOADING_SCREEN_STATE_NAME, actionEngine));
		gameStates.add(NUMGAMESTATES++, new TitleScreenState(TITLE_SCREEN_STATE_NAME, actionEngine));
		gameStates.add(NUMGAMESTATES++, new MainMenuState(MAIN_MENU_STATE_NAME, actionEngine));
		gameStates.add(NUMGAMESTATES++, new PlayState(PLAY_STATE_NAME, actionEngine));
		gameStates.add(NUMGAMESTATES++, new ControlState(CONTROL_STATE_NAME, actionEngine));
		gameStates.add(NUMGAMESTATES++, new NameEntryState(NAME_ENTRY_STATE_NAME, actionEngine));
		gameStates.add(NUMGAMESTATES++, new GameOverState(GAME_OVER_STATE_NAME, actionEngine));
		gameStates.add(NUMGAMESTATES++, new ScoreScreenState(SCORE_SCREEN_STATE_NAME, actionEngine));
		
	}
	
	private boolean isValidState(String stateName) {
		boolean isValid = false;
		
		for(int i = 0; i < gameStates.size(); i++) {
			if(gameStates.get(i).getStateName().equals(stateName)) {
				isValid = true;
				break;
			}
		}
		
		return isValid;
	}
	
	public AbstractGameState getCurrentState() {
		AbstractGameState state = null;
		
		try {
			state = gameStateStack.peek();
		} catch(EmptyStackException e) {
			DebugDumper.handleException(null, DebugDumper.ERR_MESSAGE_GAMESTATE, e.getStackTrace(), e);
		}
		
		return state;
	}
	
	public void advanceState(String stateName) {
		if(!isValidState(stateName)) {
			return;
		}
		
		AbstractGameState stateToPush = null;
		
		for(int i = 0; i < gameStates.size(); i++) {
			if(gameStates.get(i).getStateName().equals(stateName)) {
				stateToPush = gameStates.get(i);
				break;
			}
		}
		
		if(!gameStateStack.isEmpty()) {
			getCurrentState().onSuspend();
		}
		
		gameStateStack.push(stateToPush);
		
		AbstractGameState currentState = getCurrentState();
		currentState.onInit();
	}
	
	//TODO test
	public void resumePreviousState() {
		if(gameStateStack.size() > 1) {
			getCurrentState().onTerminate();
			gameStateStack.pop();
			getCurrentState().onResume();
		}
	}
}
