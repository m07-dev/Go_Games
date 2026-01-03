public class Main {
    // Note : On reçoit maintenant un int[][] (tableau d'entiers)
    public static void lancerPartie(int[][] Goban) {
        boolean finDeJeu = false;
        int JoueurActuel = 2;
        int passeTour = 0;

        // MAJ : 'pierre' est maintenant un int (1 ou 2)
        // La nouvelle fonction pierreActuel ne prend plus que le joueur en paramètre
        int pierre = MethodePlateau.pierreActuel(JoueurActuel);

        int tailleGroupe = 1;
        boolean coupValide = false;

        while (!finDeJeu) {
            do {
                // boolean[][] Visitee = Jeu.CreationTableauGroupeVisitee(Goban);

                int[] coup = Jeu.demanderCoup(JoueurActuel, Goban);
                int x = coup[0];
                int y = coup[1];

                // 1. CAS : LE JOUEUR PASSE
                if(coup[0] == -1 && coup[1] == -1){
                    passeTour++;
                    coupValide = true;
                    System.out.println("Le joueur " + JoueurActuel + " passe son tour.");
                }
                // 2. CAS : LE JOUEUR JOUE
                else {
                    pierre = MethodePlateau.pierreActuel(JoueurActuel);

                    // MAJ : verifierSiPierrePoser ne prend plus 'pierre' en argument (plus besoin)
                    if (MethodePlateau.verifierSiPierrePoser(Goban, x, y)) {

                        // A. On pose la pierre (poserPierre prend 4 arguments maintenant)
                        MethodePlateau.poserPierre(Goban, x, y, pierre);

                        // B. GESTION DES CAPTURES (Attaque)

                        // MAJ : On définit l'ennemi avec les constantes entières NOIR/BLANC
                        int ennemi = (pierre == MethodePlateau.NOIR) ? MethodePlateau.BLANC : MethodePlateau.NOIR;

                        int[][] voisins = {{x, y - 1}, {x, y + 1}, {x - 1, y}, {x + 1, y}};

                        for (int i = 0; i < 4; i++) {
                            int vX = voisins[i][0];
                            int vY = voisins[i][1];

                            if (!MethodePlateau.verifierDehorsDesLimites(Goban, vX, vY) && Goban[vX][vY] == ennemi) {
                                boolean[][] visiteMemoire = Jeu.CreationTableauGroupeVisitee(Goban);
                                if (Jeu.estGroupeVivant(Goban, vX, vY, ennemi, visiteMemoire) == false) {
                                    System.out.println("Capture !");
                                    Jeu.supprimerGroupe(Goban, vX, vY, ennemi);
                                }
                            }
                        }

                        // C. VÉRIFICATION SUICIDE (Défense)
                        boolean estSuicide = false;
                        // On vérifie le suicide APRÈS la capture potentielle
                        boolean[][] visiteSuicide = Jeu.CreationTableauGroupeVisitee(Goban);

                        if (Jeu.estGroupeVivant(Goban, x, y, pierre, visiteSuicide) == false) {
                            System.out.println(">> COUP INTERDIT : Suicide !");
                            // MAJ : On remet la case à VIDE (0)
                            Goban[x][y] = MethodePlateau.VIDE;
                            estSuicide = true;
                        }

                        // D. VALIDATION FINALE
                        if (estSuicide) {
                            coupValide = false;
                        } else {
                            coupValide = true;
                            passeTour = 0;
                            // Optionnel : compter le groupe
                            boolean[][] VisiteeCompte = Jeu.CreationTableauGroupeVisitee(Goban);
                            tailleGroupe = Jeu.CompterGroupe(Goban, x, y, pierre, VisiteeCompte);
                        }

                    } else {
                        System.out.println(">> ERREUR : Case occupée ou hors limite.");
                        coupValide = false;
                    }
                }

                // Gestion de la fin de partie
                if(passeTour >= 2){
                    System.out.println("Les deux joueurs ont passé. FIN DU JEU.");
                    finDeJeu = true;
                    coupValide = true;
                }

            } while(!coupValide && !finDeJeu);

            // Fin du tour, on affiche et on change de joueur
            if (!finDeJeu) {
                // MAJ : On utilise la classe Affichage pour dessiner
                Affichage.AffichageGoban(Goban);

                // Petit bonus : affiche la couleur en texte plutôt qu'en chiffre
                String couleur = (pierre == MethodePlateau.NOIR) ? "NOIR" : "BLANC";
                System.out.println("Taille du groupe (" + couleur + ") : " + tailleGroupe);

                JoueurActuel = Jeu.changerJoueur(JoueurActuel);
            }
        }
    }
}