package model;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe représentant un joueur humain dans le jeu de bataille navale.
 * Le joueur saisit ses coordonnées pour tirer et placer ses navires.
 */
public class JoueurHumain extends AbstractJoueur {
    
    /**
     * Constructeur de la classe JoueurHumain.
     * Initialise le joueur avec un nom et une grille de jeu vide.
     *
     * @param grille La grille du joueur.
     * @param nom Le nom du joueur.
     */
    public JoueurHumain(Grille grille, String nom) {
        super(grille, nom);
    }

    /**
     * Constructeur sans argument utilisé principalement pour des tests ou initialisations sans grille.
     */
    public JoueurHumain() {
        super();
    }

    /**
     * Demande à l'utilisateur de choisir une case sur le plateau pour tirer.
     * Assure que l'entrée utilisateur est valide (lettre entre A-J suivie d'un chiffre entre 0-9).
     *
     * @return Un tableau d'entiers représentant la position (x, y) du tir.
     */
    
    
    
    public int[] tire() {
    Scanner scanner = new Scanner(System.in);
    int x = 0, y = 0;
    boolean entreeValide = false;

    while (!entreeValide) {
        try {
            System.out.println("Choisissez une case (ex : A1) : ");
            
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Entrée invalide. Veuillez réessayer.");
            }
            
            String entree = scanner.nextLine().trim(); // Supprime les espaces au début et à la fin

            // Vérifie que l'entrée a exactement 2 caractères
            if (entree.length() != 2) {
                throw new IllegalArgumentException("Erreur : Vous devez entrer une case comme A1, B2...");
            }

            char lettre = entree.charAt(0);
            char chiffre = entree.charAt(1);

            // Vérifie que le premier caractère est une lettre entre A et J
            if (lettre < 'A' || lettre > 'J') {
                throw new IllegalArgumentException("Erreur : La première valeur doit être une lettre entre A et J.");
            }

            // Vérifie que le second caractère est un chiffre entre 1 et 9
            if (chiffre < '1' || chiffre > '9') {
                throw new IllegalArgumentException("Erreur : La deuxième valeur doit être un chiffre entre 1 et 9.");
            }

            x = lettre - 'A'; // Convertit la lettre en un index (A -> 0, B -> 1, ..., J -> 9)
            y = Character.getNumericValue(chiffre) - 1; // Convertit le chiffre en index (1 -> 0, 2 -> 1, ..., 9 -> 8)

            entreeValide = true; // Si tout est bon, on sort de la boucle

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Affiche le message d'erreur
        }
        }
        
        return new int[]{x, y}; // Retourne les coordonnées sous forme de tableau
        
    }

    
    
    

    /**
     * Rend tous les navires du joueur visibles sur la grille.
     */
    public void BateauVisible() {
        for (Bateau bat : this.flotte) {
            bat.setVisible(true);
        }
    }

    /**
     * Permet au joueur humain d'ajouter ses navires à la grille.
     * Demande les coordonnées et l'orientation (verticale ou horizontale) pour chaque navire.
     */
    public void humainAjoutBateau() {
        try (Scanner entree = new Scanner(System.in)) {
            for (int i = 0; i < tailleBateau.length; i++) {
                boolean ajouter = false;
                while (!ajouter) {
                    try {
                        System.out.print("Choisissez une coordonnée X pour votre navire " + (i + 1) + " : ");
                        int x = entree.nextInt();
                        System.out.print("Choisissez une coordonnée Y pour votre navire " + (i + 1) + " : ");
                        int y = entree.nextInt();
                        entree.nextLine(); // Consommer la ligne vide restante

                        String orientation;
                        do {
                            System.out.print("Votre navire est vertical ou horizontal (ve/ho) ? ");
                            orientation = entree.nextLine().trim().toLowerCase();
                        } while (!orientation.equals("ve") && !orientation.equals("ho"));

                        boolean estVertical = orientation.equals("ve");
                        ajouter = this.ajoutBateau(x, y, new Bateau(tailleBateau[i], true), estVertical);

                        if (ajouter) {
                            System.out.println("Navire ajouté avec succès !\n");
                            this.grille.afficher();
                        } else {
                            System.out.println("Impossible d'ajouter le navire à cette position. Veuillez réessayer.\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur : Veuillez entrer une valeur numérique pour les coordonnées.");
                        entree.nextLine(); // Consommer la ligne erronée
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                }
            }
        }
    }
}
