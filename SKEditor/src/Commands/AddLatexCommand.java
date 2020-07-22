package Commands;

import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class AddLatexCommand implements Command {
	private JMenuItem commandTable[] = new JMenuItem[50];       // the full table of commands
	private CommandCreator createCom = new CommandCreator();
	private JTextPane textArea = new JTextPane();
	private CommandAdder adCom  = new CommandAdder();
	
	public void setEntireTable(ArrayList<String> dataClass,String FileName){
		
		adCom.addCommand("title{...}","Click this button to add a title to the document");
		JMenuItem btnTitle = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("author{...}","Click this button to add an author to the document");
		JMenuItem btnAuthor = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("item..","Click this button to add an item to the document");
		JMenuItem btnItem = createCom.createCommand(adCom.getCommand(),adCom.getToolText(), textArea, commandTable,dataClass,FileName);
		adCom.addCommand("date{...}","Click this button to add a date to the document");
		JMenuItem btnDate = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("section{...}","Click this button to add a section to the document");
		JMenuItem btnSection = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("subsection{...}","Click this button to add a subsection to the document");
		JMenuItem btnSubSection = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("subsubsection{...}","Click this button to add a subsubsection to the document");
		JMenuItem btnSubSubSection = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("chapter{...}","Click this button to add a chapter to the document");
		JMenuItem btnChapter = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("usepackage{...}","Click this button to insert a package to the document");
		JMenuItem btnInsertPackage = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("frontmatter{...}","Click this button to add a frontmatter to the document");
		JMenuItem btnFrontMatter = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("mainmatter{...}","Click this button to add a mainmatter to the document");
		JMenuItem btnMainMatter = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("backmatter{...}","Click this button to add a backmatter to the document");
		JMenuItem btnBackMatter = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("add ps{...}","Click this button to add a ps to the document");
		JMenuItem btnPs = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("signature{Sender's Name}","Click this button to add the label signature");
		JMenuItem btnSignature = createCom.createCommand(adCom.getCommand(),adCom.getToolText(), textArea, commandTable,dataClass,FileName);
		adCom.addCommand("begin{itemize}..\\end{itemize}","Click this button to add an item list to the document");
		JMenuItem btnItemList = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("begin{enumerate}..\\end{enumerate}","Click this button to add an enumeration list to the document");
		JMenuItem btnEnumerateList = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("begin{table}..\\end{table}","Click this button to add a table to the document");
		JMenuItem btnTable = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
		adCom.addCommand("begin{figure}..\\end{figure}","Click this button to add a figure to the document");
		JMenuItem btnFigure = createCom.createCommand(adCom.getCommand(),adCom.getToolText(),textArea,commandTable,dataClass,FileName);
	}
	
	public void setArea(JTextPane area){
		textArea = area;
	}
	
	public JMenuItem[] getEntireTable(){
		return commandTable;
	}
	
	public String getCommandName(int index){
		return commandTable[index].getText();
	}
	
	public int getNumber(){
		return createCom.getNumber();
	}
	
	public void execute() {
	}
}