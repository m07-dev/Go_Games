import java.util.Scanner;
public class MethodePlateau {
    final static char PIERRENOIRE = '☗';
    final static char PIERREBLANCHE = '☖';
    final static char EMPLACEMENTVIDE = '*';

    public static void main(String[] args) {
        char[][] Goban = creationGoban((9));
        AffichageGoban(Goban);
    }

    // fonction qui cree et retourne le plateau goban vide
    public static char[][] creationGoban(int taille) {
        char[][] plateau_Goban = new char[taille][taille];

        // boucle qui rempli le tableau

        for (int ligne = 0; ligne < plateau_Goban.length; ligne++) {
            for (int colonne = 0; colonne < plateau_Goban[ligne].length; colonne++) {
                plateau_Goban[ligne][colonne] = EMPLACEMENTVIDE;
            }
        }
        return plateau_Goban;
    }

    // procedure qui affiche le plateau goban
    public static void AffichageGoban(char[][] plateau) {
        //boucle qui affiche coordonee ( colonnes )
        System.out.print("   ");
        for (int coordX = 0 ; coordX< plateau.length ; coordX ++ ){
            if (coordX== plateau.length-1)
                System.out.println(coordX );
            else System.out.print(coordX+"    ");

        }
        // boucle qui affiche le tableau en regardant si la case affiche est vide ou non
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            System.out.print(ligne +"  ");
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                /*affichage premiere ligne */
                if (ligne == 0) {
                    if (colonne == 0) {
                        if (plateau[ligne][colonne] == EMPLACEMENTVIDE){
                            System.out.print('┌');
                            System.out.print("────");
                        }
                        else {
                            System.out.print(plateau[ligne][colonne]);
                            System.out.print("────");
                        }
                    }
                    if (colonne != 0 && colonne != plateau.length - 1) {
                        if (plateau[ligne][colonne] == EMPLACEMENTVIDE){
                            System.out.print('┬');
                            System.out.print("────");
                        }
                        else {
                            System.out.print(plateau[ligne][colonne]);
                            System.out.print("────");
                        }
                    }
                    if (colonne == (plateau.length - 1)) {
                        if (plateau[ligne][colonne] == EMPLACEMENTVIDE){
                            System.out.print('┐');
                        }
                        else {
                            System.out.print(plateau[ligne][colonne]);
                        }
                    }
                }
                /*affichage des lignes du milieu */
                if (ligne != 0 && ligne != plateau.length - 1) {
                    if (colonne == 0) {
                        if (plateau[ligne][colonne] == EMPLACEMENTVIDE){
                            System.out.print('├');
                            System.out.print("────");
                        }
                        else {
                            System.out.print(plateau[ligne][colonne]);
                            System.out.print("────");
                        }
                    }
                    if (colonne != 0 && colonne != plateau.length - 1) {
                        if (plateau[ligne][colonne] == EMPLACEMENTVIDE){
                            System.out.print('┼');
                            System.out.print("────");
                        }
                        else {
                            System.out.print(plateau[ligne][colonne]);
                            System.out.print("────");
                        }
                    }
                    if (colonne == (plateau.length - 1)) {
                        if (plateau[ligne][colonne] == EMPLACEMENTVIDE){
                            System.out.print('┤');
                        }
                        else {
                            System.out.print(plateau[ligne][colonne]);
                        }
                    }
                }
                /*affichage derniere ligne  */
                if (ligne == plateau.length - 1) {
                    if (colonne == 0) {
                        if (plateau[ligne][colonne] == EMPLACEMENTVIDE){
                            System.out.print('└');
                            System.out.print("────");
                        }
                        else {
                            System.out.print(plateau[ligne][colonne]);
                            System.out.print("────");
                        }
                    }
                    if (colonne != 0 && colonne != plateau.length - 1) {
                        if (plateau[ligne][colonne] == EMPLACEMENTVIDE){
                            System.out.print('┴');
                            System.out.print("────");
                        }
                        else {
                            System.out.print(plateau[ligne][colonne]);
                            System.out.print("────");
                        }
                    }
                    if (colonne == (plateau.length - 1)) {
                        if (plateau[ligne][colonne] == EMPLACEMENTVIDE){
                            System.out.print('┘');
                        }
                        else {
                            System.out.print(plateau[ligne][colonne]);
                        }
                    }
                }
            }

            System.out.println("");

            // affichage des barres entre chaque ligne

            if (ligne != plateau.length - 1) {
                for (int barre=0 ; barre < plateau.length ;barre ++) {
                    if (barre==0) {
                        System.out.print("   |    ");
                    }
                    else{
                        System.out.print("|    ");
                    }
                }
                System.out.println();
            }
        }
    }

    // fonction qui retourne le caractere associe a la pierre du joueur a qui c'est le tour
    public static char pierreActuel(int JoueurActuelle, char pierre_noir, char pierre_blanc){
        char pierre;
        if (JoueurActuelle == 1 ){
            pierre = pierre_blanc ;
        }
        else
            pierre = pierre_noir;
        return pierre;
    }

    // fonction qui retourne vrai si l'emplacement au coordonnees x et y est vide
    public static boolean EmplacementVide(char[][] plateau, int x, int y, char pierre) {
        return plateau[x][y] != PIERREBLANCHE && plateau[x][y] != PIERRENOIRE;
    }

    // fonction qui retourne vrai si l'emplacement chosis est en dehors du tabeleau
    public static boolean verifierDehorsDesLimites(char[][] plateau, int x, int y) {
        return x < 0 || x >= plateau.length || y < 0 || y >= plateau.length;
    }

    // fonction qui retourne vrai si on peut poser la pierre
    public static boolean verifierSiPierrePoser (char[][] plateau, int x, int y, char pierre){
        return !verifierDehorsDesLimites(plateau, x, y) && EmplacementVide (plateau, x, y,  pierre);
    }

    // procedure qui place la pierre du joueur qui joue au coordonee demandee
    public static void poserPierre(char[][] plateau , int x, int y, char pierre ,int JoueurActuelle){
        if(verifierSiPierrePoser(plateau, x, y, pierre)){
            plateau[x][y] = pierre;
        }
    }

    // fonction qui retourne vrai si le plateauvide
    public static boolean GobanRempli(char[][] plateau){
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau.length; j++) {
                if (plateau[i][j] == EMPLACEMENTVIDE) {
                    return false;
                }
            }
        }
        return true;
    }
}
