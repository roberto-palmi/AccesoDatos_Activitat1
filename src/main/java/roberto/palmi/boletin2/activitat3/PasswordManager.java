package roberto.palmi.boletin2.activitat3;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
    private static final String fileName = "src/main/java/roberto/palmi/boletin2/activitat3/password.txt";
    private static final String passwordPredeterminada = "S3cret@";
    private String passwordHashOnFile = "";

    public PasswordManager() {
        if (!comprobarExisteArchivo()) {
            passwordHashOnFile = generarHash(passwordPredeterminada);
            guardarArchivo(passwordHashOnFile);
        } else {
            passwordHashOnFile = leerArchivo();
        }
    }

    public String getPasswordHash() {
        return passwordHashOnFile;
    }

    public boolean validarPassword(String passwordPuesta) {
        String hashIngresado = generarHash(passwordPuesta);
        return passwordHashOnFile.equals(hashIngresado);
    }

    public boolean changePassword(String nuevaPassword) {
        try {
            String nuevoHash = generarHash(nuevaPassword);
            guardarArchivo(nuevoHash);
            passwordHashOnFile = nuevoHash;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean comprobarExisteArchivo() {
        File file = new File(fileName);
        return file.exists();
    }

    private void guardarArchivo(String hash) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String leerArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String generarHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1"); //convertidor de Hash
            byte[] digest = md.digest(input.getBytes()); //convierte input a bytes y luego a hash
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generando hash: ", e);
        }
    }

}
