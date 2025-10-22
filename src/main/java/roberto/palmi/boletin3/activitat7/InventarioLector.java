package roberto.palmi.boletin3.activitat7;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class InventarioLector {
    private JSONArray ficheroJson;

    public InventarioLector(String rutaFicheroAlumnos){
        try{
            var inputStream = getClass().getClassLoader().getResourceAsStream(rutaFicheroAlumnos);
            String fichero = new String(inputStream.readAllBytes());
            JSONTokener tokener = new JSONTokener(fichero);
            //se hace esto de JSONObject porque el json empieza por {}, es un object no un array
            JSONObject base = new JSONObject(tokener);
            ficheroJson = new JSONArray(); // se crea un nuevo json con una array
            ficheroJson.put(base); // como ya es un array simplemente pongo lo del objeto y ya funciona esto es una chapuza
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String mostrarUbicacionPedido(String id){
        try {
            for (int i = 0; i < ficheroJson.length(); i++) {
                JSONObject almacen = ficheroJson.getJSONObject(i);
                String nombreAlmacen = almacen.getString("almacen");

                JSONArray productos = almacen.getJSONArray("productos");

                for (int j = 0; j < productos.length(); j++) {
                    JSONObject producto = productos.getJSONObject(j);
                    String idProducto = producto.getString("id");

                    if (idProducto.equals(id)) {
                        JSONObject ubicacion = producto.getJSONObject("ubicacion");
                        int passillo = ubicacion.getInt("pasillo");
                        String estante = ubicacion.getString("estante");

                        return "Producto: " + id + " esta en el almacen " + nombreAlmacen +
                                ", en el pasillo " + passillo + " en el estante " + estante;
                    }
                }
            }
            return "Pedido no encontrado";
        }
        catch (Exception e){
            e.printStackTrace();
            return  "error";
        }
    }
}
