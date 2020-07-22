package GenerateGui;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class KeyStrokeMenuItemSetter {
	
	public void setKeyStrokes(ArrayList<JMenuItem> list) {
		KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK);
		list.get(0).setAccelerator(keyStrokeToOpen);
		KeyStroke keyStrokeToDelete = KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK);
		list.get(1).setAccelerator(keyStrokeToDelete);
		KeyStroke keyStrokeToRollback = KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK);
		list.get(2).setAccelerator(keyStrokeToRollback);
		KeyStroke keyStrokeToSave = KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK);
		list.get(3).setAccelerator(keyStrokeToSave);
		KeyStroke keyStrokeEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
		list.get(4).setAccelerator(keyStrokeEscape);
		KeyStroke keyStrokeFind = KeyStroke.getKeyStroke(KeyEvent.VK_F,KeyEvent.CTRL_DOWN_MASK);
		list.get(5).setAccelerator(keyStrokeFind);
		KeyStroke keyStrokePrint = KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK);
		list.get(6).setAccelerator(keyStrokePrint);
		KeyStroke keyStrokeBold = KeyStroke.getKeyStroke(KeyEvent.VK_B,KeyEvent.CTRL_DOWN_MASK);
		list.get(7).setAccelerator(keyStrokeBold);
		KeyStroke keyStrokeConvertPDF = KeyStroke.getKeyStroke(KeyEvent.VK_K,KeyEvent.CTRL_DOWN_MASK);
		list.get(8).setAccelerator(keyStrokeConvertPDF);
		KeyStroke keyStrokeToVolatile = KeyStroke.getKeyStroke(KeyEvent.VK_1,KeyEvent.CTRL_DOWN_MASK);
		list.get(9).setAccelerator(keyStrokeToVolatile);
		KeyStroke keyStrokeToAutomatic = KeyStroke.getKeyStroke(KeyEvent.VK_2,KeyEvent.CTRL_DOWN_MASK);
		list.get(10).setAccelerator(keyStrokeToAutomatic);
		KeyStroke keyStrokeToImport = KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.CTRL_DOWN_MASK);
		list.get(11).setAccelerator(keyStrokeToImport);
		KeyStroke keyStrokeToGreek = KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.ALT_DOWN_MASK);
		list.get(12).setAccelerator(keyStrokeToGreek);
		KeyStroke keyStrokeToEnglish = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.ALT_DOWN_MASK);
		list.get(13).setAccelerator(keyStrokeToEnglish);
		KeyStroke keyStrokeToFrance = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.ALT_DOWN_MASK);
		list.get(14).setAccelerator(keyStrokeToFrance);
		KeyStroke keyStrokeToItalic = KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.ALT_DOWN_MASK);
		list.get(15).setAccelerator(keyStrokeToItalic);
	}
}
