import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JeuTest {

    // test qui test l'alternance des coups
    @Test
    void testChangerJoueur() {
        assertTrue(Jeu.changerJoueur(1) == 2);
        assertTrue(Jeu.changerJoueur(2) == 1);
        assertFalse(Jeu.changerJoueur(1) == 1);
    }

    // test si une pierre qui n'est pas entoure est bien vivante
    @Test
    void testPierreSeuleEstVivante() {
        int[][] plateau = new int[9][9];
        plateau[4][4] = 1;

        boolean[][] visite = new boolean[9][9];
        assertTrue(Jeu.estGroupeVivant(plateau, 4, 4, 1, visite));
    }

    // test si une piece entourée est bien capture
    @Test
    void testPierreEntoureeEstMorte() {
        int[][] plateau = new int[9][9];
        plateau[4][4] = 1;

        plateau[3][4] = 2; // Nord
        plateau[5][4] = 2; // Sud
        plateau[4][3] = 2; // Ouest
        plateau[4][5] = 2; // Est

        boolean[][] visite = new boolean[9][9];
        assertFalse(Jeu.estGroupeVivant(plateau, 4, 4, 1, visite));
    }

    // Teste la survie d'un groupe
    @Test
    void testGroupeDeDeuxPierresVivant() {
        int[][] plateau = new int[9][9];
        plateau[4][4] = 1;
        plateau[4][5] = 1;

        // On entoure pas totalement la première pierre
        plateau[3][4] = 2;
        plateau[5][4] = 2;
        plateau[4][3] = 2;

        boolean[][] visite = new boolean[9][9];
        assertTrue(Jeu.estGroupeVivant(plateau, 4, 4, 1, visite));
    }

    // test qui compte la taille d'un groupe
    @Test
    void testCompterGroupeTroisPierres() {
        int[][] plateau = new int[9][9];
        plateau[1][1] = 1;
        plateau[1][2] = 1;
        plateau[1][3] = 1;

        boolean[][] visite = new boolean[9][9];
        int taille = Jeu.CompterGroupe(plateau, 1, 1, 1, visite);

        assertEquals(3, taille);
        assertNotEquals(1, taille);
    }

    // test qui montre que les diagonales ne sont pas considerée comme des groupes
    @Test
    void testCompterGroupeDiagonale() {
        int[][] plateau = new int[9][9];
        plateau[4][4] = 1;
        plateau[5][5] = 1;
    }
}
