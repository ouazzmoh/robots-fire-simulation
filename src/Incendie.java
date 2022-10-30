
public class Incendie {
	private Case position;
	private double intensite; //Nombre de litre necessaire pour l'eteindre
	public Incendie(Case position, double intensite) {
		this.position = position;
		this.intensite = intensite;
	}
	public String toString() {
		return " l'incendie se trouve dans la case " + position.toString() + " et a besoin de " + intensite 
				+ " litres d'eau pour s'eteindre";
	}
	public Case getPosition(){
		return position;
	}
	public double getIntensite() {
		return intensite;
	}
	public void eaudeversee(double vol) {
		intensite -= vol;
	}
	public void setPosition(Case new_position) {
		position = new_position;
	}
	public void setIntensite(double intensite) {
		this.intensite = intensite;
	}
	public void updateIntensite(double vol) {
		intensite += vol;
	}
	
}
