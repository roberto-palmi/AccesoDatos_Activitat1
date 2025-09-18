package roberto.palmi;

import java.io.File;

public class Act1 {
    public Act1(){
        File carpeta = new File(Config.FOLDER);

        if (carpeta.exists() && carpeta.isDirectory()) {
            System.out.println("La carpeta existe y es una directorio.");
        } else {
            if (!carpeta.exists()) {
                System.out.println("La carpeta no existe.");
            }
            else if (carpeta.exists() && !carpeta.isDirectory()) {
                System.out.println("La carpeta existe pero no es una ruta.");
            }
        }
    }
}
