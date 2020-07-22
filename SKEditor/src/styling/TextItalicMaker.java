package styling;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class TextItalicMaker {
	
	public void italicTextMaker(JMenuItem btnItalic,JTextPane textArea) {
		btnItalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StyleHelper styleHelp  = new StyleHelper();
				styleHelp.textStyleSetter(textArea, "italic");
			}    
		});
	}
}