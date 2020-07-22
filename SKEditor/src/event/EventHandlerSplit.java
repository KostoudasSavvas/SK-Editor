package event;

public class EventHandlerSplit {
	// just a helper class that splits the inputs in necessary parts
	
	public String splitText(String text){
		String[] arrayS = text.split("[.]");
		return arrayS[0];
	}
	
	public String splitArrayList(String text){
		String[] arrayS = text.split("\\[");
		String[] result = arrayS[1].split("\\]");
		return result[0];
	}
	
	public String splitClockText(String text){
		String[] arrayS = text.split("[!]");
		return arrayS[0];
	}
	
	public String splitFinalClock(String text) {
		
		if (text.equals("10|Seconds")) {
			return "10";
		}else if(text.equals("20|Seconds")){
			return "20";
		}else if(text.equals("30|Seconds")){
			return "30";
		}else if(text.equals("40|Seconds")){
			return "40";
		}else if(text.equals("1|minute")){
			return "60";
		}else if(text.equals("1.5|minutes")){
			return "90";
		}else if(text.equals("2|minutes")){
			return "120";
		}else if(text.equals("3|minutes")){
			return "180";
		}else if(text.equals("4|minutes")){
			return "240";
		}else if(text.equals("5|minutes")){
			return "300";
		}else if(text.equals("10|minutes")){
			return "600";
		}else if(text.equals("15|minutes")){
			return "900";
		}else if(text.equals("20|minutes")){
			return "1200";
		}else if(text.equals("30|minutes")){
			return "1800";
		}else if(text.equals("45|minutes")){
			return "2700";
		}else if(text.equals("1|Hour")){
			return "3600";
		}
		return null;
	}
}