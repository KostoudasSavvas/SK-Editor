package GenerateGui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import convertToPdf.StartWindowCreator;
import event.EventHandlerEditor;
import event.MainEditorWindowHelper;
import languageApplication.GreekLanguageSetter;
import odfLibreOfficeTemplate.NewFileOdfCreator;
import paginationEditorPack.PageableEditorKit;
import serviceMenu.serviceMenuHandler;
import spellCheck.SpellCheckInitializer;
import styling.ColorsTextWriter;
import styling.FontHandler;
import styling.TextBoldMaker;
import styling.TextItalicMaker;
import themeSelector.ThemeSelector;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.LayoutManager;

public class MainEditorWindow{
	private static JFrame EditorWindow;
	private static GuiFactory GFactory4;          // important GuiFactory object to create and initialize window elements
	private static JMenu btnAddCommand;
	private static FontHandler fontHandler = new FontHandler();
	private static SpellCheckInitializer spellChecker = new SpellCheckInitializer();
	private static ColorsTextWriter colorChange = new ColorsTextWriter();
	private static KeyStrokeMenuItemSetter keyStrokeSet = new KeyStrokeMenuItemSetter(); 
	private static TextBoldMaker boldMaker = new TextBoldMaker();
	private static StartWindowCreator startWindow = new StartWindowCreator();  // object that creates the new window for the new document
	private static serviceMenuHandler serviceMenuHandling = new serviceMenuHandler();
	private static TextItalicMaker italicMaker = new TextItalicMaker();
	private static int VersionId;
	private static GreekLanguageSetter greekSetter = new GreekLanguageSetter();
	private static WordCounter wordC = new WordCounter();
	private static NewFileOdfCreator createOdf = new NewFileOdfCreator();
	private static TemplateTexCreator tCreator = new TemplateTexCreator();
	private static TxtCreator txtCreator = new TxtCreator();

