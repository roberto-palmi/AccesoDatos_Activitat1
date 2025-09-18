package roberto.palmi;
import java.io.File;

public class Act3 {
    public Act3(){
        File carpeta = new File(Config.FOLDER);
         System.out.println("Nombre de la carpeta: " + carpeta.getName());
         System.out.println("Ruta absoluta: " + carpeta.getAbsolutePath());
         System.out.println("Permisos para leer? : "+ carpeta.canRead());
         System.out.println("Permisos para escribir? : "+ carpeta.canWrite());
    }
}
