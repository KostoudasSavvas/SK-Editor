package guiNewFileWindow;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GenerateGui.GuiFactory;

public abstract class GeneralNewWindow {
	static JFrame newWindow;
	private JTextField textField_1;
	private JTextField textField_path;
	private GuiFactory GFactory2;   // important Factory object in order to create the window elements
	
	
	public GeneralNewWindow(JMenuItem btnGreek,String contents) {

	    GFactory2 = new GuiFactory();
		if (btnGreek.isSelected()) {
			newWindow = GFactory2.createFrame("Î”Î·Î¼Î¹Î¿Ï…Ï�Î³Î¯Î± ÎšÎ±Î¹Î½Î¿Ï�Ï�Î¹Î¿Ï… Î‘Ï�Ï‡ÎµÎ¯Î¿Ï…",400, 150, 450, 220);
		}else {
			newWindow = GFactory2.createFrame("New File Generator",400, 150, 450, 220);
		}
		GFactory2.onExit(newWindow, false);
		
		
		// ------------------------------------  panel,label,textField and the buttons of the window
		JPanel panel = GFactory2.createMainPanel(newWindow,2);
		panel.setLayout(null);
		
		String text="",text2="",text3="",text4="",text5="",text6="",text7="",textPath="";
		
		if (btnGreek.isSelected()) {
			text = "Î Î»Î·ÎºÏ„Ï�Î¿Î»Î¿Î³Î®ÏƒÏ„Îµ Ï„Î¿ ÏŒÎ½Î¿Î¼Î± Ï„Î¿Ï… Î±Ï�Ï‡ÎµÎ¯Î¿Ï… ÎºÎ±Î¹ Ï€Î¹Î­ÏƒÏ„Îµ ÎºÎ¿Ï…Î¼Ï€Î¯ Î´Î·Î¼Î¹Î¿Ï…Ï�Î³Î¯Î±.";
			text2 = "ÎšÎ¬Î½Ï„Îµ ÎºÎ»Î¹Îº Î±Ï…Ï„Î¿ Ï„Î¿ ÎºÎ¿Ï…Î¼Ï€Î¯ Î³Î¹Î± Î½Î± Î´Î·Î¼Î¹Î¿Ï…Ï�Î³Î·Î¸ÎµÎ¯ Ï„Î¿ Î½Î­Î¿ Î±Ï�Ï‡ÎµÎ¯Î¿.";
			text3 = "ÎšÎ¬Î½Ï„Îµ ÎºÎ»Î¹Îº Î±Ï…Ï„ÏŒ Ï„Î¿ ÎºÎ¿Ï…Î¼Ï€Î¯ Î³Î¹Î± Î½Î± ÎµÏ€Î¹ÏƒÏ„Ï�Î­ÏˆÎµÏ„Îµ ÏƒÏ„Î¿ Î¼ÎµÎ½Î¿Ï�";
			text4="Î”Î·Î¼Î¹Î¿Ï…Ï�Î³Î¯Î±";
			text5="Î‘ÎºÏ�Ï�Ï‰ÏƒÎ·";
		}else {
			text = "Name of the file you entered";
			text2 = "Press create in order to create the new template.";
			text3 = "Press Cancel in order to return to previous menu.";
			text4 = "Create";
			text5 = "Cancel";
			text6 = "Search";
			text7 = "Press Search to find the folder to create the new file.";
			textPath="Select the folder to create the new file";
		}
		
		JLabel newLabel = GFactory2.createLabelSimple(panel,text, 20, 10, 380, 20, true);
		JLabel pathLabel =  GFactory2.createLabelSimple(panel,textPath, 20, 90, 380, 20, true);
		JButton btnSave = GFactory2.createFindButton(panel,text4,20,160,100,23,text2);
		JButton btnCancel = GFactory2.createFindButton(panel,text5,140,160,100,23,text3);
		JButton btnSearch = GFactory2.createFindButton(panel,text6,335,123,100,23,text7);
		textField_1 = GFactory2.createField(panel,20,45,250,30,10);
		textField_path = GFactory2.createField(panel,20,120,300,30,10);
		textField_path.setEditable(false);
		textField_1.setEditable(false);
		
		newLabel.setVisible(false);
		textField_1.setVisible(false);
		
		newWindow.setVisible(true);
		eventListenerGenerate(newWindow,btnSave,btnCancel,btnSearch,newLabel,textField_1,textField_path,contents,btnGreek);
	}
	
	public abstract void eventListenerGenerate(JFrame newWindow,JButton btnSave,JButton btnCancel,JButton btnSearch,JLabel nameLabel,JTextField textField_1,JTextField textField_path,String contents,JMenuItem btnGreek);
	
	public void makeVisible(){
		newWindow.setVisible(true);
	}
}