package odfLibreOfficeTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import Commands.CommandsFactory;
import Commands.DeleteCommand;
import GenerateGui.WordCounter;
import convertToPdf.PDFGenerator;
import event.BulletAdder;
import event.EventHandlerBoxesController;
import event.EventHandlerRollBack;
import event.EventHandlerSave;
import event.EventHandlerSplit;
import htmlConvertToOdt.HtmlToOdtConverter;
import importExport.ImportController;
import versionManager.Documents;
import versionManager.VersionsManager;
import versionManager.VolatileVersionsStrategy;

public class OdfTemplateEventHandler {
	private int period = 10;     // 10 seconds the initial period of version storage
	private int AutomaticCount = 1;     // an integer needed to count the automatic number add
	private EventHandlerRollBack rb2 = new EventHandlerRollBack();   // important object to handle the rollback function
	private CommandsFactory comFactory = new CommandsFactory();
	private EventHandlerSave sv2 = new EventHandlerSave();           // important object to handle the save function
	private static PDFGenerator pdfConverter = new PDFGenerator();
	private EventHandlerSplit splitter = new EventHandlerSplit(); 
	private int Number = 0;                           // an integer needed for handling the volatile storage
	private EventHandlerBoxesController boxesController = new EventHandlerBoxesController();
	private ImportController importControl = new ImportController();
	private static ScheduledExecutorService scheduleExecutor;
	private static ScheduledFuture<?> scheduleManager;
	private static Runnable timeTask;
	private static HtmlToOdtConverter htmlOdtConverter = new HtmlToOdtConverter();
	private static WordCounter wordC = new WordCounter();
	private static BulletAdder bulletAdd = new BulletAdder();

	
	public void makeVisible(JFrame EditorWindow,String name,ArrayList<JCheckBoxMenuItem> boxList,ArrayList<JMenuItem> itemList,JTextPane textArea,JMenu btnAddCommand,ArrayList<JCheckBoxMenuItem> clockChoises,JLabel labelWords,JLabel labelCharacters,JMenuItem btnImport,JMenu exportMenu,JMenuItem btnGreek){
			VolatileVersionsStrategy vt1 = new VolatileVersionsStrategy();
			VersionsManager mg2 = new VersionsManager(vt1);
			ArrayList<String> list = new ArrayList<String>();
			
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
			
			EditorWindow.setTitle(name+" "+"- SK Editor");
			
		    // event handling for the save action that saves the whole file for stable storage
		    itemList.get(0).addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		if (boxList.get(1).isSelected()){         // volatile storage
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
	    				
	    				ArrayList<String> list = new ArrayList<String>();
						list.clear();
						list.add(s);
						Documents doc1 = new Documents(Number,"savvas","13-4-2019",list,splitter.splitText(name)+"Log"+Number+".txt");
						vt1.putVersion(doc1);
						Number++;
						sv2.save(textArea,name, list);
						if (btnGreek.isSelected()) {
		    				JOptionPane.showMessageDialog(null, "Î Ï�Î¿ÏƒÏ‰Ï�Î¹Î½Î® Î­ÎºÎ´Î¿ÏƒÎ· Î±Ï€Î¿Î¸Î·ÎºÎµÏ�Ï„Î·ÎºÎµ","Î¤Î¿ Ï€Î±Ï�ÏŒÎ½ Î±Ï�Ï‡ÎµÎ¯Î¿ Î±Ï€Î¿Î¸Î·ÎºÎµÏ�Ï„Î·ÎºÎµ", JOptionPane.INFORMATION_MESSAGE);
						}else {
		    				JOptionPane.showMessageDialog(null, "Current temporal version saved","Current Document Saved", JOptionPane.INFORMATION_MESSAGE);
						}
		    		}else {
		    			if (btnGreek.isSelected()) {
		    				JOptionPane.showMessageDialog(null, "Î Ï�Î­Ï€ÎµÎ¹ Î½Î± Ï„ÏƒÎµÎºÎ¬Ï�ÎµÏ„Îµ Ï„Î¿ ÎºÎ¿Ï…Ï„Î¯ Î Ï�Î¿ÏƒÏ‰Ï�Î¹Î½Î® Î±Ï€Î¿Î¸Î®ÎºÎµÏ…ÏƒÎ· Ï€Ï�Î¿ÎºÎµÎ¹Î¼Î­Î½Î¿Ï… Î½Î± Î±Ï€Î¿Î¸Î·ÎºÎµÏ�ÏƒÎµÏ„Îµ ÎµÎ½Î± Î±Ï�Ï‡ÎµÎ¯Î¿.","ÎœÎ· ÎµÏ€Î¹Î»ÎµÏ‡Î¸ÎµÎ¯Ï‚ Ï„Ï�Ï€Î¿Ï‚ Î±Ï€Î¿Î¸Î®ÎºÎµÏ…ÏƒÎ·Ï‚", JOptionPane.INFORMATION_MESSAGE);
						}else {
		    				JOptionPane.showMessageDialog(null, "Select any of the storage checkboxes in order to storage your file.","No storage type selected", JOptionPane.INFORMATION_MESSAGE);
						}
		    		}
		    	}
		    });
		    
		    // event handling for the Automatic save in order to save a version every period of time
		    boxList.get(0).addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){	
		    		if (boxList.get(1).isSelected()){                           // volatile storage
						
						scheduleExecutor = Executors.newScheduledThreadPool(1);    
					    timeTask = new Runnable() {
					        public void run() {
					        	if (boxList.get(0).isSelected() ){            // if the editor window is not active then shutdown the automatic save
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
				    				
				    				ArrayList<String> list = new ArrayList<String>();
									list.clear();
									list.add(s);
									Documents doc1 = new Documents(Number,"savvas","13-4-2019",list,splitter.splitText(name)+"Log"+Number+".txt");
									vt1.putVersion(doc1);
									Number++;
									sv2.save(textArea, name, list);
									if (btnGreek.isSelected()) {
					    				JOptionPane.showMessageDialog(null, "Î Ï�Î¿ÏƒÏ‰Ï�Î¹Î½Î® Î­ÎºÎ´Î¿ÏƒÎ· Î±Ï€Î¿Î¸Î·ÎºÎµÏ�Ï„Î·ÎºÎµ","Î¤Î¿ Ï€Î±Ï�ÏŒÎ½ Î±Ï�Ï‡ÎµÎ¯Î¿ Î±Ï€Î¿Î¸Î·ÎºÎµÏ�Ï„Î·ÎºÎµ", JOptionPane.INFORMATION_MESSAGE);
									}else {
					    				JOptionPane.showMessageDialog(null, "Current temporal version saved","Current Document Saved", JOptionPane.INFORMATION_MESSAGE);
									}		   
			    					scheduleExecutor.shutdown();
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
		    itemList.get(1).addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		
		    		Documents[] docList = vt1.getEntireHistory(name);    // a list with all the volatile strategy versions
		    		
			    	if (boxList.get(1).isSelected() && Number > 0){
				    	rb2.rollBack(Number,textArea,name, boxList.get(1), docList,btnGreek);
				    	Number--;
				    	vt1.removeVersion();     // removing the version from the volatile list
			    	}else {
			    		if (Number <= 0) {
			    			if (btnGreek.isSelected()) {
								JOptionPane.showMessageDialog(null,"Î”ÎµÎ½ Ï…Ï€Î¬Ï�Ï‡ÎµÎ¹ ÎºÎ±Î¼Î¯Î± Î´Î¹Î±Î¸Î­ÏƒÎ¹Î¼Î· Ï€Ï�Î¿Î·Î³Î¿Ï�Î¼ÎµÎ½Î· Î­ÎºÎ´Î¿ÏƒÎ· Ï„Î¿Ï… Î±Ï�Ï‡ÎµÎ¯Î¿Ï….","Î‘Ï�Ï‡Î¹ÎºÎ® Î­ÎºÎ´Î¿ÏƒÎ· Ï†Î¿Ï�Ï„ÏŽÎ¸Î·ÎºÎµ", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null,"Initial version has loaded.","There is none versions left.", JOptionPane.INFORMATION_MESSAGE);
							}
							textArea.setText(null);
			    		}else {
			    			if (btnGreek.isSelected()) {
								JOptionPane.showMessageDialog(null,"Î Ï�Î­Ï€ÎµÎ¹ Î½Î± Ï„ÏƒÎµÎºÎ¬Ï�ÎµÏ„Îµ Ï„Î¿ ÎºÎ¿Ï…Ï„Î¯ Î Ï�Î¿ÏƒÏ‰Ï�Î¹Î½Î® Î‘Ï€Î¿Î¸Î®ÎºÎµÏ…ÏƒÎ· Ï€Ï�Î¿ÎºÎµÎ¹Î¼Î­Î½Î¿Ï… Î½Î± Î¼ÎµÏ„Î±Î²ÎµÎ¯Ï„Îµ ÏƒÎµ Ï€Ï�Î¿Î·Î³Î¿Ï�Î¼ÎµÎ½ÎµÏ‚ ÎµÎºÎ´ÏŒÏƒÎµÎ¹Ï‚.","ÎšÎ¿Ï…Ï„Î¯ Î Ï�Î¿ÏƒÏ‰Ï�Î¹Î½Î® Î‘Ï€Î¿Î¸Î®ÎºÎµÏ…ÏƒÎ· Î¼Î· Î´Î¹Î±Î»ÎµÎ³Î¼Î­Î½Î¿.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null,"Please select the volatile checkbox in order to rollback to previous versions.","Volatile checkBox not selected", JOptionPane.INFORMATION_MESSAGE);
							}
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
		    itemList.get(5).addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e) {
		    		DeleteCommand delCommand  = comFactory.createDeleteCommand();
		    		delCommand.deleteFile(name);
			    	
				}
		    });
		    
		    // event handling for automatic add of bullets with every new line
		    textArea.addKeyListener(new KeyListener() {
		 			public void keyTyped(KeyEvent e) {
		 			}
		 			public void keyReleased(KeyEvent e) {
		 			    if(e.getKeyCode() == KeyEvent.VK_ENTER){
		 			        if (boxList.get(2).isSelected()) {
		 						bulletAdd.addBullet(textArea);
		 			        }else if (boxList.get(3).isSelected()) {
				            	bulletAdd.addNumber(textArea,AutomaticCount);     // auto number fill 1), 2) ..
				            	AutomaticCount++;
				            }else{
				            	AutomaticCount = 1;               // must be set to 1 to start the count sometime else
				            }
		 			    }
		 			}
		 			public void keyPressed(KeyEvent e) {
		 			}
		 	});
		    
		   // event handling to clear all the contents of the file.
		   itemList.get(3).addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e) {
		    		if (btnGreek.isSelected()) {
						if (JOptionPane.showConfirmDialog(null, "ÎˆÎ¹ÏƒÏ„Îµ ÏƒÎ¯Î³Î¿Ï…Ï�Î¿Î¹ Ï€Ï‰Ï‚ Î¸Î­Î»ÎµÏ„Îµ Î½Î± ÎºÎ±Î¸Î±Ï�Î¯ÏƒÎµÏ„Îµ Î¿Î»ÏŒÎºÎ»Î·Ï�Î¿ Ï„Î¿ Î±Ï�Ï‡Î­Î¯Î¿?", "Î•ÎºÎºÎ±Î¸Î¬Ï�Î¹ÏƒÎ·" , 0) == JOptionPane.YES_OPTION){
							textArea.setText(null);
						}
					}else {
						if (JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the entire file?", "Clear" , 0) == JOptionPane.YES_OPTION){
							textArea.setText(null);
						}
					}
				}
		   });	
		   
		 //event handling for convertion of the text to pdf file
		   itemList.get(6).addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	  pdfConverter.generatePDF(textArea,name,btnGreek,name);
			      }
		   });
		   
		 //event handling for exit of the creation of the odt file.Also automatic save of progress.
		   itemList.get(4).addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	  if (btnGreek.isSelected()) {
			    		  if (JOptionPane.showConfirmDialog(null, "Î•Î¯ÏƒÏ„Îµ ÏƒÎ¯Î³Î¿Ï…Ï�Î¿Î¹ ÏŒÏ„Î¹ Î¸Î­Î»ÎµÏ„Îµ Î½Î± Ï„ÎµÎ»ÎµÎ¹ÏŽÏƒÎµÏ„Îµ Ï„Î·Î½ Î´Î·Î¼Î¹Î¿Ï…Ï�Î³Î¯Î± Î½Î­Î¿Ï… Î±Ï�Ï‡ÎµÎ¯Î¿Ï…? Î‘Î½ Î½Î±Î¹, Ï„ÏŒÏ„Îµ Î±Ï…Ï„ÏŒÎ¼Î±Ï„Î· Î±Ï€Î¿Î¸Î®ÎºÎµÏ…ÏƒÎ· Ï„Î¿Ï… Î±Ï�Ï‡ÎµÎ¯Î¿Ï… Î¸Î± ÏƒÏ…Î¼Î²ÎµÎ¯ Î±Î¼Î­ÏƒÏ‰Ï‚.", "" , 0) == JOptionPane.YES_OPTION){
				    		  sv2.save(textArea,name,list);
				    		  EditorWindow.dispose();
				    	  }
			    	  }else {
			    		  if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the new document? If yes then an automatic save of progress will ocur.", "" , 0) == JOptionPane.YES_OPTION){
				    		  sv2.save(textArea,name,list);
				    		  EditorWindow.dispose();
				    	  }
			    	  }
			    	  
			      }
		   });
		   // event handling for import wizard
		   btnImport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (name.contains(".odt")) {
						StyledDocument docS = textArea.getStyledDocument();
						HTMLEditorKit kitHtml = new HTMLEditorKit();
						StringWriter writer = new StringWriter();
						try {
							kitHtml.write(writer, docS, 0, docS.getLength());
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
						String initialContents = writer.toString();
						htmlOdtConverter.convertToOdt(textArea, name,importControl.importFile(),false);
						htmlOdtConverter.convertToOdt(textArea, name,"Savvas",false);
					}else {
						ArrayList<String> contents = new ArrayList<String>();
						contents.clear();
						contents.add(textArea.getText());
						textArea.setText(contents.get(0)+"\n"+importControl.importFile());
					}
				}
			});
	}
	
	public static void changeScheduleTime(int timeSeconds){
	    //change to hourly update
	    if (scheduleManager!= null)
	    {
	        scheduleManager.cancel(true);
	    }
	    scheduleManager = scheduleExecutor.scheduleAtFixedRate(timeTask, timeSeconds, timeSeconds, TimeUnit.SECONDS);
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