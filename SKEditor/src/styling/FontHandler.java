package styling;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FontHandler {
	
	public void FontHandling(JComboBox<String> boxSize,JComboBox<String> boxFont,JTextPane textArea) {
		
		boxSize.addActionListener(e -> {
		      int size = Integer.parseInt((String) boxSize.getSelectedItem());
		      Action fontAction = new StyledEditorKit.FontSizeAction(String.valueOf(size), size);
		      fontAction.actionPerformed(e);
		});
		
		boxFont.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent e) {
		    	  StyledDocument doc = textArea.getStyledDocument();
	                int start = textArea.getSelectionStart();
	                int end = textArea.getSelectionEnd();
	                if (start == end) { // No selection, cursor position.
	                    return;
	                }
	                if (start > end) { 
	                    int life = start;
	                    start = end;
	                    end = life;
	                }
	                String choiseFont = (String) boxFont.getSelectedItem();
	                Style style = textArea.addStyle("Font",null);
			    	StyleConstants.setFontFamily(style,choiseFont); 
	                doc.setCharacterAttributes(start, end - start, style, false);
		      }
		});
	}
}	