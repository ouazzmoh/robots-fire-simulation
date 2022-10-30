import java.awt.Color;
import gui.GUISimulator;
import gui.ImageElement;

public class testCarte {

	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(800, 600, Color.GRAY);
		gui.addGraphicalElement(new ImageElement(0, 100, "eau.png", 10000,10000, null));
	}

}
