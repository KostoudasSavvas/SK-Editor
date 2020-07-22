package GenerateGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import guiNewFileWindow.GeneralNewWindow;
import guiNewFileWindow.NewFileTxtWindow;

public class TxtCreator {
	
	public void createTxt(JMenuItem txtTemplate,JMenuItem btnGreek) {
		txtTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralNewWindow newTxtFile = new NewFileTxtWindow(btnGreek);
				newTxtFile.makeVisible();
			}
		});
	}
}