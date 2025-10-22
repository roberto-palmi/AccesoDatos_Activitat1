package roberto.palmi.boletin3.activitat3;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LeerBiblioteca lector = new LeerBiblioteca("Datasets/biblioteca.xml");

        ArrayList<String> titulos = lector.devolverNombres();
        System.out.println("Titulos: ");
        for (String titulo : titulos) {
            System.out.println("- " + titulo);
        }

        ArrayList<Integer> cantidad = lector.devolverCantidadPorGenero();
        Genero[] generos = Genero.values();

        System.out.println("Cantidad por género: ");
        for (int i = 0; i < generos.length; i++) {
            System.out.println(generos[i].getTexto() + ": " + cantidad.get(i));
        }
    }
}
