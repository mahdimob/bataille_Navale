package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

/**
 * Classe représentant un joueur qui effectue des tirs de manière aléatoire dans le jeu de bataille navale.
 * Ce joueur dispose d'une grille de jeu, d'une flotte de bateaux et d'un nom.
 * Il choisit ses tirs sans stratégie particulière, en se basant uniquement sur des choix aléatoires.
 */
public class JoueurAleatoire extends AbstractJoueur {

    private Random aleatoire;  // Générateur de nombres aléatoires
    private ArrayList<int[]> positionsNonTirees;  // Liste des cases où l'IA peut encore tirer

    /**
     * Constructeur de la classe JoueurAleatoire.
     * Initialise le joueur avec une grille de jeu et une liste des cases où il peut tirer.
     * @param grille La grille du joueur (celle où il place ses navires).
     * @param grilleAdversaire La grille de l'adversaire sur laquelle il tire.
     */
    public JoueurAleatoire(Grille grille, Grille grilleAdversaire) {
        super(grille, "Aleatoire");  
        this.aleatoire = new Random();  
        this.positionsNonTirees = (grille != null) ? grille.getToutesPositions() : new ArrayList<>(); // Récupère toutes les positions possibles
    }

    /**
     * Constructeur sans arguments utilisé principalement pour des tests ou des initialisations sans grille.
     */
    public JoueurAleatoire() {
        this(null, null);
    }

    
    @Override
public void BateauVisible() {
    // Parcours chaque bateau de la flotte et le rend visible s'il est coulé
    for (Bateau bateau : this.flotte) {
        if (bateau.toucheCellules()) {
            bateau.setVisible(true);
        }
    }
}

    
    
    
    
    /**
     * Méthode permettant à l'IA de tirer sur une case aléatoire encore disponible.
     * @return Un tableau contenant les coordonnées [x, y] de la case ciblée.
     */
    
     @Override
    public int[] tire() {
        // Vérifier si des positions sont encore disponibles
        if (positionsNonTirees.isEmpty()) {
            throw new IllegalStateException("Aucune case disponible pour tirer.");
        }

        // Sélection aléatoire d'une case parmi celles disponibles
        int index = aleatoire.nextInt(positionsNonTirees.size());
        int[] positionTir = positionsNonTirees.get(index);

        // Supprimer la position jouée de la liste des positions disponibles
        positionsNonTirees.remove(index);

        System.out.println("🎯 Le JoueurAleatoire tire sur la case : (" + positionTir[0] + ", " + positionTir[1] + ")");
        return positionTir;
    }

    /**
     * Réinitialise la liste des positions disponibles pour tirer.
     * Utile lorsqu'une nouvelle partie commence.
     */
    public void reinitialiserPositionsNonTirees() {
        if (this.getGrille() != null) {
            this.positionsNonTirees = this.getGrille().getToutesPositions();
        } else {
            this.positionsNonTirees = new ArrayList<>();
        }
    }
}
