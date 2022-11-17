import java.util.*;

public class ChefPompierEvolue extends ChefPompier{


	public ChefPompierEvolue(Carte carte, DonneesSimulation donnees) {
		super(carte, donnees);
	}


	
	
	/**
	 * Fonction qui retourne si le robot est capable de partir
	 * @param robot
	 * @param destination
	 * @return
	 */
	public boolean canGo(Robot robot, Simulateur simulateur) {
		/*
		if (robot.getReservoir() == 0) {
			robot.programmeEvents(robot.closestWaterDestination(), simulateur);
			robot.remplirReservoir(simulateur);
			return false;
		}*/
		if (robot.getDateArrive() +1 > simulateur.getDateSimulation()) {
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
	public Incendie closestIncendie(Case positionCourante) {	
		Incendie[] incendieTableau = donnees.getIncendie();
		Case caseProche = incendieTableau[0].getPosition();
		Double min = Double.POSITIVE_INFINITY;
		for(Incendie incendie : incendieTableau) {
			if ( !(incendie.isAffecte()) && heuristicIncendie(incendie.getPosition(), positionCourante) <= min ){
				caseProche = incendie.getPosition();
				min = heuristicIncendie(caseProche, positionCourante);
			}
		}
		Incendie incendieProche = null;
		for (Incendie incendie : donnees.getIncendie()) {
			if (incendie.getPosition().equals(caseProche)) {
				incendieProche = incendie;
				break; 	
			}
		}
		
		return incendieProche;
	}
	
	
	public void strategie(Simulateur simulateur,Robot[] robotTab, Incendie[] incendieTab) {

		for (Robot robot : robotTab) {
			if (canGo(robot, simulateur)) {
//				for (Incendie incendie : incendieTab) {
				//TODO: TEST ACCESS FOR FIRE
				Incendie incendie = closestIncendie(robot.positionCourante);
				if (!(incendie.isAffecte()) && robot.access(incendie.getPosition())) {
					robot.programmeEvents(incendie.getPosition(), simulateur);
					System.out.println("**** incendie" + incendie + "affecte au robot" + robot + "******");
					incendie.setAffecte(true);
					robot.eteindreIncendie(simulateur, incendie);
					while (incendie.getIntensiteCourante() != 0) {
						robot.programmeEvents(incendie.getPosition(), simulateur);
						robot.eteindreIncendie(simulateur, incendie);		
//						}
					}
				}
			}
		}
	}
		
		
		
		
		
		
	}
	

