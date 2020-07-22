package GenerateGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.*;

import paginationEditorPack.PageableEditorKit;
import styling.MyScrollBarUI;

public class GuiFactory {
	
	// method create for the JFrame main
	public JFrame createFrame(String name,int x1,int x2,int y1, int y2){
		JFrame LPMainWindow = new JFrame(name);
		LPMainWindow.setResizable(false);
		LPMainWindow.setBounds(x1,x2,y1,y2);
		LPMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LPMainWindow.setVisible(true);
		return LPMainWindow;
	}
	
	public JFrame createFrameEditor(String name,int x1,int x2,int y1, int y2){
		JFrame Window = new JFrame(name);
		Window.setBounds(x1,x2,y1,y2);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return Window;
	}
	
	public JButton createFindButton(JPanel panel,String name,int x1,int x2,int y1, int y2,String text) {
		JButton button = new JButton(name);
		button.setBounds(x1,x2,y1,y2);
		button.setFont(new Font("Calibri", Font.PLAIN, 14));
	    button.setBackground(new Color(31,190,214));
	    button.setForeground(Color.white);
		button.setToolTipText(text);
		button.setUI(new StyledButtonUI());
		panel.add(button);
		return button;
	}
	
	public JComboBox createBox(JPanel panel,JMenuBar menuBar) {
		String subject[] = { "6", "7", "8", "9",
		        "10", "11","12","13","14","15","16","17","18","19","20","21","22","23","25","26",
		        "28","30","32","34","36","38","40","42","44","46","48","50","54","58","62","66",
		        "70","74","78","80"};
		
		JComboBox box = new JComboBox(subject);
	    ((JLabel)box.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
	    box.setPreferredSize(new Dimension(30,25));
	    panel.add(box);
	    menuBar.add(box);
	    return box;
	}
	
	public JComboBox createSimpleBox(JPanel panel,JMenuBar menuBar,String[] content) {
		JComboBox box = new JComboBox(content);
	    panel.add(box);
	    menuBar.add(box);
	    return box;
	}
	
	public JLabel createLabelBar(JMenuBar bar,String text){
		JLabel label = new JLabel(text);
		Font f = new Font("Italic",Font.ITALIC,14);
		label.setFont(f);
		bar.add(label);
		return label;
	}
	
	// method to create simple JLabels
	public JLabel createLabelSimple(JPanel panel,String name,int x1, int y1, int x2, int y2,Boolean special){
		JLabel label = new JLabel(name);
		if (special){
			label.setForeground(Color.WHITE);
		}
		label.setBounds(x1,y1,x2,y2);
		panel.add(label);
		return label;
	}
	
	public JLabel createLabelFind(JPanel panel,String name,int x1, int y1, int x2, int y2) {
		JLabel labelF = new JLabel(name);
		labelF.setBounds(x1, y1, x2, y2);
		labelF.setForeground(new Color(31,190,214));
		panel.add(labelF);
		return labelF;
	}

	// method to create the main JPanel
	public JPanel createMainPanel(JFrame window,int Number){
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(500, 500, 500, 500));
		panel.setSize(768, 568);
		
		if (Number == 1){
			panel.setBackground(Color.LIGHT_GRAY);
		}else if (Number == 2){
			panel.setBackground(Color.DARK_GRAY);
		}else{
			panel.setBackground(Color.WHITE);
		}
		
		window.getContentPane().add(panel);
		return panel;
	}
	
	public JPanel createPanelFind(JFrame mainWindow){
		JPanel panel = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(31,190,214));
                
                // Scope box implemented by four lines
                g.drawLine(60, 190, 155, 190);
                g.drawLine(155, 190, 155, 250);
                g.drawLine(155, 250, 12, 250);
                g.drawLine(12, 250, 12, 190);
                
                // Direction box implemented by four lines
                g.drawLine(267 , 190, 340, 190);
                g.drawLine(340, 190, 340, 250);
                g.drawLine(340, 250, 200, 250);
                g.drawLine(198, 250, 198, 190);
                
