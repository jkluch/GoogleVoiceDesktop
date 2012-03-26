package com.joeklu.desktop.googlevoice.soundmaker;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

public class NotifySound{
	AudioClip clip;
	
	public AudioClip createSound(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		URL url = getClass().getResource(filename);
		clip = Applet.newAudioClip(url);
		clip.play();
		return clip;
	}
	public void getSound(){
		
	}
}
