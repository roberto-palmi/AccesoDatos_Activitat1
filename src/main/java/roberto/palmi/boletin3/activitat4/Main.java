package roberto.palmi.boletin3.activitat4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PedidosController controller = new PedidosController("Datasets/pedidos.xml");

        String idPedido = "P-1002";
        Pedido pedido = controller.buscarPedidoPorId(idPedido);

        if (pedido != null) {
            System.out.println("Pedido " + idPedido + ": ");
            System.out.println("Cliente: " + pedido.getClienteNombre() + " (" + pedido.getClienteEmail() + ")");
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Supuesto total: " + pedido.getTotal());

            System.out.println("Items:");
            for (Item item : pedido.getItems()) {
                System.out.println("- " + item.getDescripcion() + " x" + item.getCantidad() + " = " +
                        (item.getCantidad() * item.getPrecioUnitario()) + "€");
            }

            double totalCalculado = controller.recalcularTotalPedido(pedido);
            System.out.println("Total calculado: " + totalCalculado + " euros");

            if (Math.abs(totalCalculado - pedido.getTotal()) < 0.01) {
                System.out.println("El total es correcto.");
            } else {
                System.out.println("El total no es correeto.");
            }

        } else {
            System.out.println("Pedido no encontrado.");
        }
    }
}