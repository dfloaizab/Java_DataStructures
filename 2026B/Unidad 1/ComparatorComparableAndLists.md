# Práctica de Laboratorio — Estructuras de Datos
## Comparación de Objetos en Java: `Comparable` vs. `Comparator`, Algoritmos de Ordenamiento y Listas Simplemente Enlazadas

**Asignatura:** Estructuras de Datos
**Docente:** [Nombre del docente]
**Duración estimada:** 2 horas (sesión de laboratorio) + trabajo autónomo

---

## 1. Introducción

En múltiples estructuras de datos —listas, árboles, colas de prioridad— y en algoritmos de ordenamiento y búsqueda es necesario comparar objetos entre sí para establecer un criterio de orden. Java ofrece dos mecanismos complementarios para lograrlo: la interfaz `Comparable<T>` y la interfaz `Comparator<T>`. Comprender cuándo y por qué usar cada una es una competencia fundamental para el diseño de estructuras de datos genéricas y reutilizables.

Esta práctica retoma la implementación de una **Lista Simplemente Enlazada (LSE)** proporcionada por el docente (`ListNode` y `SinglyLinkedList`) para aplicar estos conceptos sobre una estructura de datos real.

---

## 2. Objetivos de Aprendizaje

Al finalizar esta práctica, el estudiante estará en capacidad de:

1. Explicar las diferencias conceptuales, ventajas y desventajas entre `Comparable` y `Comparator` en Java.
2. Implementar ambas interfaces sobre clases propias para definir criterios de orden natural y criterios de orden alternativos.
3. Aplicar `Comparable`/`Comparator` dentro de un algoritmo de ordenamiento implementado manualmente (no solo mediante `Collections.sort`).
4. Comprender la estructura y el funcionamiento de una Lista Simplemente Enlazada (LSE) genérica.
5. Integrar `Comparable`/`Comparator` en operaciones sobre una LSE (inserción ordenada y búsqueda del máximo/mínimo).
6. Relacionar las operaciones de ordenamiento y búsqueda estudiadas con nociones básicas de complejidad computacional (notación Big-O).

---

## 3. Marco Teórico: `Comparable` vs. `Comparator`

### 3.1 `Comparable<T>`

Es una interfaz que se implementa **dentro de la clase** cuyos objetos se desean comparar. Define el **orden natural** del objeto mediante el método:

```java
int compareTo(T otro);
```

La convención de retorno es:
- Valor **negativo**: el objeto actual es "menor" que `otro`.
- **Cero**: los objetos son "iguales" en términos de orden.
- Valor **positivo**: el objeto actual es "mayor" que `otro`.

### 3.2 `Comparator<T>`

Es una interfaz que se implementa en una clase **externa** (o como clase anónima / expresión lambda), y permite definir uno o **varios criterios de orden alternativos**, sin modificar la clase original. Su método principal es:

```java
int compare(T o1, T o2);
```

La convención de retorno es análoga a `compareTo`.

### 3.3 Cuadro comparativo

| Criterio | `Comparable` | `Comparator` |
|---|---|---|
| Ubicación de la implementación | Dentro de la clase del objeto | En una clase externa, clase anónima o lambda |
| Método principal | `compareTo(T o)` | `compare(T o1, T o2)` |
| Número de criterios de orden | Uno solo (orden "natural") | Múltiples, uno por cada `Comparator` definido |
| Modifica la clase original | Sí (debe implementar la interfaz) | No |
| Uso típico | `Collections.sort(lista)` / `Arrays.sort(arreglo)` | `Collections.sort(lista, comparador)` / `Arrays.sort(arreglo, comparador)` |
| Acceso a atributos privados | Directo (es la propia clase) | Requiere getters públicos |

### 3.4 Ventajas y desventajas

**`Comparable`**

- *Ventajas:* orden natural único y evidente para el tipo; integración directa con estructuras como `TreeSet`, `TreeMap` y `PriorityQueue` sin configuración adicional.
- *Desventajas:* solo permite un criterio de orden; obliga a modificar el código fuente de la clase (no aplicable si la clase pertenece a una librería externa o no se puede modificar); mezcla la lógica de negocio con la lógica de orden.

**`Comparator`**

