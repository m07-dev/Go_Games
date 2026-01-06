public class MethodePlateau {

    // Définition des constantes
    // 0 = Vide, 1 = Noir, 2 = Blanc, 3 = SUICIDE
    public static final int VIDE = 0;
    public static final int NOIR = 1;
    public static final int BLANC = 2;
    public static final int SUICIDE = 3;



    // Fonction qui crée et retourne le plateau goban vide (rempli de 0)
    public static int[][] creationGoban(int taille) {
        int[][] plateau = new int[taille][taille];

        // boucle qui remplit le tableau avec des 0 (VIDE)
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                plateau[ligne][colonne] = VIDE;
            }
        }
        return plateau;
    }

    // Fonction qui retourne le chiffre associé au joueur actuel
    // Joueur 1 → 1 (Noir), Joueur 2 -> 2 (Blanc)
    public static int pierreActuel(int JoueurActuel) {
        if (JoueurActuel == 1) {
            return NOIR;
        } else {
            return BLANC;
        }
    }

    // Fonction qui retourne vrai si l'emplacement aux coordonnées x et y est vide OU PIERRE SUPPRIMER(vaut 0)
    public static boolean EmplacementVide(int[][] plateau, int x, int y) {
        return plateau[x][y] == VIDE || plateau[x][y] == SUICIDE;
    }

    // Fonction qui retourne vrai si l'emplacement choisi est en dehors du tableau
    public static boolean verifierDehorsDesLimites(int[][] plateau, int x, int y) {
        return x < 0 || x >= plateau.length || y < 0 || y >= plateau.length ;
    }

    // Fonction qui retourne vrai si on peut poser la pierre (dans les limites ET vide)
    public static boolean verifierSiPierrePoser(int[][] plateau, int x, int y) {
        return !verifierDehorsDesLimites(plateau, x, y) && EmplacementVide(plateau, x, y);
    }

    // Methode qui place le chiffre du joueur (1 ou 2) aux coordonnées demandées
    public static void poserPierre(int[][] plateau, int x, int y, int pierre) {
        if (verifierSiPierrePoser(plateau, x, y)) {
            plateau[x][y] = pierre;
        }
    }

    // Fonction qui retourne vrai si le plateau est plein (plus aucune case VIDE)
    public static boolean GobanRempli(int[][] plateau) {
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau.length; j++) {
                if (plateau[i][j] == VIDE) {
                    return false;
                }
            }
        }
        return true;
    }
}