                // Options box implemented by four lines
                g.drawLine(70, 275, 340, 275);
                g.drawLine(340, 275, 340, 320);
                g.drawLine(340, 320, 13, 320);
                g.drawLine(13, 320, 13, 275);
            }
        };
		panel.setBounds(new Rectangle(500, 500, 500, 500));
		panel.setSize(300,400);
		
		mainWindow.getContentPane().add(panel);
		panel.setLayout(null);
		return panel;
	}
	
	public JPanel createPanelServiceComplete(int number) {
		JPanel panel = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(31,190,214));
                if (number == 1) {
                	 g.drawLine(0, 30, 900, 30);
                     g.drawLine(0, 144, 900, 144);
                     g.drawLine(0, 303, 900, 303);
                     g.drawLine(0, 428, 900, 428);
                }else if (number == 2) {
                	 g.drawLine(0, 30, 900, 30);
                     g.drawLine(0, 47, 900, 47);
                     g.drawLine(0, 130, 900, 130);
                     g.drawLine(0, 280, 900, 280);
                     g.drawLine(0, 430, 900, 430);
                }else if (number == 3) {
                	 g.drawLine(0, 30, 900, 30);
                     g.drawLine(0, 65, 900, 65);
                     g.drawLine(0, 148, 900, 148);
                     g.drawLine(0, 220, 900, 220);
                     g.drawLine(0, 318, 900, 318);
                     g.drawLine(0, 403, 900, 403);
                }else if (number == 4) {
                	 g.drawLine(0, 30, 900, 30);
                     g.drawLine(0, 67, 900, 67);
                     g.drawLine(0, 133, 900, 133);
                }else if (number == 5) {
                	 g.drawLine(0, 45, 900, 45);
                     g.drawLine(400,75,400,500);
                     //g.drawLine(400,75,400,500);
                     //g.drawLine(400,75,400,500);
                     //g.drawLine(400,75,400,500);
                     //g.drawLine(400,75,400,500);
                     //g.drawLine(400,75,400,500);

                }else if (number == 6) {
                	// none drawing of lines for panel service number 6
                }else {
                	 g.drawLine(0, 30, 900, 30);
                     g.drawLine(0, 67, 900, 67);
                     g.drawLine(0, 143, 900, 143);
                     g.drawLine(0, 171, 100, 171);
                     g.drawLine(0, 187, 160, 187);
                     g.drawLine(0, 218, 160, 218);
                     g.drawLine(0, 247, 180, 247);
                     g.drawLine(0, 278, 190, 278);
                     g.drawLine(0, 346, 900, 346);
                     g.drawLine(0, 375, 85, 375);
                     g.drawLine(0, 405, 100, 405);
                     g.drawLine(0, 434, 85, 434);
                }
            }
        };
		panel.setBounds(new Rectangle(500, 500, 500, 500));
		panel.setPreferredSize(new Dimension(300,400));		
		return panel;
	}
	
	public JTextField createField(JPanel panel,int x1,int x2,int y1, int y2,int columns){
		JTextField field = new JTextField();
		Font f = new Font("Savvas",Font.BOLD,12);
		field.setFont(f);
		field.setBounds(x1, x2, y1, y2);
		field.setColumns(columns);
		panel.add(field);
		return field;
	}
	
	public JTextField createFindField(JPanel panel,int x1,int x2,int y1, int y2,int columns){
		JTextField field = new JTextField();
		field.setBounds(x1, x2, y1, y2);
		field.setColumns(columns);
		panel.add(field);
		return field;
	}
	
	public JMenuBar createBar(JPanel mainPanel){
		JMenuBar bar = new JMenuBar();
		bar.setBounds(0,0, 795, 23);
		bar.setSize(795, 23);
		mainPanel.add(bar);
		return bar;
	}
	
	public JMenu createSimpleMenu(JMenu mainMenu,String name,String text,String ImageName) {
		JMenu menu = new JMenu(name);
		menu.setToolTipText(text);
		setImage(menu,ImageName);
		mainMenu.add(menu);
		return menu;
	}
	
	public JMenu createMenu(JMenuBar bar,String text,String ImageName){
		JMenu menu = new JMenu();
		menu.setToolTipText(text);
		setImage(menu,ImageName);                    
		bar.add(menu);
		return menu;
	}
	
	public JMenuItem createMenuItemWithIcon(JMenu menu,String name,String ImageName,String text) {
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.setToolTipText(text);
		setImageItem(menuItem,ImageName);                    
		menu.add(menuItem);
		return menuItem;
	}
	
	
	public void setImageItem(JMenuItem menuItem,String ImageName){
		try {
			   Image img = ImageIO.read(getClass().getResource(ImageName));
			   menuItem.setIcon(new ImageIcon(img));
			 } catch (Exception ex) {
			   System.out.println(ex);
			 }
	}
	
	public void setImageCheckItem(JCheckBoxMenuItem menuItem,String ImageName){
		try {
			   Image img = ImageIO.read(getClass().getResource(ImageName));
			   menuItem.setIcon(new ImageIcon(img));
			 } catch (Exception ex) {
			   System.out.println(ex);
			 }
	}
	
	
	public void setImage(JMenu menu,String ImageName){
		try {
			   Image img = ImageIO.read(getClass().getResource(ImageName));
			   menu.setIcon(new ImageIcon(img));
			 } catch (Exception ex) {
			   System.out.println(ex);
			 }
	}
	
	public JMenuItem createMenuItem(JMenu menu,String name,String text){
		JMenuItem item = new JMenuItem(name);
		item.setToolTipText(text);
		menu.add(item);
		return item;
	}
	
	public JCheckBoxMenuItem createJCheckMenuItem(JMenu menu,String name,String text){
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(name);
		item.setToolTipText(text);
		menu.add(item);
		return item;
	}
	
	public JCheckBoxMenuItem createJCheckMenuItemIcon(JMenu menu,String name,String ImageName,String text){
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(name);
		item.setToolTipText(text);
		setImageCheckItem(item,ImageName);   
		menu.add(item);
		return item;
	}
	
	public JRadioButton createJCheckButton(JPanel panel,String name,int x1,int x2,int y1,int y2){
		JRadioButton item = new JRadioButton(name);
		item.setBounds(x1,x2,y1,y2);
		panel.add(item);
		return item;
	}
	
	public JTextPane createArea(PageableEditorKit kit) {
		JTextPane area = new JTextPane();
		area.setEditorKit(kit);
        Font f = new Font("Plain",Font.PLAIN,14);
        area.setFont(f);
		return area;
	}
	
	public JScrollPane createScroll(JTextPane textArea,JFrame editorWindow) {
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setComponentZOrder(scroll.getVerticalScrollBar(), 0);
	    scroll.setComponentZOrder(scroll.getViewport(), 1);
	    scroll.getVerticalScrollBar().setOpaque(false);
	    
	    scroll.setLayout(new ScrollPaneLayout() {
	        @Override
	        public void layoutContainer(Container parent) {
	          JScrollPane scrollPane = (JScrollPane) parent;

	          Rectangle availR = scrollPane.getBounds();
	          availR.x = availR.y = 0;

	          Insets parentInsets = parent.getInsets();
	          availR.x = parentInsets.left;
	          availR.y = parentInsets.top;
	          availR.width -= parentInsets.left + parentInsets.right;
	          availR.height -= parentInsets.top + parentInsets.bottom;

	          Rectangle vsbR = new Rectangle();
	          vsbR.width = 12;
	          vsbR.height = availR.height;
	          vsbR.x = availR.x + availR.width - vsbR.width;
	          vsbR.y = availR.y;

	          if (viewport != null) {
	            viewport.setBounds(availR);
	          }
	          if (vsb != null) {
	            vsb.setVisible(true);
	            vsb.setBounds(vsbR);
	          }
	        }
	      });
	    scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
		editorWindow.add(scroll,BorderLayout.CENTER);
	    return scroll;
	}
	
	public void onExit(JFrame frame, boolean dispose){

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit" , 0) == JOptionPane.YES_OPTION){

					if (dispose){
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					} else {
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					}
				} else {
					frame.setDefaultCloseOperation(0);
				}
			}
		});
	}	
}