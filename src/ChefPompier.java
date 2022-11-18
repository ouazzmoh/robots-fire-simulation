/**
 * Classe abstraite ChefPompier, dont le role est de transmettre les ordres aux pompiers présents sur la carte.
 */
public abstract class ChefPompier {
	
	protected Carte carte;
	protected DonneesSimulation donnees;
	
	/**
	 * Constructeur public, crée un nouveau chefPompier
	 * @param donnees
	 */
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
	 * Méthode abstraite stratégie, qui génère une stratégie pour eteindre toutes les incendies.
	 * @param simulateur
	 * @param robotTab
	 * @param incendieTab
	 */
	public abstract void strategie(Simulateur simulateur,Robot[] robotTab, Incendie[] incendieTab);
}
