import java.util.Objects;

/**
 * Representa un producto de inventario.
 */
public class Producto {
    private String nombre;
    private String unidadMedida;
    private int cantidad;

    public Producto(String nombre, String unidadMedida, int cantidad) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // equals/hashCode basados en el nombre, para que eliminar()/contiene()
    // en la lista puedan identificar un producto por su nombre.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Producto)) return false;
        Producto otro = (Producto) obj;
        return nombre.equalsIgnoreCase(otro.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("%s (%d %s)", nombre, cantidad, unidadMedida);
    }
}
