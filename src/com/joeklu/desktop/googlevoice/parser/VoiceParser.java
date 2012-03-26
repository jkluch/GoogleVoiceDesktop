package com.joeklu.desktop.googlevoice.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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
		System.out.println("SMS Count is: "+smsNumber);
		return smsNumber;
	}
	
	private static Properties load(String propsFile) throws IOException{
		/*
		FileOutputStream stream = new FileOutputStream("WEEE.txt");
		OutputStreamWriter out = new OutputStreamWriter(stream, "US-ASCII");;
		out.write("1221");
		out.close();
		*/
		Properties result = null;
		
		// Returns null on lookup failures:
		
		
		encodePropFile(propsFile);							//Encodes file correctly for Properties()
		InputStream in = new FileInputStream(propsFile);	//Reads in file
		if (in != null){
			result = new Properties();
			result.load(in); // Can throw IOException
		}
		testProps = result;
		return result;
	}
	
	private static void encodePropFile(String propsFile) throws IOException{
		String fileEncoding;
		
		FileReader file = new FileReader(propsFile);	//Get file encoding
		fileEncoding = file.getEncoding();				//Place encoding type into string
		
		File infile = new File(propsFile);
		
		Reader in = new InputStreamReader(new FileInputStream(infile), fileEncoding);
		Writer out = new OutputStreamWriter(new FileOutputStream(infile+".temp"), "UTF-8");
		
		int c;
		while ((c = in.read()) != -1){
			out.write(c);
		}
		in.close();
		out.close();
		
		//GoogleVoice.properties.temp is correctly encoded, just need to swap it with GoogleVoice.properties
		
		infile.delete();
		
		File newFile = new File(infile+".temp");
		newFile.renameTo(infile);
		newFile.delete();
		
		//Above deletes GoogleVoice.properties and renames GoogleVoice.properties.temp
	}
}
