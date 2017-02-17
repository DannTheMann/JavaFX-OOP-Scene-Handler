package testing;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

/**
 * Our template class for all views
 * @author Dante
 *
 */
public abstract class View {
	
	private final Scene scene; // The root scene of the view
	private final Region layout; // The root layout of the scene
	private final SceneType type; // The 'type' of scene
	
	/* fields to decide resolutions across all views */
	private final int MAX_WIDTH = 1920;
	private final int MAX_HEIGHT = 1080;
	private final int MIN_WIDTH = 720;
	private final int MIN_HEIGHT = 1280;
	
	// Has the view been built?
	private boolean built;
	
	/**
	 * Build the view, pass the root layout and the
	 * scene type to describe this view
	 * @param layout The layout, i.e BorderPane
	 * @param sceneType The SceneType
	 */
	public View(Region layout, SceneType sceneType){
		this.scene = new Scene(layout, MIN_HEIGHT, MIN_WIDTH);
		this.layout = layout;
		this.layout.setMaxHeight(MAX_HEIGHT);
		this.layout.setMaxWidth(MAX_WIDTH);
		this.type = sceneType;
	}
	
	// Final so it cannot be overridden by subclasses
	public final SceneType getType(){
		return type;
	}
	
	// Final so it cannot be overridden by subclasses
	public final Region getLayout(){
		return layout;
	}
	
	// Final so it cannot be overridden by subclasses
	public final Scene getView(){
		return scene;
	}
	
	// Final so it cannot be overridden by subclasses
	public final Parent getParent(){
		return scene.getRoot();
	}
	
	// Final so it cannot be overridden by subclasses
	public final boolean isBuilt(){
		return built;
	}
	
	// Final so it cannot be overridden by subclasses
	// Builds the scene, takes a list of 'parameters' which
	// are passed to the overriden method in subclasses called
	// rebuild
	// By default this method will call rebuild and let the
	// subclasses build the view and use the parameters
	// We use the 'built' boolean to stop any unneeded processing
	// on changing the scene
	public final boolean buildScene(String[] parameters){
		
		if(built){
			return false;
		}
		
		built = true;		
		rebuild(parameters);	
		return true;
	}
	
	// Method to be overriden by subclasses
	public abstract void rebuild(String[] parameters);

}
