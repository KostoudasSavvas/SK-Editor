package GenerateGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

import guiNewFileWindow.NewFileWindowGenerator;
import template.ArticleTemplate;
import template.BookTemplate;
import template.EmptyTemplate;
import template.LetterTemplate;
import template.ReportTemplate;

public class TemplateTexCreator {
	
	public void createTemplate(JFrame EditorWindow,JTextPane textArea,JMenuItem reportTemplate,JMenuItem articleTemplate,JMenuItem bookTemplate,JMenuItem letterTemplate,JMenuItem emptyTemplate,ArrayList<JCheckBoxMenuItem> boxList,ArrayList<JMenuItem> menuList,JMenu btnAddCommand,JMenuItem btnGreek) {
		reportTemplate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ReportTemplate rtw = new ReportTemplate();
				NewFileWindowGenerator newTemplate = new NewFileWindowGenerator(EditorWindow,rtw,textArea,boxList,menuList,btnAddCommand,btnGreek);
				newTemplate.makeVisible();
			}
		});
		
		articleTemplate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ArticleTemplate atw = new ArticleTemplate();
				NewFileWindowGenerator newTemplate = new NewFileWindowGenerator(EditorWindow,atw,textArea,boxList,menuList,btnAddCommand,btnGreek);
				newTemplate.makeVisible();
			}
		});
		
		bookTemplate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				BookTemplate btw = new BookTemplate();
				NewFileWindowGenerator newTemplate = new NewFileWindowGenerator(EditorWindow,btw,textArea,boxList,menuList,btnAddCommand,btnGreek);
				newTemplate.makeVisible();
			}
		});
		
		letterTemplate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				LetterTemplate ltw = new LetterTemplate();
				NewFileWindowGenerator newTemplate = new NewFileWindowGenerator(EditorWindow,ltw,textArea,boxList,menuList,btnAddCommand,btnGreek);
				newTemplate.makeVisible();
			}
		});
		
		emptyTemplate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				EmptyTemplate etw = new EmptyTemplate();
				NewFileWindowGenerator newTemplate = new NewFileWindowGenerator(EditorWindow,etw,textArea,boxList,menuList,btnAddCommand,btnGreek);
				newTemplate.makeVisible();
			}
		});
	}
}