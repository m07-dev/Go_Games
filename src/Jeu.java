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
            System.out.println("Joueur n°" + JoueurActuel + " , Quel est votre coup en position X ?");
            x = sc.nextInt();
            System.out.println("Joueur n°" + JoueurActuel + ", Quel est votre coup en position Y ?");
            y = sc.nextInt();

        } while (x < 0 || x >= Goban.length || y < 0 || y >= Goban.length);

        return new int[]{x, y};
    }

    /*public static int passertour(int JoueurActuel){}*/
}

