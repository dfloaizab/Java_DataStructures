import java.util.*;

// Clase Producto con Comparable para ordenación por código
class Producto implements Comparable<Producto> {
    private String codigo;
    private String descripcion;
    private int cantidad;
    private double precio;

    public Producto(String codigo, String descripcion, int cantidad, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Implementación de Comparable (ordenar por código)
    @Override
    public int compareTo(Producto otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return codigo + " - " + descripcion + " | Stock: " + cantidad + " | Precio: $" + precio;
    }
}
