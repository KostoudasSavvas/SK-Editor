package htmlConvertToOdt;

public class odtSplitSpan {
	
	
	public String getColorStyle(String fullStyle) {
		String[] firstCut = fullStyle.split("\"");
		String firstResult = firstCut[1];
		String[] secondCut = firstResult.split(";");
		return secondCut[0];
	}
	
	public String getParSize(String fullStyle) {
		String[] firstCut = fullStyle.split("\"");
		String firstResult = firstCut[1];
		String[] secondCut = firstResult.split(";");
		return secondCut[1];
	}
	
	public String getFontFamily(String fullStyle) {
		String[] firstCut = fullStyle.split("\"");
		String firstResult = firstCut[1];
		String[] secondCut = firstResult.split(";");
		return secondCut[2];
	}
	
	// this function splits the char : to get only the value
	public String getValueFixed(String value) {
		String[] firstCut = value.split(":");
		String firstResult = firstCut[1];
		return firstResult;
	}
	
	// this function splits the char : to get only the value
	public String getValueColor(String value) {
		String[] firstCut = value.split("#");
		String firstResult = firstCut[1];
		return firstResult;
	}
}