	public static void main(String args[]){
		
 		GFactory4 = new GuiFactory();
		EditorWindow =  GFactory4.createFrameEditor("SK Editor",400, 150, 900, 720);
		GFactory4.onExit(EditorWindow, false);
		 try {
	            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(MainEditorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(MainEditorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(MainEditorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(MainEditorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		// -----------------------------------  the panel of the editor
		
		JPanel panel = GFactory4.createMainPanel(EditorWindow,3);
		panel.setVisible(false);
		JPanel panel2 = new JPanel();
		EditorWindow.getContentPane().add(panel2,BorderLayout.EAST);
		panel2.setPreferredSize(new Dimension(50, 720));
		
		// -------------- menu bar and menus in menu bar
		JMenuBar menuBar = GFactory4.createBar(panel);
		JMenu File = GFactory4.createMenu(menuBar,"Click this menu to manage the files.","/folder.png");
		JMenu createMenu = GFactory4.createMenu(menuBar,"Click this menu to create new template.","/add-file.png");
		btnAddCommand =  GFactory4.createMenu(menuBar,"Click this menu to add a command in the document.","/add.png");
		JMenu SaveMenu = GFactory4.createMenu(menuBar,"Click this menu to save the version or entire file.","/floppy-disk.png");
		JMenu Storage =  GFactory4.createMenu(menuBar,"Click this menu to open the storage menu.","/tool.png");
		JMenu ToolsMenu = GFactory4.createMenu(menuBar,"Click this menu to open the tools menu.","/customer-support.png");
		JMenu texMenu = GFactory4.createSimpleMenu(createMenu,"Tex Template","Click this menu to create a tex template.","/tex.png");  //sub menu on create menu about all tex templates
		JMenu findMenu = GFactory4.createMenu(menuBar, "Click this icon to find a word or replace.", "/lens.png");
		JMenu clockMenu = GFactory4.createMenu(menuBar, "Click this icon to set the period of automatic storage.", "/stopwatch.png");
		JMenu serviceMenu = GFactory4.createMenu(menuBar, "Click this icon to get help about the software.", "/customer-service.png");
		JMenu languageMenu = GFactory4.createMenu(menuBar, "Click this icon to change the language of the application.", "/flag.png");

	    JLabel labelSpace4 = GFactory4.createLabelBar(menuBar, "         ");
		JLabel labelWords = GFactory4.createLabelBar(menuBar, "Words->");
	    JLabel labelSpace5 = GFactory4.createLabelBar(menuBar, "  ");
	    JLabel labelSpace6 = GFactory4.createLabelBar(menuBar, "  ");
	    JLabel labelSpace10 = GFactory4.createLabelBar(menuBar, "  ");
		JLabel labelCharacters = GFactory4.createLabelBar(menuBar, "Characters->");
	    JLabel labelSpace7 = GFactory4.createLabelBar(menuBar, "  ");
	    JLabel labelSpace8 = GFactory4.createLabelBar(menuBar, "  ");
	    JLabel labelSpace9 = GFactory4.createLabelBar(menuBar, "  ");

		EditorWindow.setJMenuBar(menuBar);
		LayoutManager grid  = new GridLayout(0,1);
		JMenuBar verticalMenuBar  = new JMenuBar();
		verticalMenuBar.setLayout(grid);
		panel2.add(verticalMenuBar, BorderLayout.EAST);
		JMenu printerMenu = GFactory4.createMenu(verticalMenuBar, "Click this icon to print the file you want.", "/printer.png");
		JMenu boldMenu = GFactory4.createMenu(verticalMenuBar, "Click this icon to make the selected text bold text.", "/bold-text-option.png");
		JMenu pdfMenu = GFactory4.createMenu(verticalMenuBar, "Click this icon to convert the file to pdf file.", "/pdf.png");
		JMenu italicMenu = GFactory4.createMenu(verticalMenuBar, "Click this icon to make the selected text italic text.", "/italic(1).png");
		JMenu themeMenu = GFactory4.createMenu(verticalMenuBar, "Click this icon to change the theme of the application.", "/configuration.png");
	    JMenu bulletMenu = GFactory4.createMenu(verticalMenuBar, "Click this icon to add a bullet to the file.", "/bullet-list.png");
		
		
		ThemeSelector themeSelect = new ThemeSelector(); // these two commands are about
		themeSelect.themeSelect(themeMenu);              // to change the font of the application
		
		String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		String colors[] = {"Blue","Red","White","Black","Yellow","Green","Cyan","Gray","Dark Gray"};
				
	    JComboBox boxSize = GFactory4.createBox(panel, menuBar);
	    JLabel labelSpace2 = GFactory4.createLabelBar(menuBar, "  ");
	    JComboBox boxFont = GFactory4.createSimpleBox(panel, menuBar,fonts);
	    JLabel labelSpace3 = GFactory4.createLabelBar(menuBar, "  ");
	    JComboBox boxColors = GFactory4.createSimpleBox(panel, menuBar,colors);
	    JMenu exitMenu = GFactory4.createMenu(menuBar,"Click this menu to exit the application.","/exit.png");

		// -------------- menu items
	    createMenu.addSeparator();
		JMenuItem txtTemplate = GFactory4.createMenuItemWithIcon(createMenu,"Txt Template","/Text-icon.png","Click This Button to create a txt template file.");
		createMenu.addSeparator();
		JMenuItem odfTemplate = GFactory4.createMenuItemWithIcon(createMenu,"Odt(LibreOffice)","/document.png","Click This Button to create a odf(LibreOffice) template file.");
		
		JMenuItem ReportTemplate = GFactory4.createMenuItemWithIcon(texMenu,"Report","/newspaper.png","Click This Button to create a report template file.");
		texMenu.addSeparator();
		JMenuItem LetterTemplate = GFactory4.createMenuItemWithIcon(texMenu,"Letter","/letter.png","Click This Button to create a letter template file.");
		texMenu.addSeparator();
		JMenuItem BookTemplate = GFactory4.createMenuItemWithIcon(texMenu,"Book","/open-book.png","Click This Button to create a book template file.");
		texMenu.addSeparator();
		JMenuItem ArticleTemplate = GFactory4.createMenuItemWithIcon(texMenu,"Article","/article.png","Click This Button to create an article template file.");
		texMenu.addSeparator();
		JMenuItem EmptyTemplate = GFactory4.createMenuItemWithIcon(texMenu,"Empty","/document-empty.png","Click This Button to create an empty template file.");
		
		JCheckBoxMenuItem btnVolatileStorage = GFactory4.createJCheckMenuItemIcon(Storage,"Volatile","/temporary-files.png","Click This Button to have volatile type of storage");
		Storage.addSeparator();
		JCheckBoxMenuItem boxAutomaticSave = GFactory4.createJCheckMenuItemIcon(Storage,"Automatic Save","/auto.png","Click This CheckBox to autosave every on minute the document.");
		
		JMenuItem btnLoad = GFactory4.createMenuItemWithIcon(File, "Load File","/delivery-cart.png","Click this button to load a doc to edit it.");
		File.addSeparator();
		JMenuItem btnDelDoc = GFactory4.createMenuItemWithIcon(File, "Delete File","/delete.png","Click this button to delete a document.");
		File.addSeparator();
		JMenuItem btnImport = GFactory4.createMenuItemWithIcon(File, "Import","/import.png","Click this button to import file to the current.");
		File.addSeparator();
		JMenu exportMenu = GFactory4.createSimpleMenu(File,"Export","Click this icon to export the current file in any form availiable.", "/export.png");
		JMenuItem btnExportPdf = GFactory4.createMenuItemWithIcon(exportMenu, "Export as PDF","/pdf.png","Export as Pdf.");
		JMenuItem btnExportTxt = GFactory4.createMenuItemWithIcon(exportMenu, "Export as TXT","/Text-icon.png","Export as txt.");
		JMenuItem btnExportOdt = GFactory4.createMenuItemWithIcon(exportMenu, "Export as ODT","/document.png","Export as odt(Libre Office).");
		
		JMenuItem btnClear = GFactory4.createMenuItemWithIcon(ToolsMenu, "Clear Doc","/vacuum-cleaner.png","Click this button to clear the entire doc.");
		ToolsMenu.addSeparator();
		JMenuItem btnRollBack = GFactory4.createMenuItemWithIcon(ToolsMenu, "Roll Back","/back.png","Click This Button to Roll Back to previous version of the document.");
		ToolsMenu.addSeparator();
		JMenuItem btnFind = GFactory4.createMenuItemWithIcon(findMenu, "Find/Replace","/lens.png","Click This Button to find/replace words.");
		JMenuItem btnCancel = GFactory4.createMenuItemWithIcon(ToolsMenu, "Cancel Edit","/exit.png","Click this button to return to main menu.");
		JMenuItem btnSave = GFactory4.createMenuItemWithIcon(SaveMenu, "Save File","/safebox.png","Click This Button to save the document.");
		JMenuItem btnPrint = GFactory4.createMenuItem(printerMenu, "Print File","Click This Button to print the file.");
		JMenuItem btnBold = GFactory4.createMenuItem(boldMenu, "Make Bold Text","Click This Button to make the selected text bold.");
		JMenuItem btnConvertPdf = GFactory4.createMenuItem(pdfMenu, "Convert to PDF","Click This Button to convert to pdf file.");
		JMenuItem btnGreek = GFactory4.createMenuItemWithIcon(languageMenu, "Greek","/greece.png","Click This Button to set the language to greek.");
		JMenuItem btnEnglish = GFactory4.createMenuItemWithIcon(languageMenu, "English","/united-kingdom.png","Click This Button to set the language to english.");
		JMenuItem btnFrance = GFactory4.createMenuItemWithIcon(languageMenu, "Francaise","/france.png","Click This Button to set the language to francaise.");
		JMenuItem btnItalic = GFactory4.createMenuItemWithIcon(italicMenu, "Set Italic Text","/italic(1).png","Click This Button to set the selected text to italic style.");
		JCheckBoxMenuItem btnBullet = GFactory4.createJCheckMenuItem(bulletMenu, "Add Bullet","Click This Button to add a bullet to the text.");
		JCheckBoxMenuItem btnAutomaticNumbers = GFactory4.createJCheckMenuItem(bulletMenu, "Add Automatic Numbers","Click This Button to automatic add number count to the text.");

		
		
		//------------------ clock menu items
		JCheckBoxMenuItem tenSecStorage = GFactory4.createJCheckMenuItem(clockMenu, "10|Seconds! Period Storage","Click this item to automatic storage every 10 seconds.");
		JCheckBoxMenuItem twentySecStorage = GFactory4.createJCheckMenuItem(clockMenu, "20|Seconds! Period Storage","Click this item to automatic storage every 20 seconds.");
		JCheckBoxMenuItem thirtySecStorage = GFactory4.createJCheckMenuItem(clockMenu, "30|Seconds! Period Storage","Click this item to automatic storage every 30 seconds.");
		JCheckBoxMenuItem fortySecStorage = GFactory4.createJCheckMenuItem(clockMenu, "40|Seconds! Period Storage","Click this item to automatic storage every 40 seconds.");
		JCheckBoxMenuItem OneMinuteStorage = GFactory4.createJCheckMenuItem(clockMenu, "1|minute! Period Storage","Click this item to automatic storage every 1 minute.");
		JCheckBoxMenuItem OneMinuteThirtyStorage = GFactory4.createJCheckMenuItem(clockMenu, "1.5|minutes! Period Storage","Click this item to automatic storage every 1.5 minutes.");
		JCheckBoxMenuItem TwoMinutesStorage = GFactory4.createJCheckMenuItem(clockMenu, "2|minutes! Period Storage","Click this item to automatic storage every 2 minutes.");
		JCheckBoxMenuItem ThreeMinutesStorage = GFactory4.createJCheckMenuItem(clockMenu, "3|minutes! Period Storage","Click this item to automatic storage every 3 minutes.");
		JCheckBoxMenuItem FourMinutesStorage = GFactory4.createJCheckMenuItem(clockMenu, "4|minutes! Period Storage","Click this item to automatic storage every 4 minutes.");		
		JCheckBoxMenuItem FiveMinutesStorage = GFactory4.createJCheckMenuItem(clockMenu, "5|minutes! Period Storage","Click this item to automatic storage every 5 minutes.");
		JCheckBoxMenuItem TenMinutesStorage = GFactory4.createJCheckMenuItem(clockMenu, "10|minutes! Period Storage","Click this item to automatic storage every 10 minutes.");
		JCheckBoxMenuItem FifteenMinutesStorage = GFactory4.createJCheckMenuItem(clockMenu, "15|minutes! Period Storage","Click this item to automatic storage every 15 minutes.");
		JCheckBoxMenuItem TwentyMinutesStorage = GFactory4.createJCheckMenuItem(clockMenu, "20|minutes! Period Storage","Click this item to automatic storage every 20 minutes.");
		JCheckBoxMenuItem ThirtyMinutesStorage = GFactory4.createJCheckMenuItem(clockMenu, "30|minutes! Period Storage","Click this item to automatic storage every 30 minutes.");
		JCheckBoxMenuItem FortyFiveMinutesStorage = GFactory4.createJCheckMenuItem(clockMenu, "45|minutes! Period Storage","Click this item to automatic storage every 45 minutes.");
		JCheckBoxMenuItem OneHourStorage = GFactory4.createJCheckMenuItem(clockMenu, "1|Hour! Period Storage","Click this item to automatic storage every 60 minutes.");

		ArrayList<JCheckBoxMenuItem> clockChoises = new ArrayList<JCheckBoxMenuItem>();
		clockChoises.add(tenSecStorage);clockChoises.add(twentySecStorage);clockChoises.add(thirtySecStorage);clockChoises.add(fortySecStorage);clockChoises.add(OneMinuteStorage);
		clockChoises.add(OneMinuteThirtyStorage);clockChoises.add(TwoMinutesStorage);clockChoises.add(ThreeMinutesStorage);clockChoises.add(FourMinutesStorage);clockChoises.add(FiveMinutesStorage);
		clockChoises.add(TenMinutesStorage);clockChoises.add(FifteenMinutesStorage);clockChoises.add(TwentyMinutesStorage);clockChoises.add(ThirtyMinutesStorage);clockChoises.add(FortyFiveMinutesStorage);clockChoises.add(OneHourStorage);
		
		//----------------- key Shortcuts on Menu Items
		createMenu.setMnemonic(KeyEvent.VK_N);   // key stroke alt+N to guide user to the new template choises.
		clockMenu.setMnemonic(KeyEvent.VK_T);	// key stroke alt+T to guide user to clock choises about automatic storage.
		serviceMenu.setMnemonic(KeyEvent.VK_H);	// key stroke alt+H to guide user to the help/service menu.
		languageMenu.setMnemonic(KeyEvent.VK_L); // key stroke alt+L to guide user to the language menu.
		
		KeyStroke keyStrokeToBullet = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0);   // this is the keystroke for the shortcut to the automatic add of bullets with every /n.
		btnBullet.setAccelerator(keyStrokeToBullet);
		
		KeyStroke keyStrokeToAutomaticNumbers = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0);   // this is the keystroke for the shortcut to the automatic add of numbers with every /n.
		btnAutomaticNumbers.setAccelerator(keyStrokeToAutomaticNumbers);
		
		ArrayList<JMenuItem> menuItem = new ArrayList<JMenuItem>();
		menuItem.add(btnLoad);menuItem.add(btnDelDoc);menuItem.add(btnRollBack);menuItem.add(btnSave);menuItem.add(btnCancel);
		menuItem.add(btnFind);menuItem.add(btnPrint);menuItem.add(btnBold);menuItem.add(btnConvertPdf);
		menuItem.add(btnVolatileStorage);menuItem.add(boxAutomaticSave);menuItem.add(btnImport);menuItem.add(btnGreek);menuItem.add(btnEnglish);
		menuItem.add(btnFrance);menuItem.add(btnItalic);
		keyStrokeSet.setKeyStrokes(menuItem);
		
		// -------------- the text area of the editor
		PageableEditorKit kit = new PageableEditorKit();
		final JTextPane textArea = GFactory4.createArea(kit);
		textArea.addStyle("bold", null);  // crucial for bold style
	    textArea.addStyle("italic", null);  // crucial for italic style
		JScrollPane scroll = GFactory4.createScroll(textArea,EditorWindow);
		spellChecker.initializeSpellCheck(textArea);
		
		exitMenu.addMenuListener(new MenuListener(){
			 public void menuSelected(MenuEvent e) {
				 if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the application?", "" , 0) == JOptionPane.YES_OPTION){
						EditorWindow.setVisible(false);
						EditorWindow.dispose();
				 }			
			 }
			 public void menuDeselected(MenuEvent e) {
			 }
			 public void menuCanceled(MenuEvent e) {
			 }
		});
		
		textArea.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent arg0) {				
			}

			public void insertUpdate(DocumentEvent arg0) {
				wordC.countWordsChars(textArea,labelWords,labelCharacters);
			}

			public void removeUpdate(DocumentEvent arg0) {
				wordC.countWordsChars(textArea,labelWords,labelCharacters);
			}
		});
		
