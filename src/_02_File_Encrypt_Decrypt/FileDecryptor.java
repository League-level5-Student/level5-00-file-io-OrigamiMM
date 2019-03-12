package _02_File_Encrypt_Decrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileDecryptor {
	// Create a program that opens the file created by FileEncryptor and display
	// the decrypted message to the user in a JOptionPane.

	public static void main(String[] args) {
		String path = getFileName();
		try {
			String enMess = stringFromFile(path);
			String msg = decryptString(enMess);
			JOptionPane.showMessageDialog(null, "The mesg is:\n" + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static String getFileName() {
		String fileName = "";
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileName = jfc.getSelectedFile().getAbsolutePath();
			// System.out.println(fileName);
		}
		return fileName;
	}

	static String stringFromFile(String fileName) throws IOException {
		String message = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			while (line != null) {
				message += line;
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}

	static String decryptString(String msg) {
		String dMessage = "";
		for (int i = 0; i < msg.length(); i++) {
			char c = (char) (msg.charAt(i) - 5);
			dMessage += c + "";
		}
		return dMessage;
	}

}
