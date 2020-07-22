package HardDriveManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;

public class FileSavingController {
	// default constructor
	StyledEditorKit kit;	
	
	
	public void save(String FileName,String contents,JTextPane textArea){
		FileWriter fw = null;
		
		if (FileName.contains(".odt")) {
			 try {
				 
				 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(FileName));
			     StyledDocument doc = textArea.getStyledDocument(); 
			     HTMLEditorKit kitHtml = new HTMLEditorKit();
			     kitHtml.write(out, doc, 0, doc.getLength());
			     out.close();
			    
			 }
			 catch (Exception e)
			 { 	
				 System.err.println("Unable to create output file.");
				 System.err.println(e.getMessage());
			 }
		}else {
			
			File f = new File(FileName);
			
			try {
				fw = new FileWriter(f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			// Writes the content to the file
		    try {
				fw.write(contents);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		    try {
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}