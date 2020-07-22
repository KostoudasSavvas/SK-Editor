package printer;

import javax.swing.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.printing.PDFPageable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePrinting{
	
	public void print(JTextPane textArea,JMenuItem btnPrint,String FILENAME) {
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (FILENAME.contains(".tex")) {
					JOptionPane.showMessageDialog(null, "The document is Latex template and Latex templates cannot be printed \n", "Print Action Error", JOptionPane.INFORMATION_MESSAGE);
				}else {
					// You can change the File Path accordingly
					File file = new File("Pdf Files/"+"printHelp.pdf");
					FileOutputStream fos = null;
					
					try {
						fos = new FileOutputStream(file);
						Document document = new Document();
						PdfWriter.getInstance(document, fos);
						document.open();
						document.add(new Paragraph(textArea.getText()));
						document.close();
						
					} catch (FileNotFoundException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					} catch (DocumentException ed) {
						System.out.println(ed.getMessage());
						ed.printStackTrace();
					}
					String filename = "Pdf Files/printHelp.pdf"; 
				    PDDocument document;
					try {
						document = PDDocument.load(new File (filename));
					    PrintService myPrintService = PrintServiceLookup.lookupDefaultPrintService();
						PrinterJob job = PrinterJob.getPrinterJob();
					    job.setPageable(new PDFPageable(document));
					    try {
							job.setPrintService(myPrintService);
						} catch (PrinterException e1) {
							e1.printStackTrace();
						}
					    try {
							job.print();
							JOptionPane.showMessageDialog(null, "The document has been printed succesfully \n", "Print Action Succesfull", JOptionPane.INFORMATION_MESSAGE);
						} catch (PrinterException e1) {
							e1.printStackTrace();
						}
					} catch (InvalidPasswordException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					Path deleteDocFilePath2 = Paths.get("Pdf Files/printHelp.pdf");
					try {
						Files.delete(deleteDocFilePath2);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				}
		});
	}
}