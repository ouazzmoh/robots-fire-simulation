
public abstract class Evenement {
	/**
	 * classe abstraite qui permet la gestion des evenements 
	 */
	long Date;
	
	public Evenement(long Date) {
		this.Date = Date;
	}
	
	public long GetDate() {
		return this.Date;
	}
	
	public abstract void execute();
}
