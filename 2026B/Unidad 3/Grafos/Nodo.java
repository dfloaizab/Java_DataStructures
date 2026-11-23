
import java.util.Objects;

public class Nodo<E> {

    private int id;
    private E dato;

    public Nodo(int id, E dato) {
        this.id = id;
        this.dato = dato;
    }

    public int getId() {
        return id;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Nodo<?>)) return false;

        Nodo<?> otro = (Nodo<?>) obj;

        return this.id == otro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "id=" + id +
                ", dato=" + dato +
                '}';
    }
}
