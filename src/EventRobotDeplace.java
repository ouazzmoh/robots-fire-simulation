public class EventRobotDeplace extends Evenement {
	private Direction message ;
	Robot robot;
	NatureTerrain nouvelleNature;
	
	/**
	 * Constructeur de l'evenement du deplacement du robot
	 * @param date
	 * @param message
	 * @param robot
	 * @param nouvelleNature
	 */
	public EventRobotDeplace(long date , Direction message , Robot robot, NatureTerrain nouvelleNature) {
		super(date);
		this.message = message ;
		this.robot = robot;
		this.nouvelleNature = nouvelleNature;
	}
	
	
	/**
	 * Fonction d'execution, change la position du robot
	 */
	public void execute () {
		System.out.println(this.getDate() + " robot est deplacé vers le "+ this.message ) ;
		Case nouvellePosition = robot.getCarte().getVoisin(robot.getPosition(), message);
		this.robot.setPosition(nouvellePosition);
	}
}
