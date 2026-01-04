package model;

import ecoute.AbstractModelEcoutable;
import java.util.ArrayList;

/**
 * Classe représentant un joueur du jeu de bataille navale.
 * Un joueur possède une grille de jeu, une flotte de bateaux et un nom.
 */
public abstract class AbstractJoueur extends AbstractModelEcoutable {
    protected int[] tailleBateau = {5, 4, 3, 3, 2};
    protected Grille grille;
    protected String nom;
    protected ArrayList<Bateau> flotte;

    /**
     * Constructeur principal.
     * Initialise le joueur avec un nom, une grille de jeu et une flotte vide.
     * @param grille la grille du joueur
     * @param nom Le nom du joueur
     */
    public AbstractJoueur(Grille grille, String nom) {
        this.grille = grille;
        this.nom = nom;
        this.flotte = new ArrayList<>();
    }

    /**
     * Constructeur sans argument.
     */
    public AbstractJoueur() {
        this.flotte = new ArrayList<>();
    }

    // Getters et Setters
    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Bateau> getFlotte() {
        return flotte;
    }

    public void setFlotte(ArrayList<Bateau> flotte) {
        this.flotte = flotte;
    }

    /**
     * Détruit la flotte du joueur.
     */
    public void detruireFlotte() {
        for (Bateau bateau : flotte) {
            bateau.detruit();
        }
        flotte.clear();
    }

    /**
     * Vérifie si l'emplacement est libre pour placer un bateau.
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @param bateau Le bateau à placer
     * @param estVertical Orientation du bateau
     * @return true si l'emplacement est libre, sinon false
     */
    public boolean placeLibre(int x, int y, Bateau bateau, boolean estVertical) {
        if (!estVertical) {
            if (y + bateau.getTaille() > grille.getColonnes()) return false;
            for (int i = y; i < y + bateau.getTaille(); i++) {
                if (grille.getTab()[x][i].aUnBateau()) return false;
            }
        } else {
            if (x + bateau.getTaille() > grille.getLignes()) return false;
            for (int j = x; j < x + bateau.getTaille(); j++) {
                if (grille.getTab()[j][y].aUnBateau()) return false;
            }
        }
        return true;
    }

    /**
     * Ajoute un bateau à la grille si l'emplacement est disponible.
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @param bateau Le bateau à ajouter
     * @param estVertical Orientation du bateau
     * @return true si le bateau a été ajouté, sinon false
     */
    public boolean ajoutBateau(int x, int y, Bateau bateau, boolean estVertical) {
        if (!placeLibre(x, y, bateau, estVertical)) return false;

        if (!estVertical) {
            for (int i = y; i < y + bateau.getTaille(); i++) {
                grille.getTab()[x][i].setBateauCellule(bateau);
                bateau.getCellulesBateau().add(grille.getTab()[x][i]);
            }
        } else {
            for (int j = x; j < x + bateau.getTaille(); j++) {
                grille.getTab()[j][y].setBateauCellule(bateau);
                bateau.getCellulesBateau().add(grille.getTab()[j][y]);
            }
        }
        flotte.add(bateau);
        return true;
    }

    /**
     * Vérifie si tous les bateaux du joueur ont été coulés.
     * @return true si le joueur a perdu, sinon false
     */
    public boolean aPerdu() {
        for (Bateau bateau : flotte) {
            if (!bateau.toucheCellules()) return false;
        }
        return true;
    }

    /**
     * Ajoute des bateaux aléatoirement sur la grille.
     */
    public void ajoutBateauAleatoire() {
        this.detruireFlotte();
        for (int taille : tailleBateau) {
            for (int[] position : grille.grillePosition()) {
                //Bateau bateau = new Bateau(taille);
                boolean ajoute = this.ajoutBateau(position[0], position[1], new Bateau(taille), true);
                if (!ajoute) {
                    ajoute = this.ajoutBateau(position[0], position[1], new Bateau(taille), false);
                }
                if (ajoute) break;
            }
        }
    }

    /**
     * Méthode abstraite permettant au joueur de choisir la position de son tir.
     * @return Tableau contenant les coordonnées du tir
     */
    public abstract int[] tire();

    /**
     * Méthode abstraite permettant de rendre un bateau visible.
     */
    public abstract void BateauVisible();

    @Override
    public String toString() {
        return "AbstractJoueur [nom :" + nom + "]";
    }
}
