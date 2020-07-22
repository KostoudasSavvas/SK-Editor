package importExport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.odftoolkit.odfdom.doc.OdfTextDocument;

import convertToPdf.PDFGenerator;

public class exportController {
	
	private static PDFGenerator pdfConverter = new PDFGenerator();

	public void exportFileAs(String choise,JTextPane area,String fileName,JMenuItem btnGreek) {
		
		JFileChooser openFileChooser = new JFileChooser();
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "tex files (*.tex)", "tex");

		openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("tex files (*.tex)", "tex"));
		openFileChooser.setFileFilter(xmlfilter);
		openFileChooser.setCurrentDirectory(new java.io.File("."));
		openFileChooser.setDialogTitle("Please select the document file");
		
		if(openFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (choise.contains("pdf")) {
				pdfConverter.generatePDF(area, openFileChooser.getSelectedFile().getName(), btnGreek,openFileChooser.getName());
			}else if (choise.contains("odt")) {
				OdfTextDocument outputOdt; 
				 try {
					 outputOdt = OdfTextDocument.newTextDocument();
					 outputOdt.newParagraph(area.getText());
					 outputOdt.save("Odt Files/"+openFileChooser.getSelectedFile().getName());
					 JOptionPane.showMessageDialog(null, "Odt file exported successfully.","Odt Export", JOptionPane.INFORMATION_MESSAGE);
				 }
				 catch (Exception e)
				 { 	
					 System.err.println("Unable to create output file.");
					 System.err.println(e.getMessage());
				 }
			}else {
				File out = new File("Files/"+openFileChooser.getSelectedFile().getName());
				try {
					FileWriter writer = new FileWriter(out);
					writer.write(area.getText());
					writer.close();
					 JOptionPane.showMessageDialog(null, "Txt file exported successfully.","Txt Export", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}