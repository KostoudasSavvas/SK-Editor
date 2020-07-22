package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class CommandPlaceController {
	
	// this class just places the commands at the right place in the jtextarea if the current template allows it.
	private CommandValidator valCom;                  // ValidateCommand object to check each command if applicable for the selected template.
	private CommandValidatorOnLoad valComOnLoad;      // ValidateCommandOnLoad object to check each command if applicable only for the loaded document.
	
	
	public void actionHelp(JMenuItem item,String commandText,JTextPane textArea,ArrayList<String> dataClass,String FileName){
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					String[] content = dataClass.toArray(new String[dataClass.size()]);     // convert to array for easier iteration
					StyledDocument doc = textArea.getStyledDocument();
					if(FileName.contains("tex")) {
						if (dataClass.isEmpty()){        // this case is for empty template or loaded template from disk
							CommandValidatorOnLoad valComOnLoad = new CommandValidatorOnLoad();
							if (valComOnLoad.validate(commandText, textArea)){
								try {
									doc.insertString(textArea.getCaretPosition(),commandText,null);
								} catch (BadLocationException e1) {
									e1.printStackTrace();
								}
							}
						}else{
							CommandValidator valCom = new CommandValidator();
							if (valCom.validate(content, commandText)){
								try {
									doc.insertString(textArea.getCaretPosition(),commandText,null);
								} catch (BadLocationException e1) {
									e1.printStackTrace();
								}							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "The Latex Commands are prohibited because Txt template is selected.", "False Latex Command is selected", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
	}
}