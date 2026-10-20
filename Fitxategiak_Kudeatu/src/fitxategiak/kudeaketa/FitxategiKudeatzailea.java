
package fitxategiak.kudeaketa;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import fitxategiak.eredua.Erregistroa;

/**
 * Fitxategiak eta direktorioak kudeatzeko klasea.
 *
 * java.io.File klasearen bidez fitxategi-eragiketak egiten ditu.
 * Emaitzak itzultzen ditu eta erroreak salbuespenen bidez
 * jakinarazten ditu, Menua klaseak kontsolan erakusteko.
 */
public class FitxategiKudeatzailea {

    private File lanDirektorioa;

    /**
     * Kudeatzailea sortzen du, hasierako direktorioa erabiliz.
     */
    public FitxategiKudeatzailea() {
        lanDirektorioa = new File(System.getProperty("user.dir"));
    }

    /**
     * Uneko lan-direktorioa itzultzen du.
     *
     * @return lan-direktorioa
     */
    public File getLanDirektorioa() {
        return lanDirektorioa;
    }

    /**
     * Lan-direktorioa aldatzen du (CD).
     * Bide erlatiboak uneko direktoriotik ebazten dira.
     *
     * @param bidea direktorio berriaren bidea
     * @return hautatutako lan-direktorioa
     * @throws IOException direktorioa erabilgarria ez bada
     */
    public File aldatuLanDirektorioa(String bidea) throws IOException {

        if (bidea == null) {
            throw new IllegalArgumentException("Bidea ezin da null izan.");
        }

        String bideGarbia = bidea.trim();
        File direktorioBerria;

        if (bideGarbia.isEmpty()) {
            direktorioBerria = lanDirektorioa;
        } else {
            direktorioBerria = new File(bideGarbia);

            if (!direktorioBerria.isAbsolute()) {
                direktorioBerria = new File(lanDirektorioa, bideGarbia);
            }
        }

        // Bide kanonikoak "." eta ".." osagaiak ebazten ditu.
        direktorioBerria = direktorioBerria.getCanonicalFile();

        if (!direktorioBerria.isDirectory()
                || !direktorioBerria.canRead()
                || direktorioBerria.listFiles() == null) {
            throw new IOException(
                    "Direktorioa ez da existitzen edo ezin da irakurri: "
                            + direktorioBerria.getPath());
        }

        // Egiaztapena gainditu ondoren bakarrik aldatzen da.
        lanDirektorioa = direktorioBerria;

        return lanDirektorioa;
    }

    /**
     * Lan-direktorioko fitxategiak eta direktorioak itzultzen ditu (DIR).
     *
     * @return direktorioko elementuak
     * @throws IOException edukia ezin bada irakurri
     */
    public File[] zerrendatu() throws IOException {

        File[] elementuak = lanDirektorioa.listFiles();

        // null: irakurketa-errorea; array hutsa: direktorio hutsa.
        if (elementuak == null) {
            throw new IOException(
                    "Ezin izan da direktorioaren edukia irakurri.");
        }

        return elementuak;
    }

    /**
     * Direktorio berri bat sortzen du eta lan-direktorio bihurtzen du.
     *
     * @param izena direktorio berriaren izena
     * @return sortutako direktorioa
     * @throws IOException direktorioa ezin bada sortu
     */
    public File sortuDirektorioa(String izena) throws IOException {

        egiaztatuLanDirektorioa(true);

        String izenGarbia = baliozkotuIzena(izena);
        File direktorioBerria = new File(lanDirektorioa, izenGarbia);

        if (direktorioBerria.exists()) {
            throw new IOException(
                    "Izen hori duen elementua lehendik existitzen da.");
        }

        // mkdir() metodoak false itzultzen du huts egiten badu.
        if (!direktorioBerria.mkdir()) {
            throw new IOException(
                    "Ezin izan da direktorioa sortu.");
        }

        if (!direktorioBerria.canRead()
                || direktorioBerria.listFiles() == null) {
            throw new IOException(
                    "Direktorioa sortu da, baina ezin da erabili.");
        }

        lanDirektorioa = direktorioBerria.getCanonicalFile();

        return lanDirektorioa;
    }

