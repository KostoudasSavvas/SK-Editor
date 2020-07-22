package event;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.StyledDocument;

public class BulletAdder {
	
	public void addBullet(JTextPane textArea) {		
		String imageBullet = "/dot(3).png";
		try {
			   Image img = ImageIO.read(getClass().getResource(imageBullet));
			   textArea.insertIcon(new ImageIcon(img));
			 } catch (Exception ex) {
			   System.out.println(ex);
			 }
	}
	
	
	public void addNumber(JTextPane textArea,int automaticNumber) {		
		StyledDocument doc = textArea.getStyledDocument();
		String number = ""+automaticNumber;              // converting to string for better handling
		//  Adding the number with parenthesis
		try
		{
		    doc.insertString(textArea.getCaretPosition(),number+')', null );
		}
		catch(Exception e) { System.out.println(e); }
	}
}
