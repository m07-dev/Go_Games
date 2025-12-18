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
    public static boolean[][] CreationTableauGroupeVisitee(char[][] Goban){
        boolean[][] Visitee = new boolean[Goban.length][Goban.length];
        for (int i = 0; i < Goban.length; i++) {
            for (int j = 0; j < Goban.length; j++) {
                Visitee[i][j] = false;
            }
        }
        return Visitee;
    }

    // Methode qui permet de compter la taille d'un Groupe de Pierre
        public static int CompterGroupe(char[][] Goban, int x, int y, char pierre, boolean[][] GroupeVisitee){
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
        public static boolean estGroupeVivant(char[][] Goban, int x, int y, char pierre, boolean[][] Visitee){
            if(MethodePlateau.verifierDehorsDesLimites(Goban,x,y)){
                return false;
            }
            if(Visitee[x][y]){
                return false;
            }

            if (Goban[x][y] != pierre){
                return false;
            }
            // SI on trouve un emplacement vide alors je ne suis pas
            if(Goban[x][y] == '*'){
                return true;
            }
            Visitee[x][y] = true;
            estGroupeVivant(Goban, x+1, y, pierre, Visitee);
            estGroupeVivant(Goban, x-1, y, pierre, Visitee);
            estGroupeVivant(Goban, x, y+1, pierre, Visitee);
            estGroupeVivant(Goban, x, y-1, pierre, Visitee);
        }

}

