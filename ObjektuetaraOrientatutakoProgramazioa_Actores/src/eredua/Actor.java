package eredua;

import java.time.LocalDate;

/**
 * Aktore baten informazioa irudikatzen duen klasea.
 *
 * Klase honek aktorearen identifikazio-kodea, izena,
 * jaiotzari eta heriotzari buruzko datuak, nazionalitatea
 * eta bizirik dagoen ala ez gordetzen ditu.
 */
public class Actor {

    // Aktorearen datuak
    private int codigo;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String lugarNacimiento;
    private String nacionalidad;
    private LocalDate fechaMuerte;
    private String lugarMuerte;
    private boolean vivo;

    /**
     * Actor klaseko objektu berri bat sortzen du.
     *
     * @param codigo aktorearen identifikazio-kodea
     * @param nombre aktorearen izena
     * @param fechaNacimiento aktorearen jaiotze-data
     * @param lugarNacimiento aktorearen jaioterria
     * @param nacionalidad aktorearen nazionalitatea
     * @param fechaMuerte aktorearen heriotza-data; null izan daiteke
     * @param lugarMuerte aktorearen heriotza-lekua
     * @param vivo aktorea bizirik dagoen ala ez
     */
    public Actor(
            int codigo,
            String nombre,
            LocalDate fechaNacimiento,
            String lugarNacimiento,
            String nacionalidad,
            LocalDate fechaMuerte,
            String lugarMuerte,
            boolean vivo) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.nacionalidad = nacionalidad;
        this.fechaMuerte = fechaMuerte;
        this.lugarMuerte = lugarMuerte;
        this.vivo = vivo;
    }

    /**
     * Aktorearen kodea itzultzen du.
     *
     * @return aktorearen kodea
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Aktorearen izena itzultzen du.
     *
     * @return aktorearen izena
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Aktorearen jaiotze-data itzultzen du.
     *
     * @return aktorearen jaiotze-data
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Aktorearen jaioterria itzultzen du.
     *
     * @return aktorearen jaioterria
     */
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    /**
     * Aktorearen nazionalitatea itzultzen du.
     *
     * @return aktorearen nazionalitatea
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Aktorearen heriotza-data itzultzen du.
     *
     * @return heriotza-data edo null aktorea bizirik badago
     */
    public LocalDate getFechaMuerte() {
        return fechaMuerte;
    }

    /**
     * Aktorearen heriotza-lekua itzultzen du.
     *
     * @return aktorearen heriotza-lekua
     */
    public String getLugarMuerte() {
        return lugarMuerte;
    }

    /**
     * Aktorea bizirik dagoen ala ez adierazten du.
     *
     * @return true bizirik badago; false bestela
     */
    public boolean isVivo() {
        return vivo;
    }

    /**
     * Aktorearen kodea aldatzen du.
     *
     * @param codigo kode berria
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Aktorearen izena aldatzen du.
     *
     * @param nombre izen berria
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Aktorearen jaiotze-data aldatzen du.
     *
     * @param fechaNacimiento jaiotze-data berria
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Aktorearen jaioterria aldatzen du.
     *
     * @param lugarNacimiento jaioterri berria
     */
    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    /**
     * Aktorearen nazionalitatea aldatzen du.
     *
     * @param nacionalidad nazionalitate berria
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Aktorearen heriotza-data aldatzen du.
     *
     * @param fechaMuerte heriotza-data berria
     */
    public void setFechaMuerte(LocalDate fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    /**
     * Aktorearen heriotza-lekua aldatzen du.
     *
     * @param lugarMuerte heriotza-leku berria
     */
    public void setLugarMuerte(String lugarMuerte) {
        this.lugarMuerte = lugarMuerte;
    }

    /**
     * Aktorearen bizirik egoera aldatzen du.
     *
     * @param vivo egoera berria
     */
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    /**
     * Aktorearen informazio guztia testu-kate batean itzultzen du.
     *
     * @return aktorearen datu guztiak dituen testu-katea
     */
    @Override
    public String toString() {

        return "Kodea: " + codigo
                + ", Izena: " + nombre
                + ", Jaiotze data: " + fechaNacimiento
                + ", Jaioterria: " + lugarNacimiento
                + ", Nazionalitatea: " + nacionalidad
                + ", Heriotza data: " + fechaMuerte
                + ", Heriotza lekua: " + lugarMuerte
                + ", Bizirik: " + vivo;
    }
}