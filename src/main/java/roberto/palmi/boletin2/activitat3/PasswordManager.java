package roberto.palmi.boletin2.activitat3;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class PasswordManager {
    private static final String fileName = "src/main/java/roberto/palmi/boletin2/activitat3/password.properties";
    private static final String passwordPredeterminada = "S3cret@";
    private static final String key = "passwordHash";
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
        Properties props = new Properties();
        props.setProperty(key, hash);

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            props.store(fos, "Archivo de contrasena en hash");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String leerArchivo() {
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
            return props.getProperty(key, "");
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