		ArrayList<JCheckBoxMenuItem> boxList = new ArrayList<JCheckBoxMenuItem>();
		boxList.add(btnAutomaticNumbers);
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		boxList.add(boxAutomaticSave);boxList.add(btnVolatileStorage);
		menuList.add(btnSave); menuList.add(btnRollBack); menuList.add(btnLoad);
		menuList.add(btnClear); menuList.add(btnCancel); menuList.add(btnDelDoc);
		menuList.add(btnConvertPdf);
		
		ArrayList<JMenuItem> fullMenuList = new ArrayList<JMenuItem>();
		fullMenuList.add(btnSave); fullMenuList.add(btnRollBack); fullMenuList.add(btnLoad);
		fullMenuList.add(btnClear); fullMenuList.add(btnCancel); fullMenuList.add(btnDelDoc);
		fullMenuList.add(btnConvertPdf);fullMenuList.add(btnExportPdf);fullMenuList.add(btnExportTxt);fullMenuList.add(btnExportOdt);
		fullMenuList.add(btnImport);fullMenuList.add(txtTemplate);fullMenuList.add(odfTemplate);
		fullMenuList.add(ReportTemplate);fullMenuList.add(LetterTemplate);fullMenuList.add(BookTemplate);fullMenuList.add(ArticleTemplate);
		fullMenuList.add(EmptyTemplate);fullMenuList.add(btnGreek);fullMenuList.add(btnEnglish);fullMenuList.add(btnFrance);
		fullMenuList.add(btnFind);fullMenuList.add(btnPrint);fullMenuList.add(btnBold);fullMenuList.add(texMenu);
		
		
		initializeStartWindow();
		italicMaker.italicTextMaker(btnItalic, textArea);
		colorChange.changeTextColor(boxColors, textArea);
		boldMaker.boldTextMaker(btnBold, textArea);
		fontHandler.FontHandling(boxSize, boxFont, textArea);
		serviceMenuHandling.eventListenerService(serviceMenu);
		eventListenerCreateTxt(txtTemplate,btnGreek);
		eventListenerOdf(odfTemplate,btnGreek);
		eventListenerCreate(EditorWindow,textArea,ReportTemplate,ArticleTemplate,BookTemplate,LetterTemplate,EmptyTemplate,boxList,menuList,btnAddCommand,btnGreek);
		eventListenerGenerate(EditorWindow,btnVolatileStorage,btnClear,btnRollBack,btnSave,btnCancel,btnLoad,textArea,boxAutomaticSave,VersionId,btnDelDoc,btnFind,btnConvertPdf,btnAddCommand,clockChoises,clockMenu,labelWords,labelCharacters,btnImport,exportMenu,btnGreek,btnEnglish,btnFrance,btnPrint,btnBullet,btnAutomaticNumbers);
		
