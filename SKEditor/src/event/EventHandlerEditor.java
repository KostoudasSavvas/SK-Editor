package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;
import Commands.AddLatexCommand;
import Commands.Command;
import Commands.CommandsFactory;
import Commands.DeleteCommand;
import GenerateGui.FindFunctionWindow;
import GenerateGui.WordCounter;
import HardDriveManager.FileLoaderController;
import convertToPdf.PDFGenerator;
import htmlConvertToOdt.HtmlToOdtConverter;
import importExport.ImportController;
import importExport.exportController;
import printer.FilePrinting;
import versionManager.Documents;
import versionManager.VersionsManager;
import versionManager.VolatileVersionsStrategy;

public class EventHandlerEditor implements ActionListener{
	static JFrame newWindow;
	private int number,period = 10,Number = 0;     // an integer needed for handling the volatile storage;     // 10 secs the period of version storage
	private int AutomaticCount = 1,LineCount=0;     // an integer needed to count the automatic number add     // Line Counter
	private JMenu btnAddCommand,exportMenu;
	private JCheckBoxMenuItem checkBox1,boxAutomaticSave,btnBullet,btnAutomaticNumbers;
	private JMenuItem buttonSave,buttonClear,buttonRol,buttonDelDoc,btnFind,btnConvertPdf,btnImport,btnGreek,btnEnglish,btnFrance,btnPrint;
	private JLabel labelWords,labelCharacters;
	private ArrayList<JCheckBoxMenuItem> clockChoises;
	private JTextPane textArea;
	private String openDocPath="",initialOdtText="";
	private static String initialVersion = "";    // the contents of the version that first gets loaded
	private static StyledDocument styledOdt;
	private static AddLatexCommand adLatexCommand;
	private static Command[] commandTable = new Command[50];  
	private static CommandsFactory commandFactory = new CommandsFactory();
	private EventHandlerRollBack rb = new EventHandlerRollBack();       // important object to handle the rollback function
	private EventHandlerSave sv = new EventHandlerSave();               // important object to handle the save function
	private CommandsFactory comFactory = new CommandsFactory();
	private static PDFGenerator pdfConverter = new PDFGenerator();
	private EventHandlerSplit splitter = new EventHandlerSplit();       // an object in order to split the text in necessary parts
	private VolatileVersionsStrategy vt1 = new VolatileVersionsStrategy();
	private FileLoaderController ldFile = new FileLoaderController();
	private EventHandlerBoxesController boxesController = new EventHandlerBoxesController();
	private ImportController importControl = new ImportController();
	private static ScheduledExecutorService scheduleExecutor;
	private static ScheduledFuture<?> scheduleManager;
	private static Runnable timeTask;
	private static exportController exportControl = new exportController();
	private static HtmlToOdtConverter htmlOdtConverter = new HtmlToOdtConverter();
	private static FilePrinting filePrinter = new FilePrinting();
	private static WordCounter wordC = new WordCounter(); 
	private static BulletAdder bulletAdd = new BulletAdder();
	
