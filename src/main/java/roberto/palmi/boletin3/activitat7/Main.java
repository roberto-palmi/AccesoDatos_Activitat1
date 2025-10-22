package roberto.palmi.boletin3.activitat7;

public class Main {
    public static void main(String[] args) {
        InventarioLector lector = new InventarioLector("Datasets/inventario.json");

        String ubicacion = lector.mostrarUbicacionPedido("PR-101");
        System.out.println(ubicacion);
    }
}
