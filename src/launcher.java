import java.util.Scanner;

public class launcher {

    public static void lancerPartie(char[][] Goban) {
        boolean finDeJeu = false;
        int JoueurActuel = 1;
        while (!finDeJeu) {
            int[] coup = demanderCoup(JoueurActuel, Goban);
            char pierre = Plateau.pierreActuel(JoueurActuel, 'N', 'B');
            if(Plateau.verifierSiPierrePoser(Goban, coup[0], coup[1], pierre)){
                Plateau.poserPierre(Goban, coup[0], coup[1], pierre, JoueurActuel);
            }else {
                System.out.println("Ce coup est invalide");
            }

            Plateau.AffichageGoban(Goban);

            // Probleme n°1 lorsqu'on met un pierre sur une autre differente cela change de tour donc a regler
            // Probleme n°2 Changer la fonction Emplacement vide, faire utiliser des parametre au lieu de 'N' et 'B' car possiblilté de demander a l'users de choisir ces pierre
            JoueurActuel = Joueur.changerJoueur(JoueurActuel);


        }
    }

    public static int[] demanderCoup(int JoueurActuel, char[][] Goban) {
        int x;
        int y;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Joueur n°" + JoueurActuel + " , Quel est votre coup en position X ?");
            x = sc.nextInt();
            System.out.println("Joueur n°" + JoueurActuel + ", Quel est votre coup en position Y ?");
            y = sc.nextInt();
        } while (x < 0 || x >= Goban.length || y < 0 || y >= Goban.length);

        return new int[]{x, y};
    }
}
