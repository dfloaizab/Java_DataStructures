# Estructuras de Datos 2026B
# Universidad Santiago de Cali

## Práctica: Árboles Binarios de Búsqueda (BST) en Java

**Curso:** Estructuras de Datos · **Unidad:** Estructuras no lineales
**Prerrequisitos:** lista simplemente enlazada (`Node<E>`, `List<E>`), `Comparable`, recursión básica, complejidad computacional (O, Ω, Θ).

---

## 1. Objetivos de la práctica

1. Implementar un árbol binario de búsqueda genérico (`insertar`, `buscar`, `inorden`, `altura`, `minimo`, `maximo`).
2. Aplicarlo a dos problemas sencillos: un **directorio de estudiantes** y un **contador de palabras**.
3. Relacionar el costo de las operaciones del BST con el de arreglos y listas enlazadas, y reconocer cuándo el árbol *deja* de ser eficiente.

---

## 2. Idea central en 30 segundos

Un BST es un árbol binario donde, para **cada** nodo:

- Todo lo que está en su subárbol **izquierdo** es **menor** que el nodo.
- Todo lo que está en su subárbol **derecho** es **mayor** que el nodo.

```
            2024050
           /       \
      2024020     2024080
      /     \     /     \
 2024010 2024030 2024070 2024090
```

Esa regla permite, en cada comparación, **descartar un subárbol completo** (igual que la búsqueda binaria descarta la mitad de un arreglo ordenado), pero sin necesitar un arreglo contiguo: se puede insertar sin desplazar elementos.

> **Advertencia que retomaremos en el ejercicio:** la eficiencia O(log n) depende de que el árbol esté *razonablemente balanceado*. Si los datos llegan ya ordenados, el árbol se degenera en una "lista" y las operaciones pasan a O(n).

---

## 3. Clases base (se usan en los dos ejemplos)

### 3.1 `NodoArbol<E>`

Es el equivalente de `Node<E>` de la lista, pero con **dos** referencias en lugar de una.

```java
public class NodoArbol<E> {
    E dato;
    NodoArbol<E> izquierdo;
    NodoArbol<E> derecho;

    public NodoArbol(E dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }
}
```

### 3.2 `ArbolBinarioBusqueda<E>`

`E extends Comparable<E>` obliga a que los elementos sepan compararse (aquí se reutiliza lo visto con `Comparable`).

```java
public class ArbolBinarioBusqueda<E extends Comparable<E>> {

    private NodoArbol<E> raiz;
    private int size;

    public ArbolBinarioBusqueda() {
        raiz = null;
        size = 0;
    }

    public boolean estaVacio() { return raiz == null; }
    public int tamanio() { return size; }

    // ---------- Insertar ----------
    public void insertar(E dato) {
        raiz = insertar(raiz, dato);
    }

    private NodoArbol<E> insertar(NodoArbol<E> actual, E dato) {
        if (actual == null) {
            size++;
            return new NodoArbol<>(dato);
        }
        int cmp = dato.compareTo(actual.dato);
        if (cmp < 0) {
            actual.izquierdo = insertar(actual.izquierdo, dato);
        } else if (cmp > 0) {
            actual.derecho = insertar(actual.derecho, dato);
        }
        // si cmp == 0 el elemento ya existe: no se duplica
        return actual;
    }

    // ---------- Buscar ----------
    // Devuelve el elemento almacenado o null si no existe
    public E buscar(E clave) {
        NodoArbol<E> actual = raiz;
        while (actual != null) {
            int cmp = clave.compareTo(actual.dato);
            if (cmp == 0) return actual.dato;
            actual = (cmp < 0) ? actual.izquierdo : actual.derecho;
        }
        return null;
    }

    // ---------- Recorrido inorden (queda ordenado) ----------
    public void inorden() {
        inorden(raiz);
    }

    private void inorden(NodoArbol<E> nodo) {
        if (nodo == null) return;
        inorden(nodo.izquierdo);
        System.out.println(nodo.dato);
        inorden(nodo.derecho);
    }

    // ---------- Altura ----------
    public int altura() { return altura(raiz); }

    private int altura(NodoArbol<E> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    // ---------- Mínimo y máximo ----------
    public E minimo() {
        if (estaVacio()) return null;
        NodoArbol<E> actual = raiz;
        while (actual.izquierdo != null) actual = actual.izquierdo;
        return actual.dato;
    }

    public E maximo() {
        if (estaVacio()) return null;
        NodoArbol<E> actual = raiz;
        while (actual.derecho != null) actual = actual.derecho;
        return actual.dato;
    }
}
```

