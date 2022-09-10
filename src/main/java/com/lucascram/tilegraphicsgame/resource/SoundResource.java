package com.lucascram.tilegraphicsgame.resource;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.lucascram.tilegraphicsgame.io.DebugDumper;

public class SoundResource extends AbstractResource {

	private Clip audioClip;
	
	public SoundResource(String path, String name, Clip audioClip) {
		super(path, name);
		this.audioClip = audioClip;
	}
	
	public void playClip() {
		if(audioClip != null) {
			audioClip.start();
		}
	}
	
	public void loopClip() {
		if(audioClip != null) {
			audioClip.setLoopPoints(0, -1);
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stopClip() {
		if(audioClip != null) {
			audioClip.setMicrosecondPosition(0);
			audioClip.stop();
		}
	}
	
	public void setClipVolume(float level) {
		FloatControl control = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
		try {
			control.setValue(level);
		} catch (IllegalArgumentException e) {
			DebugDumper.handleException(null, DebugDumper.ERR_MESSAGE_AUDIO_VOLUME, e.getStackTrace(), e);
		}
	}
	
	public Clip getAudioClip() {
		return audioClip;
	}
}
