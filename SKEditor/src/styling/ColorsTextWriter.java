package styling;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ColorsTextWriter {
	public void changeTextColor(JComboBox<String> boxColors,JTextPane textArea) {
		boxColors.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			    StyledDocument doc = textArea.getStyledDocument();
			    int from = textArea.getSelectionStart();
			    int to = textArea.getSelectionEnd();
			    Style style = textArea.addStyle("I'm a Style", null);
			    if (((String) boxColors.getSelectedItem()).equals("Blue")) {
			    	StyleConstants.setForeground(style, Color.blue); 
			    }else if(((String) boxColors.getSelectedItem()).equals("Red")) {
			    	StyleConstants.setForeground(style, Color.red); 
			    }else if (((String) boxColors.getSelectedItem()).equals("White")) {
			    	StyleConstants.setForeground(style, Color.white); 
			    }else if (((String) boxColors.getSelectedItem()).equals("Black")) {
			    	StyleConstants.setForeground(style, Color.black); 
			    }else if (((String) boxColors.getSelectedItem()).equals("Yellow")) {
			    	StyleConstants.setForeground(style, Color.yellow); 
			    }else if (((String) boxColors.getSelectedItem()).equals("Green")) {
			    	StyleConstants.setForeground(style, Color.green); 
			    }else if (((String) boxColors.getSelectedItem()).equals("Cyan")){
			    	StyleConstants.setForeground(style, Color.cyan); 
			    }else if (((String) boxColors.getSelectedItem()).equals("Gray")) {
			    	StyleConstants.setForeground(style, Color.gray); 
			    }else {
			    	StyleConstants.setForeground(style, Color.DARK_GRAY); 
			    }
			    doc.setCharacterAttributes(from, to-from, style, true);		    
		      }
		});
	}
}
