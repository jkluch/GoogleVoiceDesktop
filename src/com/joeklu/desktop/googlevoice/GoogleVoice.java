package com.joeklu.desktop.googlevoice;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.joeklu.desktop.googlevoice.gui.login.GoogleVoiceLoginGui;
import com.joeklu.desktop.googlevoice.parser.VoiceParser;
import com.joeklu.desktop.googlevoice.soundmaker.NotifySound;
import com.joeklu.desktop.googlevoice.traynoti.TrayNotifier;
import com.techventus.server.voice.Voice;

public class GoogleVoice{

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException{
		TrayNotifier tray = new TrayNotifier();
		NotifySound sounds = new NotifySound();
		VoiceParser parse = new VoiceParser();
		Voice voice=null;
		String[] credentials=new String[2];
		String smsCount=null;
		
		
		//credentials=parse.loginCredentials();
		
			GoogleVoiceLoginGui frame = new GoogleVoiceLoginGui();
			frame.launchLogin();
			while(frame.freezer==0){
				//Don't do the next part till login is pushed
				//Sleep for 10 seconds to save on cpu
				Thread.sleep(10000);
			}
			credentials=frame.getCredentials();
		
			
		voice = new Voice(credentials[0], credentials[1]);
		
		tray.makeGUI();
		
		while(true){
		smsCount=voice.getInboxPage(1000);
		smsCount=parse.getSMSCount(smsCount);
			if(!smsCount.equalsIgnoreCase("0")){
				sounds.createSound("sounds/sound.wav");
			}
		tray.setSMSCount(smsCount);
		Thread.sleep(60000);
		}
		
	}

}
