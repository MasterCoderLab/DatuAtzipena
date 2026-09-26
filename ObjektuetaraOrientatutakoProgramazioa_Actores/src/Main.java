import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Aktore guztiak gordetzeko ArrayList-a
        ArrayList<Actor> actores = new ArrayList<>();

        actores.add(new Actor(
                1,
                "Kevin Costner",
                LocalDate.of(1955, 1, 18),
                "Lywood, California",
                "Estados Unidos",
                null,
                null,
                true
        ));
        
        System.out.println(actores.get(0));
    }
}