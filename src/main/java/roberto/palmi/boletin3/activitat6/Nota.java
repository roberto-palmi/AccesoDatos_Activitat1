package roberto.palmi.boletin3.activitat6;

public class Nota {
    private final String asignatura;
    private final double nota;

    public Nota(String asignatura, double nota) {
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public double getNota() {
        return nota;
    }
}
