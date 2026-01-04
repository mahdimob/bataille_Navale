package model;

import ecoute.AbstractModelEcoutable;
import ecoute.notifications.JeuNotification;
import java.util.Iterator;
/**
 * Cette classe représente le jeu de bataille navale.
 * Elle contient toutes les méthodes nécessaires pour gérer la logique du jeu et faire tourner les différentes étapes de la partie.
 */
public class Jeu extends AbstractModelEcoutable{ 
 private JoueurHumain joueurHumain;
 private JoueurAleatoire joueurAleatoire;
 private AbstractJoueur joueurActuel;
 private boolean debut = false;

 /**
  * Constructeur de la classe Jeu.
  * Initialise les joueurs du jeu, et définit le joueur actuel à l'humain.
  * @param joueurHumain Le joueur humain
  * @param joueurAleatoire Le joueur aléatoire
  */
 public Jeu(JoueurHumain joueurHumain, JoueurAleatoire joueurAleatoire) {
     this.joueurHumain = joueurHumain;
     this.joueurAleatoire = joueurAleatoire;
     this.joueurActuel = joueurHumain; // Le joueur humain commence la partie
 }
 
 //.................. Getters et setters pour les joueurs.....................

 public JoueurHumain getJoueurHumain() {
     return joueurHumain;
 }

 public void setJoueurHumain(JoueurHumain joueurHumain) {
     this.joueurHumain = joueurHumain;
 }

 public JoueurAleatoire getJoueurAleatoire() {
     return joueurAleatoire;
 }

 public void setJoueurAleatoire(JoueurAleatoire joueurAleatoire) {
     this.joueurAleatoire = joueurAleatoire;
 }

 public AbstractJoueur getJoueurActuel() {
     return joueurActuel;
 }

 public void setJoueurActuel(AbstractJoueur joueurActuel) {
     this.joueurActuel = joueurActuel;
 }

 public boolean debutJeu() {
     return debut;
 }

 public void setDebut(boolean debut) {
     this.debut = debut;
 }

 /**
  * Cette méthode détermine le gagnant du jeu en vérifiant si l'un des joueurs a perdu.
  * Si un joueur a perdu, l'autre est le gagnant.
  * @return Le joueur gagnant, soit le joueur humain, soit le joueur aléatoire.
  */
 public AbstractJoueur getVainqueur() {
     // Si le joueur aléatoire a perdu, le joueur humain est le gagnant
     if (this.joueurAleatoire.aPerdu()) {
         return this.joueurHumain;
     }
     // Si le joueur humain a perdu, le joueur aléatoire est le gagnant
     if (this.joueurHumain.aPerdu()) {
         return this.joueurAleatoire;
     }
     return null; // Le jeu n'est pas encore terminé
 }

 /**
  * Vérifie si la partie est terminée en fonction de l'existence d'un vainqueur.
  * @return true si le jeu est terminé (un vainqueur a été trouvé), false sinon.
  */
 public boolean estFini() {
     return (getVainqueur() != null);
 }
 
