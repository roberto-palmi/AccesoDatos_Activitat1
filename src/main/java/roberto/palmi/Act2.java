package roberto.palmi;
import java.io.File;

public class Act2 {
    public Act2() {
        File carpeta = new File(Config.FOLDER);
        if(carpeta.exists() && carpeta.isDirectory()){
            File[] archivos = carpeta.listFiles();

            if (archivos == null){
                System.out.println("No hay ningún archivo.");
            }
            else {
                for (File archivo : archivos) {
                    System.out.println(archivo.getName());
                }
            }

        }
    }
}
