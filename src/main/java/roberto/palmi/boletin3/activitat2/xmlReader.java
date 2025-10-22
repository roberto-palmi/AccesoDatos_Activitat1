package roberto.palmi.boletin3.activitat2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


class Empleado{
    private String empresa;
    private String id;
    private String nombre;
    private String departamento;
    private Double salario;

    public Empleado(String id, String nombre,Double salario) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
    }

    public Empleado(String id, String nombre,String departamento, Double salario) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    //getters que se usan luego en los bucles para conseguir los valores
    public String getDepartamento() {return departamento;}
    public Double getSalario() {return salario;}

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
    private Map<String, Integer> empleadosPorDepartamento;

    //cuando se inicia la clase hay que pasarle directamente el archivo xml, porque esta clase solo sirve para
    // trabajar con estos archivos
    public xmlReader(String pathArchivo) {
        empleados = null;
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

    public int getNumEmpleados(String departamento) {
        return empleadosPorDepartamento.getOrDefault(departamento, 0);
    }

    //no se inicia en el constructor porque no me interesa pasarle desde el constructor de la clase el lenght
    private int iniciarListaEmpleados(int lenght){
        empleados = new Empleado[lenght];
        return empleados.length;
    }

    public Empleado[] readEmpleados(){
        // conseguimos la lista de todos los empleados
        NodeList items = dom.getElementsByTagName("empleado");
        // y aqui el tamaño
        int lenght = iniciarListaEmpleados(items.getLength());

        for (int i = 0; i < lenght; i++) {
            //item es el empleado
            Node item =  items.item(i);
            //sacamos el id que ees un atributo
            String id = item.getAttributes().getNamedItem("id").getNodeValue();

            // sacamos los hijos del empleado para conseguir los demás datos
            NodeList hijos = item.getChildNodes();
            String nombre = "";
            Double salario = 0.0;
            String departamento = "";


            for (int j = 0; j < hijos.getLength(); j++) {
                Node hijo = hijos.item(j);
                if (hijo.getNodeName().equals("nombre")) {
                    nombre = hijo.getTextContent();
                }
                else if (hijo.getNodeName().equals("salario")) {
                    salario =  Double.parseDouble(hijo.getTextContent());
                }
                else if (hijo.getNodeName().equals("departamento")) {
                    departamento = hijo.getTextContent();
                }
            }
            //meto en la tabla ya creada al principio todos los empleados
            empleados[i] = new Empleado(id, nombre, departamento, salario);
        }
        // devuelvo la tabla actualizada con los empleados
        return empleados;
    }

    public Map<String, Double> calcularSalarioMedioPorDepartamento() {
        // se actualiza la tabla
        Empleado[] empleados = readEmpleados();


        empleadosPorDepartamento = new HashMap<>();
        Map<String, Double> salariosPorDepartamento = new HashMap<>();

        for (Empleado e : empleados) {
            String departamento = e.getDepartamento();
            double salario = e.getSalario();

            // cuenta los empleados que hay en cada departamento
            empleadosPorDepartamento.put(departamento, empleadosPorDepartamento.getOrDefault(departamento, 0) + 1);

            // sumar los salarios que hay en el departamento
            salariosPorDepartamento.put(departamento, salariosPorDepartamento.getOrDefault(departamento, 0.0) + salario);
        }

        //creamos el hashmap que luego se devolverá con toda la informaicon
        Map<String, Double> salarioMedioPorDepto = new HashMap<>();

        //recorrer cada key del hash map para conseguir sus valores
        for (String depto : empleadosPorDepartamento.keySet()) {
            //conseguimos el n de empleados que hay en el departamento actual del bucle
            int num = empleadosPorDepartamento.get(depto);
            // aqui la SUMA TOTAL del departamento
            double suma = salariosPorDepartamento.get(depto);
            // y luego con el numero de empleados del departamento actual lo dividimos entre la suma para calcular la media
            double media = suma / num;
            salarioMedioPorDepto.put(depto, media);
        }

        return salarioMedioPorDepto;
    }

}