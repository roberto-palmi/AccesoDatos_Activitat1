package roberto.palmi.boletin2.activitat2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionAlumnos {
    private File archivoAlumnos;

    public GestionAlumnos(String rutaAlumnos) {
        archivoAlumnos = new File(rutaAlumnos);
        try {
            if (!archivoAlumnos.exists()) {
                archivoAlumnos.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean insertarAlumnos(Alumno[] alumnos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoAlumnos, true))) {
            for (Alumno a : alumnos) {
                bw.write(a.getNia() + ";" + a.getNombre() + ";" +
                        a.getApellido1() + ";" + a.getApellido2() + ";" +
                        a.getFechaNac());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean eliminarAlumno(Alumno alumno){
        List<String> listaSinAlumnoEliminado = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoAlumnos))){
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith(alumno.getNia() + ";")) {
                    listaSinAlumnoEliminado.add(linea);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoAlumnos))){
            for (String linea : listaSinAlumnoEliminado) {
                bw.write(linea);
                bw.newLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(archivoAlumnos))){
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea + "\n");
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
