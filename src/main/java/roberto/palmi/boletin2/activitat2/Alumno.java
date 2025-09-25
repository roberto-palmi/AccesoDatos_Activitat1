package roberto.palmi.boletin2.activitat2;

import java.util.Date;

public class Alumno {
    private String nia, nombre, apellido1, apellido2;
    private Date fechaNac;

    public  Alumno(String nia, String nombre, String apellido1, String apellido2, Date fechaNac) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNac = fechaNac;
    }
    public String getNia() {
        return nia;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido1() {
        return apellido1;
    }
    public String getApellido2() {
        return apellido2;
    }
    public Date getFechaNac() {
        return fechaNac;
    }
}
