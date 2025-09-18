package roberto.palmi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Act8 {
    public  Act8(){

    }
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No hay argumento");
            return;
        }

        String nombreArchivo = args[0];

        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo:");
            System.out.println(e.getMessage());
        }
    }

}
