package roberto.palmi.boletin3.activitat5;

//he añadido un implementation en el build.gradle para que esto no este en rojo
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.nio.file.Files;
import java.nio.file.Path;

public class ListarAlumnos {
    private JSONArray alumnos;

    public ListarAlumnos(String rutaFicheroAlumnos){
        try{
            var inputStream = getClass().getClassLoader().getResourceAsStream(rutaFicheroAlumnos);
            String fichero = new String(inputStream.readAllBytes());
            JSONTokener tokener = new JSONTokener(fichero);
            alumnos = new JSONArray(tokener);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String[] getAlumnosNombre(){
        String[] nombreAlumnos = new String[alumnos.length()];
        for (int i = 0; i < alumnos.length(); i++) {
            JSONObject alumno = alumnos.getJSONObject(i);
            String nombre = alumno.getString("nombre");
            nombreAlumnos[i] = nombre;
        }
        return nombreAlumnos;
    }

    public String[] getFechaAlumnos(){
        String[] fechaAlumnos = new String[alumnos.length()];
        for (int i = 0; i < alumnos.length(); i++) {
            JSONObject alumno = alumnos.getJSONObject(i);
            String fecha = alumno.getString("fechaNacimiento");
            fechaAlumnos[i] = fecha;
        }
        return fechaAlumnos;
    }

    public static void main(String[] args){
        ListarAlumnos lista = new ListarAlumnos("Datasets/alumnos.json");

        for (String nombre : lista.getAlumnosNombre()) {
            System.out.println("Nombre: " + nombre);
        }

        for (String fecha : lista.getFechaAlumnos()) {
            System.out.println("Fecha: " + fecha);
        }
    }

}
