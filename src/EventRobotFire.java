

public class EventRobotFire extends Evenement {
	private Robot robot;
	private Incendie[] incendieTableau;
	
	public EventRobotFire(long date , Robot robot, Incendie[] incendieTableau) {
		super(date);
		this.robot = robot;
		this.incendieTableau = incendieTableau;
	}
	public void execute () {
		//Chercher l'incendie a eteidndre
		Incendie fireToKill = null;
		
		for (Incendie incendie : incendieTableau) {
			if (incendie.getPosition().equals(this.robot.position)) {
				fireToKill = incendie;
				break; 	
			}
		}
		
		try {
		double reservoir = robot.getReservoir();
		if (fireToKill.getIntensite() - reservoir > 0) {
			fireToKill.setIntensite(fireToKill.getIntensite() - reservoir);
			System.out.println("Il reste " + fireToKill.getIntensite() + " pour l'éteindre");
			robot.deverserEau((int) reservoir);
		}
		else {
			//robot.deverserEau((int) fireToKill.getIntensite());
			System.out.println("Incendie éteinte GG");
			robot.deverserEau((int) fireToKill.getIntensite());
			fireToKill.setIntensite(0);
		}
		}catch(NullPointerException e) {
			System.out.println("La case n'a pas d'incendie");
		}
	}
		
}
