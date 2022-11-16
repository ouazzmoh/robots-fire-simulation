import java.util.*;

public class ChefPompierEvolue {

	private Carte carte;
	private DonneesSimulation donnees;
	
	
	public ChefPompierEvolue(Carte carte, DonneesSimulation donnees) {
		this.carte = carte;
		this.donnees = donnees;
	}


	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public DonneesSimulation getDonnees() {
		return donnees;
	}
	public void setDonnees(DonneesSimulation donnees) {
		this.donnees = donnees;
	}
	
	
	
	/**
	 * Fonction qui retourne si le robot est capable de partir
	 * @param robot
	 * @param destination
	 * @return
	 */
	public boolean canGo(Robot robot, Simulateur simulateur) {
		if (robot.getReservoir() == 0) {
			return false;
		}
		else if (robot.getDateArrive() +1 > simulateur.getDateSimulation()) {
			return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * Méthode heuristicIncendie, nous renvoie la distance entre le robot et la case caseCourante
	 * @param caseCourante 
	 * @return heuristic : double
	 */
	public double heuristicIncendie(Case caseCourante, Case positionCourante) {
		double heuristic = Math.abs(caseCourante.getColonne() - positionCourante.getColonne()) + 
				Math.abs(caseCourante.getLigne() - positionCourante.getLigne());
		return heuristic;
	}
	
	/**
	 * Méthode closestIncendie, qui renvoie la case de nature EAU la plus proche au robot
	 * @return caseProche
	 */
	public Case closestIncendie(Case positionCourante) {	
		Incendie[] incendieTableau = donnees.getIncendie();
		Case caseProche = incendieTableau[0].getPosition();
		Double min = Double.POSITIVE_INFINITY;
		for(Incendie incendie : incendieTableau) {
			if (heuristicIncendie(incendie.getPosition(), positionCourante) < min){
				min = heuristicIncendie(caseProche, positionCourante);
				caseProche = incendie.getPosition();
			}
		}
		return caseProche;
	}
	
	/**
	 * Méthode closestIncendieDestination, qui renvoie la case voisine de la source d'incendie la plus proche au robot.
	 * @return nouvelleDestination
	 */
	public Incendie closestIncendieDestination(Robot robot) {
		Case destinationTemp = closestIncendie(robot.positionCourante);
		double min = Double.POSITIVE_INFINITY;
		Case nouvelleDestination = null;
		for(Direction d : Direction.values()) {
			if (carte.voisinExiste(destinationTemp, d) && robot.has_accessto(carte.getVoisin(destinationTemp, d).getNature())) {
				if(heuristicIncendie(carte.getVoisin(destinationTemp, d), robot.positionCourante) < min) {
					nouvelleDestination = carte.getVoisin(destinationTemp,  d);
					min = heuristicIncendie(nouvelleDestination, robot.positionCourante);
				}
			}
		}
		
		Incendie incendieDestination = null;
		for (Incendie incendie : donnees.getIncendie()) {
			if (incendie.getPosition().equals(nouvelleDestination)) {
				incendieDestination = incendie;
				break; 	
			}
		}
		
		return incendieDestination;

	}
	
	public void strategie(Simulateur simulateur,Robot[] robotTab, Incendie[] incendieTab) {

		for (Robot robot : robotTab) {
			if (canGo(robot, simulateur)) {
//				for (Incendie incendie : incendieTab) {
				Incendie incendie = closestIncendieDestination(robot);
				if (incendie.getIntensiteCourante() != 0 && robot.access(incendie.getPosition())) {
					robot.programmeEvents(incendie.getPosition(), simulateur);
					System.out.println("**** incendie" + incendie + "affecte au robot" + robot + "******");
					incendie.setAffecte(true);
					robot.eteindreIncendie(simulateur, incendie);
					while (incendie.getIntensiteCourante() != 0) {
						robot.programmeEvents(robot.closestWaterDestination(), simulateur);
						robot.remplirReservoir(simulateur);
						robot.programmeEvents(incendie.getPosition(), simulateur);
						robot.eteindreIncendie(simulateur, incendie);
							
//						}
						break;
					}
				}
			}
		}
	}
		
		
		
		
		
		
	}
	

