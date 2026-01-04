package vue;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.ControleJeu;
import model.*;
import ecoute.ModelEcoute;
import ecoute.notifications.CelluleNotification;

/**
 * Représente l'affichage d'une cellule sur la grille.
 */
public class VueCellule extends JPanel implements ModelEcoute {
    
    /** Modèle de la cellule */
    public Cellule celluleDeGrille;

    /** Propriétés de la bordure de la cellule */
    protected int borderTop = 0;
    protected int borderBottom = 0;
    protected int borderLeft = 0;
    protected int borderRight = 0;
    protected int borderValeur = 4;
    protected Color defaultColor = new Color(0, 255, 255);

    public VueCellule(Cellule cell) {
        super();
        this.setBackground(Color.BLACK);

        // Associer le modèle et écouter les mises à jour
        this.celluleDeGrille = cell;
        this.celluleDeGrille.addListening(this);

        this.setBorder(BorderFactory.createLineBorder(defaultColor, 1));
        this.setPreferredSize(new Dimension(50, 50));

        gestionEvenement();
    }

    /**
     * Réinitialise la cellule à sa couleur par défaut.
     * Utilisé lorsque le navire devient invisible.
     */
    public void setDefaultColor() {
        this.setBorder(BorderFactory.createLineBorder(defaultColor, 1));
    }

    /**
     * Attribue l'apparence d'un navire à une cellule.
     * Configure les bordures selon l'orientation et la position du navire.
     * 
     * @param horizontal Indique si le navire est horizontal ou vertical
     * @param orientation 1 si orienté vers la droite/haut, -1 si gauche/bas
     * @param dernier Indique si la cellule est la dernière du navire
     * @param premier Indique si la cellule est la première du navire
     */
    public void attribuerPourBateau(boolean horizontal, int orientation, boolean dernier, boolean premier) {
        // Réinitialiser toutes les bordures
        this.borderTop = 0;
        this.borderBottom = 0;
        this.borderLeft = 0;
        this.borderRight = 0;

        if (horizontal) {
            borderTop = borderValeur;
            borderBottom = borderValeur;
            if (orientation == 1) {
                if (premier) borderLeft = borderValeur;
                if (dernier) borderRight = borderValeur;
            } else {
                if (premier) borderRight = borderValeur;
                if (dernier) borderLeft = borderValeur;
            }
        } else {
            borderLeft = borderValeur;
            borderRight = borderValeur;
            if (orientation == 1) {
                if (premier) borderBottom = borderValeur;
                if (dernier) borderTop = borderValeur;
            } else {
                if (premier) borderTop = borderValeur;
                if (dernier) borderBottom = borderValeur;
            }
        }
    }

    /**
     * Affiche la bordure de la cellule lorsque le navire devient visible.
     */
    public void montrerBordure() {
        this.setBorder(BorderFactory.createMatteBorder(borderTop, borderLeft, borderBottom, borderRight,
                new Color(255, 255, 0)));
    }

    /**
     * Gère l'événement de clic sur la cellule.
     */
    public void cellAppuyee() {
        // Vérifier si la cellule a déjà été touchée
        if (celluleDeGrille.getEtat() != EtatCellule.VIDE) {
            return;
        }

        // Gérer uniquement les tirs sur la grille de l'adversaire
        Container parent = this.getParent();
        if (parent instanceof GrilleVue) {
            GrilleVue grilleVueparent = (GrilleVue) parent;

            if (!grilleVueparent.estHumainGrille()) {
                ControleJeu grandParentComJeu = (ControleJeu) grilleVueparent.getParent();
                Jeu jeu = grandParentComJeu.getJeu();

                // Vérifier si la partie a commencé
                if (!jeu.debutJeu()) {
                    new MessageDialog("Appuyez sur le bouton Play pour commencer la partie",
                            JOptionPane.INFORMATION_MESSAGE).showMessageDialog();
                    return;
                }

                // Gérer le tir du joueur humain
                jeu.setJoueurActuel(jeu.getJoueurHumain());
                jeu.tireGrilleAdversaire(celluleDeGrille.getPosX(), celluleDeGrille.getPosY());

                // Vérifier si la partie est terminée
                if (!jeu.estFini()) {
                    // L'adversaire effectue un tir
                    int[] pos = jeu.getJoueurAleatoire().tire();
                    jeu.tireGrilleAdversaire(pos[0], pos[1]);

                    if (jeu.estFini()) grandParentComJeu.finJeu();
                } else {
                    grandParentComJeu.finJeu();
                }
            }
        } else {
            System.out.println("DÉBOGAGE : LA CELLULE N'A PAS DE GRILLE PARENT");
        }
    }

    /**
     * Met à jour l'affichage de la cellule selon son état.
     */
    public void handleChangementEtat() {
        if (celluleDeGrille.getEtat() == EtatCellule.VIDE) {
            this.setBackground(Color.BLACK);
        } else if (celluleDeGrille.getEtat() == EtatCellule.TOUCHE) {
            this.setBackground(Color.RED);
        } else if (celluleDeGrille.getEtat() == EtatCellule.RATE) {
            this.setBackground(Color.GREEN);
        }
    }

    /**
     * Méthode appelée lors d'une mise à jour du modèle.
     */
    @Override
    public void modelMisAJour(Object source, Object notification) {
        if (notification instanceof CelluleNotification) {
            if (notification == CelluleNotification.ETAT_CHANGE) {
                handleChangementEtat();
            }
        } else {
            System.out.println("Notification non gérée pour VueCellule : " + notification);
        }
    }

    /**
     * Initialise la gestion des événements (clic sur la cellule).
     */
    public void gestionEvenement() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cellAppuyee();
            }
        });
    }
}
