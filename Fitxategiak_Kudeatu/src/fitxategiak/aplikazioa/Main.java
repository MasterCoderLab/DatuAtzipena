package fitxategiak.aplikazioa;

import java.util.Scanner;

import fitxategiak.datuak.ErregistroDatuak;
import fitxategiak.eredua.Erregistroa;
import fitxategiak.kudeaketa.FitxategiKudeatzailea;

/**
 * Fitxategiak kudeatzeko programaren klase nagusia.
 */
public class Main {

	/**
	 * Programaren metodo nagusia.
	 *
	 * @param args komando-lerroko argumentuak
	 */
	public static void main(String[] args) {

		Scanner teklatua =
				new Scanner(System.in);

		FitxategiKudeatzailea kudeatzailea =
				new FitxategiKudeatzailea();

		Erregistroa[] datuBasea =
				ErregistroDatuak.sortuDatuBasea();

		Menua menua =
				new Menua(
						teklatua,
						kudeatzailea,
						datuBasea);

		menua.exekutatu();

		teklatua.close();
	}
}