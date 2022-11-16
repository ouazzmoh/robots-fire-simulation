public class EventRobotCharge extends Evenement {
	private Robot robot;
	
	public EventRobotCharge(long date , Robot robot) {
		super(date);
		this.robot = robot;
	}
	public void execute () {
		System.out.println(this.getDate() + " charge son reservoir ") ;
		robot.remplirEau();
	}
}
