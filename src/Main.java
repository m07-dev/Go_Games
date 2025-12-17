public class Main {
    public static void lancerPartie(char[][] Goban) {
        boolean finDeJeu = false;
        int JoueurActuel = 1;
        int passeTour = 0;
        char pierre = MethodePlateau.pierreActuel(JoueurActuel, 'N', 'B');
        boolean coupValide = false;
        while (!finDeJeu) {
            do {
                int[] coup = Jeu.demanderCoup(JoueurActuel, Goban);
                int x = coup[0];
                int y = coup[1];
                // verifier dans x ou y si il y a null et incrementer le compteur de 1
                if(coup[0] == -1 && coup[1] == -1){
                    passeTour++;

                }else {
                    pierre = MethodePlateau.pierreActuel(JoueurActuel, 'N', 'B');
                    if (MethodePlateau.verifierSiPierrePoser(Goban, x, y, pierre)) {
                        MethodePlateau.poserPierre(Goban, x, y, pierre, JoueurActuel);
                        coupValide = true;
                    } else {
                        System.out.println("Ce coup est invalide");
                        coupValide = false;
                    }
                    passeTour = 0;
                }
                if(passeTour == 2){
                    System.out.println("Vous avez tous deux passer votre tour c'est la fin du JEU");
                    coupValide = true;
                    finDeJeu = true;
                }git add
                finDeJeu = MethodePlateau.GobanRempli(Goban);
                // Probleme n°2 Changer la fonction Emplacement vide, faire utiliser des parametre au lieu de 'N' et 'B' car possiblilté de demander a l'users de choisir ces pierre
            JoueurActuel = Jeu.changerJoueur(JoueurActuel);
            }while(!coupValide);
            MethodePlateau.AffichageGoban(Goban);

        }
    }


}
