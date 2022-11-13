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
	/**
	 * Méthode qui trouve un chemin pour le drone (on rappelle que le drone peut acceder à toutes les cases) 
	 * @return LinkedList<Direction> chemin
	 */
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
	/**
	 * méthode distance (manhattan distance) et non pas la distance euclidienne, car les robots ne peuvent pas
	 * se déplacer diagonalement.
	 * @return distance de Manhattan
	 */
	public double distance() {
		return (double)Math.abs(source.getColonne() - destination.getColonne()) + 
				Math.abs(source.getLigne() - destination.getLigne());
	}

	public double distance(Case caseCourante) {
		return (double)Math.abs(caseCourante.getColonne() - destination.getColonne()) + 
				Math.abs(caseCourante.getLigne() - destination.getLigne());
	}
	/**
	 * Méthode qui crée un tableau de distances pour les cases accessibles par les robots.
	 * la distance pour les cases non accessibles par le robot est l'entier le plus grand (qu'on supposera comme l'infini)
	 * @return tableau des distances
	 */
	public double[][] distanceArray(){
		double[][] distance_carte = new double[carte.getNbLignes()][carte.getNBColonnes()]; 
		for (Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				if(robot.has_accessto(c.getNature())) {
					distance_carte[c.getLigne()][c.getColonne()] = distance(c);
				}
				else {
					distance_carte[c.getLigne()][c.getColonne()] = Double.POSITIVE_INFINITY;
				}
				
			}
		}
		return distance_carte;
	}
	public double[][] tempsHeuristicArray(){
		double[][] heuristicsArray = new double[carte.getNbLignes()][carte.getNBColonnes()];
		double[][] distance_carte = distanceArray();
		for(Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				if(robot.has_accessto(c.getNature())) {
					double dx1 = (double)(c.getLigne() - destination.getLigne());
					double dx2 = (double)(source.getLigne() - destination.getLigne());
					double dy1 = (double)(c.getColonne() - destination.getColonne());
					double dy2 = (double)(source.getColonne() - destination.getColonne());
					double cross = Math.abs(dx1*dy2 - dx2*dy1);
					heuristicsArray[c.getLigne()][c.getColonne()] = distance_carte[c.getLigne()][c.getColonne()];				
					}
				else {
					heuristicsArray[c.getLigne()][c.getColonne()] = Double.POSITIVE_INFINITY;
				}
			}
		}
		return heuristicsArray;
	}
	/**
	 * Méthode qui crée un tableau des nombres de cases traversées, (on le remplira après en cherchant le plus court chemin)
	 * @return tableau de nombre de pas
	 */
	public double[][] nbPasArray(){
		double[][] nbPasArray = new double[carte.getNbLignes()][carte.getNBColonnes()]; 
		for (Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				if (c.equals(source)) {
					nbPasArray[c.getLigne()][c.getColonne()] = 0.0;
				}
				else{
					nbPasArray[c.getLigne()][c.getColonne()] = Double.POSITIVE_INFINITY;
				}
				
			}
		}
		return nbPasArray;
	}
	/**
	 * Méthode qui retourne un tableau du coût (distance + nbpas) qu'on va essayer de minimiser pour trouver le plus court chemin
	 * (on le remplira dans la méthode aStar)
	 * @return tableau des coûts
	 */
	double[][] coutArray(){
		double[][] cout = new double[carte.getNbLignes()][carte.getNBColonnes()];
		for (Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				if (c.equals(source)) {
					cout[c.getLigne()][c.getColonne()] = distance();
				}
				else {
					cout[c.getLigne()][c.getColonne()] = Double.POSITIVE_INFINITY;
				}
			}
		}
		return cout;
	}

	/**
	 * Méthode qui implémente l'algorithme A* pour trouver le plus court chemin dans la carte
	 * @return LinkedList<Direction> path chemin a suivre pour aller de source à destination
	 */
	public LinkedList<Direction> aStar(){
		double[][] nbPas = nbPasArray();
		double[][] distanceArray = tempsHeuristicArray();
		LinkedList<Direction> path = new LinkedList<Direction>();
		LinkedList<Direction> pathH = new LinkedList<Direction>();
		HashMap<Case, Case> chemin = new HashMap<Case, Case>();
		HashMap<Case, Case> pathMap = new HashMap<Case, Case>();
		double[][] cout = coutArray();
		//Direction[] directions = {Direction.NORD, Direction.SUD, Direction.EST, Direction.OUEST};
		Queue<Case> queue = new LinkedList<Case>();
		queue.add(source);
		while(queue.size() > 0) {
			Case caseCourante = queue.poll();
			if (caseCourante.equals(destination)) {
				break;
			}
			double vitesseNord = 0;
			double vitesseSud = 0;
			double vitesseOuest = 0;
			double vitesseEst = 0;
			if (carte.voisinExiste(caseCourante, Direction.NORD)) {
				vitesseNord = robot.getVitesse(carte.getVoisin(caseCourante, Direction.NORD).getNature());
			}
			if (carte.voisinExiste(caseCourante, Direction.SUD)) {
				vitesseSud = robot.getVitesse(carte.getVoisin(caseCourante, Direction.SUD).getNature());
			}
			if (carte.voisinExiste(caseCourante, Direction.EST)) {
				vitesseEst = robot.getVitesse(carte.getVoisin(caseCourante, Direction.EST).getNature());
			}
			if (carte.voisinExiste(caseCourante, Direction.OUEST)) {
				vitesseOuest = robot.getVitesse(carte.getVoisin(caseCourante, Direction.OUEST).getNature());
			}
			double vitesseSomme = vitesseNord + vitesseSud + vitesseOuest + vitesseEst;
			for (Direction d : Direction.values()) {
				double vitesse = 0;
				switch(d) {
				case NORD:
					vitesse += vitesseNord;
				case SUD:
					vitesse += vitesseSud;
				case OUEST:
					vitesse += vitesseOuest;
				case EST:
					vitesse += vitesseEst;
				}
				if (carte.voisinExiste(caseCourante, d) && robot.has_accessto(carte.getVoisin(caseCourante, d).getNature())) {
					Case caseFille  = carte.getVoisin(caseCourante, d);
					double pasTemp = nbPas[caseCourante.getLigne()][caseCourante.getColonne()] + 1.0;
					double coutTemp = pasTemp + distanceArray[caseCourante.getLigne()][caseCourante.getColonne()]  - 
							robot.getVitesse(caseFille.getNature()) / distanceArray[caseCourante.getLigne()][caseCourante.getColonne()] ;
					if ( coutTemp < cout[caseFille.getLigne()][caseFille.getColonne()]) {
						nbPas[caseFille.getLigne()][caseFille.getColonne()] = pasTemp;
						cout[caseFille.getLigne()][caseFille.getColonne()] = coutTemp;
						queue.add(caseFille);
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
		System.out.println("looping");
		Case next = pathMap.get(source);
		path.add(carte.getDirection(source, next));
		while (! next.equals(destination)) {
			path.add(carte.getDirection(next, pathMap.get(next)));
			next = pathMap.get(next);
		}
		System.out.println(path);
		System.out.println("akram");
		return path;
		}
	}

