# Estructuras de Datos 2026B, Universidad Santiago de Cali
## Ejercicio práctico: Árboles Binarios de Búsqueda aplicados a una base de clientes

**Tema:** Estructuras de Datos no lineales — Árboles Binarios de Búsqueda (BST)

**Lenguaje:** Java

**Modalidad:** Individual o en parejas

---

## 1. Contexto

Una tienda necesita organizar su base de clientes para apoyar decisiones comerciales: reportes ordenados, campañas dirigidas a rangos de edad y reconocimiento de sus mejores compradores. Usted recibirá una implementación parcial de un BST y deberá completarla, decidir qué recorrido resuelve cada requerimiento y justificar sus decisiones.

El ejercicio no consiste solo en "hacer que funcione": se evalúa que usted comprenda **por qué** un recorrido es el adecuado y **qué costo** tiene cada operación.

## 2. Objetivos de aprendizaje

Al finalizar el ejercicio, usted estará en capacidad de:

1. **Construir** un BST a partir de una colección de objetos, eligiendo una clave de ordenamiento numérica apropiada.
2. **Implementar** las operaciones fundamentales de un BST (inserción, búsqueda, mínimo, máximo y altura) de forma recursiva.
3. **Seleccionar** el recorrido adecuado (in-orden, in-orden inverso, pre-orden o post-orden) según el problema que se desea resolver.
4. **Aprovechar** la propiedad de ordenamiento del BST para podar ramas y evitar recorridos innecesarios (consulta por rango).
5. **Analizar** el efecto de las claves duplicadas y del orden de inserción sobre la forma y el rendimiento del árbol.
6. **Argumentar** la complejidad computacional de las operaciones implementadas en el caso promedio y en el peor caso.

## 3. Enunciado

La tienda registra, por cada cliente, su **documento**, **nombre**, **edad** y **total de compras** acumulado. Se desea mantener **dos árboles** sobre los mismos clientes:

| Árbol | Clave de ordenamiento | Uso previsto |
|---|---|---|
| `arbolCompras` | Total de compras (`int`, parte entera de `total_compras`) | Reportes y reconocimiento de compradores |
| `arbolEdad` | Edad (`int`) | Campañas por rango etario |

### Requerimientos

| Id | Requerimiento | Estructura a emplear |
|---|---|---|
| R1 | Listado de **todos** los clientes de menor a mayor total de compras. | `arbolCompras` |
| R2 | Los **3 clientes con mayor** total de compras, sin ordenar ascendentemente toda la colección para luego invertirla. | `arbolCompras` |
| R3 | Clientes con edad **entre 25 y 35 años** (inclusive), sin visitar innecesariamente ramas fuera del rango. | `arbolEdad` |
| R4 | Verificar si existe un cliente con un total de compras **exacto** y, en caso afirmativo, mostrar sus datos. | `arbolCompras` |
| R5 | Cliente **más joven** y cliente **de mayor edad**. | `arbolEdad` |
| R6 | Tratamiento correcto de **clientes con la misma edad** (ningún cliente puede perderse). | `arbolEdad` |

## 4. Nota sobre el código base

El código que se presenta parte de las clases `Cliente`, `Node` y `BinarySearchTree` vistas en clase, con los siguientes ajustes que usted debe tener en cuenta:

- En `Node`, los métodos `setLeft` y `setRight` asignaban el atributo a sí mismo (`this.left = left`) en lugar de asignar el parámetro recibido. Con ese comportamiento el árbol nunca crece. Quedan corregidos.
- En `Node` se agregó `getValue()` y un constructor `Node(int key, E value)` para poder recuperar el `Cliente` almacenado.
- En `BinarySearchTree.insertNode`, el resultado de `insertRecursive` ahora se **asigna a `root`**; de lo contrario, al insertar en un árbol vacío la raíz permanecía en `null`.
- Se eliminó el atributo `keyList` de la clase; la lista se construye localmente en `getKeyList()`.

> **Reflexión previa:** antes de programar, explique con sus palabras por qué cada uno de los dos primeros errores impedía que el árbol funcionara.

## 5. Código base

### 5.1 `Cliente.java` (sin modificaciones, salvo `toString`)

