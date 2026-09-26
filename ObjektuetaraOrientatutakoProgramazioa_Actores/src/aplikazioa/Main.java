package aplikazioa;

import java.util.ArrayList;
import java.util.Scanner;

import datuak.ActorDatuak;
import eredua.Actor;
import kudeaketa.ActorKudeaketa;

/**
 * Aktoreen kudeaketa-aplikazioaren klase nagusia.
 *
 * Klase honen ardura programa abiaraztea eta
 * erabiltzailearen menu nagusia kudeatzea da.
 */
public class Main {

    // Teklatuko sarrera kudeatzeko Scanner objektua
    static Scanner sc = new Scanner(System.in);

    /**
     * Programaren abiapuntua.
     *
     * Hasierako aktoreen zerrenda eskuratzen du,
     * menu nagusia abiarazten du eta amaieran Scanner-a ixten du.
     *
     * @param args komando-lerroko argumentuak
     */
    public static void main(String[] args) {

        ArrayList<Actor> actores =
                ActorDatuak.hasierakoAktoreakSortu();

        menu(actores);

        sc.close();
    }

    /**
     * Programaren menu nagusia erakusten du eta
     * erabiltzaileak aukeratutako eragiketa exekutatzen du.
     *
     * @param actores programan kudeatuko den aktoreen zerrenda
     */
    static void menu(ArrayList<Actor> actores) {

        int aukera;

        do {

            System.out.println();
            System.out.println("===== AKTOREEN MENUA =====");
            System.out.println("1. Aktore guztiak zerrendatu");
            System.out.println("2. Aktore bat kodearen bidez bilatu");
            System.out.println("3. Bi daten arteko aktoreak zerrendatu");
            System.out.println("4. Aktore berri bat gehitu");
            System.out.println("5. Aktore bat ezabatu");
            System.out.println("6. Aktoreak izenaren arabera ordenatu");
            System.out.println("7. Aktore bat editatu");
            System.out.println("0. Irten");

            aukera =
                    ActorKudeaketa.zenbakiOsoaIrakurri(
                            sc,
                            "Aukeratu aukera bat: ");

            switch (aukera) {

                case 1:
                    ActorKudeaketa.aktoreakZerrendatu(
                            actores);
                    break;

                case 2:
                    ActorKudeaketa.aktoreaKodezBilatu(
                            actores,
                            sc);
                    break;

                case 3:
                    ActorKudeaketa.aktoreakDatenArteanZerrendatu(
                            actores,
                            sc);
                    break;

                case 4:
                    ActorKudeaketa.aktoreaGehitu(
                            actores,
                            sc);
                    break;

                case 5:
                    ActorKudeaketa.aktoreaEzabatu(
                            actores,
                            sc);
                    break;

                case 6:
                    ActorKudeaketa.aktoreakOrdenatu(
                            actores);
                    break;

                case 7:
                    ActorKudeaketa.aktoreaEditatu(
                            actores,
                            sc);
                    break;

                case 0:
                    System.out.println(
                            "Programa amaitu da.");
                    break;

                default:
                    System.out.println(
                            "Aukera ez da zuzena.");
                    break;
            }

        } while (aukera != 0);
    }
}