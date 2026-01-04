package model;

import ecoute.AbstractModelEcoutable;
import ecoute.notifications.CelluleNotification;

/**
 * Classe représentant une cellule dans la grille d'un joueur.
 * Chaque cellule peut avoir un état (VIDE, TOUCHE, etc.) et éventuellement contenir un navire (Bateau).
 */
public class Cellule extends AbstractModelEcoutable {

    // Coordonnées de la cellule dans la grille
    private int posX;                // Coordonnée X de la cellule (position horizontale)
    private int posY;                // Coordonnée Y de la cellule (position verticale)

    // L'état de la cellule : vide, touché, etc.
    private EtatCellule etat;        // L'état actuel de la cellule (par exemple, VIDE, TOUCHE, COULE)

    // Un bateau peut être affecté à cette cellule, si cette cellule contient un bateau
    private Bateau bateauCellule;    // Référence au bateau affecté à cette cellule, ou null si aucun bateau

    /**
     * Constructeur de la cellule.
     * Initialise une cellule avec des coordonnées données (posX, posY), un état VIDE et aucun bateau.
     * @param posX Coordonnée X de la cellule
     * @param posY Coordonnée Y de la cellule
     */
    public Cellule(int posX, int posY) {
        this.posX = posX;            // Initialisation de la coordonnée X de la cellule
        this.posY = posY;            // Initialisation de la coordonnée Y de la cellule
        this.etat = EtatCellule.VIDE; // L'état initial de la cellule est VIDE
        this.bateauCellule = null;   // Aucun bateau n'est affecté à cette cellule au départ
    }

    /**
     * Constructeur par défaut.
     * Initialise la cellule à la position (0, 0) avec un état VIDE et sans bateau.
     */
    public Cellule() {
        this(0, 0);  // Appel au constructeur principal avec les coordonnées par défaut (0, 0)
    }

    // --------------------- GETTERS & SETTERS ---------------------

    /**
     * Retourne la coordonnée X de la cellule.
     * @return La coordonnée X de la cellule.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Définit la coordonnée X de la cellule.
     * @param posX La nouvelle coordonnée X de la cellule.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Retourne la coordonnée Y de la cellule.
     * @return La coordonnée Y de la cellule.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Définit la coordonnée Y de la cellule.
     * @param posY La nouvelle coordonnée Y de la cellule.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Retourne l'état actuel de la cellule.
     * L'état peut être VIDE, TOUCHE, COULE, etc.
     * @return L'état de la cellule (par exemple, VIDE, TOUCHE).
     */
    public EtatCellule getEtat() {
        return etat;
    }

    /**
     * Modifie l'état de la cellule et déclenche une notification pour notifier les observateurs.
     * @param etat Le nouvel état à affecter à la cellule (par exemple, VIDE, TOUCHE).
     */
    public void setEtat(EtatCellule etat) {
        this.etat = etat; // Mise à jour de l'état de la cellule
        this.fireChangement(CelluleNotification.ETAT_CHANGE); // Notifie les observateurs du changement d'état
    }

    /**
     * Vérifie si la cellule contient un bateau.
     * @return true si la cellule contient un bateau, false sinon.
     */
    public boolean aUnBateau() {
        return (this.bateauCellule != null); // Retourne true si un bateau est affecté à la cellule
    }

    /**
     * Retourne le bateau qui se trouve dans cette cellule.
     * @return Le bateau contenu dans cette cellule, ou null si aucun bateau n'est présent.
     */
    public Bateau getBateauCellule() {
        return bateauCellule;
    }

    /**
     * Définit un bateau à cette cellule.
     * Cette méthode permet d'affecter un bateau à la cellule. Si un bateau existe déjà dans la cellule, il sera remplacé.
     * @param bateauCellule Le bateau à affecter à cette cellule.
     */
    public void setBateauCellule(Bateau bateauCellule) {
        this.bateauCellule = bateauCellule; // Affecte un bateau à cette cellule
    }
}