```java
public class Cliente {

    private String nombre, documento;
    private int edad;
    private float total_compras;

    public Cliente(String documento, int edad, String nombre, float total_compras) {
        this.documento = documento;
        this.edad = edad;
        this.nombre = nombre;
        this.total_compras = total_compras;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public float getTotal_compras() { return total_compras; }
    public void setTotal_compras(float total_compras) { this.total_compras = total_compras; }

    @Override
    public String toString() {
        return nombre + " (doc: " + documento + ", edad: " + edad
                + ", compras: $" + total_compras + ")";
    }
}
```

### 5.2 `Node.java` (con correcciones)

```java
public class Node<E> {

    /* fields */
    E value;      /* valor o dato del nodo */
    int key;      /* clave de ordenamiento */
    Node left;
    Node right;

    /* methods */
    public Node() { }

    public Node(E value) {
        this.value = value;
    }

    public Node(int key) {
        this.key = key;
    }

    public Node(int key, E value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() { return key; }
    public void setKey(int newKey) { key = newKey; }

    public E getValue() { return value; }
    public void setValue(E value) { this.value = value; }

    public Node getLeft() { return this.left; }
    public Node getRight() { return this.right; }

    // CORRECCIÓN: se asigna el parámetro, no el atributo a sí mismo
    public void setLeft(Node newLeft) { this.left = newLeft; }
    public void setRight(Node newRight) { this.right = newRight; }
}
```

### 5.3 `BinarySearchTree.java` (plantilla para completar)

Las secciones marcadas con `TODO` son responsabilidad del estudiante. No modifique las firmas de los métodos.

```java
import java.util.ArrayList;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    /* ---------- INSERCIÓN ---------- */

    // CORRECCIÓN: se actualiza la raíz con el resultado del método recursivo
    public void insertNode(Node newNode) {
        root = insertRecursive(newNode, root);
    }

    // Pregunta: ¿este método permite nodos con llave duplicada? (ver Parte C)
    public Node insertRecursive(Node nodeToInsert, Node rootNode) {
        // condición de parada: punto de inserción encontrado
        if (rootNode == null)
            return nodeToInsert;

        // llamados recursivos
        if (nodeToInsert.getKey() < rootNode.getKey())
            rootNode.setLeft(insertRecursive(nodeToInsert, rootNode.getLeft()));
        else if (nodeToInsert.getKey() > rootNode.getKey())
            rootNode.setRight(insertRecursive(nodeToInsert, rootNode.getRight()));
        return rootNode;
    }

    /* ---------- BÚSQUEDA ---------- */

    public boolean searchNodeRecursive(int key, Node rootNode) {
        if (rootNode == null)
            return false;
        if (rootNode.getKey() == key)
            return true;
        if (rootNode.getKey() > key)
            return searchNodeRecursive(key, rootNode.getLeft());
        return searchNodeRecursive(key, rootNode.getRight());
    }

    /**
     * TODO (Parte A.1): retorna el NODO cuya clave coincide con 'key',
     * o null si no existe. Es la versión de searchNodeRecursive que
     * devuelve el nodo en lugar de un booleano.
     */
    public Node buscarNodo(int key, Node rootNode) {
        // TODO
        return null;
    }

    /* ---------- RECORRIDOS (vistos en clase) ---------- */

    public void InOrder(Node root) {
        if (root == null)
            return;
        InOrder(root.getLeft());
        System.out.println("->" + root.getKey());
        InOrder(root.getRight());
    }

    public void preOrder(Node root) {
        if (root == null)
            return;
        System.out.println("->" + root.getKey());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    public void posOrder(Node root) {
        if (root == null)
            return;
        posOrder(root.getLeft());
        posOrder(root.getRight());
        System.out.println("->" + root.getKey());
    }

    /* ---------- OPERACIONES A COMPLETAR ---------- */

    /**
     * TODO (Parte A.2): retorna la lista de llaves ordenada ascendentemente.
     * Pista: construya un método auxiliar recursivo que reciba la lista
     * como parámetro y elija el recorrido que visita las llaves en orden.
     */
    public ArrayList<Integer> getKeyList() {
        ArrayList<Integer> keyList = new ArrayList<>();
        // TODO
        return keyList;
    }

    /**
     * TODO (Parte B - R1): retorna los clientes en orden ASCENDENTE de clave.
     */
    public ArrayList<Cliente> clientesAscendente() {
        ArrayList<Cliente> lista = new ArrayList<>();
        // TODO
        return lista;
    }

    /**
     * TODO (Parte B - R2): retorna, como máximo, los 'n' clientes con las
     * claves MÁS ALTAS, de mayor a menor. Debe detener el recorrido en
     * cuanto reúna 'n' clientes (no recorra el árbol completo si no es necesario).
     * Pista: ¿existe un recorrido que visite primero las claves mayores?
     */
    public ArrayList<Cliente> mayoresN(int n) {
        ArrayList<Cliente> lista = new ArrayList<>();
        // TODO
        return lista;
    }

    /**
     * TODO (Parte B - R3): retorna los clientes cuya clave esté en [min, max],
     * en orden ascendente. Debe PODAR: no descienda por una rama si ninguna de
     * sus claves puede estar dentro del rango.
     */
    public ArrayList<Cliente> clientesEnRango(int min, int max) {
        ArrayList<Cliente> lista = new ArrayList<>();
        // TODO
        return lista;
    }

    /**
     * TODO (Parte B - R5): retorna el cliente con la clave mínima (o null si
     * el árbol está vacío). Justifique por qué NO necesita recorrer todo el árbol.
     */
    public Cliente minimo() {
        // TODO
        return null;
    }

    /**
     * TODO (Parte B - R5): retorna el cliente con la clave máxima (o null si
     * el árbol está vacío).
     */
    public Cliente maximo() {
        // TODO
        return null;
    }

    /**
     * TODO (Parte D): retorna la altura del árbol. Convención: un árbol vacío
     * tiene altura -1 y un árbol con un solo nodo tiene altura 0.
     */
    public int altura(Node rootNode) {
        // TODO
        return -1;
    }
}
```

