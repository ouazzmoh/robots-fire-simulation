import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;
public class Chemin {
	/**
	 * La classe chemin, qui modélise les chemins dans la carte par une liste chainnée
	 */
	private Robot robot;
	private Case source;
	private Case destination;
	private Carte carte;
	private LinkedList<Direction> chemin;
	/**
	 * Constructeur public, qui crée un nouveau Chemin à partir d'une carte, d'une case source et d'une case destination
	 * @param carte
	 * @param source
	 * @param destination
	 */
	public Chemin(Carte carte, Case source, Case destination) {
		this.source = source;
		this.destination = destination;
		this.carte = carte;
		this.chemin = cheminDrone();
	}
	public Chemin(Robot robot, Carte carte, Case source, Case destination) {
		this.robot = robot;
		this.source = source;
		this.destination = destination;
		this.carte = carte;
		this.chemin = aStar();
		
	}
	public LinkedList<Direction> getChemin(){
		return chemin;
	}
	public void setChemin(LinkedList<Direction> chemin) {
		this.chemin = chemin;
	}
	
	public int distance() {
		return Math.abs(source.getColonne()-destination.getColonne()) + Math.abs(source.getLigne() - destination.getLigne());
	}
	public int distance(Case caseCourante) {
		return Math.abs(caseCourante.getColonne()-destination.getColonne()) + Math.abs(caseCourante.getLigne() - destination.getLigne());
	}
	public int[][] distanceArray(){
		int[][] distance_carte = new int[carte.getNbLignes()][carte.getNBColonnes()]; 
		for (Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				if(robot.has_accessto(c.getNature())) {
					distance_carte[c.getLigne()][c.getColonne()] = distance(c);
				}
				else {
					distance_carte[c.getLigne()][c.getColonne()] = Integer.MAX_VALUE;
				}
				
			}
		}
		return distance_carte;
	}
	
	public int[][] nbPasArray(){
		int[][] nbPasArray = new int[carte.getNbLignes()][carte.getNBColonnes()]; 
		for (Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				if (c.equals(source)) {
					nbPasArray[c.getLigne()][c.getColonne()] = 0;
				}
				else{
					nbPasArray[c.getLigne()][c.getColonne()] = Integer.MAX_VALUE;
				}
				
			}
		}
		return nbPasArray;
	}
	public LinkedList<Direction> cheminnaif(){
		int distance_lignes = destination.getLigne() - source.getLigne();
		int distance_colonnes = destination.getColonne() - source.getColonne();
		LinkedList<Direction> cheminnaif = new LinkedList<Direction>();
		Direction message_ligne;
		Direction message_colonne;
		if (distance_colonnes > 0) {
			message_colonne = Direction.EST;
		}
		else if (distance_colonnes < 0) {
			message_colonne = Direction.OUEST;
		}
		else {
			message_colonne = null;
		}
		if (distance_lignes > 0) {
			message_ligne = Direction.SUD;
		}
		else if (distance_lignes < 0) {
			message_ligne = Direction.NORD;
		}
		else {
			message_ligne = null;
		}
		if (distance_lignes != 0) {
			for (int i = 0; i < Math.abs(distance_lignes); i++) {
				cheminnaif.add(message_ligne);
			}
		}
		if (distance_colonnes != 0) {
			for (int i = 0; i < Math.abs(distance_colonnes); i++) {
				cheminnaif.add(message_colonne);
			}
		}
		
		return cheminnaif;
	}
	public LinkedList<Direction> cheminDrone(){
		int distance_lignes = destination.getLigne() - source.getLigne();
		int distance_colonnes = destination.getColonne() - source.getColonne();
		LinkedList<Direction> cheminnaif = new LinkedList<Direction>();
		Direction message_ligne;
		Direction message_colonne;
		int i = 0;
		int j = 0;
		if (distance_colonnes > 0) {
			message_colonne = Direction.EST;
		}
		else if (distance_colonnes < 0) {
			message_colonne = Direction.OUEST;
		}
		else {
			message_colonne = null;
		}
		if (distance_lignes > 0) {
			message_ligne = Direction.SUD;
		}
		else if (distance_lignes < 0) {
			message_ligne = Direction.NORD;
		}
		else {
			message_ligne = null;
		}
		while (i < Math.abs(distance_lignes) && j < Math.abs(distance_colonnes)) {
			cheminnaif.add(message_ligne);
			cheminnaif.add(message_colonne);
			i++;
			j++;
		}
		if (i < Math.abs(distance_lignes)) {
			for (int k = i; k < Math.abs(distance_lignes); k++) {
				cheminnaif.add(message_ligne);
			}
		}
		if (j < Math.abs(distance_colonnes)) {
			for (int k = j; k < Math.abs(distance_colonnes); k++) {
				cheminnaif.add(message_colonne);
			}
		}
		return cheminnaif;
	}
	public LinkedList<Case> plusCourtChemin(){
		LinkedList<Case> chemin = new LinkedList<Case>();
		ArrayList<Case> cases_a_visiter = new ArrayList<Case>();
		ArrayList<Case> cases_visitees = new ArrayList<Case>();
		int[][] distance_array = distanceArray();
		cases_a_visiter.add(source);
		while (cases_a_visiter.size() > 0) {
			Case caseCourante = cases_a_visiter.get(0);
			
			
		}
		return chemin;
	}
	
	public LinkedList<Direction> aStar(){
		int[][] nbPas = nbPasArray();
		int[][] distanceArray = distanceArray();
		LinkedList<Direction> path = new LinkedList<Direction>();
		LinkedList<Direction> pathH = new LinkedList<Direction>();
		int[][] cout = new int[carte.getNbLignes()][carte.getNBColonnes()];
		for (Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				if (c.equals(source)) {
					cout[c.getLigne()][c.getColonne()] = distance();
				}
				else {
					cout[c.getLigne()][c.getColonne()] = Integer.MAX_VALUE;
				}
			}
		}
		/*
		HashMap<Case, Integer> nbPas = new HashMap<Case, Integer>();
		HashMap<Case, Integer> cout = new HashMap<Case, Integer>();*/
		HashMap<Case, Case> chemin = new HashMap<Case, Case>();
		HashMap<Case, Case> pathMap = new HashMap<Case, Case>();
		Direction[] directions = {Direction.NORD, Direction.SUD, Direction.EST, Direction.OUEST};
		/*
		for (int i = 0; i < carte.getNbLignes(); i++){
			for(int j = 0; j < carte.getNBColonnes(); j++) {
				nbPas.put(carte.getCase(i, j), Integer.MAX_VALUE );
				cout.put(carte.getCase(i, j), Integer.MAX_VALUE);
			}
		}
		nbPas.put(source, 0);
		cout.put(source, distance());*/
		Queue<Case> queue = new LinkedList<Case>();
		queue.add(source);
		int i = 0;
		while(queue.size() > 0) {
			Case caseCourante = queue.poll();
			if (caseCourante.equals(destination)) {
				break;
			}
			for (Direction d : directions) {
				if (carte.voisinExiste(caseCourante, d) && robot.has_accessto(carte.getVoisin(caseCourante, d).getNature()) ) {
					Case caseFille  = carte.getVoisin(caseCourante, d);
					// int pasTemp = distanceArray[caseCourante.getLigne()][caseCourante.getColonne()]
					int pasTemp = nbPas[caseCourante.getLigne()][caseCourante.getColonne()] + 1;
					int coutTemp = pasTemp + distanceArray[caseCourante.getLigne()][caseCourante.getColonne()];
					if (coutTemp < cout[caseFille.getLigne()][caseFille.getColonne()]) {
						nbPas[caseFille.getLigne()][caseFille.getColonne()] = pasTemp;
						//nbPas.put(caseFille, pasTemp);
						cout[caseFille.getLigne()][caseFille.getColonne()] = coutTemp;
						//cout.put(caseFille, coutTemp);
						queue.add(caseFille);
						
						if(pathH.size() == i + 1) {
							pathH.remove(i);
							pathH.add(carte.getDirection(caseCourante, caseFille));
							
						}
						else {
							pathH.add(carte.getDirection(caseCourante, caseFille));
							i++;
						}
						chemin.put(caseFille, caseCourante);
						}
					}
				}
			}
		
		Case caseCourante = destination;
		while (! caseCourante.equals(source)) {
			pathMap.put(chemin.get(caseCourante), caseCourante);
			caseCourante = chemin.get(caseCourante);
			}
		Case next = pathMap.get(source);
		path.add(carte.getDirection(source, next));
		while (! next.equals(destination)) {
			path.add(carte.getDirection(next, pathMap.get(next)));
			next = pathMap.get(next);
		}
		return path;
		}
	}

