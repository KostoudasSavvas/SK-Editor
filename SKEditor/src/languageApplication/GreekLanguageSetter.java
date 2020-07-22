package languageApplication;

import java.util.ArrayList;
import javax.swing.*;

public class GreekLanguageSetter {
	
	public void GreekLanguageSet(ArrayList<JCheckBoxMenuItem> boxList,ArrayList<JCheckBoxMenuItem> clockChoises,ArrayList<JMenuItem> menuList,JLabel w,JLabel ch,JMenu exportMenu,JComboBox<String> colorsBox,JPanel panel,JMenuBar menuBar,JMenu exitMenu) {
		boxList.get(0).setText("Αυτόματη Αποθήκευση");boxList.get(1).setText("Προσωρινή Αποθήκευση");
		boxList.get(0).setToolTipText("Κάνε κλίκ αυτό το κουμπί για να αποθηκεύει αυτόματα η εφαρμογή ανά ένα χρονικό διάστημα το αρχείο.");boxList.get(1).setToolTipText("Κάνε κλίκ αυτό το κουμπί για να υπάρχει προσωρινη αποθήκευση των εκδόσεων του αρχείου.");
		clockChoises.get(0).setText("10|Δευτερόλεπτα! Περίοδος Αποθήκευσης");clockChoises.get(1).setText("20|Δευτερόλεπτα! Περίοδος Αποθήκευσης");
		clockChoises.get(2).setText("30|Δευτερόλεπτα! Περίοδος Αποθήκευσης");clockChoises.get(3).setText("40|Δευτερόλεπτα! Περίοδος Αποθήκευσης");
		clockChoises.get(4).setText("1|λεπτό! Περίοδος Αποθήκευσης");clockChoises.get(5).setText("1.5|λεπτό! Περίοδος Αποθήκευσης");
		clockChoises.get(6).setText("2|λεπτά! Περίοδος Αποθήκευσης");clockChoises.get(7).setText("3|λεπτά! Περίοδος Αποθήκευσης");
		clockChoises.get(8).setText("4|λεπτά! Περίοδος Αποθήκευσης");clockChoises.get(9).setText("5|λεπτά! Περίοδος Αποθήκευσης");
		clockChoises.get(10).setText("10|λεπτά! Περίοδος Αποθήκευσης");clockChoises.get(11).setText( "15|λεπτά! Περίοδος Αποθήκευσης");
		clockChoises.get(12).setText("20|λεπτά! Περίοδος Αποθήκευσης");clockChoises.get(13).setText( "30|λεπτά! Περίοδος Αποθήκευσης");
		clockChoises.get(14).setText( "45|λεπτά! Περίοδος Αποθήκευσης");clockChoises.get(15).setText("1|Ώρα! Περίοδος Αποθήκευσης");
		menuList.get(0).setText("Αποθήκευση Αρχείου"); menuList.get(1).setText("Επιστροφή Προηγούμενης έκδοσης"); menuList.get(2).setText("Φόρτωση Αρχείου");
		menuList.get(3).setText("Εκκαθάριση Αρχείου"); menuList.get(4).setText("Ακύρωση"); menuList.get(5).setText("Διαγραφή Αρχείου");
		menuList.get(6).setText("Μετασχηματισμός σε PDF");menuList.get(7).setText("Εξαγωγή σε PDF");menuList.get(8).setText("Εξαγωγή σε TXT");menuList.get(9).setText("Εξαγωγή σε ODT");
		menuList.get(10).setText("Εισαγωγή αρχείου δεδομένων");menuList.get(11).setText("TXT αρχείο");menuList.get(12).setText("ODF αρχείο");
		menuList.get(13).setText("Αναφορά Είδος");menuList.get(14).setText("Γράμμα Είδος");menuList.get(15).setText("Βιβλίο Είδος");menuList.get(16).setText("Άρθρο Είδος");
		menuList.get(17).setText("Κενό Είδος");menuList.get(18).setText("Ελληνικά");menuList.get(19).setText("Αγγλικά");menuList.get(20).setText("Γαλλικά");		
		menuList.get(21).setText("Έυρεση/Αντικατάσταση");menuList.get(22).setText("Εκτύπωση");menuList.get(23).setText("Έντονα Γράμματα");
		menuList.get(24).setText("ΤΕΧ αρχείο");
	    
		w.setText("Λέξεις->");
		ch.setText("Χαρακτήρες->");
		exportMenu.setText("Εξαγωγή αρχείου ως:");
	}
	
	public void GreekMessageTranslate() {
		
	}
}