public class EventRobotDeplace extends Evenement {
	private Direction message ;
	Robot robot;
//	NatureTerrain nouvelleNature;
//	Case caseArrive;
	
	/**
	 * Constructeur de l'evenement du deplacement du robot
	 * @param date
	 * @param message
	 * @param robot
	 */
	public EventRobotDeplace(long date , Direction message , Robot robot) {
		super(date);
		this.message = message ;
		this.robot = robot;
	}
	
	
	/**
	 * Fonction d'execution, change la position du robot
	 */
	public void execute () {
		System.out.println(this.getDate() + " robot is deplaced vers le "+ this.message ) ;
		
		Case position = this.robot.getPosition();
		position.setCurrentRobot(null); //Removes robot from position
		robot.updateCase(); //updates current position - before moving
		int newX;
		int newY;
		if (this.message == Direction.NORD) {
			newY = position.getLigne() - 1;
			newX = position.getColonne();
		}
		else if (this.message == Direction.SUD) {
			newY = position.getLigne() + 1;
			newX = position.getColonne();
		}
		else if (this.message == Direction.OUEST) {
			newY = position.getLigne();
			newX = position.getColonne() - 1;
		}
		else{
			newY = position.getLigne();
			newX = position.getColonne() + 1;
		}
		Case newPosition = robot.carte.getCase(newY, newX);
		robot.setPosition(newPosition);
		newPosition.setCurrentRobot(robot);
		//Updating
		robot.draw(); //new position
		
	}
}
