package guiNewFileWindow;

import javax.swing.*;
import event.eventHandlerTxt;

public class NewFileTxtWindow extends GeneralNewWindow {
	static JFrame newWindow;
 	
	// constructor for the txt template
	public NewFileTxtWindow(JMenuItem btnGreek){
		super(btnGreek,"");
	}

	public void eventListenerGenerate(JFrame newWindow,JButton btnSave,JButton btnCancel,JButton btnSearch,JLabel nameLabel,JTextField textField_1,JTextField textField_path,String contents,JMenuItem btnGreek){
		int k = 1;     // integer for the event Handling
			
		eventHandlerTxt eventHN1 = new eventHandlerTxt(newWindow,k,nameLabel,textField_1,textField_path,btnGreek);   // handling for the button create
		btnSave.addActionListener(eventHN1);
		
		eventHandlerTxt eventHN2 = new eventHandlerTxt(newWindow,(k+1),nameLabel,textField_1,textField_path,btnGreek); // handling for the button cancel
		btnCancel.addActionListener(eventHN2);
		
		eventHandlerTxt eventHN3 = new eventHandlerTxt(newWindow,(k+2),nameLabel,textField_1,textField_path,btnGreek); // handling for the button search for path
		btnSearch.addActionListener(eventHN3);
	}
}