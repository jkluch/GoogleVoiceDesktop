package com.joeklu.googlevoice.gui.login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.util.Properties;

public class GoogleVoiceLoginGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Properties testProps = null;
	private String userName = null;
	private String pass = null;
	static boolean connectOnStartup = false;
	private JPanel contentPane;
	private JTextField UserField;
	private JTextField PassField;

	/**
	 * Create the frame.
	 */
	public GoogleVoiceLoginGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGoogleVoiceLogin = new JLabel("Google Voice Login");
		lblGoogleVoiceLogin.setBounds(144, 11, 157, 39);
		lblGoogleVoiceLogin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGoogleVoiceLogin.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblGoogleVoiceLogin);
		
		UserField = new JTextField();
		UserField.setBounds(157, 84, 144, 20);
		contentPane.add(UserField);
		UserField.setColumns(10);
		
		PassField = new JTextField();
		PassField.setBounds(157, 127, 144, 20);
		contentPane.add(PassField);
		PassField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(80, 87, 67, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(80, 130, 67, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] values=new String[2];
				values[0] = UserField.getText();
				values[1]= PassField.getText();
				setCredentials(values);
			}
		});
		contentPane.add(btnLogin);
		
		btnLogin.setBounds(168, 210, 89, 23);
		contentPane.add(btnLogin);
		
		JCheckBox chckbxRememberLogin = new JCheckBox("Remember Login");
		chckbxRememberLogin.setBounds(157, 154, 105, 23);
		contentPane.add(chckbxRememberLogin);
		
		JCheckBox chckbxAutoLogin = new JCheckBox("Auto Login");
		chckbxAutoLogin.setBounds(157, 180, 97, 23);
		contentPane.add(chckbxAutoLogin);
	}
	
	public void launchLogin(){
		GoogleVoiceLoginGui frame = new GoogleVoiceLoginGui();
		frame.setVisible(true);
	}
	private void setCredentials(String[] fields){
		userName=fields[0];
		pass=fields[1];
	}
	public String[] getCredentials(){
		String[] fields=new String[2];
		fields[0]=userName;
		fields[1]=pass;
		return fields;
	}
}
