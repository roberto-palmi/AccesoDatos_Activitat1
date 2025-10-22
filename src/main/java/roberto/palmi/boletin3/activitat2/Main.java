package roberto.palmi.boletin3.activitat2;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            xmlReader lector = new xmlReader("Datasets/empleados.xml");
            Map<String, Double> medias = lector.calcularSalarioMedioPorDepartamento();

            System.out.printf("%-15s | %-12s | %s%n", "Departamento", "Nº empleados", "Salario medio");
            System.out.println("---------------------------------------------------");

            for (String depto : medias.keySet()) {
                System.out.printf("%-15s | %-12d | %.2f%n",
                        depto,
                        lector.getNumEmpleados(depto),
                        medias.get(depto));
            }
        }

         catch (Exception e) {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
        }
    }
}

