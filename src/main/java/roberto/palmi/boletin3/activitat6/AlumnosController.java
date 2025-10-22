package roberto.palmi.boletin3.activitat6;

//he añadido un implementation en el build.gradle para que esto no este en rojo
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnosController {
    private JSONArray ficheroJson;
    private List<Alumno> alumnos = new ArrayList<>();

    public AlumnosController(String rutaFicheroAlumnos){
        try{
            var inputStream = getClass().getClassLoader().getResourceAsStream(rutaFicheroAlumnos);
            String fichero = new String(inputStream.readAllBytes());
            JSONTokener tokener = new JSONTokener(fichero);
            ficheroJson = new JSONArray(tokener);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean crearAlumnos(){
        try {
            for (int i = 0; i < ficheroJson.length(); i++) {
                JSONObject alumnoJson = ficheroJson.getJSONObject(i);

                String id = alumnoJson.getString("id");
                String nombre = alumnoJson.getString("nombre");
                boolean matriculado = alumnoJson.getBoolean("matriculado");
                LocalDate fechaNacimiento = LocalDate.parse(alumnoJson.getString("fechaNacimiento"));

                JSONArray notasAlumnoJson = alumnoJson.getJSONArray("notas");
                HashMap<String, Double> notas = new HashMap<>();

                for (int j = 0; j < notasAlumnoJson.length(); j++) {
                    JSONObject notaAsignaturaJson = notasAlumnoJson.getJSONObject(j);
                    String asignatura = notaAsignaturaJson.getString("asignatura");
                    double nota = notaAsignaturaJson.getDouble("nota");
                    notas.put(asignatura, nota);
                }

                Alumno alumno = new Alumno(id, nombre, matriculado, fechaNacimiento, notas);
                alumnos.add(alumno);
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public HashMap<String, String> getNotaMasAltaPorAlumno() {
        HashMap<String, String> resultado = new HashMap<>();

        for (Alumno alumno : alumnos) {
            String asignaturaTop = null;
            double notaTop = -1;

            for (var entry : alumno.getNotas().entrySet()) {
                if (entry.getValue() > notaTop) {
                    notaTop = entry.getValue();
                    asignaturaTop = entry.getKey();
                }
            }

            if (asignaturaTop != null) {
                resultado.put(alumno.getNombre(), asignaturaTop + " (" + notaTop + ")");
            }
        }

        return resultado;
    }

    public Alumno getAlumnoConMejorMedia() {
        Alumno mejorAlumno = null;
        double mejorMedia = -1;

        for (Alumno alumno : alumnos) {
            double suma = 0;
            int total = alumno.getNotas().size();

            for (double nota : alumno.getNotas().values()) {
                suma += nota;
            }

            double media = suma / total;

            if (media > mejorMedia) {
                mejorMedia = media;
                mejorAlumno = alumno;
            }
        }

        return mejorAlumno;
    }


}
