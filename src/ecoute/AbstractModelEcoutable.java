package ecoute;

import java.util.ArrayList;

/**
 * Classe abstraite représentant un modèle qui peut être écouté par plusieurs écouteurs.
 * Elle implémente l'interface ModelEcoutable et gère une liste d'écouteurs qui réagiront aux changements dans le modèle.
 */
public abstract class AbstractModelEcoutable implements ModelEcoutable {
    
    // Liste des écouteurs attachés au modèle
    private ArrayList<ModelEcoute> ecouteurs;
    
    /**
     * Constructeur de la classe AbstractModelEcoutable.
     * Initialise la liste des écouteurs comme une nouvelle ArrayList vide.
     */
    public AbstractModelEcoutable() {
        super();  // Appel au constructeur de la classe parente (si applicable)
        this.ecouteurs = new ArrayList<ModelEcoute>();  // Initialisation de la liste des écouteurs
    }

    /**
     * Ajoute un écouteur à la liste des écouteurs.
     * Cet écouteur sera notifié lors des changements dans le modèle.
     * 
     * @param e L'écouteur à ajouter.
     */
    @Override
    public void addListening(ModelEcoute e) {
        this.ecouteurs.add(e);  // Ajoute l'écouteur à la liste
    }

    /**
     * Supprime un écouteur de la liste des écouteurs.
     * Cet écouteur ne sera plus notifié lors des changements dans le modèle.
     * 
     * @param e L'écouteur à supprimer.
     */
    @Override
    public void removeListening(ModelEcoute e) {
        this.ecouteurs.remove(e);  // Retire l'écouteur de la liste
    }

    /**
     * Notifie tous les écouteurs enregistrés qu'un changement a eu lieu.
     * Les écouteurs seront informés via la méthode modelMisAJour.
     * 
     * @param notification L'objet représentant l'information sur le changement.
     */
    protected void fireChangement(Object notification) {
        // Parcours la liste des écouteurs et leur envoie la notification du changement
        for (ModelEcoute e : ecouteurs) {
            e.modelMisAJour(this, notification);  // Appelle la méthode de mise à jour sur chaque écouteur
        }
    }

}
