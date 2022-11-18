/**
 * Classe EventMessage, classe fille de Evenement qui permet d'afficher un message.
 */
public class EventMessage extends Evenement {
	private String message ;
	
	public EventMessage(int date , String message) {
		super(date);
		this.message = message ;
	}
	public void execute () {
		System.out.println(this.getDate() + this.message ) ;
	}
}

