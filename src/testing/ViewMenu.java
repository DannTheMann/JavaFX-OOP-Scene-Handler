package testing;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

/**
 * Sample class to implement View functionality
 * @author Dante
 *
 */
public class ViewMenu extends View{
	
	private Button logout;
	
	public ViewMenu(Region layout, SceneType sceneType){
		super(layout, sceneType);
	}

	@Override
	public void rebuild(String[] parameters) {
		
		// Get the root element, we know it's a borderpane (if not potential error!)
		BorderPane bp = (BorderPane) getLayout();
		
		{
			
			// Utilise a utility class to create a button
			// JavaFX uses CSS for styling
			logout = GraphicsUtil.createNewButton("Login", "NO_CSS");
			
			// Using lambda to handle event calls
			logout.setOnAction((event)->{
				
				// If clicked, change the scene to whatever matches in our MAP
				// to SceneType.LOGIN (I.e ViewLogin)
				Main.view.setScene(SceneType.LOGIN);
				
			});
			
			bp.setCenter(logout);
			
			
		}
		
		System.out.println("Built logout.");
	}


}
