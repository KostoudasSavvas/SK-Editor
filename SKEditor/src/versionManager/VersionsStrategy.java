package versionManager;

public interface VersionsStrategy {
	void putVersion(Documents doc);
	Documents getVersion();
	Documents[] getEntireHistory(String name);
	void removeVersion();	
}