package roberto.palmi.boletin3.activitat8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Ruta del archivo JSON dentro de resources (por ejemplo "peliculas.json")
        String rutaFichero = "Datasets/peliculas.json";

        PeliculasController controller = new PeliculasController(rutaFichero);

        if (controller.conseguirPeliculas()) {

            HashMap<String, Double> medias = controller.calcularMediaPeliculas();

            System.out.println("Peliculas ordenadas: ");
            for (Map.Entry<String, Double> entry : medias.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
            System.out.println();


            List<String> generos = controller.generosOrdenados();

            System.out.println("Generos ordenados:");
            for (String genero : generos) {
                System.out.println(genero);
            }

        } else {
            System.out.println("Error al cargar o leer el JSON");
        }
    }
}