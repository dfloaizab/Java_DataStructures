//Con la implementación de la interfaz comparable, se puede comparar solo por un atributo.
public class Persona implements Comparable<Persona> {

    private String nombre;
    private int edad;
    private float salarioBase;
    

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public int compareTo(Persona otra) {
        return Integer.compare(this.edad, otra.edad);
    }
}


