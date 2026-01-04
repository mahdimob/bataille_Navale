package model;

import java.util.ArrayList;

import ecoute.AbstractModelEcoutable;
import ecoute.notifications.BateauNotification;

/**
 * Classe représentant un navire dans un jeu (par exemple, un jeu de bataille navale).
 */
public class Bateau extends AbstractModelEcoutable {

    // Attributs de la classe
    private ArrayList<Cellule> cellulesBateau;  // Liste des cellules occupées par le navire
    private boolean visible;                    // Indique si le navire est visible ou non
    private int taille;                         // Taille du navire (en termes de cellules)
    protected boolean estDetruit = false;       // Indique si le navire a été détruit
    
    /**
     * Constructeur pour créer un navire avec une taille spécifiée. 
     * Initialise la liste de cellules vide et l'état de visibilité à `false`.
     * @param taille : la taille du navire (nombre de cellules occupées).
     */
    public Bateau(int taille) {
        this.cellulesBateau = new ArrayList<>();
        this.visible = false;
        this.taille = taille;
    }

    /**
     * Constructeur pour créer un navire avec une taille et une visibilité spécifiée.
     * @param taille : la taille du navire (nombre de cellules occupées).
     * @param visible : état de visibilité du navire (visible ou non).
     */
    public Bateau(int taille, boolean visible) {
        this(taille);  // Appel au constructeur principal
        this.visible = visible;
    }

    // Getters et Setters

    /**
     * Retourne la liste des cellules occupées par le navire.
     * @return Liste des cellules du navire.
     */
    public ArrayList<Cellule> getCellulesBateau() {
        return cellulesBateau;
    }

    /**
     * Définit la liste des cellules occupées par le navire.
     * @param cellulesBateau : liste des cellules à affecter au navire.
     */
    public void setCellulesBateau(ArrayList<Cellule> cellulesBateau) {
        this.cellulesBateau = cellulesBateau;
    }

    /**
     * Retourne l'état de visibilité du navire.
     * @return `true` si le navire est visible, `false` sinon.
     */
    public boolean estVisible() {
        return visible;
    }

    /**
     * Modifie l'état de visibilité du navire et notifie du changement.
     * @param visible : état de visibilité à définir.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
        this.fireChangement(BateauNotification.BATEAU_VISIBILITEE_CHANGEE);  // Notification du changement de visibilité
    }

    /**
     * Retourne la taille du navire.
     * @return la taille du navire (nombre de cellules).
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Modifie la taille du navire.
     * @param taille : nouvelle taille du navire.
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Détruit le navire, libérant les cellules occupées et mettant son état à détruit.
     */
    public void detruit() {
        // Libère chaque cellule associée à ce navire
        for (Cellule cellule : cellulesBateau) {
            cellule.setBateauCellule(null);  // Détache ce navire de la cellule
        }
        this.estDetruit = true;
        this.fireChangement(BateauNotification.BATEAU_DETRUIT);  // Notification du changement d'état du navire
    }

    /**
     * Vérifie si toutes les cellules du navire ont été touchées.
     * Si c'est le cas, on rend le navire visible immédiatement.
     * @return `true` si toutes les cellules sont touchées, `false` sinon.
     */
    public boolean toucheCellules() {
        for (Cellule cellule : cellulesBateau) {
            if (!cellule.getEtat().equals(EtatCellule.TOUCHE)) {
                return false;  // Si une cellule n'est pas touchée, retourne false immédiatement
            }
        }
        
        // 🚀 Toutes les cellules sont touchées -> rendre immédiatement visible
        if (!this.estVisible()) {
            this.setVisible(true); // Active immédiatement la visibilité
        }
        
        return true;  
    }

    /**
     * Retourne l'état du navire, s'il est détruit ou non.
     * @return `true` si le navire est détruit, `false` sinon.
     */
    public boolean estDetruit() {
        return estDetruit;
    }

    /**
     * Modifie l'état du navire, spécifiant s'il est détruit ou non.
     * @param estDetruit : état à définir (true = détruit, false = non détruit).
     */
    public void setDetruit(boolean estDetruit) {
        this.estDetruit = estDetruit;
    }
}
