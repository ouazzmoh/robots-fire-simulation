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
		System.out.println(this.getDate() + " robot is deplaced vers le "+ this.message ) ;
		Case position = this.robot.getPosition();
		Case nouvellePosition;
		if (this.message == Direction.NORD) {
			nouvellePosition = new Case(position.getLigne() - 1, position.getColonne());
		}
		else if (this.message == Direction.SUD) {
			nouvellePosition = new Case(position.getLigne() + 1, position.getColonne());
		}
		else if (this.message == Direction.OUEST) {
			nouvellePosition = new Case(position.getLigne(), position.getColonne()-1);
		}
		else{
			nouvellePosition = new Case(position.getLigne(), position.getColonne() + 1);
		}
		nouvellePosition.setNature(nouvelleNature);
//		this.robot.dateArrive = 0;
		this.robot.setPosition(nouvellePosition);
	}
}
