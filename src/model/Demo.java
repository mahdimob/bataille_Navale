package model;

public class Demo {
     public static void main(String[] args) {
        Grille grille1 = new Grille(10,10);
        Grille grille2 = new Grille(10,10);
        JoueurHumain humain = new JoueurHumain(grille1, "Joueur humain");
        JoueurAleatoire aleatoire = new JoueurAleatoire (grille2, grille1);
        Jeu jeu = new Jeu(humain, aleatoire);
        humain.ajoutBateauAleatoire();
        aleatoire.ajoutBateauAleatoire();
        jeu.jouer();


	 

      
        
    }
    
}