**Puntos para comentar en clase**

| Fragmento | Qué observar |
|---|---|
| `insertar` privado recursivo | Devuelve el nodo (`return actual`) para que el padre "reenganche" su hijo; así se evita manejar el nodo padre aparte. |
| `buscar` iterativo | Es el mismo "dividir y vencer" de la búsqueda binaria, pero siguiendo referencias en vez de calcular un índice medio. |
| `inorden` | Izquierda → nodo → derecha produce los datos **en orden ascendente** sin ejecutar ningún algoritmo de ordenamiento. |
| `cmp == 0` en `insertar` | Decisión de diseño: aquí no se permiten duplicados. |

---

## 4. Ejemplo 1: Directorio de estudiantes por código

**Problema:** una oficina necesita guardar estudiantes, **encontrar uno por su código** rápidamente y poder **listarlos ordenados** por código.

### 4.1 Clase `Estudiante`

El BST ordena por código gracias a `compareTo`:

```java
public class Estudiante implements Comparable<Estudiante> {
    private final int codigo;
    private final String nombre;

    public Estudiante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }

    // El BST ordena por código
    @Override
    public int compareTo(Estudiante otro) {
        return Integer.compare(this.codigo, otro.codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}
```

### 4.2 Programa principal

```java
public class DirectorioEstudiantes {
    public static void main(String[] args) {
        ArbolBinarioBusqueda<Estudiante> directorio = new ArbolBinarioBusqueda<>();

        directorio.insertar(new Estudiante(2024050, "Laura Gómez"));
        directorio.insertar(new Estudiante(2024020, "Andrés Pérez"));
        directorio.insertar(new Estudiante(2024080, "Camila Rojas"));
        directorio.insertar(new Estudiante(2024010, "Julián Torres"));
        directorio.insertar(new Estudiante(2024030, "Sofía Mejía"));
        directorio.insertar(new Estudiante(2024070, "Daniel Cruz"));
        directorio.insertar(new Estudiante(2024090, "Valentina Ruiz"));

        System.out.println("Listado ordenado por código (inorden):");
        directorio.inorden();

        // Para buscar solo necesitamos un Estudiante con el código buscado
        Estudiante buscado = directorio.buscar(new Estudiante(2024070, ""));
        System.out.println("Búsqueda 2024070 -> " + (buscado != null ? buscado : "no encontrado"));

        Estudiante inexistente = directorio.buscar(new Estudiante(2024999, ""));
        System.out.println("Búsqueda 2024999 -> " + (inexistente != null ? inexistente : "no encontrado"));

        System.out.println("Primer código: " + directorio.minimo());
        System.out.println("Último código: " + directorio.maximo());
        System.out.println("Estudiantes: " + directorio.tamanio() + ", altura del árbol: " + directorio.altura());
    }
}
```

### 4.3 Salida esperada

```
Listado ordenado por código (inorden):
2024010 - Julián Torres
2024020 - Andrés Pérez
2024030 - Sofía Mejía
2024050 - Laura Gómez
2024070 - Daniel Cruz
2024080 - Camila Rojas
2024090 - Valentina Ruiz
Búsqueda 2024070 -> 2024070 - Daniel Cruz
Búsqueda 2024999 -> no encontrado
Primer código: 2024010 - Julián Torres
Último código: 2024090 - Valentina Ruiz
Estudiantes: 7, altura del árbol: 3
```

### 4.4 Explicación

- El orden de inserción (050, 020, 080, 010, 030, 070, 090) produce exactamente el árbol dibujado en la sección 2: **altura 3** para 7 elementos.
- Para encontrar el código `2024070` solo se hacen **3 comparaciones** (050 → 080 → 070), no hasta 7 como en una búsqueda lineal.
- Al buscar, se construye un `Estudiante` "de consulta" con el código y nombre vacío: el árbol solo mira el código porque `compareTo` solo compara el código.
- El listado sale ordenado sin llamar a ningún algoritmo de ordenamiento; el orden es consecuencia de la estructura.

---

## 5. Ejemplo 2: Contador de frecuencia de palabras

**Problema:** dado un texto, contar cuántas veces aparece cada palabra y mostrar el resultado **en orden alfabético**.

Aquí el nodo guarda además un contador, y si la palabra ya existe no se crea otro nodo: se incrementa la frecuencia.

