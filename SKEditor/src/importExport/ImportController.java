package importExport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import htmlConvertToOdt.HtmlToOdtConverter;

public class ImportController {
	private String FileName = "";
	private HtmlToOdtConverter htmlConvert = new HtmlToOdtConverter();
	
	public String importFile() {
		JFileChooser openFileChooser = new JFileChooser();
		String openDocPath="";
		
		JTextPane textArea = new JTextPane();
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "tex files (*.tex)", "tex");

		openFileChooser.setFileFilter(xmlfilter);
		openFileChooser.setCurrentDirectory(new java.io.File("."));
		openFileChooser.setDialogTitle("Please select the document file");
		
		if(openFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			 openDocPath = openFileChooser.getSelectedFile().toString();
			 if (openDocPath.contains(".odt")) {
				 JOptionPane.showMessageDialog(null, openDocPath,"Doc Path", JOptionPane.INFORMATION_MESSAGE);
				 htmlConvert.convertToOdt(textArea, openDocPath,"",false);
				 String importContents = htmlConvert.getImportContents();
				 return importContents;
			 }else {
				 FileReader reader = null;
					try {
						reader = new FileReader(openDocPath);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				    BufferedReader br = new BufferedReader(reader);
				  
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
					JOptionPane.showMessageDialog(null, openDocPath, "Import Action Completed", JOptionPane.INFORMATION_MESSAGE);
					return textArea.getText();
			 }  	
		}
		FileName  = openFileChooser.getName();
		return null;
	}
	
	public String getImportFileName() {
		return FileName;
	}
}