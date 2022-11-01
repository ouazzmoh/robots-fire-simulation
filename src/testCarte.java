import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import gui.GUISimulator;
import gui.ImageElement;

public class testCarte {

	public static void main(String[] args) {
		try {
			DonneesSimulation donnees = NewLecteurDonnees.lire("cartes/carteSujet.map");
			Carte carteToDraw = donnees.getCarte();
			Incendie[] incendieTableau = donnees.getIncendie();
			
			int tailleCases = carteToDraw.getTailleCases();
			int tailleCasesPixel = tailleCases / 100;
			int nbLig = carteToDraw.getNbLignes();
			int nbCol = carteToDraw.getNBColonnes();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.RED);
			Simulateur simulateur = new Simulateur(gui, donnees, 25, incendieTableau);
			Robot[] robots = donnees.getrobot();
			
			 /* scenario 0*/

			
			/* 1 er robot est deplacé vers le nord 4 fois */
			/*
			Robot robotsTodeplace = robots[0];
			simulateur.ajouteEvenement(new EventRobotDeplace(2, "nord", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(3, "nord", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(4, "nord", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(5, "nord", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(5, "sud", robotsTodeplace));
			*/
			
			/* scenario 1*/
			Robot robotsTodeplace = robots[1];
			int i = 2;
			simulateur.ajouteEvenement(new EventRobotDeplace(i, "nord", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotFire(i, robotsTodeplace, incendieTableau));	
			simulateur.ajouteEvenement(new EventRobotDeplace(i, "ouest", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, "ouest", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, "ouest", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotcharge(i, robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, "est", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, "est", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, "est", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotFire(i, robotsTodeplace, incendieTableau));	
			/*
			for (int i = 2 ; i <= 10 ; i += 2) {
				simulateur.ajouteEvenement (new EventMessage (i , " [ PING ]") ) ;
			}
			for (int i = 3 ; i <= 9 ; i += 3) {
				simulateur.ajouteEvenement (new EventMessage (i , " [ PONG ]") ) ;
			}*/
			
		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
	}

}
