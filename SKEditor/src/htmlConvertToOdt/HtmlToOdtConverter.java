package htmlConvertToOdt;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlToOdtConverter {
	private static odtSplitSpan splitSpan = new odtSplitSpan();
	private String importContents = "";
	
	public void convertToOdt(JTextPane textArea,String FilePath,String contentRollback,boolean rollBack) {
		try {
			// ------------------------------------------------------------  html to string for formatting
			Document docSoup;
			if ((!(contentRollback.equals(""))) && rollBack) {
				textArea.setText(null);
				docSoup = Jsoup.parse(contentRollback);
			}else if ((!(contentRollback.equals("")))) {
				docSoup = Jsoup.parse(contentRollback);
			}else {
				docSoup = Jsoup.parse(new File(FilePath),"utf-8");
			}
			
	        StyledDocument styledDoc = textArea.getStyledDocument();
        	String title = docSoup.title();
        	if (title.isEmpty()) {
        		//System.out.println("Your odt file does not have a title.");
        	}else {
		        //System.out.println(title);
        	}
        	    
        	Elements hTags = docSoup.select("h1, h2, h3, h4, h5, h6");
	        if (hTags.isEmpty()) {
		        //System.out.println("\nYour odt file does not have any headers.");
	        }else {
	        	for (Element header : hTags) {  
		        	System.out.println("\nHeader : " + header.attr("p")); 
		        	System.out.println("text : " + header.text());  
		        	   
		        	// getting the style of the headers
		        	Element spanStyle = docSoup.select("[style]").first();
			        String stylePar = spanStyle.attr("style"); 
			        //System.out.println(stylePar);
		        }  
	        }   
        	    
        	Elements headStyle = docSoup.select("style");
        	if (headStyle.isEmpty()) {
    	        //System.out.println("\n"+"Your file has no style css for styling.");
        	}else {
        		String style = headStyle.html(); 
    	        //System.out.println("\n"+style);
        	}
	        	
	        ArrayList<String> contents = new ArrayList<String>();
	        ArrayList<String> colors = new ArrayList<String>();
	        ArrayList<String> parSize = new ArrayList<String>();
	        ArrayList<String> parFontFamily = new ArrayList<String>();
	        ArrayList<String> parStyle = new ArrayList<String>();
	        ArrayList<Boolean> parBold = new ArrayList<Boolean>();
	        ArrayList<Boolean> parItalic = new ArrayList<Boolean>();
	        ArrayList<String> listOfSpans = new ArrayList<String>();
	        
	        Elements paragraphs = docSoup.select("p");
	        Elements spans = null;
	        int arraySpanNumberPar[] = new int[5000];
	        int counter = 0,position=0;
	        Elements spansTotal = new Elements();
	        for (Element link : paragraphs) {
	        	counter++;
	        	spans = link.select("span");
	        	for (Element span : spans) {
	        		arraySpanNumberPar[position] = counter;
	        		spansTotal.add(span);
		        	listOfSpans.add(span.outerHtml());
		        	position++;
	        	}
	        }
	        
	        for (Element span : spansTotal) {
	        	//System.out.println(span.outerHtml());
	        	if (span.outerHtml().contains("color")) {
		        	colors.add(splitSpan.getValueFixed(splitSpan.getColorStyle(span.outerHtml())));
	        	}
	        	if (span.outerHtml().contains("font-size")) {
		        	parSize.add(splitSpan.getValueFixed(splitSpan.getParSize(span.outerHtml())));
	        	}
	        	if (span.outerHtml().contains("font-family")) {
		        	parFontFamily.add(splitSpan.getValueFixed(splitSpan.getFontFamily(span.outerHtml())));
	        	}
	        	if (span.outerHtml().contains("<b>")) {
	        		parBold.add(true);
	        	}else {
	        		parBold.add(false);
	        	}
	        	
	        	if (span.outerHtml().contains("<i>")) {
	        		parItalic.add(true);
	        	}else {
	        		parItalic.add(false);
	        	}

	        	contents.add(span.text());
	        	parStyle.add(span.outerHtml());
	        }
        	
	        // ----------------------------------------------------- arrayList to array
	        String[] arrayContents = new String[contents.size()];
	        arrayContents = contents.toArray(arrayContents);
	        String[] arrayColors = new String[contents.size()];
	        arrayColors = colors.toArray(arrayColors);
	        String[] arrayParSize = new String[contents.size()];
	        arrayParSize = parSize.toArray(arrayParSize);
	        String[] arrayParFontFamily = new String[contents.size()];
	        arrayParFontFamily = parFontFamily.toArray(arrayParFontFamily);
	        String[] arrayStyle = new String[contents.size()];
	        arrayStyle = parStyle.toArray(arrayStyle);
	        Boolean[] arrayBold = new Boolean[contents.size()];
	        arrayBold = parBold.toArray(arrayBold);
	        Boolean[] arrayItalic = new Boolean[contents.size()];
	        arrayItalic = parItalic.toArray(arrayItalic);
	        String[] arrayStyleSpans = new String[contents.size()];
	        arrayStyleSpans = listOfSpans.toArray(arrayStyleSpans);
	        
	        // ----------------------------------------------------- writing to text Area(Styled Document)
	        Style style = textArea.addStyle("Color Style", null);
	        
	        for (int i =0;i < arrayStyleSpans.length; i ++) {
	        	if (!(arrayColors[i] == null)) {
		        	Color bColor = Color.decode("0x"+splitSpan.getValueColor(arrayColors[i])); 
		        	StyleConstants.setForeground(style,bColor);
	        	}
	        	if (arrayBold[i] == true) {
	        		StyleConstants.setBold(style, true);
	        	}else {
	        		StyleConstants.setBold(style, false);
	        	}
	        	if (arrayItalic[i] == true) {
	        		StyleConstants.setItalic(style, true);
	        	}else {
	        		StyleConstants.setItalic(style, false);
	        	}
	        	
	        	if (!(arrayParFontFamily[i] == null)) {
		        	StyleConstants.setFontFamily(style,arrayParFontFamily[i]); 
	        	}
	        	if (!(arrayParSize[i] == null)) {
		            StyleConstants.setFontSize(style, Integer.parseInt(arrayParSize[i].substring(1,3)));
	        	}
	        	if (arraySpanNumberPar[i+1] == arraySpanNumberPar[i]) {
	        		styledDoc.insertString(styledDoc.getLength(),arrayContents[i]+" ", style);
	        	}else {
	        		styledDoc.insertString(styledDoc.getLength(),arrayContents[i]+"\n", style);
	        	}
	        }
	        
	        HTMLEditorKit kitHtml = new HTMLEditorKit();
			StringWriter writer = new StringWriter();
			try {
				kitHtml.write(writer, styledDoc, 0, styledDoc.getLength());
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			importContents = writer.toString();
		}catch (Exception se) {
	    	JOptionPane.showMessageDialog(null, "Wrong choise of input odt file","Odt Loader", JOptionPane.INFORMATION_MESSAGE);
			se.printStackTrace();
		}
	}
	
	public String getImportContents() {
		return importContents;
	}
}