- *Ventajas:* permite múltiples criterios de orden sin alterar la clase original; aplicable a clases de terceros o de librerías estándar; favorece el principio de responsabilidad única (SRP).
- *Desventajas:* requiere una clase o lambda adicional por cada criterio; si no se documenta bien, puede generar ambigüedad sobre cuál criterio se está aplicando en cada punto del programa.

---

## 4. Ejemplo Sencillo

Se define una clase `Estudiante` y se ordena una lista de estudiantes primero por su orden natural (`Comparable`, por código) y luego por un criterio alternativo (`Comparator`, por promedio).

```java
import java.util.*;

public class Estudiante implements Comparable<Estudiante> {
    private String codigo;
    private String nombre;
    private double promedio;

    public Estudiante(String codigo, String nombre, double promedio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    public String getCodigo()   { return codigo; }
    public String getNombre()   { return nombre; }
    public double getPromedio() { return promedio; }

    // Orden natural: por código de estudiante
    @Override
    public int compareTo(Estudiante otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + promedio + ")";
    }
}
```

```java
public class DemoComparacion {
    public static void main(String[] args) {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("E03", "Camila", 4.2));
        estudiantes.add(new Estudiante("E01", "Andrés", 3.5));
        estudiantes.add(new Estudiante("E02", "Brayan", 4.8));

        // Orden natural (Comparable): por código
        Collections.sort(estudiantes);
        System.out.println("Orden por código: " + estudiantes);

        // Orden alternativo (Comparator): por promedio descendente
        Comparator<Estudiante> porPromedioDesc =
            (a, b) -> Double.compare(b.getPromedio(), a.getPromedio());

        Collections.sort(estudiantes, porPromedioDesc);
        System.out.println("Orden por promedio: " + estudiantes);
    }
}
```

**Punto de discusión en clase:** ¿qué ocurre si se necesita ordenar simultáneamente por nombre en un módulo y por promedio en otro módulo del mismo sistema? ¿Por qué `Comparator` resuelve este escenario mejor que `Comparable`?

---

## 5. Ejemplo Complejo: `Comparable`/`Comparator` dentro de un Algoritmo de Ordenamiento

En este punto no se debe usar `Collections.sort` ni `Arrays.sort`: el objetivo es que el estudiante **implemente manualmente** un algoritmo de ordenamiento (Inserción) que reciba un `Comparator<T>` genérico, evidenciando que la comparación es un parámetro del algoritmo y no una decisión fija.

```java
import java.util.*;

public class OrdenamientoGenerico {

    /**
     * Ordenamiento por inserción genérico, parametrizado con un Comparator.
     * Complejidad: O(n^2) en el peor caso, O(n) en el mejor caso (lista ya ordenada).
     */
    public static <T> void insercion(T[] arreglo, Comparator<T> comparador) {
        for (int i = 1; i < arreglo.length; i++) {
            T actual = arreglo[i];
            int j = i - 1;
            while (j >= 0 && comparador.compare(arreglo[j], actual) > 0) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = actual;
        }
    }

    public static void main(String[] args) {
        Estudiante[] arreglo = {
            new Estudiante("E03", "Camila", 4.2),
            new Estudiante("E01", "Andrés", 3.5),
            new Estudiante("E02", "Brayan", 4.8)
        };

        // Se ordena usando el Comparator, sin tocar la clase Estudiante
        insercion(arreglo, Comparator.comparing(Estudiante::getNombre));
        System.out.println(Arrays.toString(arreglo));

        // El mismo método, con un criterio de orden distinto
        insercion(arreglo, (a, b) -> Double.compare(a.getPromedio(), b.getPromedio()));
        System.out.println(Arrays.toString(arreglo));
    }
}
```

**Punto de discusión en clase:** obsérvese que el método `insercion` es completamente independiente del criterio de orden: puede reutilizarse para ordenar estudiantes, productos, fechas, etc., sin modificar una sola línea del algoritmo. Esta es la principal ventaja de diseño de separar el algoritmo de ordenamiento de la lógica de comparación.

---

## 6. Aplicación sobre la Lista Simplemente Enlazada (LSE)

### 6.1 ¿Qué es una Lista Simplemente Enlazada?

