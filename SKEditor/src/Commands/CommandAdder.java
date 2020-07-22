package Commands;

public class CommandAdder {
	private String command;
	private String buttonToolText;
	
	public void addCommand(String command,String buttonToolText){
		this.command = command;
		this.buttonToolText = buttonToolText;
	}
	
	public String getCommand(){
		return command;
	}
	
	public String getToolText(){
		return buttonToolText;
	}
}