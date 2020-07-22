package Commands;

import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class CommandsFactory {	
	// this function creates the commands table with all the commands
	public AddLatexCommand createAddCommand(JMenu menu,ArrayList<String> dataClass,JTextPane textArea,String FileName){
		AddLatexCommand comFactory = new AddLatexCommand();
		comFactory.setArea(textArea);
		comFactory.setEntireTable(dataClass,FileName);
		JMenuItem [] table = comFactory.getEntireTable();               // the full table with the commands
			
		for(int i=0;i<comFactory.getNumber();i++){
			menu.add(table[i]);
			menu.addSeparator();
		}
		return comFactory;
	}
	
	public DeleteCommand createDeleteCommand(){
		DeleteCommand delCommand = new DeleteCommand();
		return delCommand;
	}
}