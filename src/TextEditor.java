import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextEditor {
	JFrame frame;
	JPanel panel;
	JButton saveButton;
	JButton loadButton;
	JTextArea text;
	String fileName;

	public static void main(String[] args) {
		new TextEditor().GUI();
	}

	void GUI() {
		frame = new JFrame();
		panel = new JPanel();
		saveButton = new JButton("Save");
		loadButton = new JButton("load");
		text = new JTextArea("type in some text here", 30, 40);
		
		frame.add(panel);
		panel.add(text);
		panel.add(saveButton);
		panel.add(loadButton);
		saveButton.addActionListener((e)->{
			saveFile();
		});
		loadButton.addActionListener((e)->{
			loadFile();
		});
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	void saveFile(){
		String textTyped = text.getText();
		String path = JOptionPane.showInputDialog("Where would you like to save the file?");
		try {
			FileWriter fw = new FileWriter(path);
			fw.write(textTyped);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void loadFile() {
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileName = jfc.getSelectedFile().getAbsolutePath();
		}
		
		String savingText = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader(fileName));
			String temp = bf.readLine();
			while(temp != null) {
				savingText += temp;
				temp = bf.readLine();
			}
			bf.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		text.setText(savingText);
	}
}
