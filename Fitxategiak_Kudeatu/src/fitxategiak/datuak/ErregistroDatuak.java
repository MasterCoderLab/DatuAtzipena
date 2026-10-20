package fitxategiak.datuak;

import fitxategiak.eredua.Erregistroa;

/**
 * Programak erabiliko dituen erregistroen datuak
 * sortzeko klasea.
 */
public class ErregistroDatuak {

	/**
	 * Ikasturtearen hasieran esleitutako
	 * aktoreen datu-basea sortzen du.
	 *
	 * @return aktoreen erregistroen array-a
	 */
	public static Erregistroa[] sortuDatuBasea() {

		return new Erregistroa[] {

				new Erregistroa(
						1,
						"Kevin Costner",
						"1955-01-18",
						"Lywood, California",
						"Estados Unidos",
						"",
						""),

				new Erregistroa(
						2,
						"Héctor Alterio",
						"1929-10-21",
						"Buenos Aires",
						"Argentina",
						"",
						""),

				new Erregistroa(
						3,
						"Ricardo Darín",
						"1957-01-16",
						"Buenos Aires",
						"Argentina",
						"",
						""),

				new Erregistroa(
						4,
						"Viggo Mortensen",
						"1958-10-20",
						"Manhattan, New York",
						"Estados Unidos",
						"",
						""),

				new Erregistroa(
						5,
						"Liv Tyler",
						"1977-07-01",
						"Portland, Maine",
						"Estados Unidos",
						"",
						""),

				new Erregistroa(
						6,
						"Orlando Bloom",
						"1977-01-13",
						"Canterbury, Kent",
						"Gran Bretaña",
						"",
						""),

				new Erregistroa(
						7,
						"Javier Bardem",
						"1969-03-01",
						"Las Palmas de Gran Canaria",
						"España",
						"",
						""),

				new Erregistroa(
						8,
						"Belén Rueda",
						"1965-03-16",
						"Madrid",
						"España",
						"",
						""),

				new Erregistroa(
						9,
						"Lola Dueñas",
						"1929-04-16",
						"",
						"España",
						"",
						""),

				new Erregistroa(
						10,
						"Humphrey Bogart",
						"1899-12-25",
						"New York, New York",
						"Estados Unidos",
						"1957-01-14",
						"Los Angeles, C"),

				new Erregistroa(
						11,
						"Ingrid Bergman",
						"1915-08-29",
						"Stockholm",
						"Suecia",
						"1982-08-28",
						"Londres")
		};
	}
}