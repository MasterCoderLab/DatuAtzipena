public class KaixoMundua {

    public static void main(String[] args) {

        boolean b;
        b = true;

        System.out.println("Kaixo Mundua, aurreneko adibidea naiz");
        System.out.println(Math.sqrt(-1));
        System.out.println(1.1e200 * 1.1e200);
        System.out.println(-1.1e200 * 1.1e200);

        /*
         * Flotatzaile motako hiru balio berezi daude:
         * infinitu positibo, infinitu negatibo eta ez zenbaki bat.
         * Puntu flotatzaileko eragiketa batek ordenagailuaren ahalmena
         * (overflow) gainditzen duenean sortzen dira.
         */

        if (b)
            System.out.println("Es cierto");
    }
}