 /**
  * Cette méthode permet à un joueur de tirer sur la grille de l'adversaire.
  * Elle prend en paramètre les coordonnées (x, y) où le joueur souhaite tirer.
  * En fonction du tir, la cellule visée est mise à jour pour indiquer si elle a été touchée ou ratée.
  * Ensuite, le tour du joueur est passé à l'adversaire.
  * @param x La coordonnée x de la cellule visée
  * @param y La coordonnée y de la cellule visée
  */
  public void tireGrilleAdversaire(int x, int y) {
    // Déterminer l'adversaire en fonction du joueur actuel
    AbstractJoueur adversaire = (this.joueurActuel == this.joueurHumain) ? this.joueurAleatoire : this.joueurHumain;
    
    // Récupérer la cellule de l'adversaire à la position donnée
    Cellule celluleAdversaire = adversaire.getGrille().getCellulePosition(x, y);

    // Vérifier si la case a déjà été touchée ou ratée
    if (celluleAdversaire.getEtat() == EtatCellule.TOUCHE || celluleAdversaire.getEtat() == EtatCellule.RATE) {
        System.out.println("Erreur : Cette case a déjà été sélectionnée. Veuillez choisir une autre case.");
        return;  // On sort de la méthode si la case a déjà été touchée ou ratée
    }

    // Si la cellule contient un bateau, elle est marquée comme "touchée", sinon "ratée"
    if (celluleAdversaire.aUnBateau()) {
        celluleAdversaire.setEtat(EtatCellule.TOUCHE);
        
    } else {
        celluleAdversaire.setEtat(EtatCellule.RATE);
        
    }

    // Vérifier si le bateau est détruit et le rendre visible
    if (celluleAdversaire.aUnBateau()) {
        Bateau bat = celluleAdversaire.getBateauCellule();
        if (bat.toucheCellules()) {  
            System.out.println("🚢 Le bateau de taille " + bat.getTaille() + " a été coulé !");
            
            bat.setVisible(true);  
            bat.setDetruit(true);  
            
            System.out.println("🔎 Le bateau est maintenant visible.");
        }
    }

    // Passer le tour à l'adversaire
    this.joueurActuel = adversaire;
}


 /**
  * Crée la flotte du joueur humain de manière aléatoire.
  * Ajoute des bateaux à la grille du joueur humain et les rend visibles.
  */
 public void humainAjoutBateauAleatoire() {
     this.joueurHumain.ajoutBateauAleatoire();
     this.joueurHumain.BateauVisible();
     this.fireChangement(JeuNotification.HUMAIN_FLOTTE_CREE); // Notification pour l'ajout des bateaux
 }

 /**
  * Crée la flotte du joueur aléatoire de manière aléatoire.
  * Ajoute des bateaux à la grille du joueur aléatoire.
  */
 public void aleatoireAjoutBateau() {
     this.joueurAleatoire.ajoutBateauAleatoire();
     this.fireChangement(JeuNotification.ALEATOIRE_FLOTTE_CREE); // Notification pour l'ajout des bateaux
 }

 /**
  * Démarre la partie en créant la flotte du joueur aléatoire et en changeant l'état de début de jeu.
  * La partie commence réellement lorsque cette méthode est appelée.
  */
 public void debutPartie() {
     this.aleatoireAjoutBateau(); // Crée la flotte du joueur aléatoire
     this.setDebut(true); // Le jeu commence
     this.fireChangement(JeuNotification.PARTIE_COMMENCEE); // Notification pour le début de la partie
 }

 /**
  * Joue une partie complète de bataille navale entre les deux joueurs. 
  * Tant que la partie n'est pas terminée, chaque joueur joue à son tour, 
  * tirant sur la grille de l'adversaire. Le jeu se termine dès qu'un vainqueur est trouvé.
  */
 public void jouer() {
     // Tant que le jeu n'est pas terminé
     while (!estFini()) {
         // Déterminer l'adversaire en fonction du joueur actuel
         AbstractJoueur adversaire = (this.joueurActuel == this.joueurHumain) ? this.joueurAleatoire : this.joueurHumain;
         System.out.println(joueurActuel.getNom() + " joue :");
         System.out.println("Grille de : " + adversaire.getNom());
         
         // Afficher la grille de l'adversaire
         adversaire.grille.afficher();
         
         // Demander au joueur actuel de tirer
         int[] pos = joueurActuel.tire();
         
         // Appliquer le tir sur la grille de l'adversaire
         this.tireGrilleAdversaire(pos[0], pos[1]);
         
         // Vérifier le vainqueur après chaque tir
         this.getVainqueur();
     }
     
     // Afficher le gagnant une fois la partie terminée
     System.out.println("Le joueur " + getVainqueur().getNom() + " a gagné !");
 }
}
