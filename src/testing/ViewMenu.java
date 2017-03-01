package testing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Sample class to implement View functionality
 * @author Dante
 *
 */
public class ViewMenu extends View{
	
	private Button switchButton;
	private Text text;
	
	public ViewMenu(Pane layout, SceneType sceneType){
		super(layout, sceneType);
	}

	@Override
	public boolean rebuild(String[] parameters) {
		
		// Get the root element, we know it's a borderpane (if not potential error!)
		BorderPane bp = (BorderPane) getLayout();
		
		{
			
			VBox vbox = new VBox(5);
			
			// Utilise a utility class to create a button
			// JavaFX uses CSS for styling
			switchButton = GraphicsUtil.createNewButton("Switch", "NO_CSS");
			text = GraphicsUtil.createNewText("Here is Scene 2", "NO_CSS");
			
			// Using lambda to handle event calls
			switchButton.setOnAction((event)->{
				
				// If clicked, change the scene to whatever matches in our MAP
				// to SceneType.LOGIN (I.e ViewLogin)
				Main.view.setScene(SceneType.LOGIN);
				
			});
			
			vbox.getChildren().add(switchButton);
			vbox.getChildren().add(text);
			
			vbox.setAlignment(Pos.CENTER);
			
			// Set the login button
			bp.setTop(vbox);
			bp.setPadding(new Insets(20,20,20,20));
			
			
		}
		
		System.out.println("Built logout.");
		
		return true;
	}

	@Override
	public boolean refresh(String[] parameters) {

		// ... update some data here, maybe update a table with some SQL?
		
		return true;
	}


}
