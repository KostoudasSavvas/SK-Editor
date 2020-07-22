package Commands;

import javax.swing.JOptionPane;

public class CommandValidator {
	// this class validates each command in regards with the chosen template
	
	// validation method
	public Boolean validate(String[] content,String commandText){
		if (content[0].contains("letter")){
			if (commandText.contains("section") || commandText.contains("matter") || commandText.contains("item") || commandText.contains("chapter") || commandText.contains("title") || commandText.contains("author") || commandText.contains("begin")){
				JOptionPane.showMessageDialog(null, "The selected command is not placed because letter template is selected.", "False Latex Command is selected", JOptionPane.INFORMATION_MESSAGE);
			}else{
				return true;
			}
		}else if (content[0].contains("article")){
			if (commandText.contains("chapter") ||commandText.contains("ps") || commandText.contains("signature")){
				JOptionPane.showMessageDialog(null, "The selected command is not placed because article template is selected.", "False Latex Command is selected", JOptionPane.INFORMATION_MESSAGE);
			}else{
				return true;
			}
		}else if (content[0].contains("report") || content[0].contains("book")){
			if (commandText.contains("ps") || commandText.contains("signature")){
				JOptionPane.showMessageDialog(null, "The selected command is not placed because report or book template is selected.", "False Latex Command is selected", JOptionPane.INFORMATION_MESSAGE);
			}else{
				return true;
			}
		}
		return false;
	}	
}