package vue;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import controleur.ControleJeu;
import model.Jeu;

/** 
 * Fenêtre principale de l'interface graphique du jeu.
 * Cette classe initialise et affiche la fenêtre du jeu.
 */
public class FenetrePrincipale extends JFrame {
    private static final long serialVersionUID = 7376825297884956163L;
    
    /** Dimensions de l'écran */
    private java.awt.Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
    private int largeurEcran = (tailleEcran.width * 2 / 3);
    private int hauteurEcran = (tailleEcran.height * 2 / 3);

    /**
     * Constructeur de la fenêtre principale.
     * Initialise l'interface graphique et affiche la fenêtre.
     * 
     * @param jeu Le modèle du jeu
     */
    public FenetrePrincipale(Jeu jeu) {
        // Initialisation du contrôleur du jeu
        ControleJeu controleJeu = new ControleJeu(jeu);
        
        // Configuration de la fenêtre
        this.setLayout(new BorderLayout());
        this.add(controleJeu, BorderLayout.CENTER);
        this.setSize(largeurEcran, hauteurEcran);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
