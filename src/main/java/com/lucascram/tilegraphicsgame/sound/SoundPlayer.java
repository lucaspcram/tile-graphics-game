package com.lucascram.tilegraphicsgame.sound;

import com.lucascram.tilegraphicsgame.io.DebugDumper;
import com.lucascram.tilegraphicsgame.resource.*;


/*
 * Class is static for convenience. Allows tracks to be played across gamestates.
 */

public class SoundPlayer {
	
	public static final String TITLE_MUS = "title_mus";
	
	private static ResourceManager resourceManager;
	private static boolean  musicLoaded;
	
	private SoundPlayer() {
		
	}
	
	public static void loadMusic() {
		resourceManager = new ResourceManager();
		musicLoaded = false;
		
		resourceManager.loadSound("res/sound/title.wav", TITLE_MUS);
		
		musicLoaded = true;
	}
	
	public static void loopTrack(String trackName) {
		try {
			resourceManager.getSoundResource(trackName).loopClip();
		} catch(NullPointerException e) {
			DebugDumper.handleException(null, DebugDumper.ERR_MESSAGE_RESOURCE, e.getStackTrace(), e);
		}
	}
	
	public static void stopTrack(String trackName) {
		try {
			resourceManager.getSoundResource(trackName).stopClip();
		} catch(NullPointerException e) {
			DebugDumper.handleException(null, DebugDumper.ERR_MESSAGE_RESOURCE, e.getStackTrace(), e);
		}
	}
	
	public static void modifyTrackVolume(String trackName, float level) {
		try {
			resourceManager.getSoundResource(trackName).setClipVolume(level);
		} catch(NullPointerException e) {
			DebugDumper.handleException(null, DebugDumper.ERR_MESSAGE_RESOURCE, e.getStackTrace(), e);
		}
	}
}
