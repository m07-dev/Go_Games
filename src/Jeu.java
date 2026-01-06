import java.util.Scanner;

public class Jeu {

    public static int changerJoueur(int JoueurActuel){
        if(JoueurActuel == 1){
            JoueurActuel = 2;
        }else{
            JoueurActuel = 1;
        }
        return JoueurActuel;
    }

    // On prend en int[][] pour demander les coups
    public static int[] demanderCoup(String nomJoueur, int[][] Goban) {
        int x;
        int y;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(nomJoueur + ", Tapez un coup pour la position Y OU taper -1 pour PASSER ?");
            x = sc.nextInt();
            System.out.println(nomJoueur + ", Tapez un coup pour la position X OU taper -1 pour PASSER ?");
            y = sc.nextInt();
        } while (x < -1 || x >= Goban.length || y < -1 || y >= Goban.length); // mettre à -1 pour compter les passes

        return new int[]{x, y};
    }

    // Fonction qui permet de savoir si un groupe a été visité ou pas
    public static boolean[][] CreationTableauGroupeVisitee(int[][] Goban){
        boolean[][] Visitee = new boolean[Goban.length][Goban.length];
        for (int i = 0; i < Goban.length; i++) {
            for (int j = 0; j < Goban.length; j++) {
                Visitee[i][j] = false;
            }
        }
        return Visitee;
    }

    // Fonction qui permet de compter le groupe de pierre avec
    public static int CompterGroupe(int[][] Goban, int x, int y, int pierre, boolean[][] GroupeVisitee){
        if (MethodePlateau.verifierDehorsDesLimites(Goban,x,y)){
            return 0;
        }
        if(GroupeVisitee[x][y]){
            return 0;
        }
        if(Goban[x][y] != pierre){
            return 0;
        }
        int taille = 1;
        GroupeVisitee[x][y]= true;
        taille += CompterGroupe(Goban, x+1, y, pierre, GroupeVisitee);
        taille += CompterGroupe(Goban, x-1, y, pierre, GroupeVisitee);
        taille += CompterGroupe(Goban, x, y+1, pierre, GroupeVisitee);
        taille += CompterGroupe(Goban, x, y-1, pierre, GroupeVisitee);

        return taille;
    }

    // Fonctionn qui permet de savoir si un groupe est vivant c.-à-d. Si dans le groupe, ont au moins un case vide
    public static boolean estGroupeVivant(int[][] Goban, int x, int y, int pierre, boolean[][] Visitee){
        if(MethodePlateau.verifierDehorsDesLimites(Goban,x,y)){
            return false;
        }
        if(Visitee[x][y]){
            return false;
        }

        // MAJ : On utilise la constante VIDE (qui vaut 0)
        if(Goban[x][y] == MethodePlateau.VIDE){
            return true;
        }

        if (Goban[x][y] != pierre){
            return false;
        }

        Visitee[x][y] = true;

        boolean nord = estGroupeVivant(Goban, x+1, y, pierre, Visitee);
        boolean sud = estGroupeVivant(Goban, x-1, y, pierre, Visitee);
        boolean est = estGroupeVivant(Goban, x, y+1, pierre, Visitee);
        boolean ouest = estGroupeVivant(Goban, x, y-1, pierre, Visitee);

        return nord || sud || est || ouest;
    }

    // Fonction qui permet de supprimer un groupe de pierre
    public static int supprimerGroupe(int[][] Goban, int x, int y, int pierre){
        if(MethodePlateau.verifierDehorsDesLimites(Goban,x,y)){
            return 0;
        }
        if(Goban[x][y] != pierre){
            return 0;
        }

        Goban[x][y] = MethodePlateau.VIDE;

        return 1 + supprimerGroupe(Goban,x+1,y,pierre) + supprimerGroupe(Goban,x-1,y,pierre) + supprimerGroupe(Goban,x,y+1,pierre) + supprimerGroupe(Goban,x,y-1,pierre);
    }

    // Fonction principale appelée à la fin du jeu
    public static void calculerScoreFinal(int[][] Goban, int capturesNoir, int capturesBlanc,String nomBlanc, String nomNoir) {
        boolean[][] visite = new boolean[Goban.length][Goban.length];
        double scoreFinalNoir = capturesNoir;
        double scoreFinalBlanc = capturesBlanc + 0.5; // Point supplémentaire attribuée au blanc -> "KOMI"

        for (int x = 0; x < Goban.length; x++) {
            for (int y = 0; y < Goban.length; y++) {
                // On cherche les zones VIDES non visitées
                if (Goban[x][y] == MethodePlateau.VIDE && !visite[x][y]) {


                    // result[0] = taille, result[1] = toucheNoir (1=oui), result[2] = toucheBlanc (1=oui)
                    int[] resultatZone = {0, 0, 0};
                    CalculTailleZoneVide(Goban, x, y, visite, resultatZone);

                    int tailleZone = resultatZone[0];
                    boolean toucheNoir = resultatZone[1] > 0;
                    boolean toucheBlanc = resultatZone[2] > 0;

                    if (toucheNoir && !toucheBlanc) {
                        scoreFinalNoir += tailleZone;
                        System.out.println("Territoire Noir détecté de taille " + tailleZone);
                    } else if (toucheBlanc && !toucheNoir) {
                        scoreFinalBlanc += tailleZone;
                        System.out.println("Territoire Blanc détecté de taille " + tailleZone);
                    } else {
                        System.out.println("Zone neutre (Dame) de taille " + tailleZone);
                    }
                }
            }
        }
        System.out.println("FIN DE PARTIE !");
        System.out.println("Score Noir : " + scoreFinalNoir);
        System.out.println("Score Blanc : " + scoreFinalBlanc);
        if (scoreFinalNoir > scoreFinalBlanc) {
            System.out.println("BRAVO " + nomNoir.toUpperCase() + " ! TU AS GAGNÉ !");
        } else {
            System.out.println("BRAVO " + nomBlanc.toUpperCase() + " ! TU AS GAGNÉ !");
        }
    }

    // Fonction récursive pour explorer une zone vide
    public static void CalculTailleZoneVide(int[][] Goban, int x, int y, boolean[][] visite, int[] resultat) {
        // Si hors limites ou déjà visité (pour les cases vides), on arrête
        if (MethodePlateau.verifierDehorsDesLimites(Goban, x, y)) return;
        if (visite[x][y]) return;

        // Si c'est une PIERRE, on note juste la couleur et on s'arrête (c'est une frontière)
        if (Goban[x][y] == MethodePlateau.NOIR) {
            resultat[1] = 1; // Touche Noir
            return;
        }
        if (Goban[x][y] == MethodePlateau.BLANC) {
            resultat[2] = 1; // Touche Blanc
            return;
        }

        // C'est une case VIDE non visitée : on la compte !
        visite[x][y] = true;
        resultat[0]++; // +1 taille

        // Appel récursif
        CalculTailleZoneVide(Goban, x + 1, y, visite, resultat);
        CalculTailleZoneVide(Goban, x - 1, y, visite, resultat);
        CalculTailleZoneVide(Goban, x, y + 1, visite, resultat);
        CalculTailleZoneVide(Goban, x, y - 1, visite, resultat);
    }
}