public class Main {
    // Note : On reçoit maintenant un int[][] (tableau d'entiers)
    public static void lancerPartie(int[][] Goban) {
        boolean finDeJeu = false;
        int JoueurActuel = 2;
        int passeTour = 0;
        int pierre = MethodePlateau.pierreActuel(JoueurActuel);
        int tailleGroupe = 1;
        boolean coupValide = false;

        // NOUVEAU : Variables pour compter les prisonniers
        int capturesNoir = 0;
        int capturesBlanc = 0;

        while (!finDeJeu) {
            do {
                // boolean[][] Visitee = Jeu.CreationTableauGroupeVisitee(Goban);

                int[] coup = Jeu.demanderCoup(JoueurActuel, Goban);
                int x = coup[0];
                int y = coup[1];

                // 1. CAS : LE JOUEUR PASSE
                if (coup[0] == -1 && coup[1] == -1) {
                    passeTour++;
                    coupValide = true;
                    System.out.println("Le joueur " + JoueurActuel + " passe son tour.");
                }
                // 2. CAS : LE JOUEUR JOUE
                else {
                    pierre = MethodePlateau.pierreActuel(JoueurActuel);

                    // MAJ : verifierSiPierrePoser ne prend plus 'pierre' en argument (plus besoin)
                    if (MethodePlateau.verifierSiPierrePoser(Goban, x, y)) {

                        // A. On pose la pierre (poserPierre prend 4 arguments maintenant).
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
                                    // NOUVEAU : On récupère le nombre de pierres mangées
                                    int nbPierresMangees = Jeu.supprimerGroupe(Goban, vX, vY, ennemi);

                                    // NOUVEAU : On ajoute au score du joueur actuel
                                    if (pierre == MethodePlateau.NOIR) {
                                        capturesNoir += nbPierresMangees;
                                    } else {
                                        capturesBlanc += nbPierresMangees;
                                    }
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

                // Gestion de la fin de partie passerTour ou le Goban est rempli
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
                // NOUVEAU : On peut afficher les prisonniers à chaque tour
                System.out.println("Prisonniers - Noir: " + capturesNoir + " | Blanc: " + capturesBlanc);

                JoueurActuel = Jeu.changerJoueur(JoueurActuel);
            }
        }

        // --- FIN DU JEU ---

        // Le calcul final
        System.out.println("Calcul du score final...");
        Jeu.calculerScoreFinal(Goban, capturesNoir, capturesBlanc);
    }
}