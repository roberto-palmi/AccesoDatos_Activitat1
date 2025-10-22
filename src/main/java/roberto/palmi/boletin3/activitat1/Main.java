package roberto.palmi.boletin3.activitat1;

public class Main {
    public static void main(String[] args) {
        try {
            xmlReader lector = new xmlReader("Datasets/empleados.xml");
            Empleado[] empleados = lector.readInfoEsencialEmpleados();

            for (Empleado e : empleados) {
                System.out.println("ID: " + e.id + ", Nombre: " + e.nombre + ", Salario: " + e.salario);
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
        }
    }
}

