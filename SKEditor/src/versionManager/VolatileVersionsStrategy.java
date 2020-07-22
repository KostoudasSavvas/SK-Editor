package versionManager;

public class VolatileVersionsStrategy implements VersionsStrategy{

	private Documents[] DocList = new Documents[500];         // the list of versions in history
	private int NumberOfVersions = 0;
		
	public void putVersion(Documents version) {
		DocList[NumberOfVersions] = version;
		NumberOfVersions++;
	}
	
	public Documents getVersion() {
		return DocList[NumberOfVersions - 1];
	}
	
	public void removeVersion() {
		if (NumberOfVersions == 0){
			System.out.println("Initial empty version of the document has reached");
		}else{
			DocList[NumberOfVersions] = null;
			NumberOfVersions--;
		}
	}

	public Documents[] getEntireHistory(String name) {
		return DocList;
	}
	
	public int getNumberVersions(){
		return NumberOfVersions;
	}
}