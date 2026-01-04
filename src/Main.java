
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import model.*;
import vue.*;

public class Main {
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
	        
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		new PhotoEcran();
		// Creating human player grid object
		Grille grille = new Grille(10,10);

		// Creating random player grid object
		Grille grilleAleatoire = new Grille(10,10);

		// Creating human player and random player object
		JoueurHumain humain = new JoueurHumain (grille, "Moi");
		JoueurAleatoire aleatoire = new JoueurAleatoire (grilleAleatoire, grille);

		// Create the game object and launch GUI
		Jeu jeu = new Jeu(humain, aleatoire);
	    new FenetrePrincipale(jeu);

	}

}
