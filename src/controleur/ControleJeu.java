package controleur;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.*;
import vue.*;
import ecoute.ModelEcoute;
import ecoute.notifications.JeuNotification;

/**
 * Classe représentant la vue principale du jeu de bataille navale.
 * Elle gère l'affichage du tableau de jeu, ainsi que les boutons de contrôle permettant de démarrer la partie et de placer les navires.
 */
public class ControleJeu extends JPanel implements ModelEcoute {

    /** Contraintes de la mise en page GridBag */
    protected GridBagConstraints gbc;

    /** Modèle du jeu associé à cette vue */
    protected Jeu jeu;

    /** Vue de la grille du joueur humain */
    protected GrilleVue humainGrilleVue;

    /** Vue de la grille du joueur aléatoire */
    protected GrilleVue aleatoireGrilleVue;

    /** Panneau contenant les boutons de contrôle */
    protected JPanel buttonPanel;
    protected JButton aleatoireButton; // Bouton pour placer les navires du joueur humain de manière aléatoire
    protected JButton playButton; // Bouton pour démarrer la partie
    
    public Jeu getJeu() {
        return this.jeu;
    }

    /**
     * Constructeur de la classe ControleJeu.
     * Initialise les éléments graphiques de la vue et les événements associés.
     * 
     * @param jeu Le modèle du jeu associé à cette vue.
     */
    public ControleJeu(Jeu jeu) {
        super();
        this.setBackground(Color.BLACK);

        // Association du modèle au contrôleur et ajout de l'écouteur
        this.jeu = jeu;
        this.jeu.addListening(this);

        // Création des vues des grilles
        humainGrilleVue = new GrilleVue(jeu.getJoueurHumain().getGrille(), "Ma Grille", true, true);
        aleatoireGrilleVue = new GrilleVue(jeu.getJoueurAleatoire().getGrille(), "Grille du Joueur Aléatoire", false, false);

        // Définition du layout de la vue
        this.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.insets = new Insets(10, 0, 0, 0);

        // Ajout de la grille du joueur humain à la vue
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.weightx = 1;
        this.add(humainGrilleVue, this.gbc);

        // Ajout de la grille du joueur aléatoire à la vue
        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.weightx = 1;
        this.add(aleatoireGrilleVue, this.gbc);

        // Configuration de l'interface des boutons de contrôle
        this.gbc.insets = new Insets(35, 0, 0, 0);
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        
        // Création et configuration du bouton pour placer les bateaux aléatoirement
        aleatoireButton = new JButton("Placer mes Bateaux 🚢🚢 ");
        aleatoireButton.setPreferredSize(new Dimension(250, 40));
        aleatoireButton.setFont(new Font("Arial", Font.BOLD, 17));
        aleatoireButton.setSize(400, 100);
        
        // Création et configuration du bouton pour démarrer la partie
        playButton = new JButton("Jouer");
        playButton.setPreferredSize(new Dimension(250, 40));
        playButton.setFont(new Font("Arial", Font.BOLD, 17));
        playButton.setSize(400, 100);

        buttonPanel.add(aleatoireButton);
        buttonPanel.add(playButton);

        // Positionnement du panneau de boutons en bas au centre de la fenêtre
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.gbc.gridwidth = 2; // Le panneau occupe les 2 colonnes
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.weighty = 0.0; // Pas besoin d'espace vertical supplémentaire
        this.gbc.anchor = GridBagConstraints.PAGE_END; // Aligner le panneau en bas
        this.add(buttonPanel, this.gbc);

        // Liaison des événements
        gererEvenement();
    }

    /**
     * Crée la flotte du joueur humain en ajoutant les navires de manière aléatoire.
     */
    public void creerHumainFlotte() {
        if (jeu.debutJeu()) {
            // Message d'avertissement si la partie est déjà en cours
            new MessageDialog("Vous ne pouvez pas placer vos Bateaux en pleine partie", JOptionPane.WARNING_MESSAGE).showMessageDialog();
        } else {
            // Ajoute la flotte aléatoirement
            this.jeu.humainAjoutBateauAleatoire();
        }
    }

    /**
     * Gère la fin de la partie en affichant un message de félicitations et en fermant l'application.
     */
    public void finJeu() {
        if (jeu.estFini()) {
            // Déterminer le gagnant de la partie
            AbstractJoueur gagnant = jeu.getVainqueur();
            String finMessage = "";

            // Message spécifique selon le gagnant
            if (gagnant instanceof JoueurHumain) {
                finMessage = "Félicitations ! Tu as mené cette bataille avec talent et stratégie. Une victoire bien méritée !";
            } else {
                finMessage = "Bien joué ! La revanche sera pour bientôt.";
            }

            // Afficher le message de fin et quitter
            int resultat = new MessageDialog(finMessage, JOptionPane.INFORMATION_MESSAGE)
                    .showConfirmationMessageDialog("Ok", "Quitter");

            if (resultat == JOptionPane.OK_OPTION) {
                System.exit(0);
            } else {
                System.exit(0);
            }
        }
    }

    /**
     * Gère le clic sur le bouton de démarrage de la partie.
     * Démarre la partie si elle n'est pas encore commencée.
     */
    public void debutJeuClique() {
        if (jeu.debutJeu()) {
            new MessageDialog("La partie est déjà en cours", JOptionPane.INFORMATION_MESSAGE).showMessageDialog();
        } else {
            // Démarre la partie
            this.jeu.debutPartie();
        }
    }

    /**
     * Gère la notification de création de la flotte du joueur humain.
     * Crée les vues des navires du joueur humain.
     */
    public void poigneeHumainFlotteCree() {
        // Assigner une vue à chaque bateau de la flotte
        ArrayList<Bateau> flotte = this.jeu.getJoueurHumain().getFlotte();

        for (Bateau bateau : flotte) {
            new VueBateau(bateau, this.humainGrilleVue);
        }
    }

    /**
     * Gère la notification de création de la flotte du joueur aléatoire.
     * Crée les vues des navires du joueur aléatoire.
     */
    public void handleAleatoireFlotteCree() {
        // Assigner une vue à chaque bateau de la flotte
        ArrayList<Bateau> fleet = this.jeu.getJoueurAleatoire().getFlotte();

        for (Bateau ship : fleet) {
            new VueBateau(ship, this.aleatoireGrilleVue);
        }
    }

    /**
     * Gère la notification du début du jeu. Affiche un message indiquant que la partie peut commencer.
     */
    public void handleJeuCommencee() {
        new MessageDialog("La partie peut commencer", JOptionPane.INFORMATION_MESSAGE).showMessageDialog();
    }

    /**
     * Méthode appelée lors de la mise à jour du modèle pour traiter les notifications reçues.
     * 
     * @param source L'objet source de la notification.
     * @param notification L'objet notification contenant le type de changement.
     */
    @Override
    public void modelMisAJour(Object source, Object notification) {
        if (notification instanceof JeuNotification) {
            if (notification == JeuNotification.HUMAIN_FLOTTE_CREE) {
                this.poigneeHumainFlotteCree();
            } else if (notification == JeuNotification.ALEATOIRE_FLOTTE_CREE) {
                this.handleAleatoireFlotteCree();
            } else if (notification == JeuNotification.PARTIE_COMMENCEE) {
                this.handleJeuCommencee();
            }
        } else {
            System.out.println("Notification non gérée pour VueCellule : " + notification);
        }
    }

    /**
     * Gère les événements des boutons (placement des navires et démarrage de la partie).
     */
    public void gererEvenement() {
        aleatoireButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creerHumainFlotte();
            }
        });

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                debutJeuClique();
            }
        });
    }
}
