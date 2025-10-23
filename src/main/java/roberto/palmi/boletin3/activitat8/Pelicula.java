package roberto.palmi.boletin3.activitat8;

import java.util.HashMap;
import java.util.List;

public class Pelicula {
    private String id;
    private String titulo;
    private List<String> generos;
    private HashMap<String, Double>  puntuaciones;

    public Pelicula(String id, String titulo, List<String> generos, HashMap<String, Double> puntuaciones) {
        this.id = id;
        this.titulo = titulo;
        this.generos = generos;
        this.puntuaciones = puntuaciones;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public HashMap<String, Double> getPuntuaciones() {
        return puntuaciones;
    }
}
