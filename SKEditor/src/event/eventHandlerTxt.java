package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GenerateGui.NewFileCreator;

public class eventHandlerTxt implements ActionListener{
	private JFrame newWindow;               // the window of the new file
   	private int number;
   	private JTextField JField;               // the name of the file
   	private JTextField textField_path;      // the path of the new file
   	private JMenuItem btnGreek;
   	private JLabel labelName;
	
	public eventHandlerTxt(JFrame newWindow,int number,JLabel labelName,JTextField JField,JTextField textField_path,JMenuItem btnGreek){
		this.newWindow = newWindow;
		this.number = number;
		this.JField = JField;
		this.btnGreek = btnGreek;
		this.textField_path = textField_path;
		this.labelName = labelName;
	}

	public void actionPerformed(ActionEvent e) {
		
		if (number == 1){   // button create was clicked
			
			if (JField.getText().isEmpty()){
				if (btnGreek.isSelected()) {
					JOptionPane.showMessageDialog(null, "Î Î±Ï�Î±ÎºÎ±Î»ÏŽ Ï„Î¿Ï€Î¿Î¸ÎµÏ„Î®ÏƒÏ„Îµ Ï„Î¿ ÏŒÎ½Î¿Î¼Î± Ï„Î¿Ï… Î½Î­Î¿Ï… Î±Ï�Ï‡ÎµÎ¯Î¿Ï….");
				}else {
					JOptionPane.showMessageDialog(null, "Please type the name of the new document.");
				}			
			}else if (!(JField.getText().contains("txt"))) {
				if (btnGreek.isSelected()) {
					JOptionPane.showMessageDialog(null, "Î Î±Ï�Î±ÎºÎ±Î»ÏŽ Ï„Î¿Ï€Î¿Î¸ÎµÏ„Î®ÏƒÏ„Îµ Î­Î½Î± ÏƒÏ‰ÏƒÏ„ÏŒ ÏŒÎ½Î¿Î¼Î± Î±Ï�Ï‡ÎµÎ¯Î¿Ï… Ï„Î¿ Î¿Ï€Î¿Î¯Î¿ Î¸Î± Ï€Ï�Î­Ï€ÎµÎ¹ Î½Î± Î­Ï‡ÎµÎ¹ ÎºÎ±Ï„Î¬Î»Î·Î¾Î· .txt");
				}else {
					JOptionPane.showMessageDialog(null, "Please type a correct name of the new document with .txt ending.");
				}
			} else if (textField_path.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please set the destination folder of the new document.");
			}else{
				
				PrintWriter writer = null;
				
				try {
					writer = new PrintWriter(textField_path.getText(),"UTF-8");
					writer.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				
				Object[] options = {"OK"};              // the button and the n are for the confirm message that the empty doc was created
				if (btnGreek.isSelected()) {
					int n = JOptionPane.showOptionDialog(newWindow,"Î Î¹ÏƒÏ„Î¿Ï€Î¿Î¯Î·ÏƒÎ· ÎšÎ±Î¹Î½Î¿Ï�Ï�Î¹Î¿Ï… Î‘Ï�Ï‡ÎµÎ¯Î¿Ï…","Î”Î·Î¼Î¹Î¿Ï…Ï�Î³ÏŒÏ‚ ÎºÎ±Î¹Î½Î¿Ï�Ï�Î¹Î¿Ï… Î±Ï�Ï‡ÎµÎ¯Î¿Ï…",JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null, options,options[0]);					
				}else {
					int n = JOptionPane.showOptionDialog(newWindow,"New Document Verification","Txt New file Creator",JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null, options,options[0]);
				}
				
				NewFileCreator nCreate = new NewFileCreator("");
				MainEditorWindowHelper helper = new MainEditorWindowHelper();
				helper.makeVisible(nCreate.getFrame(),textField_path.getText(),nCreate.getBoxList(),nCreate.getMenuList(),nCreate.getTextArea(),nCreate.getCommandMenu(),nCreate.getClockChoises(),nCreate.getLabelWords(),nCreate.getLabelChars(),nCreate.getImportItem(),nCreate.getExportMenu(),nCreate.getGreekItem());
				newWindow.setVisible(false);
			}
		}else if (number == 2){          // button cancel was clicked
			newWindow.setVisible(false);
		}else if (number == 3) {         // button search was clicked
			File fileToSave;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a folder to save");   
			 
			int userSelection = fileChooser.showSaveDialog(newWindow);
			
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    fileToSave = fileChooser.getSelectedFile();
			    textField_path.setText(fileToSave.getAbsolutePath());
			    File fn = new File(textField_path.getText());
				JField.setText(fn.getName());
				JField.setVisible(true);
				labelName.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Something wrong happened. Please select again the destination folder.");
			}
			
		}else{
			if (btnGreek.isSelected()) {
				JOptionPane.showMessageDialog(null, "Î Î±Ï�Î±ÎºÎ±Î»ÏŽ Î´Î¹Î±Î»Î­Î¾Ï„Îµ ÎºÎ¬Ï€Î¿Î¹Î¿ Î±Ï€ÏŒ Ï„Î± Î´Ï�Î¿ ÎºÎ¿Ï…Î¼Ï€Î¹Î¬.");
			}else {
				JOptionPane.showMessageDialog(null, "Please select any of the two buttons.");
			}		
		}
	}
}