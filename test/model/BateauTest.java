package model;

import model.Bateau;
import model.Cellule;
import model.EtatCellule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

public class BateauTest {

    private Bateau bateau;
    private Cellule cellule1;
    private Cellule cellule2;

    @Before
    public void setUp() {
        // Création de deux cellules pour le bateau
        cellule1 = new Cellule(1, 1);
        cellule2 = new Cellule(1, 2);

        // Création du bateau avec 2 cellules
        bateau = new Bateau(2);
        ArrayList<Cellule> cellules = new ArrayList<>();
        cellules.add(cellule1);
        cellules.add(cellule2);

        bateau.setCellulesBateau(cellules);
        cellule1.setBateauCellule(bateau);
        cellule2.setBateauCellule(bateau);
    }

    @Test
    public void testVisibiliteBateau() {
        // Vérifier que le bateau n'est pas visible au départ
        assertFalse("Le bateau ne doit pas être visible", bateau.estVisible());

        // Changer la visibilité du bateau
        bateau.setVisible(true);

        // Vérifier que le bateau est désormais visible
        assertTrue("Le bateau doit être visible", bateau.estVisible());
    }

    @Test
    public void testDetruireBateau() {
        // Vérifier que le bateau n'est pas détruit initialement
        assertFalse("Le bateau ne doit pas être détruit", bateau.estDetruit());

        // Détruire le bateau
        bateau.detruit();

        // Vérifier que le bateau est maintenant détruit
        assertTrue("Le bateau doit être détruit", bateau.estDetruit());
    }

    @Test
    public void testToucheCellules() {
        // Vérifier que toutes les cellules sont d'abord non touchées
        assertFalse("Le bateau ne doit pas être complètement touché", bateau.toucheCellules());

        // Marquer la cellule1 comme touchée
        cellule1.setEtat(EtatCellule.TOUCHE);
        // Marquer la cellule2 comme touchée
        cellule2.setEtat(EtatCellule.TOUCHE);

        // Vérifier que le bateau est maintenant complètement touché
        assertTrue("Le bateau doit être complètement touché", bateau.toucheCellules());

        // Vérifier que le bateau devient visible après avoir été touché
        assertTrue("Le bateau doit être visible après que toutes les cellules sont touchées", bateau.estVisible());
    }

    @Test
    public void testEtatBateau() {
        // Vérifier l'état initial du bateau (non détruit)
        assertFalse("Le bateau ne doit pas être détruit initialement", bateau.estDetruit());

        // Vérifier que le bateau n'a pas encore été détruit
        assertTrue("Le bateau ne doit pas être détruit immédiatement", bateau.estDetruit() == false);

        // Détruire le bateau
        bateau.detruit();

        // Vérifier que le bateau est bien détruit
        assertTrue("Le bateau doit être détruit après l'appel à la méthode detruit", bateau.estDetruit());
    }
}

