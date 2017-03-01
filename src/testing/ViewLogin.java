package testing;

import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Sample class to implement View functionality
 * 
 * @author Dante
 *
 */
public class ViewLogin extends View {

	private Button login;
	private Text text;

	public ViewLogin(Pane layout, SceneType sceneType) {
		super(layout, sceneType);
	}

	@Override
	public boolean rebuild(String[] parameters) {
		// .. build something

		// Get the root element, we know it's a borderpane (if not potential
		// error!)
		BorderPane bp = (BorderPane) getLayout();

		{

			VBox vbox = new VBox(5);

			// Utilise a utility class to create a button and text
			// JavaFX uses CSS for styling
			login = GraphicsUtil.createNewButton("Switch", "NO_CSS");
			text = GraphicsUtil.createNewText("Here is Scene 1", "NO_CSS");

			// Using lambda to handle event calls
			login.setOnAction((event) -> {

				// If clicked, change the scene to whatever matches in our MAP
				// to SceneType.MENU (I.e ViewMenu)
				Main.view.setScene(SceneType.MENU);

			});

			vbox.getChildren().add(login);
			vbox.getChildren().add(text);

			vbox.setAlignment(Pos.CENTER);

			// Set the login button
			bp.setTop(vbox);

			// Add some dummy views for the sake of it
			bp.setBottom(addDummyLineChart());

			bp.setPadding(new Insets(20, 0, 0, 0));

		}

		return true;
	}

	/**
	 * Purely for demonstrating different views, this entire set of data is
	 * always there but never removed and kept in memory so that ease of access
	 * is available rather than reloading the entire chart of information
	 * 
	 * @return a dummy chart for random values
	 */
	private LineChart<Number, Number> addDummyLineChart() {
		// defining the axes
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Duration");
		yAxis.setLabel("MM");
		// creating the chart
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		lineChart.setTitle("Monitoring flow of Rain");
		// defining a series
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		series.setName("Rainfall measurement");
		// populating the series with data

		Random rand = new Random();

		for (int i = 0; i < 20; i++) {
			series.getData().add(new XYChart.Data<Number, Number>(i, rand.nextInt(25) + 1));
		}
		lineChart.getData().add(series);
		return lineChart;
	}

	@Override
	public boolean refresh(String[] parameters) {

		// ... update some data here, maybe have an error message for logging in
		// incorrectly?

		return false;
	}
}
