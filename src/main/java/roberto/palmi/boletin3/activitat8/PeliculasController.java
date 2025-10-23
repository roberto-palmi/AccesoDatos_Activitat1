package roberto.palmi.boletin3.activitat8;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;

public class PeliculasController {
    private JSONArray ficheroJson;
    private List<Pelicula> peliculas;
    public PeliculasController(String rutaFicheroAlumnos){
        this.peliculas = new ArrayList<>();
        try{
            var inputStream = getClass().getClassLoader().getResourceAsStream(rutaFicheroAlumnos);
            String fichero = new String(inputStream.readAllBytes());
            JSONTokener tokener = new JSONTokener(fichero);

            JSONObject base = new JSONObject(tokener);
            ficheroJson = base.getJSONArray("peliculas");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean conseguirPeliculas(){
        try{
            for (int i=0; i<ficheroJson.length(); i++){
                JSONObject pelicula =  ficheroJson.getJSONObject(i);

                String id = pelicula.getString("id");
                String nombre = pelicula.getString("titulo");
                List<String> generos = new ArrayList<>();
                JSONArray generosArray = pelicula.getJSONArray("generos");

                for (int j = 0; j < generosArray.length(); j++){
                    generos.add(generosArray.getString(j));
                }
                HashMap<String, Double> listaPuntuaciones = new HashMap<>();
                JSONObject puntuaciones =  pelicula.getJSONObject("puntuaciones");

                listaPuntuaciones.put("imdb", puntuaciones.getDouble("imdb"));
                listaPuntuaciones.put("rt",   ((double) puntuaciones.getInt("rt")/10));

                Pelicula peliculaRecibida = new Pelicula(id, nombre, generos, listaPuntuaciones);
                peliculas.add(peliculaRecibida);
            }
            return !peliculas.isEmpty();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public HashMap<String, Double> calcularMediaPeliculas(){
        HashMap<String, Double> mediaPeliculas = new HashMap<>();
        try{
            for (int i=0; i<peliculas.size(); i++){
                Pelicula pelicula = peliculas.get(i);
                HashMap<String, Double> puntuaciones = pelicula.getPuntuaciones();
                Double media = 0.0;
                for (String key : puntuaciones.keySet()){
                    media += puntuaciones.get(key);
                }
                media = media/puntuaciones.size();
                mediaPeliculas.put(pelicula.getTitulo(), media);
            }
            //convertir hashmap a list
            List<Map.Entry<String, Double>> listaOrdenada = new ArrayList<>(mediaPeliculas.entrySet());
            listaOrdenada.sort(Map.Entry.comparingByValue(Comparator.reverseOrder())); // de mayor a menor

            // lo convierto a linkedhashmap que guarda el orden
            LinkedHashMap<String, Double> resultadoOrdenado = new LinkedHashMap<>();
            // por cada elemento en lista ordenada lo meto en el linked hash map
            for (Map.Entry<String, Double> entry : listaOrdenada) {
                resultadoOrdenado.put(entry.getKey(), entry.getValue());
            }
            return resultadoOrdenado;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> generosOrdenados(){
        List<String> generos = new ArrayList<>();
        try{
            for (Pelicula p : peliculas){
                for (String key : p.getGeneros()){
                    if(!generos.contains(key)){
                        generos.add(key);
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        List<String> listaOrdenada = new ArrayList<>(generos);
        Collections.sort(listaOrdenada);
        return listaOrdenada;
    }


}
