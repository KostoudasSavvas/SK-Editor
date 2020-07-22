package event;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import htmlConvertToOdt.HtmlToOdtConverter;
import versionManager.Documents;

public class EventHandlerRollBack {
	private EventHandlerSplit splitter = new EventHandlerSplit();
	private HtmlToOdtConverter odtConvert = new HtmlToOdtConverter();
	// default constructor
	
	public void rollBack(int Number,JTextPane textArea,String name,JCheckBoxMenuItem checkBox1,Documents[] VolatileVersionsList,JMenuItem btnGreek){
		if (checkIfEmpty(VolatileVersionsList)){
			if (btnGreek.isSelected()) {
				JOptionPane.showMessageDialog(null,"Για να γίνει επιστροφή σε προηγούμενη έκδοση θα πρέπει να έχετε αποθηκεύσει κάποια.","Μη υπαρκτή αποθηκεύμενη έκδοση.", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null,"You must save a version first in order to load it.","You haven't store any versions yet.", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(checkBox1.isSelected() && Number > 0){         // volatile storage
			if (Number <= 0){
				textArea.setText(null);
			}else{
				Number--;
				if (name.contains(".odt")) {
					odtConvert.convertToOdt(textArea, name,splitter.splitArrayList(VolatileVersionsList[Number].getContents()),true);
				}else {
					textArea.setText(splitter.splitArrayList(VolatileVersionsList[Number].getContents()));	
				}
			}	
		}
	}
	// this method checks if the document list with the versions is empty
	public static Boolean checkIfEmpty(Documents[] list){
		if (list[1] == null){
			return true;
		}else{
			return false;
		}
	}
}