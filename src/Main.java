public class Main {
    public static void lancerPartie(int[][] Goban,String nomJ1, String nomJ2) {
        boolean finDeJeu = false;
        int JoueurActuel = 2;
        int passeTour = 0;
        int pierre = MethodePlateau.pierreActuel(JoueurActuel);
        int tailleGroupe = 1;
        boolean coupValide = false;
        int capturesNoir = 0;
        int capturesBlanc = 0;
        // Boucle qui gére la partie
        while (!finDeJeu) {
            String nomActuel = (JoueurActuel == 1) ? nomJ1 : nomJ2; // if sur une seul ligne ( vue en doc )
            // demande de saisie du coup et verification si celui si est valide ,et enregistre l'impact  qu'il aura sur le jeu
            do {
                int[] coup = Jeu.demanderCoup(nomActuel, Goban);
                int x = coup[0];
                int y = coup[1];

                // le joueur passe son tour
                if (coup[0] == -1 && coup[1] == -1) {
                    passeTour++;
                    coupValide = true;
                    System.out.println("Le joueur " + JoueurActuel + " passe son tour.");
                }
                // le joueur joue
                else {
                    pierre = MethodePlateau.pierreActuel(JoueurActuel);


                    if (MethodePlateau.verifierSiPierrePoser(Goban, x, y)) {

                        // On pose la pierre
                        MethodePlateau.poserPierre(Goban, x, y, pierre);

                        // Voir la capture

                        int ennemi;
                        if (pierre == MethodePlateau.NOIR) {
                            ennemi = MethodePlateau.BLANC;
                        } else {
                            ennemi = MethodePlateau.NOIR;
                        }

                        int[][] voisins = {{x, y - 1}, {x, y + 1}, {x - 1, y}, {x + 1, y}};

                        for (int i = 0; i < 4; i++) {
                            int vX = voisins[i][0];
                            int vY = voisins[i][1];

                            if (!MethodePlateau.verifierDehorsDesLimites(Goban, vX, vY) && Goban[vX][vY] == ennemi) {
                                boolean[][] visiteMemoire = Jeu.CreationTableauGroupeVisitee(Goban);
                                if (!Jeu.estGroupeVivant(Goban, vX, vY, ennemi, visiteMemoire)) {
                                    System.out.println("Capture !");
                                    // On récupère le nombre de pierres capturé
                                    int nbPierresMangees = Jeu.supprimerGroupe(Goban, vX, vY, ennemi);

                                    // On ajoute au score du joueur actuel
                                    if (pierre == MethodePlateau.NOIR) {
                                        capturesNoir += nbPierresMangees;
                                    } else {
                                        capturesBlanc += nbPierresMangees;
                                    }
                                }
                            }
                        }

                        // verification sucide
                        boolean estSuicide = false;
                        // On vérifie le suicide après la capture potentielle
                        boolean[][] visiteSuicide = Jeu.CreationTableauGroupeVisitee(Goban);

                        if (!Jeu.estGroupeVivant(Goban, x, y, pierre, visiteSuicide)) {
                            System.out.println(">> COUP INTERDIT : Suicide !");
                            // On remet la case à VIDE (0)
                            Goban[x][y] = MethodePlateau.VIDE;
                            estSuicide = true;
                        }

                        if (estSuicide) {
                            coupValide = false;
                        } else {
                            coupValide = true;
                            passeTour = 0;
                        }

                    } else {
                        System.out.println(">> ERREUR : Case occupée ou hors limite.");
                        coupValide = false;
                    }
                }

                // Si les deux joueurs passe leur tour la partie prend fin
                if (passeTour >= 2) {
                    System.out.println("Les deux joueurs ont passé. FIN DU JEU.");
                    finDeJeu = true;
                    coupValide = true;
                } else if (MethodePlateau.GobanRempli(Goban)) {
                    System.out.println("Le plateau est complet ! FIN DU JEU.");
                    finDeJeu = true;
                    coupValide = true;

                }
            } while(!coupValide && !finDeJeu);

            if (!finDeJeu) {
                Affichage.AffichageGoban(Goban);
                // Affichage Des prisonniers à chaque tour
                System.out.println("Prisonniers - Noir: " + capturesNoir + " | Blanc: " + capturesBlanc);

                JoueurActuel = Jeu.changerJoueur(JoueurActuel);
            }
        }

        // Calcul du score final puis affichage des resultats
        System.out.println("Calcul du score final...");
        Jeu.calculerScoreFinal(Goban, capturesNoir, capturesBlanc, nomJ1, nomJ2);
    }
}
