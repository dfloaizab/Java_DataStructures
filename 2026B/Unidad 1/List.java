import java.util.NoSuchElementException;

/**
 * Implementación de una lista simplemente enlazada:
 * Lista simplemente enlazada
 */
public class List<E> {

    //Una lista simplemente enlazada se construye con una referencia al primer y al último nodos
    Node<E> head, tail;
    int size;


    //Operaciones de la estructura de datos:
    //crear lista vacía:
    public List()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean estaVacia() {
        return size == 0;
    }

    //Agregar elemento al final (append):
    public void append(E data)
    {
        Node<E> nuevoNodo = new Node<>(data);
        if (estaVacia())
        {
            head = nuevoNodo;
            tail = nuevoNodo;
        }
        else {
            tail.next = nuevoNodo;
            tail = nuevoNodo;
        }
        size++;
    }

    //Agregar elemento al inicio:
    public void agregarAlInicio(E data)
    {
        Node<E> nuevoNodo = new Node<>(data);
        if (estaVacia())
        {
            head = nuevoNodo;
            tail = nuevoNodo;
        }
        else {
            nuevoNodo.next = head;
            head = nuevoNodo;
        }
        size++;
    }

    //Recorrer:
    public void recorrer()
    {
        Node<E> actual = head;
        StringBuilder sb = new StringBuilder("[");
        while (actual != null)
        {
            sb.append(actual.data);
            if (actual.next != null) sb.append(", ");
            actual = actual.next;
        }
        sb.append("]");
        System.out.println(sb);
    }

    //Verificar si está vacía:
    // (implementada arriba: estaVacia())

    //Ubicar elemento en posición i:
    public E obtenerEnPosicion(int i)
    {
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException("Posición inválida: " + i);
        }
        Node<E> actual = head;
        for (int pos = 0; pos < i; pos++)
        {
            actual = actual.next;
        }
        return actual.data;
    }

    //Buscar un elemento por algún valor:
    public boolean buscar(E valor)
    {
        Node<E> actual = head;
        while (actual != null)
        {
            if (actual.data.equals(valor))
            {
                return true;
            }
            actual = actual.next;
        }
        return false;
    }

    public int tamanio() {
        return size;
    }

    public E primero() {
        if (estaVacia()) throw new NoSuchElementException("Lista vacía");
        return head.data;
    }

    public E ultimo() {
        if (estaVacia()) throw new NoSuchElementException("Lista vacía");
        return tail.data;
    }
}