Una **Lista Simplemente Enlazada (LSE)** es una estructura de datos dinámica y lineal compuesta por **nodos**. Cada nodo almacena un dato y una referencia (enlace) al **siguiente** nodo de la secuencia. A diferencia de un arreglo, una LSE no requiere un bloque contiguo de memoria ni un tamaño fijo definido de antemano: crece y se reduce dinámicamente durante la ejecución del programa.

Elementos característicos de la LSE que se trabaja en esta práctica (clases `ListNode<T>` y `SinglyLinkedList<T>`):

- **`head`**: referencia al primer nodo de la lista.
- **`tail`**: referencia al último nodo de la lista.
- **`size`**: número de elementos actualmente almacenados.
- Cada `ListNode` conoce su **dato** (`data`) y su **siguiente nodo** (`next`); no conoce al nodo anterior (por eso es "simplemente" enlazada, y no doblemente enlazada).

Operaciones básicas ya provistas en el código base: `isEmpty()`, `appendNode()`, `insertNode()`, `printList()`, `getNumElements()`, `deleteNode()`, `findNode()`.

> **Nota técnica para el estudiante:** revisen con cuidado el código base entregado. Encontrarán una inconsistencia en el segundo constructor de `SinglyLinkedList` (usa un atributo `maxSize` que no coincide con el atributo declarado `max_size`) y un método `getNode(int pos)` incompleto. Como parte de la actividad de esta sección deberán corregir y completar estos puntos, ya que se necesitarán para las actividades siguientes.

### 6.2 Actividad guiada: inserción ordenada usando `Comparable`

Se propone añadir a `SinglyLinkedList<T>` un método que inserte un nuevo dato **manteniendo el orden** de la lista, apoyándose en `Comparable`:

```java
/**
 * Inserta un nuevo dato en la posición correspondiente para mantener
 * la lista ordenada según el orden natural (Comparable) de T.
 * Requiere que T implemente Comparable<T>.
 */
@SuppressWarnings("unchecked")
void insertSorted(T data) {
    ListNode<T> newNode = new ListNode<>(data);
    Comparable<T> comparableData = (Comparable<T>) data;

    if (isEmpty() || comparableData.compareTo(head.getData()) <= 0) {
        newNode.setNext(head);
        head = newNode;
        if (tail == null) tail = newNode;
        size++;
        return;
    }

    ListNode<T> current = head;
    while (current.getNext() != null &&
           ((Comparable<T>) current.getNext().getData()).compareTo(data) < 0) {
        current = current.getNext();
    }
    newNode.setNext(current.getNext());
    current.setNext(newNode);
    if (newNode.getNext() == null) tail = newNode;
    size++;
}
```

### 6.3 Actividad guiada: búsqueda del máximo/mínimo usando `Comparator`

Se propone añadir un método que recorra la LSE y determine el elemento máximo según un `Comparator<T>` recibido como parámetro, para no depender de un único orden natural:

```java
/**
 * Retorna el dato máximo de la lista según el criterio dado por comparador.
 * Retorna null si la lista está vacía.
 */
T findMax(Comparator<T> comparador) {
    if (isEmpty()) return null;

    ListNode<T> current = head;
    T maxData = current.getData();

    while (current != null) {
        if (comparador.compare(current.getData(), maxData) > 0) {
            maxData = current.getData();
        }
        current = current.getNext();
    }
    return maxData;
}
```

**Ejemplo de uso con la clase `Estudiante`:**

```java
SinglyLinkedList<Estudiante> lista = new SinglyLinkedList<>();
lista.appendNode(new ListNode<>(new Estudiante("E03", "Camila", 4.2)));
lista.appendNode(new ListNode<>(new Estudiante("E01", "Andrés", 3.5)));
lista.appendNode(new ListNode<>(new Estudiante("E02", "Brayan", 4.8)));

Estudiante mejorPromedio = lista.findMax(
    (a, b) -> Double.compare(a.getPromedio(), b.getPromedio())
);
System.out.println("Mejor promedio: " + mejorPromedio);
```

**Punto de discusión en clase:** ¿por qué `insertSorted` depende de que `T` implemente `Comparable`, mientras que `findMax` no impone esa restricción sobre `T`? ¿Qué método es más flexible para reutilizar la LSE con clases que no se pueden modificar?

---

## 7. Ejercicio para Casa

### 7.1 Contexto

