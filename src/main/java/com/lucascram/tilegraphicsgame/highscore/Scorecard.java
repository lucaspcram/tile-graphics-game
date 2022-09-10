package com.lucascram.tilegraphicsgame.highscore;

public class Scorecard implements Comparable<Scorecard> {
	
	private String 		playerName;
	private int			score;
	
	public Scorecard(String playerName, int score) {
		this.playerName = playerName;
		this.score = score;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getScore() {
		return score;
	}

	public int compareTo(Scorecard s) {
		Scorecard other = s;
		
		if(other.getScore() > score) {
			return 1;
		} else if(other.getScore() < score) {
			return -1;
		}
		
		return 0;
	}
	
	public String toString() {
		return playerName + ";" + score;
	}
}
