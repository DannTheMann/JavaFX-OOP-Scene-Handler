package testing;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * Our template class for all views
 * @author Dante
 *
 */
public abstract class View {
	
	private final Scene scene; // The root scene of the view
	private final Pane layout; // The root layout of the scene
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
	public View(Pane layout, SceneType sceneType){
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
		
		if(built){ // Scene already built, no need to rebuild
			return false;
		}
		
		built = true;		
		rebuild(parameters);	
		return true;
	}
	
	// Used in conjunction with ViewController.rebuildCurrentView as to wipe
	// the current Views layout children and then call the Views rebuild
	// function so it can populate the view again
	public final boolean buildScene(String[] parameters, boolean overwrite){		
		if(overwrite || (!built)){
			built = false;
			layout.getChildren().clear(); // Clear all nodes and elements in the scene
			return buildScene(parameters);
		}
		return false;
	}
	
	// Method to be overriden by subclasses
	// Rebuild is designed to build the entire scene
	// regardless of state
	public abstract boolean rebuild(String[] parameters);
	
	// Refresh is designed to refresh data present on
	// the scene and shouldn't add or remove nodes
	// unless required by the data
	public abstract boolean refresh(String[] parameters);

}
