package com.joeklu.googlevoice.parser;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;
import javax.swing.JOptionPane;

public class VoiceParser {
	static Properties inbox = null;
	static int smsCount;
	static boolean connectOnStartup = false;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String userName = "";
	static String pass = "";
	static Properties testProps = null;
	
	public VoiceParser(){
		
	}
	public String[] loginCredentials(){
		String[] credentials = new String[2];
		try{
			//String userdir = System.getProperty("user.dir");
			//System.out.println("./GoogleVoice.properties");
			testProps = load("./GoogleVoice.properties");
			userName = testProps.getProperty("username");
			pass = testProps.getProperty("password");
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Could not find properties file");
		}
		credentials[0]=userName;
		credentials[1]=pass;
		return credentials;
	}
	
	public String getSMSCount(String stringToParse){
		String tempWord;
		String lookFor="\"sms\":";
		String smsNumber="";
		int i=0;
			
		while(((stringToParse.length()-lookFor.length())-i)!=-1){
			tempWord=stringToParse.substring(i, (lookFor.length()+i));
			if(lookFor.equals(tempWord)){
				smsNumber=stringToParse.substring((lookFor.length()+i), (lookFor.length()+i+1));
				break;
			}
			i++;
		}
		System.out.println(smsNumber);
		return smsNumber;
	}
	
	private static Properties load(String propsFile) throws IOException{
		//
		FileOutputStream stream = new FileOutputStream("WEEE.txt");
		OutputStreamWriter out = new OutputStreamWriter(stream, "US-ASCII");;
		out.write("1221");
		out.close();
		//
		Properties result = null;
		
		// Returns null on lookup failures:
		InputStream in = new FileInputStream(propsFile); 
		if (in != null){
			System.out.print("fuckin file");
			result = new Properties();
			result.load(in); // Can throw IOException
		}
		testProps = result;
		return result;
	}
}
