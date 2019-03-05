package _01_File_Recorder;

import java.io.FileWriter;

import javax.swing.JOptionPane;

public class FileRecorder {
	// Create a program that takes a message from the user and saves it to a file.

	public static void main(String[] args) {
		String message = JOptionPane.showInputDialog("What would you like to write in this file?");
		writeFile(message);
	}
	
	static void writeFile(String msg) {
		try {
			FileWriter fr = new FileWriter("src/_00_Intro_To_File_Input_and_Output/test2.txt");
			fr.write(msg);
			fr.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
