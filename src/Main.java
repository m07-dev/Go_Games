public class Main {
    public static void lancerPartie(char[][] Goban) {
        boolean finDeJeu = false;
        int JoueurActuel = 2;
        int passeTour = 0;
        char pierre = MethodePlateau.pierreActuel(JoueurActuel, MethodePlateau.PIERRENOIRE, MethodePlateau.PIERREBLANCHE);
        int tailleGroupe = 1;
        boolean coupValide = false;

        // boucle principale du jeu qui continue tant que la partie n'est pas finie

        while (!finDeJeu) {

            //Boucle ( DO WHILE ) qui demande la saisie au joueur les coordones de son coup et qui verifie s'il est possible
            do {
                // creation d'un tableau rempli de false que l'on utilise pour compter la taille des groupes

                boolean[][] Visitee = Jeu.CreationTableauGroupeVisitee(Goban);

                // demande a l'utilisateur de saisir les coordonee

                int[] coup = Jeu.demanderCoup(JoueurActuel, Goban);
                int x = coup[0];
                int y = coup[1];

                // joueur passe son tour

                if(coup[0] == -1 && coup[1] == -1){
                    passeTour++;
                    coupValide = true;

                }

                // verification des coordonees
                else {
                    /// le coup est valide
                    pierre = MethodePlateau.pierreActuel(JoueurActuel, MethodePlateau.PIERRENOIRE, MethodePlateau.PIERREBLANCHE);
                    if (MethodePlateau.verifierSiPierrePoser(Goban, x, y, pierre)) {

                        // place le pion du joueur au coordonné choisis.

                        MethodePlateau.poserPierre(Goban, x, y, pierre, JoueurActuel);

                        // ameliorer car on a deja changerjoueur
                        char ennemi;
                        if (pierre == MethodePlateau.PIERRENOIRE) {
                            ennemi = MethodePlateau.PIERREBLANCHE;
                        } else {
                            ennemi = MethodePlateau.PIERRENOIRE;
                        }

                        // PRÉPARER LES 4 VOISINS (Nord, Sud, Est, Ouest)
                        // On met leurs coordonnées dans un petit tableau pour pouvoir faire une boucle
                        int[][] voisins = {
                                {x, y - 1}, // Nord
                                {x, y + 1}, // Sud
                                {x - 1, y}, // Ouest
                                {x + 1, y}  // Est
                        };

                        //on regarde pour chaque voisin
                        for (int i = 0; i < 4; i++) {
                            int vX = voisins[i][0]; // Coordonnée X du voisin
                            int vY = voisins[i][1]; // Coordonnée Y du voisin

                            // verifie si ennemie ou en dehors du plateau
                            if (!MethodePlateau.verifierDehorsDesLimites(Goban, vX, vY) && Goban[vX][vY] == ennemi) {


                                boolean[][] visiteMemoire = Jeu.CreationTableauGroupeVisitee(Goban);

                                if (Jeu.estGroupeVivant(Goban, vX, vY, ennemi, visiteMemoire) == false) {
                                    // groupe mort on supprime
                                    System.out.println("Capture !");
                                    Jeu.supprimerGroupe(Goban, vX, vY, ennemi);
                                }
                            }
                        }
                        // compte la taille du groupe contenant le pion qui vient d'etre posée
                        tailleGroupe = Jeu.CompterGroupe(Goban,x,y,pierre,Visitee);
                        coupValide = true;
                    } else {
                        // le coup n'est valide.
                        System.out.println("Ce coup est invalide");
                        coupValide = false;
                    }
                    passeTour = 0;
                }
                // si les deux joueurs passent leur tour simultanément la boucle principale prend fin
                if(passeTour == 2){
                    System.out.println("Vous avez tous deux passer votre tour c'est la fin du JEU");
                    finDeJeu = true;
                }

            }while(!coupValide);

            // une fois le coup joue, on affiche le plateau actuel

            MethodePlateau.AffichageGoban(Goban);

            //

            System.out.println(tailleGroupe + " est la taille de la pierre : " + pierre);

            // changement de joueur a chaque tour

            JoueurActuel = Jeu.changerJoueur(JoueurActuel);

        }
    }
}