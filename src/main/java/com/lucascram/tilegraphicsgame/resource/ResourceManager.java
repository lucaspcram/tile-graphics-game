package com.lucascram.tilegraphicsgame.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.lucascram.tilegraphicsgame.graphics.Animation;
import com.lucascram.tilegraphicsgame.io.DebugDumper;

public class ResourceManager {
	
	private Map<String, AbstractResource> 	resourceMap;
	
	public ResourceManager() {
		resourceMap = new HashMap<String, AbstractResource>();
	}
	
	public void loadImage(String path, String name) {
		ImageResource resource;
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			DebugDumper.handleException(path, DebugDumper.ERR_MESSAGE_FILEIO, e.getStackTrace(), e);
		}
		
		resource = new ImageResource(path, name, image);
		resource.setLoaded(true);
		resourceMap.put(resource.getName(), resource);
	}
	
	public void loadSound(String path, String name) {
		SoundResource resource;
		Clip audioClip = null;
		
		try {
			audioClip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
			audioClip.open(inputStream);
			inputStream.close();
		} catch(Exception e) {
			DebugDumper.handleException(path, DebugDumper.ERR_MESSAGE_FILEIO, e.getStackTrace(), e);
		}
		
		resource = new SoundResource(path, name, audioClip);
		resource.setLoaded(true);
		resourceMap.put(resource.getName(), resource);
	}
	
	public void loadFont(String path, String name) {
		
	}
	
	public void loadAnimation(BufferedImage[] frames, long[] endTimes, String name) {
		AnimationResource resource;
		Animation anim = new Animation();
		for(int i = 0; i < frames.length; i++) {
			anim.addFrame(frames[i], endTimes[i]);
		}
		
		resource = new AnimationResource(null, name, anim);
		resource.setLoaded(true);
		resourceMap.put(resource.getName(), resource);
	}
	
	public ImageResource getImageResource(String name) {
		ImageResource resource = null;
		resource = (ImageResource)resourceMap.get(name);
		return resource;
	}
	
	public SoundResource getSoundResource(String name) {
		SoundResource resource = null;
		resource = (SoundResource)resourceMap.get(name);
		return resource;
	}
	
	public FontResource getFontResource(String name) {
		FontResource resource = null;
		resource = (FontResource)resourceMap.get(name);
		return resource;
	}
	
	public AnimationResource getAnimationResource(String name) {
		AnimationResource resource = null;
		resource = (AnimationResource)resourceMap.get(name);
		return resource;
	}
}
