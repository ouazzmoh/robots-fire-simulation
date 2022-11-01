
public abstract class Evenement {
	/**
	 * classe abstraite qui permet la gestion des evenements 
	 */
	private long Date;
	
	public Evenement(long Date) {
		this.Date = Date;
	}
	
	public long getDate() {
		return this.Date;
	}
	
	public abstract void execute();
}
