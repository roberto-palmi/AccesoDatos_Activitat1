package roberto.palmi.boletin1;

import java.io.*;

public class Act11 {
    public void concat(File f1, File f2){
        File newFile = new File(Config.FOLDER + "/nuevoarchivo");
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
                BufferedReader reader1 = new BufferedReader(new FileReader(f1));
                BufferedReader reader2 = new BufferedReader(new FileReader(f2))
        ) {
            while (true){
                String linea;
                if ((linea = reader1.readLine()) != null) {
                    writer.write(linea);
                    writer.newLine();
                }
                if ((linea = reader2.readLine()) != null) {
                    writer.write(linea);
                    writer.newLine();
                }

                if (reader1.readLine() == null &&  reader2.readLine() == null) {
                    System.out.println("Archivos juntados");
                    break;
                }



            }

        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
