import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import gui.GUISimulator;

public class TestStratElementaire {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			//mushroomOfHell-20x20
			String fichierName = "cartes/mushroomOfHell-20x20.map";
			DonneesSimulation donneesInit = NewLecteurDonnees.lire(fichierName);
			Carte carteToDraw = donneesInit.getCarte();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.RED);
			
			
			ChefPompier chefElem = new ChefPompier(carteToDraw, donneesInit);
			Simulateur simulateur = new Simulateur(gui, donneesInit, chefElem, fichierName);

		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        
        }

	}
}
