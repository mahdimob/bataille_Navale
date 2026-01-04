package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import ecoute.AbstractModelEcoutable;

/**
 * Cette classe représente la grille d'un joueur dans le jeu, qui contient toutes les cellules du jeu.
 * Chaque cellule peut contenir un bateau ou être dans un état particulier (touchée, ratée, vide).
 */
public class Grille extends AbstractModelEcoutable {

    // Nombre de lignes et de colonnes de la grille
    private int lignes;               // Nombre de lignes de la grille
    private int colonnes;             // Nombre de colonnes de la grille

    // Tableau à deux dimensions représentant la grille de cellules
    private Cellule[][] tab;          // Tableau représentant la grille de cellules

    // Liste contenant toutes les positions possibles de la grille
    private ArrayList<int[]> toutesPositions = new ArrayList<>();

    /**
     * Constructeur de la classe permettant d'initialiser la grille.
     * Cette méthode initialise la grille avec les dimensions spécifiées, crée chaque cellule,
     * et initialise la liste des positions possibles pour chaque cellule de la grille.
     * @param lignes Nombre de lignes dans la grille.
     * @param colonnes Nombre de colonnes dans la grille.
     */
    public Grille(int lignes, int colonnes) {
        this.lignes = lignes;           // Initialisation du nombre de lignes
        this.colonnes = colonnes;       // Initialisation du nombre de colonnes
        this.tab = new Cellule[lignes][colonnes];  // Création du tableau de cellules

        // Initialisation des cellules et ajout des positions possibles
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                toutesPositions.add(new int[]{i, j});  // Ajout de chaque position de cellule à la liste
                this.tab[i][j] = new Cellule(i, j);    // Initialisation de chaque cellule avec ses coordonnées
            }
        }
    }

    // --------------------- GETTERS & SETTERS ---------------------

    /**
     * Retourne la liste de toutes les positions possibles de la grille.
     * @return Liste des positions sous forme de tableau [x, y].
     */
    public ArrayList<int[]> getToutesPositions() {
        return toutesPositions;
    }

    /**
     * Définit la liste de toutes les positions possibles de la grille.
     * @param toutesPositions Nouvelle liste des positions à définir.
     */
    public void setToutesPositions(ArrayList<int[]> toutesPositions) {
        this.toutesPositions = toutesPositions;
    }

    /**
     * Retourne le nombre de lignes de la grille.
     * @return Nombre de lignes de la grille.
     */
    public int getLignes() {
        return lignes;
    }

    /**
     * Retourne le nombre de colonnes de la grille.
     * @return Nombre de colonnes de la grille.
     */
    public int getColonnes() {
        return colonnes;
    }

    /**
     * Retourne le tableau des cellules représentant la grille.
     * @return Tableau bidimensionnel des cellules.
     */
    public Cellule[][] getTab() {
        return tab;
    }

    /**
     * Définit le tableau des cellules représentant la grille.
     * @param tab Nouveau tableau de cellules à définir.
     */
    public void setTab(Cellule[][] tab) {
        this.tab = tab;
    }

    /**
     * Cette méthode renvoie la cellule correspondant à une position spécifique dans la grille.
     * Si la position est hors limites, la méthode retourne null.
     * @param x Coordonnée X (ligne) de la cellule recherchée.
     * @param y Coordonnée Y (colonne) de la cellule recherchée.
     * @return La cellule à la position spécifiée, ou null si hors limites.
     */
    public Cellule getCellulePosition(int x, int y) {
        // Vérifie que les coordonnées sont dans les limites de la grille
        if (x >= 0 && x < lignes && y >= 0 && y < colonnes) {
            return this.tab[x][y];  // Retourne la cellule à la position spécifiée
        }
        return null;  // Retourne null si la position est hors limites
    }

    /**
     * Cette méthode permet d'afficher l'état actuel de la grille.
     * Elle affiche les indices des colonnes et lignes, ainsi que l'état de chaque cellule.
     * Les cellules touchées sont affichées avec un "X", les cellules ratées avec un "!", et les autres sont vides.
     */
    public void afficher() {
        // Affichage des indices des colonnes
        System.out.print("  ");
        for (int i = 0; i < colonnes; i++) {
            System.out.print(i + " ");  // Affiche les indices de colonnes
        }
        System.out.println();

        // Affichage des lignes de la grille avec leurs indices
        for (int i = 0; i < lignes; i++) {
            System.out.print((char) ('A' + i) + " ");  // Affiche l'indice de ligne (A, B, C, ...)
            for (int j = 0; j < colonnes; j++) {
                // Affichage de l'état des cellules
                if (this.tab[i][j].getEtat() == EtatCellule.TOUCHE) {
                    System.out.print("X ");  // Affiche "X" pour une cellule touchée
                } else if (this.tab[i][j].getEtat() == EtatCellule.RATE) {
                    System.out.print("! ");  // Affiche "!" pour une cellule ratée
                } else {
                    System.out.print("  ");  // Affiche un espace pour une cellule vide
                }
            }
            System.out.println();  // Passe à la ligne suivante
        }
    }

    /**
     * Cette méthode retourne une liste aléatoire de toutes les positions de la grille de jeu.
     * Elle mélange les positions possibles (x, y) de manière aléatoire.
     * @return Une liste aléatoire de positions de la grille.
     */
    public List<int[]> grillePosition() {
        List<int[]> positions = new ArrayList<>(toutesPositions);  // Crée une copie de la liste des positions
        Collections.shuffle(positions);  // Mélange la liste des positions de manière aléatoire
        return positions;  // Retourne la liste mélangée des positions
    }
}
