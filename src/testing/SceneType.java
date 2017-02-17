package testing;

public enum SceneType {
	
	LOGIN("Login panel"),
	MENU("The menu"),
	RECRUIMENT("The recruit");

	String description;
	
	SceneType(String desc){
		this.description = desc;
	}
	
}
