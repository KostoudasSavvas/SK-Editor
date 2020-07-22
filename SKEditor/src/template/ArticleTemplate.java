package template;

import java.util.ArrayList;

public class ArticleTemplate extends BasicTemplate {
	
	private ArrayList<String> dataArticle = new ArrayList<String>();
	
	public ArticleTemplate(){
		super();
		this.dataArticle.add(setData());
	}
	
	// this method inserts to the template with the necessary pre-edited Latex commands
	public String setData(){
		String data = "";
		data = "";
		data = "\\documentclass{article}"+"\n"+"\\usepackage{}"+"\n"+"\\begin{document}"+"\n"
				+"\\title{...}"+"\n"+"\\author{...}"+"\n"+"\\date{\\today}"+"\n\n\n\n\n"+"\\end{document}";
		return data;
	}
	
	public ArrayList<String> getData(){
		return dataArticle;
	}	
}