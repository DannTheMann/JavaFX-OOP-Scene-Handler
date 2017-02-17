package testing;

public class Main {

	// Single point of access, everything can access the GUI from here
	public static final ViewController view = new ViewController();
	
	// We start here
	public static void main(String[] args) {
		
		System.out.println("Starting...");
		
		// Launch the GUI
		view.launch();
		
		System.out.println("Finished...");
		
	}

}
