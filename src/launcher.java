import java.util.Scanner;

public class launcher {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int BoutonLancer;
        int taille;


        int pierre_blanc = MethodePlateau.BLANC;
        int pierre_noir = MethodePlateau.NOIR;

        System.out.println();

        // Affichage du TITRE du jeu
        System.out.println("                                                                         █████████     ███████    ███████████    █████████   ██████   █████\n" +
                "                                                                        ███▒▒▒▒▒███  ███▒▒▒▒▒███ ▒▒███▒▒▒▒▒███  ███▒▒▒▒▒███ ▒▒██████ ▒▒███ \n" +
                "                                                                       ███     ▒▒▒  ███     ▒▒███ ▒███    ▒███ ▒███    ▒███  ▒███▒███ ▒███ \n" +
                "                                                                      ▒███         ▒███      ▒███ ▒██████████  ▒███████████  ▒███▒▒███▒███ \n" +
                "                                                                      ▒███    █████▒███      ▒███ ▒███▒▒▒▒▒███ ▒███▒▒▒▒▒███  ▒███ ▒▒██████ \n" +
                "                                                                      ▒▒███  ▒▒███ ▒▒███     ███  ▒███    ▒███ ▒███    ▒███  ▒███  ▒▒█████ \n" +
                "                                                                       ▒▒█████████  ▒▒▒███████▒   ███████████  █████   █████ █████  ▒▒█████\n" +
                "                                                                         ▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒    ▒▒▒▒▒▒▒▒▒▒▒  ▒▒▒▒▒   ▒▒▒▒▒ ▒▒▒▒▒    ▒▒▒▒▒ ");

        System.out.println();
        System.out.println();
        System.out.println();

        // lancement de la partie si le joueur saisie le chiffre 1
        do {
            System.out.println("                                                                        Bienvenue dans GoGames : appuyez sur 1 pour lancer une partie de GO ");
            BoutonLancer = sc.nextInt();
        } while (BoutonLancer != 1);

        // demande du pseudo du joueur 1
        System.out.println("Entrez le prénom du Joueur 1 (Blancs) :");
        String nomJ1 = sc.next();

        // demande du pseudo du joueur 2
        System.out.println("Entrez le prénom du Joueur 2 (Noirs) :");
        String nomJ2 = sc.next();

        // demande la taille du plateau sur lequel le joueur veut jouer
        do {
            System.out.println("Sur un plateau de quelle taille voulez vous jouez : 9, 13, 19 ");
            taille = sc.nextInt();
        } while (taille != 9 && taille != 13 && taille != 19);

        System.out.println(nomJ1 + " vous avez les pierres blanches vous debutez avec un avantage de 0.5 points\n" +
                nomJ2+" vous avez les pierres noires c'est donc vous qui commencez ");
        System.out.println();

        // Presentation du plateau de jeu
        System.out.println("Voici le plateau sur le quelle vous allez jouer ");
        System.out.println();

        // Creation du plateau de jeu
        int[][] Goban = MethodePlateau.creationGoban(taille);

        // Affihchage du plateau de jeu
        Affichage.AffichageGoban(Goban);

        System.out.println();

        // lancement de la partie
        Main.lancerPartie(Goban, nomJ1, nomJ2);
    }
}
