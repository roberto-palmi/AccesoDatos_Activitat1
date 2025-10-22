package roberto.palmi.boletin3.activitat4;

import java.util.List;

public class Pedido {
    private final String id;
    private final String clienteNombre;
    private final String clienteEmail;
    private final String fecha;
    private final double total;
    private final List<Item> items;

    public Pedido(String id, String clienteNombre, String clienteEmail, String fecha, double total, List<Item> items) {
        this.id = id;
        this.clienteNombre = clienteNombre;
        this.clienteEmail = clienteEmail;
        this.fecha = fecha;
        this.total = total;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public String getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public List<Item> getItems() {
        return items;
    }
}
