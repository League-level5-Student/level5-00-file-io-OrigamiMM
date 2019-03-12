package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save
	 * list, and load list.
	 * 
	 * When add task is clicked: ask the user for a task and save it to an array
	 * list
	 * 
	 * When the view tasks button is clicked: show all the tasks in the list
	 * 
	 * When the remove task button is clicked: prompt the user for which task to
	 * remove and take it off of the list.
	 * 
	 * When the save list button is clicked: Save the list to a file
	 * 
	 * When the load list button is clicked: Prompt the user for the location of the
	 * file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file
	 * into the list.
	 */

	ArrayList<String> tasks = new ArrayList<String>();
	JFrame frame;
	JPanel panel;
	JButton[] buttons;
	String currentTasksDisplay = "";
	String loadedFile = "";

	public static void main(String[] args) {
		ToDoList list = new ToDoList();
		try {
			list.loadFile("src/_03_To_Do_List/test2.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.GUI();
	}

	void GUI() {
		frame = new JFrame();
		panel = new JPanel();
		buttons = new JButton[] { new JButton("AddTask"), new JButton("View Task"), new JButton("Remove Task"),
				new JButton("Save List"), new JButton("Load List") };

		frame.add(panel);
		for (int i = 0; i < buttons.length; i++) {
			panel.add(buttons[i]);
			buttons[i].addActionListener(this);
		}

		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	void updateTaskDisplay() {
		currentTasksDisplay = "";
		for (int i = 0; i < tasks.size(); i++) {
			currentTasksDisplay += i + 1 + ". " + tasks.get(i) + "\n";
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// AddTask Button
		if (buttons[0] == e.getSource()) {
			String task = JOptionPane.showInputDialog(null, "Type in your task");
			tasks.add(task);
		} else if (buttons[1] == e.getSource()) {
			updateTaskDisplay();
			JOptionPane.showMessageDialog(null, currentTasksDisplay);
		} else if (buttons[2] == e.getSource()) {
			String number = JOptionPane.showInputDialog(null,
					"Which task number would you like to remove?\n" + currentTasksDisplay);
			int index = Integer.parseInt(number);
			tasks.remove(index - 1);
			updateTaskDisplay();
			JOptionPane.showMessageDialog(null, "Task " + index + " has been removed");
		} else if (buttons[3] == e.getSource()) {
			try {
				FileWriter fr = new FileWriter("src/_03_To_Do_List/test2.txt");
				for (int j = 0; j < tasks.size(); j++) {
					fr.write(tasks.get(j) + "\n");
				}
				fr.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (buttons[4] == e.getSource()) {
			JFileChooser jfc = new JFileChooser();
			int returnVal = jfc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				loadedFile = jfc.getSelectedFile().getAbsolutePath();
				try {
					loadFile(loadedFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	void loadFile(String fileName) throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			while (line != null) {
				tasks.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
