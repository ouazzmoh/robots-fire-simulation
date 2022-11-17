
public abstract class ChefPompier {
	
	protected Carte carte;
	protected DonneesSimulation donnees;
//	protected boolean endSimulation;
	
	public ChefPompier(Carte carte, DonneesSimulation donnees) {
		this.carte = carte;
		this.donnees = donnees;
//		this.endSimulation = false;
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
	
	abstract void strategie(Simulateur simulateur,Robot[] robotTab, Incendie[] incendieTab);
}