		// -------------------------------------------------  this case is if the editor opens a file as a tool with open-with function
		if (args.length > 0) {
			FileReader reader = null;
			try {
				reader = new FileReader(args[0]);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			BufferedReader br = new BufferedReader(reader);
				  
			try {
				textArea.read(br, null);
			} catch (IOException e1) {
						
				e1.printStackTrace();
			}
				    
			try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			textArea.requestFocus();	
			String contents = textArea.getText();
			NewFileCreator nCreate;
			
			
			if (textArea.getText() == null) {
				nCreate = new NewFileCreator("");   // case for the empty template
			}else {
				nCreate = new NewFileCreator(contents); // all the other templates
			}
			MainEditorWindowHelper helper = new MainEditorWindowHelper();
			helper.makeVisible(nCreate.getFrame(),args[0],nCreate.getBoxList(),nCreate.getMenuList(),nCreate.getTextArea(),nCreate.getCommandMenu(),nCreate.getClockChoises(),nCreate.getLabelWords(),nCreate.getLabelChars(),nCreate.getImportItem(),nCreate.getExportMenu(),nCreate.getGreekItem());
		}else {
			makeVisible();
		}
		// ------------------------------------------------
		 
		
	}
	
	public static void eventListenerGenerate(JFrame newWindow,JCheckBoxMenuItem checkBox1,JMenuItem btnClear,JMenuItem buttonRol,JMenuItem btnSave,JMenuItem btnCancel,JMenuItem btnLoad,JTextPane textArea,JCheckBoxMenuItem boxAutomaticSave,int VersionId,JMenuItem btnDelDoc,JMenuItem btnFind,JMenuItem convertPdf,JMenu btnAddCommand,ArrayList<JCheckBoxMenuItem> clockChoises,JMenu clockMenu,JLabel labelW,JLabel labelC,JMenuItem btnImport,JMenu exportMenu,JMenuItem btnGreek,JMenuItem btnEnglish,JMenuItem btnFrance,JMenuItem btnPrint,JCheckBoxMenuItem btnBullet,JCheckBoxMenuItem btnAutomaticNumbers){
		int k = 1;     // integer for the event Handling
		
		EventHandlerEditor eventHN7 = new EventHandlerEditor(newWindow,(k+6),checkBox1,btnClear,buttonRol,btnSave,textArea,boxAutomaticSave,btnDelDoc,btnFind,convertPdf,btnAddCommand,clockChoises,clockMenu,labelW,labelC,btnImport,exportMenu,btnGreek,btnEnglish,btnFrance,btnPrint,btnBullet,btnAutomaticNumbers); // handling for the button cancel
		btnCancel.addActionListener(eventHN7);
		
		EventHandlerEditor eventHN8 = new EventHandlerEditor(newWindow,(k+7),checkBox1,btnClear,buttonRol,btnSave,textArea,boxAutomaticSave,btnDelDoc,btnFind,convertPdf,btnAddCommand,clockChoises,clockMenu,labelW,labelC,btnImport,exportMenu,btnGreek,btnEnglish,btnFrance,btnPrint,btnBullet,btnAutomaticNumbers); //handling for the button load document
		btnLoad.addActionListener(eventHN8);
		
		EventHandlerEditor eventNH9 = new EventHandlerEditor(newWindow,(k+8),checkBox1,btnClear,buttonRol,btnSave,textArea,boxAutomaticSave,btnDelDoc,btnFind,convertPdf,btnAddCommand,clockChoises,clockMenu,labelW,labelC,btnImport,exportMenu,btnGreek,btnEnglish,btnFrance,btnPrint,btnBullet,btnAutomaticNumbers);
		btnSave.addActionListener(eventNH9);
		
		EventHandlerEditor eventNH10 = new EventHandlerEditor(newWindow,(k+9),checkBox1,btnClear,buttonRol,btnSave,textArea,boxAutomaticSave,btnDelDoc,btnFind,convertPdf,btnAddCommand,clockChoises,clockMenu,labelW,labelC,btnImport,exportMenu,btnGreek,btnEnglish,btnFrance,btnPrint,btnBullet,btnAutomaticNumbers);
		btnFind.addActionListener(eventNH10);
	}
	
	// initializes the start window and after that the editor opens
	public static void initializeStartWindow() {
		try {
			startWindow.initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void makeVisible() {
		EditorWindow.setVisible(true);
	}
	
	public static void eventListenerOdf(JMenuItem odfTemplate,JMenuItem btnGreek) {
		createOdf.createOdf(odfTemplate,btnGreek);
	}
	
	public static void eventListenerCreate(JFrame EditorWindow,JTextPane textArea,JMenuItem reportTemplate,JMenuItem articleTemplate,JMenuItem bookTemplate,JMenuItem letterTemplate,JMenuItem emptyTemplate,ArrayList<JCheckBoxMenuItem> boxList,ArrayList<JMenuItem> menuList,JMenu btnAddCommand,JMenuItem btnGreek) {
		tCreator.createTemplate(EditorWindow,textArea,reportTemplate, articleTemplate, bookTemplate, letterTemplate, emptyTemplate,boxList,menuList,btnAddCommand,btnGreek);
	}
	
	public static void eventListenerCreateTxt(JMenuItem txtTemplate,JMenuItem btnGreek) {
		txtCreator.createTxt(txtTemplate,btnGreek);
	}	
}