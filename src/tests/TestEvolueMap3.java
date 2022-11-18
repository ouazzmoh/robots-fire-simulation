package tests;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;


import simulation.*;
import io.*;
import donnees.*;
import chefpompier.*;

import gui.GUISimulator;

public class TestEvolueMap3 {
	public static void main(String[] args) {
		try {
			String fichierName = "cartes/desertOfDeath-20x20.map";
			DonneesSimulation donneesInit = NewLecteurDonnees.lire(fichierName);
			Carte carteToDraw = donneesInit.getCarte();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
			
			
			ChefPompier chefEvol = new ChefPompierEvolue(carteToDraw, donneesInit);
			Simulateur simulateur = new Simulateur(gui, donneesInit, chefEvol, fichierName);



		}catch (FileNotFoundException e) {
            System.out.println("fichier inexistant");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format invalide" + e.getMessage());
        
        }

	}
}