### 5.4 `Main.java` (programa de prueba para completar)

```java
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Cliente[] clientes = {
            new Cliente("1001", 34, "Ana Torres",     850000f),
            new Cliente("1002", 28, "Luis Prado",     1200000f),
            new Cliente("1003", 45, "Marta Ríos",     430000f),
            new Cliente("1004", 28, "Camilo Vélez",   990000f),   // misma edad que Luis
            new Cliente("1005", 52, "Sofía Mejía",    2100000f),
            new Cliente("1006", 31, "Julián Ortiz",   670000f),
            new Cliente("1007", 23, "Valentina Cruz", 310000f),
            new Cliente("1008", 39, "Andrés Lozano",  1750000f),
            new Cliente("1009", 34, "Paola Gil",      540000f)    // misma edad que Ana
        };

        BinarySearchTree arbolCompras = new BinarySearchTree();
        BinarySearchTree arbolEdad = new BinarySearchTree();

        // Creación e inserción
        for (Cliente c : clientes) {
            // TODO 1: cree un nodo con clave = (int) c.getTotal_compras() y valor = c,
            //         e insértelo en arbolCompras.

            // TODO 2: cree un nodo con clave = c.getEdad() y valor = c,
            //         e insértelo en arbolEdad.
        }

        // Verificación de la construcción
        System.out.println("Clientes registrados: " + clientes.length);
        System.out.println("Nodos en arbolCompras: " + arbolCompras.getKeyList().size());
        System.out.println("Nodos en arbolEdad: " + arbolEdad.getKeyList().size());
        // ¿Coinciden? Si no, vea la Parte C.

        // R1: listado ascendente por compras
        System.out.println("\n== R1: clientes por compras (ascendente) ==");
        // TODO: imprima los clientes de arbolCompras.clientesAscendente()

        // R2: tres mayores compradores
        System.out.println("\n== R2: Top 3 compradores ==");
        // TODO: imprima los clientes de arbolCompras.mayoresN(3)

        // R3: clientes entre 25 y 35 años
        System.out.println("\n== R3: clientes entre 25 y 35 años ==");
        // TODO: imprima los clientes de arbolEdad.clientesEnRango(25, 35)

        // R4: búsqueda exacta por total de compras
        System.out.println("\n== R4: búsqueda por total de compras ==");
        int buscado = 990000;
        // TODO: use buscarNodo; si existe, imprima el cliente; si no, informe que no existe.

        // R5: extremos por edad
        System.out.println("\n== R5: más joven y mayor ==");
        // TODO: imprima arbolEdad.minimo() y arbolEdad.maximo()
    }
}
```

