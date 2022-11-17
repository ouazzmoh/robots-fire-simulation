import java.util.*;

public class ChefPompierSimple extends ChefPompier{

	public ChefPompierSimple(Carte carte, DonneesSimulation donnees) {
		super(carte, donnees);
	}

	/**
	 * Fonction qui retourne si le robot est capable de partir
	 * @param robot
	 * @param destination
	 * @return
	 */
	public boolean canGoElementaire(Robot robot, Simulateur simulateur) {
//		if (!(robot.has_accessto(destination.getNature()))) {
//			return false;
//		}
		/*if (robot.getReservoir() == 0) {
			
			robot.programmeEvents(robot.closestWaterDestination(), simulateur);
			robot.remplirReservoir(simulateur);
			return false;
		}*/
		if (robot.getDateArrive() +1 > simulateur.getDateSimulation()) {
			return false;
		}
		return true;
	}
	
	public void strategie(Simulateur simulateur,Robot[] robotTab, Incendie[] incendieTab) {

		for (Robot robot : robotTab) {
			if (canGoElementaire(robot, simulateur)) {
				for (Incendie incendie : incendieTab) {
					if (!(incendie.isAffecte()) && robot.access(incendie.getPosition())) {
						incendie.setAffecte(true);
						while (incendie.getIntensiteCourante() != 0) {
							robot.programmeEvents(incendie.getPosition(), simulateur);
							System.out.println("**** incendie" + incendie + "affecte au robot" + robot + "******");
							robot.eteindreIncendie(simulateur, incendie);						
						}
						break;
					}
				}
			}
		}
	}
		
		
		
		
		
		
	}
	

