package Commands;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class CommandValidatorOnLoad {
	// this class validates the commands in regards with the templates but ONLY in the case that the editor is empty and we load a doc to edit from disk.
	
	public Boolean validate(String commandText,JTextPane textArea){
		String areaContent = textArea.getText();
		if (areaContent.contains("letter")){
			if (commandText.contains("section") || commandText.contains("matter") || commandText.contains("item") || commandText.contains("chapter") || commandText.contains("title") || commandText.contains("author") || commandText.contains("begin")){
				JOptionPane.showMessageDialog(null, "The selected command is not placed because letter template is selected.", "False Latex Command is selected", JOptionPane.INFORMATION_MESSAGE);
			}else{
				return true;
			}
		}else if (areaContent.contains("article")){
			if (commandText.contains("chapter") ||commandText.contains("ps") || commandText.contains("signature")){
				JOptionPane.showMessageDialog(null, "The selected command is not placed because article template is selected.", "False Latex Command is selected", JOptionPane.INFORMATION_MESSAGE);
			}else{
				return true;
			}
		}else if (areaContent.contains("report") || areaContent.contains("book")){
			if (commandText.contains("ps") || commandText.contains("signature")){
				JOptionPane.showMessageDialog(null, "The selected command is not placed because report or book template is selected.", "False Latex Command is selected", JOptionPane.INFORMATION_MESSAGE);
			}else{
				return true;
			}
		}else{   // this case is for the new empty template
			return true;
		}
		return false;
	}
}