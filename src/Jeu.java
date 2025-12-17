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

    public static int[] demanderCoup(int JoueurActuel, char[][] Goban) {
        int x;
        int y;
        do {
            Scanner sc = new Scanner(System.in);
            // demander une saisie pour passez le tour utiliser le null
            System.out.println("Joueur n°" + JoueurActuel + " , Tapez un coup pour la position X OU taper -1 pour PASSER ?");
            x = sc.nextInt();
            System.out.println("Joueur n°" + JoueurActuel + ", Tapez un coup pour la  position Y OU taper -1 pour PASSER ?");
            y = sc.nextInt();
        } while (x < -1 || x >= Goban.length || y < -1 || y >= Goban.length); // mettre x et y a -1 au pour accepter les tourPasser

        return new int[]{x, y};
    }
    // Methode qui permet de stocker les groupevivant deja visitée
    public static boolean[][] CreationTableauGroupeVisitee(char[][] Goban, int x, int y){
        boolean[][] Visitee = new boolean[Goban.length][Goban.length];
        for (int i = 0; i < Goban.length; i++) {
            for (int j = 0; j < Goban.length; j++) {
                Visitee[x][y] = false;
            }
        }
        return Visitee;
    }

    // Methode qui permet de compter le nombre de GroupVivant
    public static int CompterGroupe(char[][] Goban, int x, int y, char pierre, boolean[][] GroupeVisitee){
        int taille;
        if (MethodePlateau.verifierDehorsDesLimites(Goban,x,y)){
            return 0;
        }
        if(GroupeVisitee[x][y]){
            return 0;
        }
        if(Goban[x][y] != pierre){
            return 0;
        }
        GroupeVisitee[x][y]= true;
        taille = CompterGroupe(Goban, x+1, y, pierre, GroupeVisitee) + 1;
        taille = CompterGroupe(Goban, x-1, y, pierre, GroupeVisitee) + 1;
        taille = CompterGroupe(Goban, x, y+1, pierre, GroupeVisitee) + 1;
        taille = CompterGroupe(Goban, x, y-1, pierre, GroupeVisitee) + 1;

        return taille;
    }


}

