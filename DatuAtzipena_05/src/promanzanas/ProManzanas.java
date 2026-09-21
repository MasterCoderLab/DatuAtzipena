package promanzanas;

/*
 * ProManzanas klaseak astelehenetik asteazkenera erositako sagar kopurua
 * array batean gordetzen du eta informazioa kontsolan erakusten du.
 */
public class ProManzanas {

    /*
     * Javan array guztiak objektuak dira.
     */

    public static void main(String[] args) {

        int manzanas[];

        manzanas = new int[3];

        manzanas[0] = 3;
        manzanas[1] = 6;
        manzanas[2] = 9;

        System.out.println("Compré " + manzanas[0] + " manzanas el lunes");
        System.out.println("Compré " + manzanas[1] + " manzanas el martes");
        System.out.println("Compré " + manzanas[2] + " manzanas el miércoles");

        System.out.println("Días de la semana " + manzanas.length);
    }
}