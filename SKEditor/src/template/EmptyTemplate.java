package template;

import java.util.ArrayList;

public class EmptyTemplate  extends BasicTemplate{
	
	private ArrayList<String> dataEmpty = new ArrayList<String>();
	
	public EmptyTemplate(){
		super();
	}

	public ArrayList<String> getData() {
		return dataEmpty;
	}
}