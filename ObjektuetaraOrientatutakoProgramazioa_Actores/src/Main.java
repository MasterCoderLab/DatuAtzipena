import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Aktore guztiak gordetzeko ArrayList-a
        ArrayList<Actor> actores = new ArrayList<>();

        // Hasierako aktoreak ArrayList-ean kargatzen ditu
        aktoreakKargatu(actores);

        // Lehen aktorea erakusteko behin-behineko proba
        System.out.println(actores.get(0));
    }

 // Hasierako aktoreen datuak ArrayList-ean kargatzen ditu
    static void aktoreakKargatu(ArrayList<Actor> actores) {

        actores.add(new Actor(
                1,
                "Kevin Costner",
                LocalDate.of(1955, 1, 18),
                "Lywood, California",
                "Estados Unidos",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                2,
                "Héctor Alterio",
                LocalDate.of(1929, 10, 21),
                "Buenos Aires",
                "Argentina",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                3,
                "Ricardo Darín",
                LocalDate.of(1957, 1, 16),
                "Buenos Aires",
                "Argentina",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                4,
                "Viggo Mortensen",
                LocalDate.of(1958, 10, 20),
                "Manhattan, New York",
                "Estados Unidos",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                5,
                "Liv Tyler",
                LocalDate.of(1977, 7, 1),
                "Portland, Maine",
                "Estados Unidos",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                6,
                "Orlando Bloom",
                LocalDate.of(1977, 1, 13),
                "Canterbury, Kent",
                "Gran Bretaña",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                7,
                "Javier Bardem",
                LocalDate.of(1969, 3, 1),
                "Las Palmas de Gran Canaria",
                "España",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                8,
                "Belén Rueda",
                LocalDate.of(1965, 3, 16),
                "Madrid",
                "España",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                9,
                "Lola Dueñas",
                LocalDate.of(1929, 4, 16),
                "",
                "España",
                null,
                "",
                true
        ));

        actores.add(new Actor(
                10,
                "Humphrey Bogart",
                LocalDate.of(1899, 12, 25),
                "New York, New York",
                "Estados Unidos",
                LocalDate.of(1957, 1, 14),
                "Los Angeles, C",
                false
        ));

        actores.add(new Actor(
                11,
                "Ingrid Bergman",
                LocalDate.of(1915, 8, 29),
                "Stockholm",
                "Suecia",
                LocalDate.of(1982, 8, 28),
                "Londres",
                false
        ));
    }
}