package roberto.palmi.boletin3.activitat3;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LeerBiblioteca {
    private Document dom;
    private final Genero[] tiposGeneros = Genero.values();
    //cuando se inicia la clase hay que pasarle directamente el archivo xml, porque esta clase solo sirve para
    // trabajar con estos archivos
    public LeerBiblioteca(String pathArchivo) {
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

    public ArrayList<String> devolverNombres(){
        NodeList items = dom.getElementsByTagName("libro");
        ArrayList<String> titulos = new ArrayList<>(items.getLength());

        for(int i = 0; i<items.getLength(); i++){
            Node item = items.item(i);
            NodeList hijos = item.getChildNodes();

            for (int j = 0; j < hijos.getLength(); j++) {
                Node hijo = hijos.item(j);
                if (hijo.getNodeName().equals("titulo")) {
                    titulos.add(hijo.getTextContent());
                }
            }

        }
        return titulos;
    }
    public ArrayList<Integer> devolverCantidadPorGenero() {
        NodeList items = dom.getElementsByTagName("libro");
        if (items.getLength() == 0) return null;
        Map<Genero, Integer> contador = new HashMap<>();

        for (int i = 0; i < items.getLength(); i++) {
            Node libro = items.item(i);
            NodeList hijos = libro.getChildNodes();

            for (int j = 0; j < hijos.getLength(); j++) {
                Node hijo = hijos.item(j);
                if (hijo.getNodeName().equals("generos")) {
                    NodeList generos = hijo.getChildNodes();
                    for (int k = 0; k < generos.getLength(); k++) {
                        Node generoNode = generos.item(k);
                        if (generoNode.getNodeName().equals("genero")) {
                            String textoGenero = generoNode.getTextContent();
                            Genero genero = Genero.desdeTexto(textoGenero);
                            if (genero != null) {
                                contador.put(genero, contador.getOrDefault(genero, 0) + 1);
                            }
                        }
                    }
                }
            }
        }
        ArrayList<Integer> resultado = new ArrayList<>();
            for (Genero genero : tiposGeneros) {
                resultado.add(contador.getOrDefault(genero, 0));
            }
            return resultado;
    }

}
