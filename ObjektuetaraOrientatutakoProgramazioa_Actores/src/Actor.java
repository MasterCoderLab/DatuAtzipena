
import java.time.LocalDate;

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
    
    // Aktore berri bat sortzeko eraikitzailea
    public Actor(int codigo, String nombre, LocalDate fechaNacimiento,
            String lugarNacimiento, String nacionalidad,
            LocalDate fechaMuerte, String lugarMuerte, boolean vivo) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.nacionalidad = nacionalidad;
        this.fechaMuerte = fechaMuerte;
        this.lugarMuerte = lugarMuerte;
        this.vivo = vivo;
    }
    
    // Aktorearen datuak eskuratzeko metodoak
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public LocalDate getFechaMuerte() {
        return fechaMuerte;
    }

    public String getLugarMuerte() {
        return lugarMuerte;
    }

    public boolean isVivo() {
        return vivo;
    }
    
 // Aktorearen datuak aldatzeko metodoak
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setFechaMuerte(LocalDate fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public void setLugarMuerte(String lugarMuerte) {
        this.lugarMuerte = lugarMuerte;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
 // Aktorearen informazio guztia testu batean itzultzeko metodoa
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