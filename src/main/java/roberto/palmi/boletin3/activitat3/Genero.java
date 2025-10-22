package roberto.palmi.boletin3.activitat3;

public enum Genero {
    TECNOLOGIA("Tecnología"),
    PROGRAMACION("Programación"),
    RECETARIO("Recetario"),
    METODOLOGIAS("Metodologías");

    private final String texto;

    Genero(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public static Genero desdeTexto(String texto) {
        for (Genero g : values()) {
            if (g.getTexto().equalsIgnoreCase(texto.trim())) {
                return g;
            }
        }
        return null;
    }
}