    /**
     * Erregistro baten .aad fitxategia sortzen du.
     *
     * @param erregistroa datu-baseko erregistroa
     * @return true sortu bada; false lehendik existitzen bada
     * @throws IOException fitxategia ezin bada sortu
     */
    public boolean sortuAadFitxategia(Erregistroa erregistroa)
            throws IOException {

        if (erregistroa == null) {
            throw new IllegalArgumentException("Erregistro baliogabea.");
        }

        egiaztatuLanDirektorioa(true);

        String izena = baliozkotuIzena(erregistroa.getIzena()) + ".aad";
        File fitxategia = new File(lanDirektorioa, izena);

        // createNewFile() metodoak ez ditu fitxategiak gainidazten.
        if (fitxategia.createNewFile()) {
            return true;
        }

        if (fitxategia.isFile()) {
            return false;
        }

        throw new IOException(
                "Helburua ez da fitxategi arrunta: " + izena);
    }

    /**
     * Lan-direktorioko .aad fitxategiak eskuratzen ditu.
     *
     * @return .aad luzapena duten fitxategiak
     * @throws IOException direktorioa ezin bada irakurri
     */
    public File[] aadFitxategiak() throws IOException {

        File[] fitxategiak = lanDirektorioa.listFiles(
                elementua -> elementua.isFile()
                        && elementua.getName()
                                .toLowerCase(Locale.ROOT)
                                .endsWith(".aad"));

        if (fitxategiak == null) {
            throw new IOException(
                    "Ezin izan da direktorioaren edukia irakurri.");
        }

        return fitxategiak;
    }

    /**
     * Lan-direktorioko .aad fitxategi bat ezabatzen du.
     *
     * @param fitxategia ezabatu beharreko fitxategia
     * @throws IOException fitxategia ezin bada ezabatu
     */
    public void ezabatuAadFitxategia(File fitxategia)
            throws IOException {

        egiaztatuLanDirektorioa(true);

        if (fitxategia == null
                || !fitxategia.isFile()
                || !fitxategia.getName()
                        .toLowerCase(Locale.ROOT)
                        .endsWith(".aad")) {
            throw new IllegalArgumentException(
                    "Ez da .aad fitxategi balioduna.");
        }

        // Lan-direktoriotik kanpoko fitxategiak ezabatzea eragozten da.
        File gurasoa = fitxategia.getAbsoluteFile()
                .getParentFile().getCanonicalFile();

        if (!gurasoa.equals(lanDirektorioa.getCanonicalFile())) {
            throw new IOException(
                    "Fitxategia lan-direktoriotik kanpo dago.");
        }

        // delete() metodoaren emaitza egiaztatzen da.
        if (!fitxategia.delete()) {
            throw new IOException(
                    "Ezin izan da ezabatu: " + fitxategia.getName());
        }
    }

    /**
     * Lan-direktorioa eta beharrezko baimenak egiaztatzen ditu.
     *
     * @param idazteko true idazteko baimena behar bada
     * @throws IOException direktorioa erabilgarria ez bada
     */
    private void egiaztatuLanDirektorioa(boolean idazteko)
            throws IOException {

        if (!lanDirektorioa.isDirectory()
                || !lanDirektorioa.canRead()
                || (idazteko && !lanDirektorioa.canWrite())) {
            throw new IOException(
                    "Lan-direktorioa ez dago erabilgarri edo baimenik ez dago.");
        }
    }

    /**
     * Fitxategi edo direktorio baten izena egiaztatzen du.
     *
     * @param izena egiaztatu beharreko izena
     * @return baliozkotutako izena
     * @throws IllegalArgumentException izena baliogabea bada
     */
    private String baliozkotuIzena(String izena) {

        if (izena == null || izena.isBlank()) {
            throw new IllegalArgumentException(
                    "Izena ezin da hutsik egon.");
        }

        String izenGarbia = izena.trim();

        if (izenGarbia.equals(".") || izenGarbia.equals("..")) {
            throw new IllegalArgumentException(
                    "Izen hori ezin da erabili.");
        }

        // Bide-banatzaileak eta karaktere baliogabeak baztertzen dira.
        for (char karakterea : izenGarbia.toCharArray()) {
            if (Character.isISOControl(karakterea)
                    || "/\\:*?\"<>|".indexOf(karakterea) >= 0) {
                throw new IllegalArgumentException(
                        "Izenak karaktere baliogabeak ditu.");
            }
        }

        return izenGarbia;
    }
}
