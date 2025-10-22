package roberto.palmi.boletin3.activitat6;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class Alumno {
    private String id;
    private String nombre;
    private boolean matriculado;
    private LocalDate fechaNacimiento;
    private HashMap<String , Double> notas;

    public Alumno(String id, String nombre, boolean matriculado, LocalDate fechaNacimiento, HashMap<String, Double> notas) {
        this.id = id;
        this.nombre = nombre;
        this.matriculado = matriculado;
        this.fechaNacimiento = fechaNacimiento;
        this.notas = notas;
    }

    public String getNombre() {
        return nombre;
    }

    public HashMap<String, Double> getNotas() {
        return notas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(id, alumno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
