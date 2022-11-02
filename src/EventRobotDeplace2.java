public class EventRobotDeplace2 extends Evenement {
	private String message ;
	Robot robot;
	NatureTerrain nouvelleNature;
	
	public EventRobotDeplace2(long date , String message , Robot robot, NatureTerrain nouvelleNature) {
		super(date);
		this.message = message ;
		this.robot = robot;
		this.nouvelleNature = nouvelleNature;
	}
	public void execute () {
		System.out.println(this.getDate() + " robot is deplaced vers le "+ this.message ) ;
		
		//Do it after 4 steps //
		long dateArrive = this.getDate() + 4;
		
		//
		
		Case position = this.robot.getPosition();
		Case nouvellePosition;
		if (this.message == "nord") {
			nouvellePosition = new Case(position.getLigne() - 1, position.getColonne());
		}
		else if (this.message == "sud") {
			nouvellePosition = new Case(position.getLigne() + 1, position.getColonne());
		}
		else if (this.message == "ouest") {
			nouvellePosition = new Case(position.getLigne(), position.getColonne()-1);
		}
		else{
			nouvellePosition = new Case(position.getLigne(), position.getColonne() + 1);
		}
		nouvellePosition.setNature(nouvelleNature);
		this.robot.setPosition(nouvellePosition);
	}
}
