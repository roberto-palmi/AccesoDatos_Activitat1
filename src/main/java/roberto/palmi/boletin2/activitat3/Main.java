package roberto.palmi.boletin2.activitat3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PasswordManager pm = new PasswordManager();

        boolean salir = false;

        while (!salir) {
            System.out.println("**********************");
            System.out.println("1. Validar acceso");
            System.out.println("2. Salir");
            System.out.println("**********************");
            System.out.print("Elige una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Introduce la contraseña: ");
                    String password = sc.nextLine();
                    if (pm.validarPassword(password)) {
                        System.out.println("Has entrado al programa");
                        mostrarMenuInterno(sc, pm);
                    } else {
                        System.out.println("Acceso denegado.");
                    }
                    break;

                case "2":
                    salir = true;
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Por favor pon una opción válida");
            }
        }

        sc.close();
    }

    private static void mostrarMenuInterno(Scanner sc, PasswordManager pm) {
        boolean salir = false;

        while (!salir) {
            System.out.println("*************************");
            System.out.println("1. Modificar contraseña");
            System.out.println("2. Salir");
            System.out.println("*************************");
            System.out.print("Elige una opcion: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    modificarPassword(sc, pm);
                    break;
                case "2":
                    salir = true;
                    break;
                default:
                    System.out.println("Por favor pon una opción válida");
            }
        }
    }

    private static void modificarPassword(Scanner sc, PasswordManager pm) {
        System.out.print("Introduce la contraseña anterior: ");
        String oldPass = sc.nextLine();

        if (!pm.validarPassword(oldPass)) {
            System.out.println("Contraseña incorrecta. No se puede modificar.");
            return;
        }

        System.out.print("Introduce la nueva contraseña: ");
        String newPass = sc.nextLine();

        if (comprobarRequisitosPassword(newPass)) {
            pm.changePassword(newPass);
            System.out.println("Contraseña modificada.");
        } else {
            System.out.println("La contraseña no cumple los requisitos de seguridad.");
        }
    }

    private static boolean comprobarRequisitosPassword(String pass) {
        boolean tieneMayus = pass.matches(".*[A-Z].*"); //1 mayus
        boolean tieneMinus = pass.matches(".*[a-z].*"); //1 minus
        boolean tieneNumero = pass.matches(".*[0-9].*"); //1 numero
        boolean tieneEspecial = pass.matches(".*[^a-zA-Z0-9].*"); // 1 car diferente a lo anterior (simbolo)
        boolean longitud = pass.length() >= 8;

        return tieneMayus && tieneMinus && tieneNumero && tieneEspecial && longitud; // en vez de poner un if gigante esto devuelve true si está todo bien
    }
}
