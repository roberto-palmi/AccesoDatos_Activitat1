package roberto.palmi;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.BufferedReader;

public class Act5 {
    public static class GestionArchivos{
        public boolean crearArchivo(String directorio, String archivo){
            File ruta =  new File(directorio);
            if(!ruta.exists()){
                File nuevoArchivo =  new File(archivo);
                try {
                    if (nuevoArchivo.createNewFile()) return true;
                }
                catch (Exception e) {
                    System.out.println("Error: " +  e.getMessage());
                    return false;
                }
                return false;
            }
            else return false;
        }

        public void listarDirectorio(String directorio) {
            File carpeta = new File(directorio);
            if (carpeta.exists() && carpeta.isDirectory()) {
                File[] objetos = carpeta.listFiles();
                if (objetos!=null){
                    for(File archivo : objetos){
                        if (archivo.isDirectory()) {
                            System.out.println(archivo.getName() + " d " + archivo.length() + " bytes");
                            if (archivo.canRead()) System.out.print(" r");
                            if (archivo.canWrite()) System.out.print("w");
                        }
                        else {
                            System.out.println(archivo.getName() + " f " + archivo.length() + " bytes");
                            if (archivo.canRead()) System.out.print(" r");
                            if (archivo.canWrite()) System.out.print("w");
                        }
                    }
                }

            }
        }

        public void verInfo(String directorio, String archivo){
            File carpeta = new File(directorio);
            File fichero = new File(directorio + "/" + archivo);

            System.out.println("Nombre del archivo " + fichero.getName());
            System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
            System.out.println("Leer? " + fichero.canRead());
            System.out.println("Escribir? " + fichero.canWrite());
            System.out.println("Tamaño: " + fichero.length());
            if (fichero.isDirectory()) System.out.println("Es un directorio");
            else System.out.println("Es un fichero");
        }

        public void leerArchivo (String archivo){
            try{
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
                br.close();
            }
            catch(Exception e){
                System.out.println("Error: " +  e.getMessage());
            }

        }
        public void verContenidoBinario (String archivo){
            try{
                FileInputStream f = new FileInputStream(new File(archivo));
                int i, count=0;
                while ((i = f.read()) != -1){
                    if (count==10) {
                        System.out.println();
                        count = 0;
                    }
                    System.out.printf("%02X ", i);
                    count++;
                }
            }
            catch (Exception e){
                System.out.println("Error: " +  e.getMessage());
            }
        }


    }
}