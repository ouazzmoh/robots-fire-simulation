import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import gui.GUISimulator;
import gui.ImageElement;

public class testCarte {

	public static void main(String[] args) {
		try {
			DonneesSimulation donnees = NewLecteurDonnees.lire("cartes/spiralOfMadness-50x50.map");
			Carte carteToDraw = donnees.getCarte();
			
			int tailleCases = carteToDraw.getTailleCases();
			int tailleCasesPixel = tailleCases / 100;
			int nbLig = carteToDraw.getNbLignes();
			int nbCol = carteToDraw.getNBColonnes();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.RED);

			Simulateur simulateur = new Simulateur(gui, donnees, 11);
			for (int i = 2 ; i <= 10 ; i += 2) {
				simulateur.ajouteEvenement (new EventMessage (i , " [ PING ]") ) ;
			}
			for (int i = 3 ; i <= 9 ; i += 3) {
				simulateur.ajouteEvenement (new EventMessage (i , " [ PONG ]") ) ;
			}
			
		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
	}

}