	public EventHandlerEditor(JFrame newWindow,int number,JCheckBoxMenuItem checkBox1,JMenuItem buttonClear,JMenuItem buttonRol,JMenuItem buttonSave,JTextPane textArea,JCheckBoxMenuItem boxAutomaticSave,JMenuItem buttonDelDoc,JMenuItem btnFind,JMenuItem btnConvertPdf,JMenu btnAddCommand,ArrayList<JCheckBoxMenuItem> clockChoises,JMenu clockMenu,JLabel labelWords,JLabel labelCharacters,JMenuItem btnImport,JMenu exportMenu,JMenuItem btnGreek,JMenuItem btnEnglish,JMenuItem btnFrance,JMenuItem btnPrint,JCheckBoxMenuItem btnBullet,JCheckBoxMenuItem btnAutomaticNumbers){
		this.newWindow = newWindow;
		this.number = number;
		this.checkBox1 = checkBox1;
		this.buttonRol = buttonRol;
		this.buttonSave = buttonSave;
		this.buttonClear = buttonClear;
		this.textArea = textArea;
		this.boxAutomaticSave = boxAutomaticSave;
		this.buttonDelDoc = buttonDelDoc;
		this.btnFind = btnFind;
		this.btnConvertPdf = btnConvertPdf;
		this.btnAddCommand = btnAddCommand;
		this.clockChoises = clockChoises;
		this.labelWords = labelWords;
		this.labelCharacters = labelCharacters;
		this.exportMenu = exportMenu;
		this.btnImport = btnImport;
		this.btnGreek = btnGreek;
		this.btnEnglish = btnEnglish;
		this.btnFrance = btnFrance;
		this.btnPrint = btnPrint;
		this.btnBullet  = btnBullet;
		this.btnAutomaticNumbers = btnAutomaticNumbers;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (number == 7){      // Event handling for the cancel button
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the application?", "Exit" , 0) == JOptionPane.YES_OPTION){
				newWindow.setVisible(false);
				System.exit(0);
			}
		}else if (number == 8){     // Event handling for the load button
			clockChoises.get(0).setSelected(true);
			VersionsManager mg2 = new VersionsManager(vt1);
			ArrayList list = new ArrayList<String>();
			// -------------------------------------------------------------------------------------------------------------------------------- this code loads the file
			String FilePath = ldFile.getLoadName(btnGreek);
			
			if (FilePath.contains(".odt")) {
				try {			       
					htmlOdtConverter.convertToOdt(textArea, FilePath,"",false);  //html to odt formatting!
					styledOdt = textArea.getStyledDocument();   // contents initial version after load
					HTMLEditorKit kitHtml = new HTMLEditorKit();
					StringWriter writer = new StringWriter();
					try {
						kitHtml.write(writer, styledOdt, 0, styledOdt.getLength());
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					initialOdtText = writer.toString();
				} catch (Exception se) {
					se.printStackTrace();
				}	
			}else if ((FilePath.contains(".txt")) || (FilePath.contains(".tex"))){
				FileReader reader = null;
				try {
					reader = new FileReader(FilePath);
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
				initialVersion = textArea.getText();   // contents of initial version after load
				textArea.addStyle("bold", null);  // crucial for bold style
			    textArea.addStyle("italic", null);  // crucial for italic style
			}else {
				JOptionPane.showMessageDialog(null, "You must select a document in order to edit it.","None Chosen Document", JOptionPane.INFORMATION_MESSAGE);
			}
			
			openDocPath = ldFile.getName(); 
			newWindow.setTitle(openDocPath+" "+"- SK Editor");
			
			// -----------------  if txt template the table with latex commands is not availiable.
			if (ldFile.getName().contains("tex")) {
				ArrayList<String> textLoaded = new ArrayList<String>();
				textLoaded.add(textArea.getText());
				eventListenerGenerateCommandWindow(btnAddCommand,newWindow,textLoaded,textArea,ldFile.getName());
			}
			
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
			
			// ------------------------------------------------------------------------------------------------------------------- -------------------- end of load code
			
			buttonSave.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (checkBox1.isSelected()){         // volatile storage
						if (openDocPath.contains(".odt")) { 
		    				ArrayList<String> list = new ArrayList<String>();
							list.clear();
							list.add(saveHelperOdt(textArea));
							Documents doc1 = new Documents(Number,"savvas","13-4-2019",list,splitter.splitText(ldFile.getName())+"Log"+Number+".txt");
							vt1.putVersion(doc1);
							Number++;
							sv.save(textArea, openDocPath, list);
			    			JOptionPane.showMessageDialog(null, "Current temporal version saved","Current Document Saved", JOptionPane.INFORMATION_MESSAGE);
						}else {
							ArrayList<String> list = new ArrayList<String>();
		    				list.clear();
							list.add(textArea.getText());
							Documents doc1 = new Documents(Number,"savvas","13-4-2019",list,ldFile.getName()+"Log"+Number+".txt");
							vt1.putVersion(doc1);
							Number++;
							sv.save(textArea, openDocPath, list);
			    			JOptionPane.showMessageDialog(null, "Current temporal version saved","Current Document Saved", JOptionPane.INFORMATION_MESSAGE);
						}
					}else{
		    			JOptionPane.showMessageDialog(null, "Select any of the storage checkboxes in order to storage your file.","No storage type selected", JOptionPane.INFORMATION_MESSAGE);					
					}
				}
			});
			    
