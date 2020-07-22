package template;

import java.util.ArrayList;

public class BookTemplate extends BasicTemplate {
	
	private ArrayList<String> dataBook = new ArrayList<String>();
	
	public BookTemplate(){
		super();
		this.dataBook.add(setData());
	}

	// this method inserts to the template with the necessary pre-edited Latex commands
	public String setData(){
		String data = "";
		data = "";
		data = "\\documentclass{book}"+"\n"+"\\usepackage{}"+"\n"+"\\begin{document}"+"\n"
				+"\\title{...}"+"\n"+"\\author{...}"+"\n"+"\\date{\\today}"+"\n\n"+"\\frontmatter"+"\n\n\n"+"\\mainmatter"+""
						+ "\n\n\n"+"\\backmatter"+"\n\n\n"+"\\end{document}";
		return data;
	}
	
	public ArrayList<String> getData(){
		return dataBook;
	}
}