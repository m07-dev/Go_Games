import java.util.Scanner;
public class launcher {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int BoutonLancer;
        int taille;
        int pierre_blanc = MethodePlateau.PIERREBLANCHE;
        int pierre_noir = MethodePlateau.PIERRENOIRE;
        System.out.println();

        // Affichage du TITRE du Jeu

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

        do{
            System.out.println("                                                                        Bienvenue dans GoGames : appuyez sur 1 pour lancer une partie de GO ");
            BoutonLancer = sc.nextInt();
        }while(BoutonLancer != 1);

        // demande la taille du plateau sur lequel le joueur veux jouer

        do{
            System.out.println("Sur un plateau de quelle taille voulez vous jouez : 9, 13, 19 ");
            taille = sc.nextInt();
        }
        while(taille != 9 && taille  != 13 &&  taille != 19);

        System.out.println("Joueur numero 1 vous avez les pierres blanches vous debutez avec un avantage de 0.5 points\n" +
                            "Joueur numero 2 vous avez les pierres noires c'est donc vous qui commencez ");
        System.out.println();

        // Presentation du plateau de jeu : creation du plateau Goban puis affichage de ce plateau

        System.out.println("Voici le plateau sur le quelle vous allez jouer ");
        System.out.println();
        char[][] Goban = MethodePlateau.creationGoban(taille);
        MethodePlateau.AffichageGoban(Goban);
        System.out.println();

        // lancement de la partie

        Main.lancerPartie(Goban);
    }

}