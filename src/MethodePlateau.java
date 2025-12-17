import java.util.Scanner;

public class MethodePlateau {
    final static char PIERRENOIRE = 'N';
    final static char PIERREBLANCHE = 'B';
    final static char EMPLACEMENTVIDE = '*';

    public static void main(String[] args) {
        char[][] Goban = creationGoban((9));
        AffichageGoban(Goban);
    }
    public static char[][] creationGoban(int taille) {
        char[][] plateau_Goban = new char[taille][taille];
        // boucle qui cree le tableau

        for (int ligne = 0; ligne < plateau_Goban.length; ligne++) {
            for (int colonne = 0; colonne < plateau_Goban[ligne].length; colonne++) {
                plateau_Goban[ligne][colonne] = EMPLACEMENTVIDE;
            }
        }
        // Mise en place de la premiere ligne
                /*
                if (ligne == 0) {
                    if (colonne == 0) {
                        plateau_Goban[ligne][colonne] = '┌';
                    }
                    if (colonne != 0 && colonne != plateau_Goban.length - 1) {
                        plateau_Goban[ligne][colonne] = '┬';
                    }
                    if (colonne == (plateau_Goban[ligne].length - 1)) {
                        plateau_Goban[ligne][colonne] = '┐';
                    }
                }
                // Mise en place de tout ce qui a entre la 1er et la derniere
                if (ligne != plateau_Goban.length - 1 && ligne != 0) {
                    if (colonne == 0) {
                        plateau_Goban[ligne][colonne] = '├';
                    }
                    if (colonne != 0 && colonne != plateau_Goban[ligne].length - 1) {
                        plateau_Goban[ligne][colonne] = '┼';
                    }
                    if (colonne == plateau_Goban[ligne].length - 1) {
                        plateau_Goban[ligne][colonne] = '┤';
                    }
                }
                //Mise en place de la derniere ligne
                if (ligne == plateau_Goban.length - 1) {
                    if (colonne == 0) {
                        plateau_Goban[ligne][colonne] = '└';
                    }
                    if (colonne != 0 && colonne != plateau_Goban[ligne].length - 1) {
                        plateau_Goban[ligne][colonne] = '┴';
                    }
                    if (colonne == (plateau_Goban[ligne].length - 1)) {
                        plateau_Goban[ligne][colonne] = '┘';
                    }
                }
            }
        }*/
        return plateau_Goban;
    }
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
    public static char pierreActuel(int JoueurActuelle, char pierre_noir, char pierre_blanc){
        char pierre;
        if (JoueurActuelle == 1 ){
            pierre = pierre_blanc ;
        }
        else
            pierre = pierre_noir;
        return pierre;
    }
    // retourne vrai si emplacement vide
    public static boolean EmplacementVide(char[][] plateau, int x, int y, char pierre) {
        return plateau[x][y] != PIERREBLANCHE && plateau[x][y] != PIERRENOIRE;
    }
    public static boolean verifierDehorsDesLimites(char[][] plateau, int x, int y) {
        return x < 0 || x >= plateau.length || y < 0 || y >= plateau.length;
    }
    public static boolean verifierSiPierrePoser (char[][] plateau, int x, int y, char pierre){
        return !verifierDehorsDesLimites(plateau, x, y) && EmplacementVide (plateau, x, y,  pierre);
    }
    public static void poserPierre(char[][] plateau , int x, int y, char pierre ,int JoueurActuelle){
        if(verifierSiPierrePoser(plateau, x, y, pierre)){
            plateau[x][y] = pierre;
        }
    }
    public static boolean GobanRempli(char[][] plateau){
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau.length; j++) {
                if (plateau[i][j] == EMPLACEMENTVIDE) {
                    return true;
                }
            }
        }
        return false;
    }
}