```java
public class ContadorPalabras {

    // Nodo propio: guarda la palabra y cuántas veces apareció
    static class NodoPalabra {
        String palabra;
        int frecuencia;
        NodoPalabra izquierdo, derecho;

        NodoPalabra(String palabra) {
            this.palabra = palabra;
            this.frecuencia = 1;
        }
    }

    private NodoPalabra raiz;
    private int palabrasDistintas;

    public void agregar(String palabra) {
        raiz = agregar(raiz, palabra);
    }

    private NodoPalabra agregar(NodoPalabra actual, String palabra) {
        if (actual == null) {
            palabrasDistintas++;
            return new NodoPalabra(palabra);
        }
        int cmp = palabra.compareTo(actual.palabra);
        if (cmp < 0) {
            actual.izquierdo = agregar(actual.izquierdo, palabra);
        } else if (cmp > 0) {
            actual.derecho = agregar(actual.derecho, palabra);
        } else {
            actual.frecuencia++;   // ya existía: solo contamos una ocurrencia más
        }
        return actual;
    }

    public void imprimirAlfabetico() { inorden(raiz); }

    private void inorden(NodoPalabra nodo) {
        if (nodo == null) return;
        inorden(nodo.izquierdo);
        System.out.println(String.format("%-12s %d", nodo.palabra, nodo.frecuencia));
        inorden(nodo.derecho);
    }

    public int distintas() { return palabrasDistintas; }

    public static void main(String[] args) {
        String texto = "el arbol binario de busqueda guarda los datos ordenados "
                     + "y el recorrido inorden muestra los datos de menor a mayor "
                     + "el arbol permite buscar un dato sin recorrer todos los datos";

        ContadorPalabras contador = new ContadorPalabras();
        for (String p : texto.split(" ")) {
            contador.agregar(p);
        }

        System.out.println("Palabra      Frecuencia");
        contador.imprimirAlfabetico();
        System.out.println("Palabras distintas: " + contador.distintas());
    }
}
```

### 5.1 Salida esperada

```
Palabra      Frecuencia
a            1
arbol        2
binario      1
buscar       1
busqueda     1
dato         1
datos        3
de           2
el           3
guarda       1
inorden      1
los          3
mayor        1
menor        1
muestra      1
ordenados    1
permite      1
recorrer     1
recorrido    1
sin          1
todos        1
un           1
y            1
Palabras distintas: 23
```

### 5.2 Explicación

- El texto tiene 31 palabras, pero el árbol solo tiene **23 nodos**: las repetidas no crean nodos nuevos, solo suben el contador.
- `String` ya implementa `compareTo` (orden lexicográfico), por eso el árbol queda alfabético y `inorden` imprime la lista ordenada.
- Contrasta con hacerlo con una lista enlazada: para cada palabra habría que recorrer la lista buscándola (O(n) por palabra). Con el BST, en promedio, O(log n) por palabra.
- Esta es la misma idea que hay detrás de los `TreeMap` de Java: un árbol de búsqueda que asocia una clave con un valor.

---

## 6. Tabla resumen: BST frente a estructuras lineales

### 6.1 Resumen del BST

| Aspecto | Descripción |
|---|---|
| **Utilidad** | Mantener datos **ordenados dinámicamente** y permitir búsqueda, inserción y eliminación eficientes sin reordenar toda la colección. |
| **Aplicaciones típicas** | Índices de bases de datos (en variantes balanceadas), diccionarios y mapas ordenados (`TreeMap`, `TreeSet`), autocompletado, registros consultados por clave, conteo de frecuencias, consultas por rango. |
| **Ventajas** | Búsqueda, inserción y eliminación en O(log n) promedio; recorrido inorden entrega los datos ordenados; no requiere memoria contigua; admite consultas de mínimo, máximo y rangos. |
| **Desventajas** | Si no está balanceado, puede degenerar a O(n); más memoria por nodo (dos referencias); sin acceso por índice; implementación más compleja (sobre todo `eliminar`); peor localidad de memoria que un arreglo. |

### 6.2 Comparación con estructuras lineales

