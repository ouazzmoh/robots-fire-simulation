package tests;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import simulation.*;
import io.*;
import donnees.*;
import chefpompier.*;

import gui.GUISimulator;

public class TestEvoluePerso {
	public static void main(String[] args) {
		try {
			String fichierName = "cartes/" + args[0];
			DonneesSimulation donneesInit = NewLecteurDonnees.lire(fichierName);
			Carte carteToDraw = donneesInit.getCarte();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
			
			
			ChefPompier chefEvol = new ChefPompierSimple(carteToDraw, donneesInit);
			Simulateur simulateur = new Simulateur(gui, donneesInit, chefEvol, fichierName);



		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier qui correspond åa " + args[0] + " invalide: " + e.getMessage());
        
        }

	}
}
