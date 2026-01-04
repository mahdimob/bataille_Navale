package vue;

import java.awt.*;
import javax.swing.*;
import model.Grille;
import ecoute.ModelEcoute;
import ecoute.notifications.GrilleNotification;

/**
 * Classe représentant l'affichage d'une grille.
 */
public class GrilleVue extends JPanel implements ModelEcoute {
    /** Dimensions de la grille */
    protected int lignes = 10;
    protected int colonnes = 10;

    /** Tableau des cellules affichées */
    protected VueCellule[][] bordureVue;
    protected Grille grilleModel;

    /** Gestionnaire de mise en page */
    protected GridBagConstraints gbc;

    /** Libellé de la grille */
    protected String grilleLabel = "Joueur";
    protected boolean humainGrille;

    public GrilleVue(Grille grilleModel, String grilleLabel, boolean estHumainGrille, boolean humainGrille) {
        super();
        this.setBackground(Color.BLACK);
    
        // Initialisation du modèle et ajout du listener
        this.grilleModel = grilleModel;
        this.grilleModel.addListening(this);
    
        this.grilleLabel = grilleLabel;
        this.humainGrille = humainGrille;
    
        this.lignes = this.grilleModel.getLignes();
        this.colonnes = this.grilleModel.getColonnes();
        this.bordureVue = new VueCellule[this.lignes][this.colonnes];
       
        // Définition de la mise en page
        this.setLayout(new GridBagLayout());
    
        // Création d'un objet de contraintes pour le layout
        this.gbc = new GridBagConstraints();
    
        // Hauteur naturelle, largeur maximale
        this.gbc.fill = GridBagConstraints.BOTH;
    
        // Positionnement du libellé de la grille
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.gridwidth = this.colonnes;  // Étendre sur toute la largeur de la grille
        this.gbc.insets = new Insets(5, 5, 5, 5);  // Espacement autour du libellé
    
        // Création et ajout du libellé de la grille
        JLabel grilleLabelField = new JLabel(grilleLabel, SwingConstants.CENTER);
        grilleLabelField.setFont(new Font("Arial", Font.BOLD, 16)); // Définition de la police
        grilleLabelField.setForeground(Color.WHITE);  // Couleur du texte
        this.add(grilleLabelField, this.gbc);
    
        // Réinitialisation de la largeur de la grille pour les cellules
        this.gbc.gridwidth = 1;
        this.gbc.ipadx = 30;
        this.gbc.ipady = 30;
        this.gbc.weightx = 0;
        this.gbc.weighty = 0;
        
        // Création de la grille
        creerGrille();
    }
    
    /**
     * Vérifie si la grille appartient au joueur humain.
     * @return true si c'est la grille du joueur humain, sinon false.
     */
    public boolean estHumainGrille() {
        return humainGrille;
    }

    /**
     * Définit si la grille appartient au joueur humain.
     * @param humainGrille true si la grille appartient à un humain, sinon false.
     */
    public void setHumainGrille(boolean humainGrille) {
        this.humainGrille = humainGrille;
    }

    /**
     * Génère la grille avec ses cellules.
     */
    public void creerGrille() {
        // Ajout des cellules pour former la grille
        for (int i = 0; i < this.lignes; i++) {
            for (int j = 0; j < this.colonnes; j++) {
                this.gbc.gridx = j;
                this.gbc.gridy = i + 1;

                this.bordureVue[i][j] = new VueCellule(this.grilleModel.getTab()[i][j]);
                this.add(this.bordureVue[i][j], this.gbc);
            }
        }
    }

    /**
     * Retourne la grille de cellules visibles.
     * @return Tableau des cellules visibles.
     */
    public VueCellule[][] getBordureVue() {
        return bordureVue;
    }

    /**
     * Modifie la grille de cellules visibles.
     * @param bordureVue Tableau des cellules visibles.
     */
    public void setBordureVue(VueCellule[][] bordureVue) {
        this.bordureVue = bordureVue;
    }

    /**
     * Gère les mises à jour de la grille en fonction des notifications du modèle.
     */
    @Override
    public void modelMisAJour(Object source, Object notification) {
        if (notification instanceof GrilleNotification) {
            System.out.println("Affichage erreurs");
        } else {
            System.out.println("Notification non gérée pour GrilleVue: " + notification);
        }
    }
}
