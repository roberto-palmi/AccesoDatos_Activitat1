package roberto.palmi.boletin3.activitat6;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        AlumnosController controller = new AlumnosController("Datasets/alumnos.json");
        controller.crearAlumnos();

        //nota mas alta de cada alumno
        HashMap<String, String> notasAltas = controller.getNotaMasAltaPorAlumno();
        for (var entry : notasAltas.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        // alumno con la mejor media
        Alumno mejor = controller.getAlumnoConMejorMedia();
        if (mejor != null) {
            // consigue las notas, luego los values del hashmap, lo hace un stream de dobles y es la media o 0
            double media = mejor.getNotas().values().stream().mapToDouble(Double::doubleValue).average().orElse(0);
            System.out.println("Alumno con mejor media: " + mejor.getNombre() + " (" + media + ")");
        }
    }
}