| Criterio | Arreglo desordenado | Arreglo ordenado | Lista enlazada | **BST** |
|---|---|---|---|---|
| Búsqueda (promedio) | O(n) | O(log n) (binaria) | O(n) | **O(log n)** |
| Búsqueda (peor caso) | O(n) | O(log n) | O(n) | **O(n)** si se degenera |
| Inserción | O(1) al final* | O(n) (desplazar) | O(1) al inicio / con `tail` | **O(log n)** promedio |
| Eliminación | O(n) | O(n) | O(n) (buscar) + O(1) | **O(log n)** promedio |
| Recorrido ordenado | requiere ordenar: O(n log n) | O(n) | requiere ordenar | **O(n)** (inorden) |
| Acceso por índice | O(1) | O(1) | O(n) | No aplica |
| Memoria extra por elemento | Ninguna | Ninguna | 1 referencia | 2 referencias |
| Cuándo conviene | Pocos datos, solo recorrer | Datos casi estáticos, muchas búsquedas | Muchas inserciones/borrados al inicio | Muchas búsquedas **y** cambios frecuentes, con orden |

\* Amortizado, si hay espacio disponible en la estructura subyacente.

**Lectura rápida:** el arreglo ordenado busca tan bien como el BST pero insertar es costoso; la lista inserta bien pero buscar es costoso. El BST es un **punto medio**: ambas operaciones en O(log n) en promedio, siempre que el árbol se mantenga balanceado.

---

## 7. Ejercicio para la casa

### Parte A: completar el árbol (programación)

Sobre `ArbolBinarioBusqueda<E>` implementen:

1. `boolean contiene(E clave)`: devuelve `true` si el elemento existe.
2. `int contarHojas()`: cuenta los nodos sin hijos (recursivo).
3. `void preorden()` y `void postorden()`: análogos a `inorden`.
4. `void eliminar(E clave)`: debe tratar los **tres casos**:
   - El nodo es una **hoja** (se quita).
   - El nodo tiene **un solo hijo** (el hijo lo reemplaza).
   - El nodo tiene **dos hijos** (se reemplaza su dato por el **sucesor inorden**, es decir, el mínimo del subárbol derecho, y luego se elimina ese sucesor).

Pruébenlo con el directorio del Ejemplo 1: eliminen `2024010` (hoja), luego `2024080` (dos hijos) y verifiquen con `inorden()` que el orden se conserva.

### Parte B: experimento (análisis de complejidad)

1. Escriban un programa que inserte los números del 1 al **1000** en un BST en **orden ascendente** y muestre `altura()`.
2. Repitan con los mismos 1000 números en **orden aleatorio** (`Collections.shuffle`).
3. **Antes de ejecutar**, escriban su predicción de ambas alturas y justifíquenla.
4. Agreguen un contador de comparaciones a `buscar` y midan cuántas comparaciones cuesta buscar el elemento `1000` en cada árbol, y compárenlo con una búsqueda lineal en una `List` de 1000 elementos.
5. Expliquen en 5 a 8 líneas qué pasó y qué implica para la elección de la estructura.

### Parte C: tabla comparativa propia

Completen en su informe la siguiente tabla con **evidencia de sus propias pruebas** (conteos, alturas, tiempos) y no solo con la teoría:

| Operación / criterio | Lista enlazada (`List<E>` de la Unidad 1) | Arreglo ordenado | BST aleatorio | BST con datos ordenados | Observaciones propias |
|---|---|---|---|---|---|
| Comparaciones para buscar el elemento 1000 | | | | | |
| Altura / profundidad | — | — | | | |
| Costo de insertar un elemento nuevo | | | | | |
| Listar todos en orden | | | | | |
| Memoria por elemento | | | | | |

Al final, respondan: **¿para qué tipo de aplicación elegirían un BST y para cuál no?** Justifiquen con complejidades.

### Entregables

- Código fuente `.java` de las partes A y B (compilable).
- Un archivo `INFORME.md` con las predicciones, resultados, la tabla de la Parte C y las conclusiones.

### Criterios de evaluación sugeridos

| Criterio | Peso |
|---|---|
| `eliminar` correcto en los tres casos | 30 % |
| Métodos `contiene`, `contarHojas`, `preorden`, `postorden` | 20 % |
| Experimento y análisis (predicción vs. resultado) | 25 % |
| Tabla comparativa con evidencia propia y conclusiones | 25 % |

---

## 8. Cómo compilar y ejecutar

Desde la carpeta con todos los `.java`:

```bash
javac *.java
java DirectorioEstudiantes
java ContadorPalabras
```

---

## 9. Para profundizar

- Los BST tienen variantes que **se autobalancean** (AVL, árbol rojo-negro), que garantizan O(log n) incluso en el peor caso; son la base de `TreeMap` y `TreeSet` en Java.
- Los **heaps** (montículos) son otro árbol binario, pero con una regla distinta (el padre es mayor o menor que los hijos) y se usan para colas de prioridad y Heapsort.
