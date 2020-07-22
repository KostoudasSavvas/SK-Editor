package styling;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class StyleHelper {
	// this class utility is to provide help on style text setting and to reduce double code.
	
	public void textStyleSetter(JTextPane textArea,String styleChoise) {
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
         Style style = textArea.getStyle(styleChoise);

         if (styleChoise.contains("italic")) {
        	 if (StyleConstants.isItalic(style)) {
                 StyleConstants.setItalic(style, false);
             } else {
                 StyleConstants.setItalic(style, true);
             }
         }else {
        	 if (StyleConstants.isBold(style)) {
                 StyleConstants.setBold(style, false);
             } else {
                 StyleConstants.setBold(style, true);
             }
         }
         doc.setCharacterAttributes(start, end - start, style, false);
	}
}