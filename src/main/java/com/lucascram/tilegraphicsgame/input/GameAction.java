package com.lucascram.tilegraphicsgame.input;

public class GameAction {
	
	private int		mapping;
	private String	name;
	private boolean	unhandled;
	
	public GameAction(int mapping, String name) {
		this.mapping = mapping;
		this.name = name;
		this.unhandled = false;
	}
	
	/*
	 * Accessors and Mutators
	 */
	
	public int getMapping() {
		return mapping;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isUnhandled() {
		return unhandled;
	}
	
	public void setUnhandled() {
		unhandled = true;
	}
	
	public void handle() {
		unhandled = false;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mapping;
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		GameAction other = (GameAction) obj;
		if (mapping != other.mapping) {
			return false;
		}
		return true;
	}
	
	
}
