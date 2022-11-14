
public class ChefPompier {
	private Robot[] robots;
	private Incendie[] incendies;
	private Carte carte;
	public ChefPompier(DonneesSimulation donnees) {
		robots = donnees.getrobot();
		incendies = donnees.getIncendie();
		carte = donnees.getCarte();
	}
	public Robot[] getRobots() {
		return robots;
	}
	public void setRobots(Robot[] robots) {
		this.robots = robots;
	}
	public Incendie[] getIncendies() {
		return incendies;
	}
	public void setIncendies(Incendie[] incendies) {
		this.incendies = incendies;
	}
	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public boolean checkIfAvailable(Robot robot) {
		return robot.isAvailable();
	}
}
