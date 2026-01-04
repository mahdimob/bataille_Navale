package vue;

import javax.swing.JPanel;
import java.util.ArrayList;
import model.*;
import ecoute.ModelEcoute;
import ecoute.notifications.BateauNotification;

/**
 * Classe représentant la vue d'un bateau.
 */
public class VueBateau extends JPanel implements ModelEcoute {
    /** Modèle du bateau */
    protected Bateau bateau;

    /** Tableau unidimensionnel des vues des cellules du bateau */
    protected VueCellule[] bateauCellVue;
    
    /** Grille parente du bateau */
    protected GrilleVue parenteGrilleVue;

    public VueBateau(Bateau bateau, GrilleVue parenteGrilleVue) {
        // Initialisation du modèle et écoute des événements
        this.bateau = bateau;
        this.bateau.addListening(this);

        this.parenteGrilleVue = parenteGrilleVue;

        creerBateau();

        if (this.bateau.estVisible()) {
            this.setVisibilitie(true);
        }
    }

    /**
     * Récupère la vue de cellule associée à une cellule du modèle du bateau.
     * @param cellule La cellule du modèle.
     * @return La vue de la cellule associée.
     */
    private VueCellule getVueCellulle(Cellule cellule) {
        int x = cellule.getPosX();
        int y = cellule.getPosY();

        return this.parenteGrilleVue.getBordureVue()[x][y];
    }

    /**
     * Crée l'apparence visuelle du bateau.
     */
    public void creerBateau() {
        int index = 0;
        ArrayList<Cellule> bateauCellulles = bateau.getCellulesBateau();
        boolean horizontalBateau = true;
        int orientation = 1; // 1 si le bateau est orienté vers le haut ou la droite, -1 sinon

        for (Cellule cell : bateauCellulles) {
            VueCellule vueCell = this.getVueCellulle(cell);

            if (index < (bateauCellulles.size() - 1)) {
                Cellule nextCellule = bateauCellulles.get(index + 1);

                if (index == 0) {
                    // Déterminer une seule fois si le bateau est horizontal ou vertical
                    // et son orientation (haut/bas ou gauche/droite)
                    horizontalBateau = nextCellule.getPosX() == cell.getPosX();
                    orientation = horizontalBateau ? (nextCellule.getPosY() > cell.getPosY() ? 1 : -1) : (nextCellule.getPosX() < cell.getPosX() ? 1 : -1);
                }
            }

            boolean first = (index == 0);
            boolean last = (index == (bateauCellulles.size() - 1));

            vueCell.attribuerPourBateau(horizontalBateau, orientation, last, first);
            index++;
        }
    }

    /**
     * Détruit l'affichage du bateau.
     */
    public void DetruireBateau() {
        this.setVisibilitie(false);
    }

    /**
     * Définit la visibilité du bateau.
     * @param show true pour afficher, false pour cacher.
     */
    public void setVisibilitie(boolean show) {
        ArrayList<Cellule> bateauCellulles = bateau.getCellulesBateau();
        if (show) {
            for (Cellule cell : bateauCellulles) {
                VueCellule vueCell = this.getVueCellulle(cell);
                vueCell.montrerBordure();
            }
        } else {
            for (Cellule cell : bateauCellulles) {
                VueCellule vueCell = this.getVueCellulle(cell);
                vueCell.setDefaultColor();
            }
        }
    }

    /**
     * Méthode de mise à jour lors d'une notification du modèle.
     */
    @Override
    public void modelMisAJour(Object source, Object notification) {
        //System.out.println("🔔 Notification reçue dans VueBateau: " + notification);
        
        if (notification instanceof BateauNotification) {
            if (BateauNotification.BATEAU_DETRUIT.equals(notification)) {
                //System.out.println("🔥 Le bateau est détruit, mise à jour de l'affichage...");
                this.DetruireBateau();
            } 
            else if (BateauNotification.BATEAU_VISIBILITEE_CHANGEE.equals(notification)) {
                //System.out.println("🚢 Mise à jour de la visibilité : " + this.bateau.estVisible());
                this.setVisibilitie(this.bateau.estVisible());
            }
        } else {
            //System.out.println("⚠️ Notification inconnue: " + notification);
        }
    }
}