package event;

import java.util.ArrayList;
import javax.swing.JCheckBoxMenuItem;

public class EventHandlerBoxesController {
	public void enableDisable(ArrayList<JCheckBoxMenuItem> clockChoises,JCheckBoxMenuItem boxSelected) {
		if (boxSelected.isEnabled()) {
			for (JCheckBoxMenuItem box : clockChoises){
				if (!(box.getText().equals(boxSelected.getText()))) {
					box.setEnabled(false);
				}
			}
		}else {
			for (JCheckBoxMenuItem box : clockChoises){
				if (!(box.getText().equals(boxSelected.getText()))) {
					box.setEnabled(true);
				}
			}
		}
	}
	
	public void enableAll(ArrayList<JCheckBoxMenuItem> clockChoises) {
		for (JCheckBoxMenuItem box : clockChoises){
				box.setEnabled(true);
		}
	}
}