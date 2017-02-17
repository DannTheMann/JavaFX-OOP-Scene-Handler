package testing;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

/**
 * Sample class to implement View functionality
 * @author Dante
 *
 */
public class ViewLogin extends View{

	private Button login;
	
	public ViewLogin(Region layout, SceneType sceneType) {
		super(layout, sceneType);
	}

	@Override
	public void rebuild(String[] parameters) {
		// .. build something
		
		// Get the root element, we know it's a borderpane (if not potential error!)
		BorderPane bp = (BorderPane) getLayout();
		
		{
			
			// Utilise a utility class to create a button
			// JavaFX uses CSS for styling
			login = GraphicsUtil.createNewButton("Login", "NO_CSS");
			
			// Using lambda to handle event calls
			login.setOnAction((event)->{
				
				// If clicked, change the scene to whatever matches in our MAP
				// to SceneType.MENU (I.e ViewMenu)
				Main.view.setScene(SceneType.MENU);
				
			});
			
			// Set the login button
			bp.setCenter(login);
			
			
		}
		
		System.out.println("Built login.");
	}

	@Override
	public void refresh(String[] parameters) {
		
		// ... update some data here, maybe have an error message for logging in incorrectly?
		
	}
}
