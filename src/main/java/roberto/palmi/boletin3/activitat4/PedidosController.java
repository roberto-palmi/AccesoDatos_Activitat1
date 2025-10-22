package roberto.palmi.boletin3.activitat4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class PedidosController {
    private Document dom;
    public PedidosController(String pathArchivo) {
        try {
            //se mete en la carpeta de resources del proyecto
            InputStream is = getClass().getClassLoader().getResourceAsStream(pathArchivo);

            if (is == null) {
                throw new FileNotFoundException("Recurso no encontrado: " + pathArchivo);
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.dom = builder.parse(is);
            this.dom.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pedido buscarPedidoPorId(String idPedido) {
        NodeList pedidos = dom.getElementsByTagName("pedido");

        for (int i = 0; i < pedidos.getLength(); i++) {
            Element pedidoElement = (Element) pedidos.item(i);
            String id = pedidoElement.getAttribute("id");

            if (id.equals(idPedido)) {
                Element cliente = (Element) pedidoElement.getElementsByTagName("cliente").item(0);
                String nombre = cliente.getElementsByTagName("nombre").item(0).getTextContent();
                String email = cliente.getElementsByTagName("email").item(0).getTextContent();
                String fecha = pedidoElement.getElementsByTagName("fecha").item(0).getTextContent();

                NodeList itemNodes = pedidoElement.getElementsByTagName("item");
                List<Item> listaItems = new ArrayList<>();

                for (int j = 0; j < itemNodes.getLength(); j++) {
                    Element item = (Element) itemNodes.item(j);

                    String sku = item.getAttribute("sku");
                    String descripcion = item.getElementsByTagName("descripcion").item(0).getTextContent();
                    int cantidad = Integer.parseInt(item.getElementsByTagName("cantidad").item(0).getTextContent());
                    double precioUnitario = Double.parseDouble(item.getElementsByTagName("precioUnitario").item(0).getTextContent());

                    listaItems.add(new Item(sku, descripcion, cantidad, precioUnitario));
                    }

                    double totalArchivo = Double.parseDouble(
                            pedidoElement.getElementsByTagName("total").item(0).getTextContent()
                    );

                    return new Pedido(id, nombre, email, fecha, totalArchivo, listaItems);
                }
            }

            return null;
        }

    public double recalcularTotalPedido(Pedido pedido) {
        double total = 0.0;
        for (Item item : pedido.getItems()) {
            total += item.getCantidad() * item.getPrecioUnitario();
        }
        return total;
    }
}
