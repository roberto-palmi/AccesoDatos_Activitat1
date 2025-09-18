package roberto.palmi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Act9 {
    public Act9(File archivo1, File archivo2) {
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(archivo1));
            BufferedReader br2 = new BufferedReader(new FileReader(archivo2));
            String linea1, linea2;
            int numeroLinea = 1;
            boolean sonIguales = true;
            while (true) {
                linea1 = br1.readLine();
                linea2 = br2.readLine();

                if (linea1 == null && linea2 == null) {
                    break;
                }

                if (linea1 == null || linea2 == null || !linea1.equals(linea2)) {
                    System.out.println("Línea " + numeroLinea + ":");
                    System.out.println("Archivo 1: " + (linea1 != null ? linea1 : "null"));
                    System.out.println("Archivo 2: " + (linea2 != null ? linea2 : "null"));
                    sonIguales = false;
                }

                numeroLinea++;
            }

            if (sonIguales) {
                System.out.println("Los archivos son idénticos.");
            } else {
                System.out.println("Los archivos son diferentes.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
