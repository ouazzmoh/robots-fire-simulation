

public class EventRobotFire extends Evenement {
	Robot robot;
	Incendie[] incendieTableau;
	
	public EventRobotFire(long date , Robot robot, Incendie[] incendieTableau) {
		super(date);
		this.robot = robot;
		this.incendieTableau = incendieTableau;
	}
	public void execute () {
		//Chercher l'incendi a eteidndre
		Incendie fireToKill = incendieTableau[0];
		
		for (Incendie incendie : incendieTableau) {
			if (incendie.getPosition().equals(this.robot.position)) {
				fireToKill = incendie;
				break; //kill the first fire
				
			}
		}
		
			
		
		double reservoir = robot.getReservoir();
		if (fireToKill.getIntensite() - reservoir > 0) {
			fireToKill.setIntensite(fireToKill.getIntensite() - reservoir);
			System.out.println("Il reste " + fireToKill.getIntensite() + " pour l'éteindre");
			robot.deverserEau((int) reservoir);
		}
		else {
			robot.deverserEau((int) fireToKill.getIntensite());
			System.out.println("Incendie éteinte GG");
			fireToKill.setIntensite(0);
		}
	}
		
}
