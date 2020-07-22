package Commands;

import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class CommandCreator {
	private int NumberOfCommands  = 0;
	private CommandPlaceController place = new CommandPlaceController();                   // this is an object in order to set the command at the right place in the jtextArea
	
	public JMenuItem createCommand(String command,String text,JTextPane textArea,JMenuItem[] table,ArrayList<String> dataClass,String FileName){
		JMenuItem item = new JMenuItem(command);
		item.setToolTipText(text);
		table[NumberOfCommands] = item;
		NumberOfCommands++;
		place.actionHelp(item,"\\"+command, textArea,dataClass,FileName);
		return item;
	}
	
	public int getNumber(){
		return NumberOfCommands;
	}
}