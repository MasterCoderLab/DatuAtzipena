package fitxategiak.aplikazioa;

import java.io.File;
import java.util.Scanner;

import fitxategiak.kudeaketa.AusazkoFitxategiKudeatzailea;

/**
 * Programaren abiapuntua da.
 */
public class Main {

    /**
     * Fitxategiaren bidea argumentu gisa hartu eta menua abiarazten du.
     *
     * @param args fitxategiaren izena eta bidea
     */
    public static void main(String[] args) {
        if (args.length != 1 || args[0].isBlank()) {
            System.out.println("Erabilera: java fitxategiak.aplikazioa.Main <fitxategiaren_bidea>");
            return;
        }

        File fitxategia = new File(args[0]);
        AusazkoFitxategiKudeatzailea kudeatzailea =
                new AusazkoFitxategiKudeatzailea(fitxategia);

        try (Scanner teklatua = new Scanner(System.in)) {
            new Menua(teklatua, kudeatzailea).exekutatu();
        }
    }
}
