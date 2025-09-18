package roberto.palmi;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("Actividad 1: ");
        Act1 ej1 = new Act1();
        System.out.println("Actividad 2: ");
        Act2 ej2 = new Act2();
        System.out.println("Actividad 3: ");
        Act3 ej3 = new Act3();
        System.out.println("Actividad 4: ");
        Act4 ej4 = new Act4();

        System.out.println("Actividad 5: ");
        Act5.GestionArchivos ej5 = new Act5.GestionArchivos();

        String directorio = Config.FOLDER;
        String nombreArchivo = "archivoEjercicios.txt";
        String nombreArchivo2 = "archivoEjercicios2.txt";
        String rutaArchivo = directorio + "/" + nombreArchivo;

        ej5.crearArchivo(directorio, nombreArchivo);
        ej5.listarDirectorio(directorio);
        ej5.verInfo(directorio, nombreArchivo);
        ej5.leerArchivo(rutaArchivo);
        ej5.verContenidoBinario(rutaArchivo);

        System.out.println("Actividad 8: ");
        Act8 ej8 = new Act8();

        System.out.println("Actividad 9: ");
        Act9 ej9 = new Act9(new File(directorio + "/" + nombreArchivo), new File(directorio + "/" + nombreArchivo2));



    }
}