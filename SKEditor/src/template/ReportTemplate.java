package template;

import java.util.ArrayList;

public class ReportTemplate  extends BasicTemplate{
	
	private ArrayList<String> dataReport = new ArrayList<String>();
	
	public ReportTemplate(){
		super();
		this.dataReport.add(setData());
	}
	
	// this method inserts to the template with the necessary pre-edited Latex commands
	public String setData(){
		String data = "";
		data = "";
		data = "\\documentclass{report}"+"\n"+"\\usepackage{}"+"\n"+"\\begin{document}"+"\n"
				+"\\title{...}"+"\n"+"\\author{...}"+"\n"+"\\date{\\today}"+"\n\n\n\n\n\n\n"+"\\end{document}";
		return data;
	}
	
	public ArrayList<String> getData(){
		return dataReport;
	}
}