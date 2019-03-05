package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileEncryptor {
	// Create a program that takes a message from the user.
	// Use the methods in the String and Character classes to save
	// an encrypted form of the message to a file
	public static void main(String[] args) {
		String normalMessage = JOptionPane.showInputDialog("What would you like to encrpt?");
		String enMessage = encrpytString(normalMessage);
		encrpytMessageFile(enMessage);
	}
	
	static String encrpytString(String s) {
		String nString = "";
		for(int i = 0; i < s.length(); i++) {
			char c = (char) (s.charAt(i) + 5);
			nString += c + "";
		}
		return nString;
	}
	
	static void encrpytMessageFile(String msg) {
		try {
			FileWriter fr = new FileWriter("src/_00_Intro_To_File_Input_and_Output/test.txt");
			fr.write(msg);
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
