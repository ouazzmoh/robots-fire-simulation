

public class DonneesSimulation {
	private Carte carte;
	private Incendie[] incendies;
	private Robot[] robots;
	public DonneesSimulation(Carte carte, Incendie[] incendies, Robot[] robots) {
		this.carte = carte;
		this.incendies = incendies;
		this.robots = robots;
	}
	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public Incendie[] getIncendie() {
		return incendies;
	}
	public void setIncendie(Incendie[] incendies) {
		this.incendies = incendies;
	}
	public Incendie getIncendie(int i) {
		return incendies[i];
	}
	public Robot[] getrobot() {
		return robots;
	}
	public void setRobot(Robot[] robots) {
		this.robots = robots;
	}
	public Robot getrobot(int i) {
		return robots[i];
	}
	
}
