import java.util.Map.Entry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
public class Path {
	private Case source;
	private Case destination;
	private Robot robot;
	private Carte carte;
	private LinkedList<Direction> path;
	public Path(Robot robot,Carte carte, Case source, Case Destination) {
		this.source = source;
		this.robot = robot;
		this.destination = Destination;
		this.carte = carte;
		this.path = aStar();
	}
	public double h(Case caseCourante) {
		double heuristic = Math.abs(caseCourante.getColonne() - destination.getColonne()) + 
				Math.abs(caseCourante.getLigne() - destination.getLigne());
		double dx1 = (double)(caseCourante.getLigne() - destination.getLigne());
		double dx2 = (double)(source.getLigne() - destination.getLigne());
		double dy1 = (double)(caseCourante.getColonne() - destination.getColonne());
		double dy2 = (double)(source.getColonne() - destination.getColonne());
		double cross = Math.abs(dx1*dy2 - dx2*dy1);
		heuristic += cross * 0.001;
		return heuristic;
	}
	public static ArrayList<Case> getKeys(HashMap<Case, Case> path, Case caseCourante){
		ArrayList<Case> caseList = new ArrayList<Case>();
		for(Entry<Case, Case> entry : path.entrySet()) {
			if(caseCourante.equals(entry.getValue())) {
				caseList.add(entry.getKey());
			}
		}
		return caseList;
	}
	public LinkedList<Direction> aStar() {
		HashMap<Case, Double> g_score = new HashMap<Case, Double>();
		HashMap<Case, Double> f_score = new HashMap<Case, Double>();
		HashMap<Case, Case> aPath = new HashMap<Case, Case>();
		for(Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				g_score.put(c, Double.POSITIVE_INFINITY);
				f_score.put(c, Double.POSITIVE_INFINITY);
			}
		}
		g_score.put(source, 0.0);
		f_score.put(source, h(source));
		LinkedList<Case> queue = new LinkedList<Case>();
		queue.add(source);
		int i = 1;
		while(!(queue.isEmpty())) {
			Case caseCourante = queue.poll();
			i--;
			/*if (caseCourante.equals(destination)) {
				break;
			}*/
			double min = Double.POSITIVE_INFINITY;
			double j = i;
			for(Direction d : Direction.values()) {
				if (carte.voisinExiste(caseCourante, d) && robot.has_accessto(carte.getVoisin(caseCourante, d).getNature())) {
					Case caseFille = carte.getVoisin(caseCourante, d);
					double temp_g_score = g_score.get(caseCourante) + 1.0;
					double temp_f_score = temp_g_score + h(caseFille) * 
							carte.getTailleCases() /  robot.getVitesse(caseFille.getNature());
					if (temp_f_score < f_score.get(caseFille)) {
						g_score.put(caseFille, temp_g_score);
						f_score.put(caseFille, temp_f_score);
						//queue.addFirst(caseFille);
						if(f_score.get(caseFille) < min) {
							queue.addFirst(caseFille);
							min = f_score.get(caseFille);
							i++;
						}
						else {
							queue.add(caseFille);
							/*if (f_score.get(caseFille) < min) {
								queue.poll();
								queue.addFirst(caseFille);
							}*/
						}
						
						aPath.put(caseFille, caseCourante);
					}
					
				}
			}
			
		}
		HashMap<Case, Case> pathMap = new HashMap<Case, Case>();
		LinkedList<Direction> path = new LinkedList<Direction>();
		Case caseCourante = destination;
		double min = Double.POSITIVE_INFINITY;
		while (! caseCourante.equals(source)) {
			pathMap.put(aPath.get(caseCourante), caseCourante);
			caseCourante = aPath.get(caseCourante);
			}
		System.out.println("looping");
		Case next = pathMap.get(source);
		path.add(carte.getDirection(source, next));
		while (! next.equals(destination)) {
			path.add(carte.getDirection(next, pathMap.get(next)));
			next = pathMap.get(next);
		}
		System.out.println(path);
		System.out.println("akram");
		System.out.println(i);
		return path;
		
		
		
	}
	public Case getSource() {
		return source;
	}
	public void setSource(Case source) {
		this.source = source;
	}
	public Case getDestination() {
		return destination;
	}
	public void setDestination(Case destination) {
		this.destination = destination;
	}
	public Robot getRobot() {
		return robot;
	}
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public LinkedList<Direction> getPath() {
		return path;
	}
	public void setPath(LinkedList<Direction> path) {
		this.path = path;
	}
}
