package odfLibreOfficeTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import guiNewFileWindow.NewOdfWindow;

public class NewFileOdfCreator {
	
	public void createOdf(JMenuItem odfTemplate,JMenuItem btnGreek) {
		odfTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewOdfWindow newOdfFile = new NewOdfWindow(btnGreek);
				newOdfFile.makeVisible();
			}
		});
	}
}
