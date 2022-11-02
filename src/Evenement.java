import java.util.Objects;

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
	
	
	@Override
	public int hashCode() {
		return Objects.hash(Date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evenement other = (Evenement) obj;
		return Date == other.Date;
	}
}
