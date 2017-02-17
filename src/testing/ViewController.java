package testing;

import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Class to handle Views and when they're built and loaded
 * @author Dante
 *
 */
public class ViewController extends Application {

	private static final String TITLE = "Test";
	
	// These have to be static for our OOP to work
	private static Stage stage;
	private static View currentView; // Current view loaded
	
	// Use Java generics where possible
	// Holds all our different possible views
	private static final Map<SceneType, View> views = new TreeMap<>();
	
	public View getCurrentView(){
		return currentView;
	}
	
	// Java FX starts here, builds the 'stage' and then displays it
	@Override
	public void start(Stage primaryStage) throws Exception {
			
		System.out.println("Starting...");
		
		// Create our 'default' view
		currentView = new ViewLogin(new BorderPane(), SceneType.LOGIN);
		
		// Set some bits here
		stage = primaryStage;
		stage.setTitle(TITLE);
		
		// Load all views		
		views.put(SceneType.LOGIN, new ViewLogin(new BorderPane(), SceneType.LOGIN));
		views.put(SceneType.MENU, currentView);
		
		// Build the current scene, create all it's buttons, text etc. 
		// Takes 'parameters' these are whatever you want them to be, a String[] array.
		currentView.buildScene(null);
		stage.setScene(currentView.getView());
		
		// Show the view
		stage.show();
		
	}
	
	// If we want to change the View, we do it here
	public void setScene(SceneType menu){
		// some fancy transition here
		
		// We grab it from our map of views
		View view = views.get(menu);
		
		// If it doesn't exist then we throw an error
		if(view == null)
			throw new IllegalArgumentException("No view by the ID of \"" + menu + "\".");
		
		// If the view has already been built, no point building it again
		if(!view.isBuilt()){
			view.buildScene(null);
		}		
		// However if we want to rebuild the view, see the method below (Maybe something changed)
		
		stage.setScene(view.getView());
	}
	
	// If some data changed somewhere (Maybe in the database) refresh the view, your method can
	// decide what to refresh
	public void rebuildCurrentView(String[] parameters){
		currentView.rebuild(parameters);
	}
	
	public void launch() {
		System.out.println("Launching...");
		super.launch();
	}

}