Se entrega una clase `Producto` (código base a construir por el estudiante) con los atributos: `codigo` (String), `nombre` (String), `precio` (double) y `cantidadEnStock` (int).

### 7.2 Puntos a completar o implementar

1. **Corrección del código base:** corregir la inconsistencia del atributo `maxSize`/`max_size` en `SinglyLinkedList`, y completar el método `getNode(int pos)` para que retorne el nodo ubicado en la posición indicada (validando la posición con `posIsValid`).
2. **Comparable:** implementar `Comparable<Producto>` en la clase `Producto`, definiendo como orden natural el `codigo`.
3. **Comparator:** implementar al menos dos `Comparator<Producto>` distintos: uno por `precio` ascendente y otro por `cantidadEnStock` descendente.
4. **Ordenamiento manual:** implementar el algoritmo de **ordenamiento por selección** (`selectionSort`), genérico y parametrizado con `Comparator<T>`, siguiendo el mismo enfoque de la Sección 5.
5. **Búsqueda:** implementar una **búsqueda binaria** (`binarySearch`) sobre un arreglo de `Producto` ya ordenado por `codigo`, usando `Comparable` o `Comparator` según corresponda.
6. **Lista Simplemente Enlazada:** cargar una LSE de `Producto` con al menos 8 elementos y:
   - Insertar todos los productos usando `insertSorted` (orden natural por código).
   - Usar `findMax` para obtener el producto de mayor precio y el de mayor stock (dos llamadas, con dos comparadores distintos).
   - Imprimir la lista antes y después de cada operación usando `printList()`.
7. **Análisis de complejidad:** para cada uno de los siguientes algoritmos implementados, determinar su complejidad temporal en notación Big-O (mejor caso y peor caso), justificando la respuesta con base en el número de comparaciones realizadas:
   - `insercion` (Sección 5)
   - `selectionSort` (punto 4)
   - `binarySearch` (punto 5)
   - `findMax` sobre la LSE (Sección 6.3)

### 7.3 Preguntas de análisis

1. ¿Por qué `binarySearch` exige que la colección esté previamente ordenada, y qué relación tiene esto con la complejidad O(log n)?
2. Si `Producto` no implementara `Comparable`, ¿sería posible aun así ordenar una lista de productos? Justifique la respuesta relacionándola con los conceptos de la Sección 3.
3. ¿Qué ventaja ofrece una Lista Simplemente Enlazada frente a un arreglo cuando se realizan inserciones frecuentes en posiciones intermedias? ¿Qué desventaja tiene la LSE frente al arreglo en cuanto al acceso a un elemento por posición?
4. Explique, en términos de complejidad, por qué el método `findMax` sobre la LSE es O(n) sin importar el `Comparator` que se utilice.
5. Compare la complejidad de `insertSorted` sobre la LSE (Sección 6.2) frente a `appendNode`. ¿En qué escenario conviene usar cada una?

### 7.4 Formato de entrega

- Código fuente en Java, organizado por clases (`Producto.java`, `ListNode.java`, `SinglyLinkedList.java`, `OrdenamientoGenerico.java`, clase con el método `main` de pruebas).
- Documento breve (PDF o Word) con las respuestas a las preguntas de análisis de la Sección 7.3 y la tabla de complejidades solicitada en el punto 7 de la Sección 7.2.

---

## 8. Rúbrica de Evaluación Sugerida

| Criterio | Puntaje |
|---|---|
| Corrección e implementación de `Comparable`/`Comparator` en `Producto` | 20% |
| Correcciones al código base de la LSE (`maxSize`, `getNode`) | 15% |
| Implementación correcta de `selectionSort` genérico con `Comparator` | 20% |
| Implementación correcta de `binarySearch` | 15% |
| Uso correcto de `insertSorted` y `findMax` sobre la LSE | 15% |
| Calidad de las respuestas de análisis de complejidad | 15% |

---

## 9. Referencias Sugeridas

- Documentación oficial de Oracle: interfaces `Comparable<T>` y `Comparator<T>` (paquetes `java.lang` y `java.util`).
- Cormen, T. H. et al. *Introduction to Algorithms*. Capítulos sobre ordenamiento por inserción, selección y búsqueda binaria.
- Weiss, M. A. *Data Structures and Algorithm Analysis in Java*. Capítulo sobre listas enlazadas.
