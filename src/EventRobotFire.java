

public class EventRobotFire extends Evenement {
	Robot robot;
	Incendie[] incendieTableau;
	
	public EventRobotFire(int date , Robot robot, Incendie[] incendieTableau) {
		super(date);
		this.robot = robot;
		this.incendieTableau = incendieTableau;
	}
	public void execute () {
		System.out.println(this.getDate() + " robot is shuting down fire " ) ;
		int j = 1;
		Case caseCourante = this.incendieTableau[0].getPosition();
		Incendie[] newArr = new Incendie[this.incendieTableau.length - 1];
		while ((caseCourante.getLigne() != this.robot.position.getLigne()) || ((caseCourante.getLigne() != this.robot.position.getLigne()))) {
			caseCourante = this.incendieTableau[j].getPosition();
			j++;
		}
		this.incendieTableau[j-1].etat = 0;
		/*
        for (int i = 0; i < this.incendieTableau.length-1; i++) {
            if(this.incendieTableau[i] == this.incendieTableau[j-1]){
                for(int index = 0; index < i; index++){
                    newArr[index] = this.incendieTableau[index];
                }
                for(int k = i; k < this.incendieTableau.length - 1; k++){
                    newArr[k] = this.incendieTableau[k+1];
                }
                break;
            }
        }
        this.incendieTableau = newArr;
        incendieTableau = newArr;
        System.out.println(this.incendieTableau[4]) ;
        */
        
	}
}
