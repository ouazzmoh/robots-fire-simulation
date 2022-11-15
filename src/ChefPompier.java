import java.util.*;

public class ChefPompier {

	private Carte carte;
	private DonneesSimulation donnees;
	
	
	public ChefPompier(Carte carte, DonneesSimulation donnees) {
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
	public boolean canGoElementaire(Robot robot, Simulateur simulateur) {
//		if (!(robot.has_accessto(destination.getNature()))) {
//			return false;
//		}
		if (robot.getReservoir() == 0) {
			return false;
		}
		else if (robot.getDateArrive() + 1 > simulateur.getDateSimulation()) {
			return false;
		}
		return true;
	}
	
	public void strategie(Simulateur simulateur,Robot[] robotTab, Incendie[] incendieTab) {

		for (Robot robot : robotTab) {
			if (canGoElementaire(robot, simulateur)) {
				for (Incendie incendie : incendieTab) {
					if (!(incendie.isAffecte()) && robot.access(incendie.getPosition())) {
						robot.programmeEvents(incendie.getPosition(), simulateur);
						System.out.println("**** incendie" + incendie + "affecte au robot" + robot + "******");
						incendie.setAffecte(true);
						//incendie.setIntensite(0); // juste pour voir les cases visites a enlever
						robot.eteindreIncendie(0, simulateur, incendie);
						robot.remplirEau();
						while (incendie.intensiteCourante != 0) {
							robot.eteindreIncendie(0, simulateur, incendie);
							robot.remplirEau();
						}
						
						break;
					}
				}
			}
		}
	}
			
		
		
		
	}
	

