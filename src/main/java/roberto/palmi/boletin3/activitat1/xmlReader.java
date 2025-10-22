package roberto.palmi.boletin3.activitat1;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;


class Empleado{
    //he leido tarde el enunciado no sabía que solo eran
    String empresa = "";
    String id="";
    String nombre="";
    String departamento="";
    String moneda="";
    String salario = "";
    LocalDate fechaAlta = LocalDate.parse("2000-01-01");

    public Empleado(String empresa, String id, String nombre, String departamento, String moneda, String salario, LocalDate fechaAlta) {
        this.empresa = empresa;
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.moneda = moneda;
        this.salario = salario;
        this.fechaAlta = fechaAlta;
    }

    public Empleado(String id, String nombre,String salario) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
    }

    public String getEmpresa() {
        return empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(empresa, empleado.empresa) && Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empresa, id);
    }

}

public class xmlReader {
    private Empleado empleados[];
    private Document dom;

    public xmlReader(String pathArchivo) throws java.io.FileNotFoundException {
        empleados = null;
        try {
            // Obtiene el InputStream del recurso
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

    private int iniciarListaEmpleados(int lenght){
        empleados = new Empleado[lenght];
        return empleados.length;
    }

    public Empleado[] readInfoEsencialEmpleados(){
        NodeList items = dom.getElementsByTagName("empleado");
        int lenght = iniciarListaEmpleados(items.getLength());

        for (int i = 0; i < lenght; i++) {
            Node item =  items.item(i);
            String id = item.getAttributes().getNamedItem("id").getNodeValue();

            NodeList hijos = item.getChildNodes();
            String nombre = "";
            String salario = "";

            for (int j = 0; j < hijos.getLength(); j++) {
                Node hijo = hijos.item(j);
                if (hijo.getNodeName().equals("nombre")) {
                    nombre = hijo.getTextContent();
                }
                else if (hijo.getNodeName().equals("salario")) {
                    salario = hijo.getTextContent();
                }
            }

            empleados[i] = new Empleado(id, nombre, salario);
        }

        return empleados;
    }
}