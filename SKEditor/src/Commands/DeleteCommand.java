package Commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import event.EventHandlerSplit;

public class DeleteCommand implements Command {
	public void deleteFile(String openDocPath){
		JFileChooser deleteDocFileChooser = new JFileChooser();
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "tex files (*.tex)", "tex");
		int i = 1;
		deleteDocFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("tex files (*.tex)", "tex"));
		deleteDocFileChooser.setFileFilter(xmlfilter);
		deleteDocFileChooser.setCurrentDirectory(new java.io.File("."));
		deleteDocFileChooser.setDialogTitle("Please select the document file.");
		
		if(deleteDocFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String deleteDocFilePath = deleteDocFileChooser.getSelectedFile().toString();
			Path deleteDocFilePath2 = Paths.get(deleteDocFilePath);
			EventHandlerSplit splitter = new EventHandlerSplit();
			
			if ((deleteDocFileChooser.getSelectedFile().getName()).equals(openDocPath)){
			    JOptionPane.showMessageDialog(null, "The delete action of a current edited document is not permitted.Please select another document to delete.", "Delete Action Not Permitted", JOptionPane.INFORMATION_MESSAGE);

			}else{
					try {
						Files.delete(deleteDocFilePath2);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
						File folder = new File("Files/");
						for (File f : folder.listFiles()){                  // this loop needed in order to delete the entire history of versions
							if (f.getName().endsWith(splitter.splitText(deleteDocFileChooser.getSelectedFile().getName())+"Log"+i+".txt")){
								f.delete();
								i++;
							}
						}
					JOptionPane.showMessageDialog(null, "The document and the history of versions were deleted \n"+deleteDocFilePath, "Delete Action Succesfull", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}	
	
	public void execute() {	
	}
}