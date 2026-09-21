package proleerdatos;

import java.util.Scanner;

/*
 * ProLeerDatos klaseak erabiltzaileari izena, adina eta garaiera
 * eskatzen dizkio Scanner erabiliz, eta datuak pantailan erakusten ditu.
 *
 * Galdera:
 * Zer gertatzen da parentesiak kentzen baditugu (adina + altuera)?
 *
 * Erantzuna:
 * Parentesiekin, adina eta altuera lehenengo matematikoki batzen dira.
 * Parentesirik gabe, String bat dagoenez, + operadoreak balioak
 * testu bezala kateatzen ditu eta ez ditu matematikoki batzen.
 */
public class ProLeerDatos {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String cadena;
        int adina;
        double altuera;

        System.out.println("Zure izena idatzi: ");
        cadena = sc.nextLine();

        System.out.println("Izena duzu: " + cadena);

        System.out.println("Zure adina idatzi: ");
        adina = sc.nextInt();

        System.out.println("Dauzkazu: " + adina + " urte");

        System.out.println("Idatzi zure garaiera hamartarrekin: ");
        altuera = sc.nextDouble();

        System.out.println("Neurria: " + altuera + " metro");

        System.out.println("Adina + altuera= " + (adina + altuera));

        sc.close();
    }
}