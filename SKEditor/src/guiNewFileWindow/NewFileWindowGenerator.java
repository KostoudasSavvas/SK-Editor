package guiNewFileWindow;

import javax.swing.*;
import event.EventHandlerNewFile;
import java.util.ArrayList;
import template.BasicTemplate;

public class NewFileWindowGenerator extends GeneralNewWindow{
	
	static JFrame newWindow;
	
	// constructor for the various templates: Book,Report,Article,Letter and empty template
	public NewFileWindowGenerator(JFrame EditorWindow,BasicTemplate template,JTextPane textArea,ArrayList<JCheckBoxMenuItem> boxList,ArrayList<JMenuItem> menuList,JMenu btnAddCommand,JMenuItem btnGreek){
		super(btnGreek,template.getData().toString());
	}
	
	public void eventListenerGenerate(JFrame newWindow,JButton btnSave,JButton btnCancel,JButton btnSearch,JLabel labelName,JTextField textField_1,JTextField textField_path,String contents,JMenuItem btnGreek){
		int k = 1;     // integer for the event Handling
			
		EventHandlerNewFile eventHN1 = new EventHandlerNewFile(newWindow,k,labelName,textField_1,textField_path,contents,btnGreek);   // handling for the button create
		btnSave.addActionListener(eventHN1);
		
		EventHandlerNewFile eventHN2 = new EventHandlerNewFile(newWindow,(k+1),labelName,textField_1,textField_path,contents,btnGreek); // handling for the button cancel
		btnCancel.addActionListener(eventHN2);
		
		EventHandlerNewFile eventHN3 = new EventHandlerNewFile(newWindow,(k+2),labelName,textField_1,textField_path,contents,btnGreek); // handling for the button search
		btnSearch.addActionListener(eventHN3);
	}
}