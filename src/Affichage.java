public class Affichage {

    static final String SYMBOLE_NOIR = "○";
    static final String SYMBOLE_BLANC = "●";

    // Méthode qui recois en parametre le plateau de jeu et l'affiche
    public static void AffichageGoban(int[][] plateau) {
        int taille = plateau.length;
        // appel de la méthode qui affiche les coordonnées X
        afficherCoordX(taille);

        for (int i = 0; i < taille; i++) {

            afficherLigneDeJeu(plateau[i], i, taille);

            // On affiche les barres verticales sauf après la dernière ligne
            if (i < taille - 1) {
                afficherInterLigne(taille);
            }
        }
    }

    //Affiche les numéros de colonnes (0, 1, 2...) tout en haut
    public static void afficherCoordX(int taille) {
        System.out.print("   "); // Espace initial pour s'aligner avec les numéros de ligne
        for (int i = 0; i < taille; i++) {
            // "   " ou "  " sert à garder l'alignement si le chiffre > 9
            System.out.print(i + (i < 10 ? "    " : "   ")); // if en une seule ligne vu dans une documentation
        }
        System.out.println();
    }

    // Affiche une ligne complète (Numéro + Pierres + Traits horizontaux)
    public static void afficherLigneDeJeu(int[] lignePlateau, int numeroLigne, int taille) {
        // Affichage du numéro de ligne à gauche
        if (numeroLigne < 10) {
            // Si le chiffre est petit (0-9), on met 2 espaces pour ne pas avoir de décalage pendant l'affichage
            System.out.print(numeroLigne + "  ");
        } else {
            // Si le nombre a deux chiffres (10+), on ne met qu'un seul espace
            System.out.print(numeroLigne + " ");
        }

        for (int col = 0; col < taille; col++) {
            // On récupère le bon symbole (Pierre ou Trait)
            String symbole = recupererSymbole(lignePlateau[col], numeroLigne, col, taille);

            System.out.print(symbole);

            // Trait horizontal de liaison (sauf pour la dernière colonne)
            if (col < taille - 1) {
                System.out.print("────");
            }
        }
        System.out.println(); // Retour à la ligne à la fin de la rangée
    }

    // Affiche les barres verticales entre les lignes ("|    |")
    public static void afficherInterLigne(int taille) {
        System.out.print("   "); // Espace pour s'aligner sous les pierres
        for (int j = 0; j < taille; j++) {
            System.out.print("|    ");
        }
        System.out.println();
    }

    // Choisit ce qu'on doit dessiner dans la case (Noir, Blanc, ou Intersection )
    public static String recupererSymbole(int valeurCase, int ligne, int col, int taille) {
        if (valeurCase == MethodePlateau.NOIR) {
            return SYMBOLE_NOIR;
        } else if (valeurCase == MethodePlateau.BLANC) {
            return SYMBOLE_BLANC;
        } else {
            // Si c'est vide, on dessine l'intersection appropriée (coin, bord, centre)
            return RecupereLeCaractereDuTableau(ligne, col, taille);
        }
    }

    // Fonction qui retourne le caractere associe a l'intersection du tableau que l'on veut afficher
    public static String RecupereLeCaractereDuTableau(int ligne, int col, int taille) {
        // Première ligne (Haut)
        if (ligne == 0) {
            if (col == 0) return "┌";           // Coin Haut-Gauche
            if (col == taille - 1) return "┐";  // Coin Haut-Droite
            return "┬";                         // Bord Haut
        }
        // Dernière ligne (Bas)
        else if (ligne == taille - 1) {
            if (col == 0) return "└";           // Coin Bas-Gauche
            if (col == taille - 1) return "┘";  // Coin Bas-Droite
            return "┴";                         // Bord Bas
        }
        // Lignes du milieu
        else {
            if (col == 0) return "├";           // Bord Gauche
            if (col == taille - 1) return "┤";  // Bord Droit
            return "┼";                         // Centre
        }
    }
}
