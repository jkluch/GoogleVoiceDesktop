package com.joeklu.desktop.googlevoice.gui.sms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import java.awt.Rectangle;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class GoogleVoiceSms extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3466273950571443581L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoogleVoiceSms frame = new GoogleVoiceSms();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GoogleVoiceSms() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(splitPane);
		
		String[] smsList = new String[20];
		smsList[0]=("Hey Test");
		smsList[1]=("Hey 2 Test");
		smsList[2]=("Hey 3 Test");
		JList<String> list = new JList<String>();
		JTextPane tempPane = new JTextPane();
		tempPane.setText("Hey I hope I can Read this!");
		splitPane.setLeftComponent(tempPane);
		
		JTextPane textPane = new JTextPane();
		JSplitPane splitPane2 = new JSplitPane();
		splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane2);
		splitPane2.setTopComponent(textPane);
		JTextPane tempPane2 = new JTextPane();
		tempPane2.setText("Hey I hope I can Read this!");
		splitPane2.setBottomComponent(tempPane2);
	}

}