## 6. Actividades

### Parte A — Construcción y búsqueda

1. Implemente `buscarNodo`.
2. Implemente `getKeyList` mediante el recorrido adecuado y verifique que la lista resultante esté ordenada.
3. Complete los `TODO 1` y `TODO 2` de `Main` y construya los dos árboles.

### Parte B — Elección del recorrido

Implemente los métodos de los requerimientos R1, R2, R3 y R5. **Antes de programar cada uno**, complete la siguiente tabla (puede incluirla como comentario en el código o en un documento aparte):

| Requerimiento | Recorrido elegido | Justificación | ¿Se poda o se detiene antes de visitar todos los nodos? |
|---|---|---|---|
| R1 | | | |
| R2 | | | |
| R3 | | | |
| R5 | | | |

### Parte C — Claves duplicadas (R6)

1. Ejecute el programa con el código base y compare el número de clientes con el número de nodos de `arbolEdad`. ¿Cuántos clientes se perdieron y por qué?
2. Modifique `insertRecursive` para que las claves iguales **no se descarten**. Una alternativa es enviar las claves iguales a la rama derecha.
3. Verifique que `clientesEnRango` y `getKeyList` siguen produciendo resultados correctos con la nueva política.
4. Explique qué ocurre ahora con `buscarNodo` cuando hay varios clientes con la misma clave: ¿cuál de ellos encuentra? Proponga (sin necesidad de implementarla) una alternativa para recuperar todos.

### Parte D — Forma del árbol y complejidad

1. Implemente `altura`.
2. Construya un árbol insertando las claves `10, 20, 30, 40, 50, 60` en ese orden. Calcule su altura y dibuje su forma. Haga lo mismo con el orden `40, 20, 60, 10, 30, 50`.
3. A partir de lo observado, responda:
   - ¿Qué complejidad tiene la búsqueda en cada caso?
   - ¿Qué debería ocurrir con los datos de entrada para evitar el peor caso? ¿Qué estructura de datos mencionada en clase o en las referencias resuelve este problema?

## 7. Entregables

- Archivos `Cliente.java`, `Node.java`, `BinarySearchTree.java` y `Main.java` completos y ejecutables.
- La tabla de la Parte B diligenciada.
- Un breve documento (máximo una página) con las respuestas a las Partes C y D.

## 8. Criterios de evaluación

| Criterio | Descripción | Peso |
|---|---|---|
| Correctitud de las operaciones | Inserción, búsqueda, mínimo, máximo y altura funcionan según lo especificado. | 30 % |
| Elección y uso del recorrido | El recorrido empleado es el adecuado para cada requerimiento y se justifica correctamente. | 25 % |
| Uso de la propiedad del BST | Se aprovecha el ordenamiento para podar o detener el recorrido (R2, R3, R5). | 15 % |
| Manejo de duplicados | La política elegida evita pérdida de datos y se analizan sus consecuencias. | 15 % |
| Análisis de complejidad | Argumentación clara del caso promedio y del peor caso. | 10 % |
| Calidad del código | Legibilidad, nombres significativos y comentarios pertinentes. | 5 % |

## 9. Referencias

- Cormen, T. H., Leiserson, C. E., Rivest, R. L. y Stein, C. *Introduction to Algorithms* (capítulo "Binary Search Trees"). MIT Press.
- Weiss, M. A. *Data Structures and Algorithm Analysis in Java* (capítulo "Trees"). Pearson.
- Goodrich, M. T., Tamassia, R. y Goldwasser, M. H. *Data Structures and Algorithms in Java*. Wiley.
- Sedgewick, R. y Wayne, K. *Algorithms* (4.ª ed., sección 3.2, "Binary Search Trees"). Addison-Wesley.
- Oracle. *Java Platform, Standard Edition API Specification: `java.util.ArrayList`*. Disponible en la documentación oficial de Java.
