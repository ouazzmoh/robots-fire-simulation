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
			
			int tailleCases = carteToDraw.getTailleCases();
			int tailleCasesPixel = tailleCases / 100;
			int nbLig = carteToDraw.getNbLignes();
			int nbCol = carteToDraw.getNBColonnes();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.RED);
			
			/* scenario 0*/

			Simulateur simulateur = new Simulateur(gui, donnees, 20);
			Robot[] robots = donnees.getrobot();
			/* 1 er robot est deplacé vers le nord 4 fois */
			Robot robotsTodeplace = robots[0];
			//double vitesse = robotsTodeplace.getVitesse(robotsTodeplace.getPosition().getNature());
			simulateur.ajouteEvenement(new EventRobot(2, "nord", robotsTodeplace));
			/*
			simulateur.ajouteEvenement(new EventMessage(2, "[wait]"));
			simulateur.ajouteEvenement(new EventMessage(2, "[wait]"));
			simulateur.ajouteEvenement(new EventMessage(2, "[wait]"));
			simulateur.ajouteEvenement(new EventMessage(2, "[wait]"));*/
			simulateur.ajouteEvenement(new EventRobot(3, "nord", robotsTodeplace));
			/*
			simulateur.ajouteEvenement(new EventMessage(3, "[wait]"));
			simulateur.ajouteEvenement(new EventMessage(3, "[wait]"));
			simulateur.ajouteEvenement(new EventMessage(3, "[wait]"));
			simulateur.ajouteEvenement(new EventMessage(3, "[wait]"));*/
			simulateur.ajouteEvenement(new EventRobot(4, "nord", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobot(5, "nord", robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobot(5, "sud", robotsTodeplace));
			
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
