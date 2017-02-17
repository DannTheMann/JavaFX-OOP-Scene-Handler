package testing;

import java.lang.reflect.Field;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * Utility class to handle one time functionality of 
 * graphical interfaces
 * @author DJA33
 *
 */
public class GraphicsUtil {
	
	/**
	 * Create a new ToolTip
	 * @param message its message
	 * @param font the font to use
	 * @param delay delay before appearing
	 * @return ToolTip
	 */
	public static Tooltip createNewToolTip(String message, Font font, double delay) {
		Tooltip tp = new Tooltip(message);
		tp.setContentDisplay(ContentDisplay.BOTTOM);
		tp.setFont(font);
		tp.setOpacity(.85);
		modifyTooltipTimer(tp, delay);
		return tp;
	}
	
	/**
	 * Modify the delay of a tooltips appearance
	 * @param tooltip The tooltip to modify
	 * @param delay the delay to modify
	 */
    public static void modifyTooltipTimer(Tooltip tooltip, double delay) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(delay)));
            
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            //Out.out.loglnErr("Failed to modify ToolTip!");
            e.printStackTrace();
        }
    }
    
    /**
     * Create a new rectangle
     * @param css The css to apply
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @return The rectangle
     */
    public static Rectangle createNewRectangle(String css, int width, int height){
    	Rectangle r = new Rectangle();
    	r.getStyleClass().add(css);
    	r.setX(50);
    	r.setY(50);
    	r.setWidth(width);
    	r.setHeight(height);
    	r.setArcWidth(25);	
    	r.setArcHeight(25);	
    	return r;
    }
    
    /**
     * Create an image icon
     * @param image the image
     * @param width Width of the image
     * @param height Height of the image
     * @return ImageView of the image
     */
    public static ImageView createIconImage(Image image, int width, int height){
		ImageView iv = new ImageView(image);
		iv.setFitHeight(height);
		iv.setFitWidth(width);
		iv.setPreserveRatio(true);
		return iv;
    }
    
    /**
     * Create a new Text object
     * @param msg The message it contains
     * @param font The font to use
     * @param css The stylesheet to use
     * @return Text
     */
    public static Text createNewText(String msg, Font font, String css){
    	Text text = new Text(msg);
    	text.setFont(font);
    	text.getStyleClass().add(css);
		text.setTextAlignment(TextAlignment.CENTER);
    	return text;	
    }
    
    /**
     * Create new text object
     * @param msg The message it contains
     * @param font The stylesheet to use
     * @return Text
     */
    public static Text createNewText(String msg, String css){
    	Text text = new Text(msg);
    	text.getStyleClass().add(css);
    	return text;	
    }
    
    public static Button createNewButton(String buttonTitle, String css){
    	
    	final Button button = new Button(buttonTitle);
    	button.getStyleClass().add(css);
    	return button;
    	
    }
    
    public static Region createNewButton(String buttonTitle, String css, 
    		String backgroundCSS, int width, int height){
    	
    	final StackPane stackPane = new StackPane();
    	final Button button = new Button(buttonTitle);
    	button.getStyleClass().add(css);  		   		
    	stackPane.getChildren().add(createNewRectangle(backgroundCSS, width, height));
    	stackPane.getChildren().add(button);

    	return stackPane;   	
    }
    
    /**
     * Show an alert dialog
     * @param title Title of the dialog
     * @param mast The header of the dialog
     * @param body The body message
     * @param dialogType What type of dialog to show (Alert, warning etc)
     */
	public static void showAlert(String title, String mast, String body, AlertType dialogType) {
		Alert alert = new Alert(dialogType, body, ButtonType.OK);
		alert.setTitle(title);
		alert.setHeaderText(mast);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
	}
}
