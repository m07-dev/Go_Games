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
            // demander une saisie pour passez le tour utilisé le null
            System.out.println("Joueur n°" + JoueurActuel + " , Tapez un coup pour la position X OU taper -1 pour PASSER ?");
            x = sc.nextInt();
            System.out.println("Joueur n°" + JoueurActuel + ", Tapez un coup pour la position Y OU taper -1 pour PASSER ?");
            y = sc.nextInt();
        } while (x < -1 || x >= Goban.length || y < -1 || y >= Goban.length); // mettre x et y a -1 au pour accepter les tourPasser

        return new int[]{x, y};
    }
    // Fonction qui permet de créer un tableau de boolean pour verifier si un groupe de pierre est visite
    public static boolean[][] CreationTableauGroupeVisitee(char[][] Goban){
        boolean[][] Visitee = new boolean[Goban.length][Goban.length];
        for (int i = 0; i < Goban.length; i++) {
            for (int j = 0; j < Goban.length; j++) {
                Visitee[i][j] = false;
            }
        }
        return Visitee;
    }

    // Fonction qui permet de compter le nombre de pierre dans un groupe
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
    // Fonction qui permet de verifier si un groupe de pierre est vivant
    public static boolean estGroupeVivant(char[][] Goban, int x, int y, char pierre, boolean[][] Visitee){
        if(MethodePlateau.verifierDehorsDesLimites(Goban,x,y)){
            return false;
        }
        if(Visitee[x][y]){
            return false;
        }

        // si on voit du vide alors c'est bon
        if(Goban[x][y] == MethodePlateau.EMPLACEMENTVIDE){
            return true;
        }

        // sinon ennemie
        if (Goban[x][y] != pierre){
            return false;
        }

        Visitee[x][y] = true;

        // on stock dans des variables
        boolean nord = estGroupeVivant(Goban, x+1, y, pierre, Visitee);
        boolean sud = estGroupeVivant(Goban, x-1, y, pierre, Visitee);
        boolean est = estGroupeVivant(Goban, x, y+1, pierre, Visitee);
        boolean ouest = estGroupeVivant(Goban, x, y-1, pierre, Visitee);

        // retour vrai si le voisin ont trouvé du vide
        return nord || sud || est || ouest;
    }
    // Fonction qui supprime le groupe
    public static int supprimerGroupe(char[][] Goban, int x, int y, char pierre){
        if(MethodePlateau.verifierDehorsDesLimites(Goban,x,y)){
            return 0;
        }
        if(Goban[x][y] != pierre){
            return 0;
        }
        Goban[x][y] = MethodePlateau.EMPLACEMENTVIDE;
        // comptage des pierres supprimée
        return 1 + supprimerGroupe(Goban,x+1,y,pierre) + supprimerGroupe(Goban,x-1,y,pierre) + supprimerGroupe(Goban,x,y+1,pierre) + supprimerGroupe(Goban,x,y-1,pierre);
    }
}