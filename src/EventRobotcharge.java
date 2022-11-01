
public class EventRobotcharge extends Evenement {
	Robot robot;
	
	public EventRobotcharge(int date , Robot robot) {
		super(date);
		this.robot = robot;
	}
	public void execute () {
		System.out.println(this.getDate() + " charge son reservoir ") ;
		robot.remplirEau();
	}
}
