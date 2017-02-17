This is a sample way to handle JavaFX scenes and stages. 

It's main port of call comes from View and ViewController. The ViewController handles which view is currently active and being displayed to the GUI while the View class acts as a framework for subclasses to implement ontop of. The ViewController is accessible from the main class as a static final reference, such that any View can access and change to another view within themselves.

Any class that extends View can utilise it's 'rebuild' method to create the scene it demonstrates, passing parameters when needed. The View will only be built once to stop unnessecary processing but can be rebuilt as many times as the user so desires using the 'rebuild' function.

As of right now the Views are separated and handled by an Enum called SceneType which allows for simple switching between scenes. This happens in the function 'setScene' within the ViewController. Feel free to add an animated transition in here.

Finally a small little Utility class (GraphicsUtil) exists (Purely static) to provide functions for generic tasks.
