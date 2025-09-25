package roberto.palmi.boletin2.activitat2;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activitat2 {
    public static void main(String[] args) {
        try {
            GestionAlumnos gestion = new GestionAlumnos("alumnos.txt");

            SimpleDateFormat formatoDate = new SimpleDateFormat("yyyy-MM-dd");

            Alumno[] nuevosAlumnos = {
                    new Alumno("12345", "Roberto", "Palmi", "Marcu", formatoDate.parse("2006-09-26")),
                    new Alumno("67869", "Ezequiel", "Menor", "Apellido2", formatoDate.parse("2006-01-01"))
            };

            boolean nuevosAlumnosAgregados = gestion.insertarAlumnos(nuevosAlumnos);
            if (nuevosAlumnosAgregados) System.out.println("Lista de alumnos agregados correctamente");

            System.out.println(gestion);

            boolean alumnoEliminado = gestion.eliminarAlumno(nuevosAlumnos[1]);
            if (alumnoEliminado) System.out.println("Alumno eliminado correctamente");
            else System.out.println(gestion);

        } catch (ParseException e) {
            System.out.println("Error en la fecha: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
