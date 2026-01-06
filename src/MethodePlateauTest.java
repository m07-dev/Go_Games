import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MethodePlateauTest {

    @Test
    void testVerifierDehorsDesLimites() {
        int[][] plateau = new int[9][9];

        // En dehors
        assertTrue(MethodePlateau.verifierDehorsDesLimites(plateau, -1, 0));

        // En dehors
        assertTrue(MethodePlateau.verifierDehorsDesLimites(plateau, 9, 9));

        // À l'intérieur
        assertFalse(MethodePlateau.verifierDehorsDesLimites(plateau, 0, 0));
    }

    @Test
    void testVerifierSiPierrePoser() {
        int[][] plateau = new int[9][9];
        // on place une pierre en 4 4
        plateau[4][4] = MethodePlateau.NOIR;

        // on essaie de poser en 4 4
        assertFalse(MethodePlateau.verifierSiPierrePoser(plateau, 4, 4));

        // on essaie de pose en 0 0
        assertTrue(MethodePlateau.verifierSiPierrePoser(plateau, 0, 0));
    }

    @Test
    void testGobanRempli() {
        // on cree un tableau pour tester la methode goban rempli
        int[][] plateau = new int[2][2];

        // on test la fonction lorsque le tableau est vide
        assertFalse(MethodePlateau.GobanRempli(plateau));

        // On rempli le tableau
        plateau[0][0] = 1;
        plateau[0][1] = 1;
        plateau[1][0] = 1;
        plateau[1][1] = 1;

        // on test la fonction lorsque le tableau est rempli
        assertTrue(MethodePlateau.GobanRempli(plateau));
    }

    @Test
    void testPierreActuelle() {
        // Joueur 1 doit donner NOIR (1)
        assertEquals(MethodePlateau.NOIR, MethodePlateau.pierreActuel(1));

        // Joueur 2 doit donner BLANC (2)
        assertEquals(MethodePlateau.BLANC, MethodePlateau.pierreActuel(2));

        assertNotEquals(MethodePlateau.BLANC, MethodePlateau.pierreActuel(1));
    }
}
