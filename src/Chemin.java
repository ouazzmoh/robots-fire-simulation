import java.util.LinkedList;
import java.util.ArrayList;
public class Chemin {
	/**
	 * La classe chemin, qui modélise les chemins dans la carte par une liste chainnée
	 */
	private Case source;
	private Case destination;
	private Carte carte;
	private LinkedList<String> chemin;
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
	public LinkedList<String> getChemin(){
		return chemin;
	}
	public void setChemin(LinkedList<String> chemin) {
		this.chemin = chemin;
	}
	
	public int distance() {
		return Math.abs(source.getColonne()-destination.getColonne()) + Math.abs(source.getLigne() - destination.getLigne());
	}
	public int distance(Case caseCourante) {
		return Math.abs(caseCourante.getColonne()-destination.getColonne()) + Math.abs(caseCourante.getLigne() - destination.getLigne());
	}
	public int[][] distance_array(){
		int[][] distance_carte = new int[carte.getNbLignes()][carte.getNBColonnes()]; 
		for (Case[] ca : carte.getCarte()) {
			for(Case c : ca) {
				distance_carte[c.getLigne()][c.getColonne()] = distance(c);
			}
		}
		return distance_carte;
	}
	public LinkedList<String> cheminnaif(){
		int distance_lignes = destination.getLigne() - source.getLigne();
		int distance_colonnes = destination.getColonne() - source.getColonne();
		LinkedList<String> cheminnaif = new LinkedList<String>();
		String message_ligne;
		String message_colonne;
		if (distance_colonnes > 0) {
			message_colonne = "est";
		}
		else if (distance_colonnes < 0) {
			message_colonne = "ouest";
		}
		else {
			message_colonne = "rien";
		}
		if (distance_lignes > 0) {
			message_ligne = "sud";
		}
		else if (distance_lignes < 0) {
			message_ligne = "nord";
		}
		else {
			message_ligne = "rien";
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
	public LinkedList<String> cheminDrone(){
		int distance_lignes = destination.getLigne() - source.getLigne();
		int distance_colonnes = destination.getColonne() - source.getColonne();
		LinkedList<String> cheminnaif = new LinkedList<String>();
		String message_ligne;
		String message_colonne;
		int i = 0;
		int j = 0;
		if (distance_colonnes > 0) {
			message_colonne = "est";
		}
		else if (distance_colonnes < 0) {
			message_colonne = "ouest";
		}
		else {
			message_colonne = "rien";
		}
		if (distance_lignes > 0) {
			message_ligne = "sud";
		}
		else if (distance_lignes < 0) {
			message_ligne = "nord";
		}
		else {
			message_ligne = "rien";
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
		int[][] distance_array = distance_array();
		cases_a_visiter.add(source);
		while (cases_a_visiter.size() > 0) {
			Case caseCourante = cases_a_visiter.get(0);
			
			
		}
		return chemin;
	}
}
