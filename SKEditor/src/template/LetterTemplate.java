package template;

import java.util.ArrayList;

public class LetterTemplate extends BasicTemplate{
	
	private ArrayList<String> dataLetter = new ArrayList<String>();
	
	public LetterTemplate(){
		super();
		this.dataLetter.add(setData());
	}
	
	// this method sets the data of the letter template 
	public String setData(){
		String data = "";
		data = "\\documentclass{letter}"+"\n"+"\\usepackage{}"+"\n"+"\\signature{Sender's Name}"+"\n"+"\\address{ }"+"\n"+"\\begin{document}"+"\n"
				+ "\\begin{letter}{Destination Address...}"+"\n"+"\\opening{Dear Sir or Madam:}"+"\n\n\n\n\n"+"\\closing{Yours Faithfully,}"
						+ "\n"+"\\ps"+"\n\n\n"+"\\encl{Copyright permission form}"+"\n\n"+"\\end{letter}"+"\n"+"\\end{document}";
		return data;
	}
	
	public ArrayList<String> getData(){
		return dataLetter;
	}	
}