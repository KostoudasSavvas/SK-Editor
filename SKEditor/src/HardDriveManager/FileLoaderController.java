package HardDriveManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileLoaderController {
	String DocName="";
	
	public String load(){
		JFileChooser openFileChooser = new JFileChooser();
		String openDocPath="";
		
		JTextArea textArea = new JTextArea();
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "tex files (*.tex)", "tex");

		openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("tex files (*.tex)", "tex"));
		openFileChooser.setFileFilter(xmlfilter);
		openFileChooser.setCurrentDirectory(new java.io.File("."));
		openFileChooser.setDialogTitle("Please select the document file.");
		
		if(openFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			 openDocPath = openFileChooser.getSelectedFile().toString();
			   	FileReader reader = null;
				try {
					reader = new FileReader(openDocPath);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			    BufferedReader br = new BufferedReader(reader);
			    DocName =  openFileChooser.getSelectedFile().getName();
			  
			    try {
					textArea.read(br, null);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			    
			    try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			    textArea.requestFocus();
			    JOptionPane.showMessageDialog(null, openDocPath, "Directory of chosen latex document", JOptionPane.INFORMATION_MESSAGE);
			    
		}
		return textArea.getText();
	}
	
	public String getLoadName(JMenuItem btnGreek) {
		JFileChooser openFileChooser = new JFileChooser();
		String openDocPath="";
		
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "odt files (*.odt)","odt");
		FileNameExtensionFilter xmlfilter2 = new FileNameExtensionFilter( "tex files (*.tex)","tex");
		FileNameExtensionFilter xmlfilter3 = new FileNameExtensionFilter( "txt files (*.txt)","txt");

		openFileChooser.addChoosableFileFilter(xmlfilter);
		openFileChooser.addChoosableFileFilter(xmlfilter2);
		openFileChooser.addChoosableFileFilter(xmlfilter3);
		openFileChooser.setFileFilter(xmlfilter3);
		openFileChooser.setFileFilter(xmlfilter);
		openFileChooser.setFileFilter(xmlfilter2);
		openFileChooser.setCurrentDirectory(new java.io.File("."));
		if (btnGreek.isSelected()) {
			openFileChooser.setDialogTitle("Παρακαλώ διαλέξτε το αρχείο φόρτωσης.");
		}else {
			openFileChooser.setDialogTitle("Please select the document file.");
		}
		
		if(openFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			 openDocPath = openFileChooser.getSelectedFile().toString();
			 DocName =  openFileChooser.getSelectedFile().getName(); 
			 if (btnGreek.isSelected()) {
				 JOptionPane.showMessageDialog(null, openDocPath, "Φάκελος αρχείου φόρτωσης", JOptionPane.INFORMATION_MESSAGE);
			 }else {
				 JOptionPane.showMessageDialog(null, openDocPath, "Directory of chosen document", JOptionPane.INFORMATION_MESSAGE);
			 }
		}
		
		return openDocPath;
	}
	
	public String getName(){
		return DocName;
	}
}