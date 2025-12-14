import java.sql.SQLOutput;
import java.util.Scanner;
public class Main {
    final static char VIDE = '_';
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int taille;
        int pierre_blanc = 'B';
        int pierre_noir = 'N';

        do{
            System.out.println("Quelle taille choissisez-vous : 9, 13, 19 ");
            taille = sc.nextInt();
        }
        while(taille != 9 && taille  != 13 &&  taille != 19);
        // Creation
        char[][] Goban = Plateau.creationGoban(taille);
        // Affichage du TITRE du Jeu
            System.out.println("                                                                         █████████     ███████    ███████████    █████████   ██████   █████\n" +
                    "                                                                        ███▒▒▒▒▒███  ███▒▒▒▒▒███ ▒▒███▒▒▒▒▒███  ███▒▒▒▒▒███ ▒▒██████ ▒▒███ \n" +
                    "                                                                       ███     ▒▒▒  ███     ▒▒███ ▒███    ▒███ ▒███    ▒███  ▒███▒███ ▒███ \n" +
                    "                                                                      ▒███         ▒███      ▒███ ▒██████████  ▒███████████  ▒███▒▒███▒███ \n" +
                    "                                                                      ▒███    █████▒███      ▒███ ▒███▒▒▒▒▒███ ▒███▒▒▒▒▒███  ▒███ ▒▒██████ \n" +
                    "                                                                      ▒▒███  ▒▒███ ▒▒███     ███  ▒███    ▒███ ▒███    ▒███  ▒███  ▒▒█████ \n" +
                    "                                                                       ▒▒█████████  ▒▒▒███████▒   ███████████  █████   █████ █████  ▒▒█████\n" +
                    "                                                                         ▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒    ▒▒▒▒▒▒▒▒▒▒▒  ▒▒▒▒▒   ▒▒▒▒▒ ▒▒▒▒▒    ▒▒▒▒▒ ");

        System.out.println("Le Joueur1 Pierre blanche \n Le Joueur2 Pierre noir");
        launcher.lancerPartie(Goban);
    }

}