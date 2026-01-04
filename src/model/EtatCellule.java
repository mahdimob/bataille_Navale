package model;

/**
 * Enumération représentant l'état d'une cellule dans la grille du joueur.
 */

public enum EtatCellule {
    VIDE,   // La cellule est vide
    TOUCHE, // La cellule contient un bateau touché
    RATE    // La cellule a été visée mais ne contient pas de bateau
}
