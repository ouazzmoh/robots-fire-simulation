/**
 * Classe fille de la clasee Evenement, c'est l'evenement du déplacement du robot.
 */
public class EventRobotDeplace extends Evenement {
	private Direction direction ;
	Robot robot;
	NatureTerrain nouvelleNature;
	
	/**
	 * Constructeur de l'evenement du deplacement du robot vers une direction donnée.
	 * @param date
	 * @param message
	 * @param robot
	 */
	public EventRobotDeplace(long date , Direction direction , Robot robot, NatureTerrain nouvelleNature) {
		super(date);
		this.direction = direction ;
		this.robot = robot;
		this.nouvelleNature = nouvelleNature;
	}
	
	public void execute () {
		System.out.println(this.getDate() + this.robot.vitesse + " robot est deplacé vers le "+ this.direction ) ;
		Case nouvellePosition = robot.carte.getVoisin(robot.getPosition(), direction);
		this.robot.setPosition(nouvellePosition);
	}
}
