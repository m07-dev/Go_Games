public class launcher {

    public static void lancerPartie(char[][] Goban) {
        boolean PartieEstEnCours = true;
        while (PartieEstEnCours) {
            System.out.println("Joueur numero 1 c'est vous qui avez les pierres blanches c'est vous qui commencez !");
            //selection pierre
            //bougeagepirre
            Plateau.AffichageGoban(Goban);
            PartieEstEnCours=false;
        }
    }
}
