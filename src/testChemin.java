import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.util.Iterator;
import gui.GUISimulator;

public class testChemin {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			DonneesSimulation donnees = NewLecteurDonnees.lire("cartes/carteSujet.map");
			Carte carteToDraw = donnees.getCarte();
			Incendie[] incendieTableau = donnees.getIncendie();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.RED);
			Simulateur simulateur = new Simulateur(gui, donnees, null);
			
			Robot[] robots = donnees.getrobot();
			Robot robotsTodeplace = robots[2];
			Case source = robotsTodeplace.getPosition();
			Case destination = carteToDraw.getCase(6, 0);
			/*Chemin chemin = new Chemin(robotsTodeplace, carteToDraw, source, destination);
			Iterator<Direction> it = chemin.getChemin().iterator();*/
			Path path = new Path(robotsTodeplace, carteToDraw, source, destination);
			Iterator<Direction> it2 = path.getPath().iterator();
			int i = 1;
			/*Path path2 = new Path(robotsTodeplace, carteToDraw);
			Iterator<Direction> it3 = path2.getPath().iterator();
			while(it3.hasNext()) {
				robotsTodeplace.deplacerEffectivement(it3.next(), carteToDraw, i,simulateur);
				i++;
			}
			*/
			while(it2.hasNext()) {
				robotsTodeplace.deplacerEffectivement(it2.next(), carteToDraw, i,simulateur);
				i++;
			}


			//robotsTodeplace.eteindreIncendie(i, simulateur);
			
			Case destination2 = carteToDraw.getCase(6, 1);
			Chemin chemin2 = new Chemin(robotsTodeplace, carteToDraw, destination, destination2);
			Iterator<Direction> it3 = chemin2.getChemin().iterator();
			int j = 1;
			while(it3.hasNext()) {
				robotsTodeplace.deplacerEffectivement(it3.next(), carteToDraw, i,simulateur);
				j++;
			}
			//Deplacer le robot in the current date of simulateur
			/*
			robotsTodeplace.deplacerEffectivement(Direction.NORD, carteToDraw, 1,simulateur);
			robotsTodeplace.eteindreIncendie(incendieTableau, 1, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, 2 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, 3 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, 4 ,simulateur);
			robotsTodeplace.remplirReservoir(4, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, 5 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, 6 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, 7 ,simulateur);
			robotsTodeplace.eteindreIncendie(incendieTableau, 7, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, 8 ,simulateur);
			robotsTodeplace.eteindreIncendie(incendieTableau, 9, simulateur);*/
			System.out.println("TEST");



		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        
        }

	}
}
