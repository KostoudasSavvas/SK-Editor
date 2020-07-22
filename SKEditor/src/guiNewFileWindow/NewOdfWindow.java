package guiNewFileWindow;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import odfLibreOfficeTemplate.eventHandlerOdf;

public class NewOdfWindow extends GeneralNewWindow{
	static JFrame newWindow;
	
	public NewOdfWindow(JMenuItem btnGreek) {
		super(btnGreek,"");
	}
	
	public void eventListenerGenerate(JFrame newWindow,JButton btnSave,JButton btnCancel,JButton btnSearch,JLabel labelName,JTextField textField_1,JTextField textField_path,String contents,JMenuItem btnGreek){
		int k = 1;     // integer for the event Handling
			
		eventHandlerOdf eventHN1 = new eventHandlerOdf(newWindow,k,labelName,textField_1,textField_path,btnGreek);   // handling for the button create
		btnSave.addActionListener(eventHN1);
		
		eventHandlerOdf eventHN2 = new eventHandlerOdf(newWindow,(k+1),labelName,textField_1,textField_path,btnGreek); // handling for the button cancel
		btnCancel.addActionListener(eventHN2);
		
		eventHandlerOdf eventHN3 = new eventHandlerOdf(newWindow,(k+2),labelName,textField_1,textField_path,btnGreek); // handling for the button search
		btnSearch.addActionListener(eventHN3);
	}
}