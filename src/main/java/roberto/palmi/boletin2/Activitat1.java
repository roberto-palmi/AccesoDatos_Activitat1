package roberto.palmi.boletin2;

import java.io.*;

public class Activitat1 {

    public static String comprobarDni(String dni) {
        // Rellenar con ceros a la izquierda si hace falta
        String fullDni = String.format("%08d", Integer.parseInt(dni));

        // Calcular la letra
        String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(fullDni);
        char letra = letrasDni.charAt(numero % 23);

        return fullDni + letra;
    }

    public static boolean ficheroDni(File archivoDni) throws IOException {
        if  (!archivoDni.exists()) return false;
        File archivoDniCompleto = new File(archivoDni.getName()+"_conLetras");;
        try (
        BufferedReader br = new BufferedReader( new FileReader(archivoDni));
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDniCompleto))
        ) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String dniConLetra = comprobarDni(br.readLine());
                bw.write(dniConLetra);
                bw.newLine();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static void main(String[] args) {
        try {
            File archivoDni = new File("pasarArchivo");
            if (archivoDni.exists()) {
                ficheroDni(archivoDni);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
