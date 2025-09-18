package roberto.palmi;

import java.io.*;

public class Act10 {
    public void concat(File f1, File f2, File archivoDestino){
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDestino));
                BufferedReader reader1 = new BufferedReader(new FileReader(f1));
                BufferedReader reader2 = new BufferedReader(new FileReader(f2))
        ) {
            String linea;
            while ((linea = reader1.readLine()) != null) {
                writer.write(linea);
                writer.newLine();
            }
            while ((linea = reader2.readLine()) != null) {
                writer.write(linea);
                writer.newLine();
            }

            System.out.println("Archivos juntados");

        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