			boxAutomaticSave.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent e){
					if (checkBox1.isSelected()){                           // volatile storage
						
						scheduleExecutor = Executors.newScheduledThreadPool(1);    
					    timeTask = new Runnable() {
					        public void run() {
					        	if (FilePath.contains(".odt")) {
					        		if (newWindow.isActive() && boxAutomaticSave.isSelected() ){ 
					        			
					        			ArrayList<String> list = new ArrayList<String>();
										list.clear();
										list.add(saveHelperOdt(textArea));
										Documents doc1 = new Documents(Number,"savvas","13-4-2019",list,splitter.splitText(ldFile.getName())+"Log"+Number+".txt");
										vt1.putVersion(doc1);
					        			Number++;
										sv.save(textArea, FilePath, list);
						    			JOptionPane.showMessageDialog(null, "Current temporal version saved","Current Document Saved", JOptionPane.INFORMATION_MESSAGE);	    					
										scheduleExecutor.shutdown();
				    				}
					        	}else {
					        		if (newWindow.isActive() && boxAutomaticSave.isSelected() ){            // if the editor window is not active then shutdown the automatic save
										ArrayList<String> list = new ArrayList<String>();
										list.clear();
										list.add(textArea.getText());
										Documents doc1 = new Documents(Number,"savvas","13-4-2019",list,splitter.splitText(ldFile.getName())+"Log"+Number+".txt");
										vt1.putVersion(doc1);
										Number++;
										sv.save(textArea, openDocPath, list);
						    			JOptionPane.showMessageDialog(null, "Current temporal version saved","Current Document Saved", JOptionPane.INFORMATION_MESSAGE);			    					
										scheduleExecutor.shutdown();
				    				}
					        	}
					        	if (period != 10) {
					                changeScheduleTime(period);
					        	}
					        }
					    };

					    //instantiate with default time
					    scheduleManager = scheduleExecutor.scheduleAtFixedRate(timeTask, 10, 10, TimeUnit.SECONDS);
					}
				}
			});
			
			// event handling for the rollback button in order to rollback to a previous version
			buttonRol.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent e){
			    	Documents[] docList = vt1.getEntireHistory(ldFile.getName());
			    	
			    	if (checkBox1.isSelected() && Number > 0){
			    		rb.rollBack(Number,textArea,ldFile.getName(), checkBox1, docList,btnGreek);
					    Number--;
					    vt1.removeVersion();  // removing the version from the volatile list
			    	}else {
			    		if (Number <= 0) {
							JOptionPane.showMessageDialog(null,"Initial version has loaded.","There is none versions left.", JOptionPane.INFORMATION_MESSAGE);
			    			if (FilePath.contains(".odt")) {
			    				htmlOdtConverter.convertToOdt(textArea, FilePath,initialOdtText,true);
			    			}else {
				    			textArea.setText(initialVersion);
			    			}
			    		}else {
							JOptionPane.showMessageDialog(null,"Please select the volatile checkbox in order to rollback to previous versions.","Volatile checkBox not selected", JOptionPane.INFORMATION_MESSAGE);
			    		}
			    	}
			    }
			});
			
			for (JCheckBoxMenuItem choise : clockChoises) {
				choise.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if (choise.isSelected()) {
							String xaxa = splitter.splitClockText(choise.getText());
							String ch = splitter.splitFinalClock(xaxa);
							period = Integer.parseInt(ch);
							boxesController.enableDisable(clockChoises, choise);
						}
					}
				});
			}

			// event handling for delete document 
			buttonDelDoc.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
			    	DeleteCommand delCommand  = comFactory.createDeleteCommand();
			    	delCommand.deleteFile(openDocPath);
				}
			});
			
			// event handling for clear the entire doc
			buttonClear.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the entire file?", "Clear" , 0) == JOptionPane.YES_OPTION){
						textArea.setText(null);
						AutomaticCount = 1;
					}
				}
			});
			
			// event handling for automatic add of bullets or numbers with every new line
			textArea.addKeyListener(new KeyListener() {
			    public void keyTyped(KeyEvent e) {
			    	
			    }
			    public void keyReleased(KeyEvent e) {
			        if(e.getKeyCode() == KeyEvent.VK_ENTER){
			            if (btnBullet.isSelected()) {
							bulletAdd.addBullet(textArea);     // auto bullet add
							LineCount = countLines(textArea);
			            }else if (btnAutomaticNumbers.isSelected()) {
			            	bulletAdd.addNumber(textArea,AutomaticCount);     // auto number fill 1), 2) ..
			            	AutomaticCount++;
			            	LineCount = countLines(textArea);
			            }else{
			            	AutomaticCount = 1;               // must be set to 1 to start the count sometime else
			            	LineCount = countLines(textArea);
			            }
			        }else {			        	
			        	
						while (LineCount >  countLines(textArea)) {
			        		AutomaticCount = AutomaticCount - 1;
			        		LineCount--;
						}
						
			        }
			    }
			    public void keyPressed(KeyEvent e) {
			    	
			    }
			});
			
			
			//event handling for printing the contents of the files
			btnPrint.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	  filePrinter.print(textArea, btnPrint,ldFile.getName());
			      }
			});
			
			//event handling for convertion of the text to pdf file
			btnConvertPdf.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	  pdfConverter.generatePDF(textArea,ldFile.getName(),btnGreek,FilePath);
			      }
			});
			
			btnImport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (FilePath.contains(".odt")) {
						String initialContents = saveHelperOdt(textArea);//writer.toString();
						htmlOdtConverter.convertToOdt(textArea, FilePath,importControl.importFile(),false);
						htmlOdtConverter.convertToOdt(textArea, FilePath,"Savvas",false);
					}else {
						ArrayList<String> contents = new ArrayList<String>();
						contents.clear();
						contents.add(textArea.getText());
						textArea.setText(contents.get(0)+"\n"+importControl.importFile());
					}
				}
			});
			
			// export as pdf
			exportMenu.getItem(0).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (ldFile.getName().contains("tex")) {
		    			JOptionPane.showMessageDialog(null, "Pdf Export not applicable for tex template","Pdf Export", JOptionPane.INFORMATION_MESSAGE);
					}else {
						exportControl.exportFileAs("pdf",textArea, ldFile.getName(), btnGreek);
					}
				}
			});
			
			// export as odt
			exportMenu.getItem(2).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (ldFile.getName().contains("tex")) {
		    			JOptionPane.showMessageDialog(null, "Odt Export not applicable for tex template","Odt Export", JOptionPane.INFORMATION_MESSAGE);
					}else {
						exportControl.exportFileAs("odt",textArea, ldFile.getName(), btnGreek);
					}
				}
			});
			
			// export as txt
			exportMenu.getItem(1).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (ldFile.getName().contains("tex")) {
					    JOptionPane.showMessageDialog(null, "Txt Export not applicable for tex template","Txt Export", JOptionPane.INFORMATION_MESSAGE);
					}else {
						exportControl.exportFileAs("txt",textArea, ldFile.getName(), btnGreek);
					}
				}
			});
		}else if (number == 10) {
			FindFunctionWindow ffw = new FindFunctionWindow(textArea);
			ffw.makeVisible();
		}
	}
	
	public static String saveHelperOdt(JTextPane textArea) {
		StyledDocument styleDoc = textArea.getStyledDocument();    // the version
		HTMLEditorKit kitHtml = new HTMLEditorKit();
		StringWriter writer = new StringWriter();
		try {
			kitHtml.write(writer, styleDoc, 0, styleDoc.getLength());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		String s = writer.toString();
		return s;
	}
	
	public static int countLines(JTextPane textArea) {
		int returnNumber = 0;
        int caretPosition = textArea.getDocument().getLength();
        Element root = textArea.getDocument().getDefaultRootElement();
        String text = "1" + System.getProperty("line.separator");
           for(int i = 2; i < root.getElementIndex(caretPosition); i++) {
              text += i + System.getProperty("line.separator");
              if ((i+1) >= root.getElementIndex(caretPosition)) {
            	  returnNumber = i;
              }
           }
        return returnNumber;
     }
	
	public static void changeScheduleTime(int timeSeconds){
	    //change to hourly update
	    if (scheduleManager!= null){
	        scheduleManager.cancel(true);
	    }
	    scheduleManager = scheduleExecutor.scheduleAtFixedRate(timeTask, timeSeconds, timeSeconds, TimeUnit.SECONDS);
	}
	
	public static void eventListenerGenerateCommandWindow(JMenu buttonAdd,JFrame EditorWindow,ArrayList<String> dataClass,JTextPane textArea,String FileName){
		adLatexCommand = commandFactory.createAddCommand(buttonAdd, dataClass, textArea,FileName);
		commandTable[commandTable.length - 1] = adLatexCommand;       // placing the adLatexCommand in the commands table
	}
}