
package com.joeklu.desktop.googlevoice.traynoti;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import com.joeklu.desktop.googlevoice.soundmaker.NotifySound;


public class TrayNotifier{
		/* Use an appropriate Look and Feel */
	NotifySound sounds = new NotifySound();
	MenuItem countItem;
	
	public TrayNotifier(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex1) {
			ex1.printStackTrace();
		} catch (InstantiationException ex2) {
			ex2.printStackTrace();
		} catch (ClassNotFoundException ex3) {
			ex3.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		//Schedule a job for the event-dispatching thread:
		//adding TrayIcon.
	}
	public void makeGUI(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private void createAndShowGUI(){
		//Check the SystemTray support
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		PopupMenu popup = new PopupMenu();
		final TrayIcon trayIcon =
				new TrayIcon(createImage("images/favicon.png", "tray icon"));
		final SystemTray tray = SystemTray.getSystemTray();
		
		// Create a popup menu components
		MenuItem aboutItem = new MenuItem("About");
		MenuItem soundItem = new MenuItem("Sound Test");
		countItem = new MenuItem("New SMS count: 0");
		Menu displayMenu = new Menu("Display");
		MenuItem errorItem = new MenuItem("Error");
		MenuItem warningItem = new MenuItem("Warning");
		MenuItem infoItem = new MenuItem("Info");
		MenuItem noneItem = new MenuItem("None");
		MenuItem exitItem = new MenuItem("Exit");
		
		//Add components to popup menu
		popup.add(aboutItem);
		popup.add(countItem);
		popup.add(soundItem);
		popup.addSeparator();
		popup.add(displayMenu);
		displayMenu.add(errorItem);
		displayMenu.add(warningItem);
		displayMenu.add(infoItem);
		displayMenu.add(noneItem);
		popup.add(exitItem);
		
		trayIcon.setPopupMenu(popup);
		
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
			return;
		}
		
		trayIcon.setImageAutoSize(true);
		trayIcon.setToolTip("Googe Voice Desktop");
		
		trayIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"This dialog box is run from System Tray");
			}
		});
		
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Made by Joe Kluchinski\nMarch 2012");
			}
		});
		
		soundItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sounds.createSound("sounds/sound.wav");
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuItem item = (MenuItem)e.getSource();
				//TrayIcon.MessageType type = null;
				System.out.println(item.getLabel());
				if ("Error".equals(item.getLabel())) {
					//type = TrayIcon.MessageType.ERROR;
					trayIcon.displayMessage("Sun TrayIcon Demo",
							"This is an error message", TrayIcon.MessageType.ERROR);
					
				} else if ("Warning".equals(item.getLabel())) {
					//type = TrayIcon.MessageType.WARNING;
					trayIcon.displayMessage("Sun TrayIcon Demo",
							"This is a warning message", TrayIcon.MessageType.WARNING);
					
				} else if ("Info".equals(item.getLabel())) {
					//type = TrayIcon.MessageType.INFO;
					trayIcon.displayMessage("Sun TrayIcon Demo",
							"This is an info message", TrayIcon.MessageType.INFO);
					
				} else if ("None".equals(item.getLabel())) {
					//type = TrayIcon.MessageType.NONE;
					trayIcon.displayMessage("Sun TrayIcon Demo",
							"This is an ordinary message", TrayIcon.MessageType.NONE);
				}
			}
		};
		
		errorItem.addActionListener(listener);
		warningItem.addActionListener(listener);
		infoItem.addActionListener(listener);
		noneItem.addActionListener(listener);
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});
	}
	public void setSMSCount(String SMS){
		SMS="New SMS count "+SMS;
		countItem.setLabel(SMS);
	}
	//Obtain the image URL
	protected static Image createImage(String path, String description) {
		URL imageURL = TrayNotifier.class.getResource(path);
		
		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}
}
