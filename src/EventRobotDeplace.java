import gui.ImageElement;

public class EventRobotDeplace extends Evenement {
	private String message ;
	Robot robot;
	
	public EventRobotDeplace(int date , String message , Robot robot) {
		super(date);
		this.message = message ;
		this.robot = robot;
	}
	public void execute () {
		System.out.println(this.getDate() + " robot is deplaced vers le "+ this.message ) ;
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
		this.robot.setPosition(nouvellePosition);
	}
}
