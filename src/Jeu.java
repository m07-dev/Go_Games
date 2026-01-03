import java.util.Scanner;

public class Jeu {
    public static void main(String[] args) {
    }

    public static int changerJoueur(int JoueurActuel){
        if(JoueurActuel == 1){
            JoueurActuel = 2;
        }else{
            JoueurActuel = 1;
        }
        return JoueurActuel;
    }

    // MAJ : On prend un int[][] au lieu de char[][]
    public static int[] demanderCoup(int JoueurActuel, int[][] Goban) {
        int x;
        int y;
        Scanner sc = new Scanner(System.in); // Mieux vaut le déclarer ici
        do {
            System.out.println("Joueur n°" + JoueurActuel + " , Tapez un coup pour la position X OU taper -1 pour PASSER ?");
            x = sc.nextInt();
            System.out.println("Joueur n°" + JoueurActuel + ", Tapez un coup pour la position Y OU taper -1 pour PASSER ?");
            y = sc.nextInt();
        } while (x < -1 || x >= Goban.length || y < -1 || y >= Goban.length);

        return new int[]{x, y};
    }

    // MAJ : int[][]
    public static boolean[][] CreationTableauGroupeVisitee(int[][] Goban){
        boolean[][] Visitee = new boolean[Goban.length][Goban.length];
        for (int i = 0; i < Goban.length; i++) {
            for (int j = 0; j < Goban.length; j++) {
                Visitee[i][j] = false;
            }
        }
        return Visitee;
    }

    // MAJ : int[][] et int pierre
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

    // MAJ : int[][] et int pierre
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

    // MAJ : int[][] et int pierre
    public static int supprimerGroupe(int[][] Goban, int x, int y, int pierre){
        if(MethodePlateau.verifierDehorsDesLimites(Goban,x,y)){
            return 0;
        }
        if(Goban[x][y] != pierre){
            return 0;
        }

        Goban[x][y] = MethodePlateau.SUICIDE;

        return 1 + supprimerGroupe(Goban,x+1,y,pierre) + supprimerGroupe(Goban,x-1,y,pierre) + supprimerGroupe(Goban,x,y+1,pierre) + supprimerGroupe(Goban,x,y-1,pierre);
    }
}