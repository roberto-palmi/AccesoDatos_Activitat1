package roberto.palmi.boletin1;
import java.io.File;
import java.util.Date;

public class Act4 {
    public Act4(){
        File archivoAct4 = new File(Config.FOLDER + "/Act4.java");

        System.out.println("Nombre de la carpeta: " + archivoAct4.getName());
        System.out.println("Ruta absoluta: " + archivoAct4.getAbsolutePath());
        System.out.println("Archivo oculto? :"+ archivoAct4.isHidden());
        System.out.println("Permisos para leer? : "+ archivoAct4.canRead());
        System.out.println("Permisos para escribir? : "+ archivoAct4.canWrite());

        Date fecha = new Date(archivoAct4.lastModified());
        System.out.println("Última fecha de modificación: " + fecha.toString());

        System.out.println("Tamaños: bytes = " + archivoAct4.length() + " KB: " +  archivoAct4.length()/1024 + " MB: " +   archivoAct4.length()/1024/1024);

    }
}
