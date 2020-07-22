package convertToPdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import event.EventHandlerSplit;
import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.XDocConverterException;
import fr.opensagres.xdocreport.core.document.DocumentKind;

public class PDFGenerator {
	private EventHandlerSplit splitter = new EventHandlerSplit(); 
	
	// Method to Convert The File to PDF Document
	public void generatePDF(JTextPane textArea,String name,JMenuItem btnGreek,String FilePath) {
		if(name.contains("tex")) {
			
			JOptionPane.showMessageDialog(null, "PDF Export is not available for latex files.","SK Editor", JOptionPane.INFORMATION_MESSAGE);

			/*   this is only usefull for linux operating system
			Process p;
			try {
			    p = Runtime.getRuntime().exec("/usr/bin/pdflatex "+FilePath);
			    p.waitFor();
				JOptionPane.showMessageDialog(null, "PDF Generated Successfully from Latex File "+name,"Latex to PDF Converter", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
			    e.printStackTrace();
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
			
			String nameLog = name.replaceFirst(".tex", ".log");
			String nameAux = name.replaceFirst(".tex", ".aux");
			Path deleteLogFile = Paths.get(nameLog);    // path to the generated LOG file from PDF conversion
			Path deleteAuxFile = Paths.get(nameAux);    // path to the generated AUX file from PDF conversion
			
			try {
				Files.delete(deleteLogFile);      // these files are
				Files.delete(deleteAuxFile);      // deleted because are useless
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			// Finally we move the generated PDF file to the folder of the selected Latex file.
			String pdfName = name.replaceFirst(".tex",".pdf");
			String outputPath = FilePath.replaceFirst(name,pdfName);
			
			try{
				Path temp = Files.move(Paths.get(pdfName),Paths.get(outputPath));
			}catch (IOException ex){
				ex.printStackTrace();
			}*/
			
 		}else {
			// You can change the File Path accordingly
 			
 			String helperString = "";
 			if (FilePath.equals(name)) {
 				File f  = new File(FilePath);
 				helperString = f.getName();
 			}else {
 				helperString = name;
 			}
 			String replaceString = splitter.splitText(helperString)+".pdf";
 			String filepath = FilePath.replaceAll(helperString,replaceString);
 			
			File file = new File(filepath);
			FileOutputStream fos = null;
			
			if (name.contains(".odt")) {
				//JOptionPane.showMessageDialog(null, "About to be done ODT to PDF Export","SK Editor", JOptionPane.INFORMATION_MESSAGE);
				
				// 1) Create options ODT 2 PDF to select well converter form the registry
				Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);

				// 2) Get the converter from the registry
				IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

				// 3) Convert ODT 2 PDF
				InputStream in = null;
				try {
					in = new FileInputStream(new File("C:\\Users\\savaf\\Desktop\\savvas.odt"));
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
				OutputStream out = null;
				try {
					out = new FileOutputStream(new File("C:\\Users\\savaf\\Desktop\\resultSikos.pdf"));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					converter.convert(in, out, options);
				} catch (XDocConverterException e) {
					e.printStackTrace();
				}	
				    
				JOptionPane.showMessageDialog(null, "PDF From ODT Exported succesfully","SK Editor", JOptionPane.INFORMATION_MESSAGE);
			}else {
				try {
					fos = new FileOutputStream(file);
					Document document = new Document();
					PdfWriter.getInstance(document, fos);
					document.open();
					document.add(new Paragraph(textArea.getText()));
					document.close();
					JOptionPane.showMessageDialog(null, "PDF Generated Successfully at folder: "+filepath,"Pdf Converter", JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				} catch (DocumentException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}	
		}
	}
}