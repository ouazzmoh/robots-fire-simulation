
import java.util.HashMap;
import java.util.Iterator;
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
	public Path(Carte carte, Case source, Case destination) {
		this.source = source;
		this.destination = destination;
		this.carte = carte;
		this.path = cheminDrone();
	}
	public Path(Robot robot, Carte carte) {
		this.robot = robot;
		this.source = robot.getPosition();
		this.carte = carte;
		Case destinationTemp = closestWaterSource();
		System.out.println(destinationTemp);
		double min = Double.POSITIVE_INFINITY;
		Case nouvelleDestination = null;
		for(Direction d : Direction.values()) {
			if (carte.voisinExiste(destinationTemp, d) && robot.has_accessto(carte.getVoisin(destinationTemp, d).getNature())) {
				if(distance(carte.getVoisin(destinationTemp, d), source) < min) {
					nouvelleDestination = carte.getVoisin(destinationTemp,  d);
					min = distance(nouvelleDestination, source);
				}
			}
		}
		this.destination = nouvelleDestination;
		this.path = aStar();
		
	}
	public LinkedList<Direction> getPath() {
		return path;
	}
	
	public Case closestWaterSource(){
		ArrayList<Case> sourcesEau = carte.getSourcesEau();
		Iterator<Case> it = sourcesEau.iterator();
		Case caseProche = null;
		Case caseTemp = null;
		Double min = Double.POSITIVE_INFINITY;
		while(it.hasNext()) {
			caseTemp = it.next();
			if (heuristicEau(caseTemp) < min){
				caseProche = caseTemp;
				min = heuristicEau(caseTemp);
			}
		}
		return caseProche;
	}
	
	public double distance(Case source, Case destination) {
		return Math.abs(source.getColonne() - destination.getColonne()) + 
				Math.abs(source.getLigne() - destination.getLigne());
	}
	public double heuristicEau(Case caseCourante) {
		double heuristic = Math.abs(caseCourante.getColonne() - source.getColonne()) + 
				Math.abs(caseCourante.getLigne() - source.getLigne());
		return heuristic;
	}
	public double heuristic(Case caseCourante) {
		double heuristic = Math.abs(caseCourante.getColonne() - destination.getColonne()) + 
				Math.abs(caseCourante.getLigne() - destination.getLigne());
		double x1 = (double)(caseCourante.getLigne() - destination.getLigne());
		double x2 = (double)(source.getLigne() - destination.getLigne());
		double y1 = (double)(caseCourante.getColonne() - destination.getColonne());
		double y2 = (double)(source.getColonne() - destination.getColonne());
		double cross = Math.abs(x1*y2 - x2*y1);
		heuristic += cross * 0.001;
		return heuristic * carte.getTailleCases() / robot.getVitesse(caseCourante.getNature()) ;
	}
	/*public double heuristic(Case caseCourante) {
		double heuristic = Math.abs(caseCourante.getColonne() - destination.getColonne()) + 
				Math.abs(caseCourante.getLigne() - destination.getLigne());
		double x1 = (double)(caseCourante.getLigne() - destination.getLigne());
		double x2 = (double)(source.getLigne() - destination.getLigne());
		double y1 = (double)(caseCourante.getColonne() - destination.getColonne());
		double y2 = (double)(source.getColonne() - destination.getColonne());
		double cross = Math.abs(x1*y2 - x2*y1);
		heuristic += cross * 0.001;
		return heuristic;
	}*/
	
	/*public double[][] heuristicArray(){
		double[][] heuristicArray = new double[carte.getNbLignes()][carte.getNBColonnes()];
		for (Case[] ca : carte.getCarte()) {
			for (Case c : ca) {
				if (robot.has_accessto(c.getNature())) {
					heuristicArray[c.getLigne()][c.getColonne()] = heuristic(c)  *
							carte.getTailleCases() / robot.getVitesse(c.getNature());
				}
				else {
					heuristicArray[c.getLigne()][c.getColonne()] = Double.POSITIVE_INFINITY;
				}
			}
		}
		return heuristicArray;
	}*/
	public double[][] nbPas(){
		double[][] nbPas = new double[carte.getNbLignes()][carte.getNBColonnes()];
		for(Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				if (c.equals(source)) {
					nbPas[c.getLigne()][c.getColonne()] = 0.0;
				}
				else {
					nbPas[c.getLigne()][c.getColonne()] = Double.POSITIVE_INFINITY;
				}
			}
		}
		return nbPas;
	}
	public double[][] cout(){
		double[][] cout = new double[carte.getNbLignes()][carte.getNBColonnes()];
		for(Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				if(c.equals(source)) {
					cout[c.getLigne()][c.getColonne()] = heuristic(c);
				}
				else {
					cout[c.getLigne()][c.getColonne()] = Double.POSITIVE_INFINITY;
				}
			}
		}
		return cout;
	}
	public double get(double[][] array, Case c) {
		return array[c.getLigne()][c.getColonne()];
	}
	
	public LinkedList<Direction> cheminDrone(){
		int distanceLignes = destination.getLigne() - source.getLigne();
		int distanceColonnes = destination.getColonne() - source.getColonne();
		LinkedList<Direction> cheminnaif = new LinkedList<Direction>();
		Direction directionLigne;
		Direction directionColonne;
		int i = 0;
		int j = 0;
		if (distanceColonnes > 0) {
			directionColonne = Direction.EST;
		}
		else if (distanceColonnes < 0) {
			directionColonne = Direction.OUEST;
		}
		else {
			directionColonne = null;
		}
		if (distanceLignes > 0) {
			directionLigne = Direction.SUD;
		}
		else if (distanceLignes < 0) {
			directionLigne = Direction.NORD;
		}
		else {
			directionLigne = null;
		}
		while (i < Math.abs(distanceLignes) && j < Math.abs(distanceColonnes)) {
			cheminnaif.add(directionLigne);
			cheminnaif.add(directionColonne);
			i++;
			j++;
		}
		if (i < Math.abs(distanceLignes)) {
			for (int k = i; k < Math.abs(distanceLignes); k++) {
				cheminnaif.add(directionLigne);
			}
		}
		if (j < Math.abs(distanceColonnes)) {
			for (int k = j; k < Math.abs(distanceColonnes); k++) {
				cheminnaif.add(directionColonne);
			}
		}
		return cheminnaif;
	}
	public LinkedList<Direction> aStar() {
		double[][] nbPas = nbPas();
		double[][] cout = cout();
		//double[][] heuristicArray = heuristicArray();
		HashMap<Case, Case> pathMap = new HashMap<Case, Case>();
		LinkedList<Case> queue = new LinkedList<Case>();
		HashMap<Case, Case> pathMapReversed = new HashMap<Case, Case>();
		LinkedList<Direction> path = new LinkedList<Direction>();
		queue.add(source);
		int i = 1;
		while(!(queue.isEmpty())) {
			i++;
			Case caseCourante = queue.poll();
			/*if (caseCourante.equals(destination)) {
				break;
			}*/
			double min = Double.POSITIVE_INFINITY;
			double j = i;
			for(Direction d : Direction.values()) {
				if (carte.voisinExiste(caseCourante, d) && robot.has_accessto(carte.getVoisin(caseCourante, d).getNature())) {
					Case caseFille = carte.getVoisin(caseCourante, d);
					double pasTemp = get(nbPas, caseCourante) + 1.0;
					double coutTemp = pasTemp + heuristic(caseFille);
					if (coutTemp < get(cout, caseFille)) {
						nbPas[caseFille.getLigne()][caseFille.getColonne()] = pasTemp;
						cout[caseFille.getLigne()][caseFille.getColonne()] = coutTemp;
						if(get(cout, caseFille) < min) {
							queue.addFirst(caseFille);
							min = get(cout,caseFille);
						}
						else {
							queue.add(caseFille);
						}
						pathMap.put(caseFille, caseCourante);
					}
				}
			}
		}
		Case caseCourante = destination;
		while (! caseCourante.equals(source)) {
			pathMapReversed.put(pathMap.get(caseCourante), caseCourante);
			caseCourante = pathMap.get(caseCourante);
			}
		System.out.println("looping");
		Case next = pathMapReversed.get(source);
		path.add(carte.getDirection(source, next));
		while (! next.equals(destination)) {
			path.add(carte.getDirection(next, pathMapReversed.get(next)));
			next = pathMapReversed.get(next);
		}
		System.out.println(path);
		System.out.println("akram");
		System.out.println(i);
		return path;	
		
	}
}

