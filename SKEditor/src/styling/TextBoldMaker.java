package styling;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TextBoldMaker {
	
	public void boldTextMaker(JMenuItem btnBold,JTextPane textArea) {
		btnBold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StyleHelper styleHelp  = new StyleHelper();
				styleHelp.textStyleSetter(textArea, "bold");
			}    
		});
	}
}	