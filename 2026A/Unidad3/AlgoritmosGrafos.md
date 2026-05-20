# Algoritmos de Recorrido en Grafos: BFS y DFS
### Estructuras de Datos — Ingeniería de Sistemas - Universidad Santiago de Cali, 2026

---

## Contenido

1. [BFS — Búsqueda en Anchura](#1-bfs--búsqueda-en-anchura-breadth-first-search)
   - [Explicación conceptual](#11-explicación-conceptual)
   - [Implementación en Java](#12-implementación-en-java)
   - [Ejemplo práctico](#13-ejemplo-práctico-red-de-conexiones-de-una-red-social)
   - [Ejercicio de aplicación](#14-ejercicio-de-aplicación)
2. [DFS — Búsqueda en Profundidad](#2-dfs--búsqueda-en-profundidad-depth-first-search)
   - [Explicación conceptual](#21-explicación-conceptual)
   - [Implementación en Java](#22-implementación-en-java)
   - [Ejemplo práctico](#23-ejemplo-práctico-detección-de-ciclos-en-dependencias-de-módulos)
   - [Ejercicio de aplicación](#24-ejercicio-de-aplicación)

---

## 1. BFS — Búsqueda en Anchura (Breadth-First Search)

### 1.1 Explicación Conceptual

**BFS** (Breadth-First Search) es un algoritmo de recorrido de grafos que explora los nodos **nivel por nivel**, es decir, primero visita todos los vecinos inmediatos de un nodo antes de avanzar a los nodos más lejanos.

**Intuición:** Imagina que arrojas una piedra en un estanque y los círculos concéntricos que se forman representan el orden en que BFS visita los nodos: primero los más cercanos al origen, luego los más alejados.

**Estructura de datos clave:** BFS usa una **cola (Queue)** para garantizar que los nodos se visiten en el orden en que fueron descubiertos (FIFO — primero en entrar, primero en salir).

**Propiedades importantes:**

- Garantiza encontrar el **camino más corto** (en número de aristas) entre el nodo origen y cualquier otro nodo en grafos no ponderados.
- Visita **todos** los nodos alcanzables desde el origen.
- Complejidad temporal: **O(V + E)**, donde V = vértices y E = aristas.
- Complejidad espacial: **O(V)** por el uso de la cola y el arreglo de visitados.

**Pasos del algoritmo:**

1. Marcar el nodo inicial como visitado y agregarlo a la cola.
2. Mientras la cola no esté vacía:
   a. Extraer el nodo al frente de la cola.
   b. Procesar ese nodo (imprimirlo, almacenarlo, etc.).
   c. Para cada vecino no visitado del nodo actual, marcarlo como visitado y agregarlo a la cola.

---

### 1.2 Implementación en Java

```java
import java.util.*;

/**
 * Representación de un grafo no dirigido usando listas de adyacencia.
 * Cada nodo tiene una lista de sus vecinos directos.
 */
public class GrafoBFS {

    // Número de vértices del grafo
    private int numVertices;

    // Lista de adyacencia: para cada vértice, una lista de sus vecinos
    private List<List<Integer>> listaAdyacencia;

    /**
     * Constructor: inicializa el grafo con V vértices sin aristas.
     * @param numVertices número total de vértices del grafo
     */
    public GrafoBFS(int numVertices) {
        this.numVertices = numVertices;
        listaAdyacencia = new ArrayList<>();

        // Inicializar una lista vacía para cada vértice
        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia.add(new ArrayList<>());
        }
    }

    /**
     * Agrega una arista no dirigida entre los vértices u y v.
     * Al ser no dirigido, se añade en ambas listas.
     * @param u vértice origen
     * @param v vértice destino
     */
    public void agregarArista(int u, int v) {
        listaAdyacencia.get(u).add(v);
        listaAdyacencia.get(v).add(u);
    }

    /**
     * Ejecuta el algoritmo BFS desde el vértice de inicio dado.
     * Imprime el orden en que se visitan los nodos.
     * @param inicio vértice desde el que comienza el recorrido
     */
    public void bfs(int inicio) {
        // Arreglo para registrar qué vértices ya fueron visitados
        boolean[] visitado = new boolean[numVertices];

        // Cola para mantener el orden de exploración (FIFO)
        Queue<Integer> cola = new LinkedList<>();

        // Paso 1: marcar el nodo inicial y agregarlo a la cola
        visitado[inicio] = true;
        cola.add(inicio);

        System.out.print("Recorrido BFS desde el nodo " + inicio + ": ");

        // Paso 2: procesar la cola hasta que esté vacía
        while (!cola.isEmpty()) {

            // Extraer el nodo al frente de la cola
            int nodoActual = cola.poll();
            System.out.print(nodoActual + " ");

            // Recorrer todos los vecinos del nodo actual
            for (int vecino : listaAdyacencia.get(nodoActual)) {

                // Solo visitar el vecino si aún no ha sido visitado
                if (!visitado[vecino]) {
                    visitado[vecino] = true; // Marcar antes de encolar para evitar duplicados
                    cola.add(vecino);
                }
            }
        }
        System.out.println();
    }

    /**
     * Punto de entrada para probar el grafo y el recorrido BFS.
     */
    public static void main(String[] args) {
        /*
         * Grafo de ejemplo con 6 vértices (0 a 5):
         *
         *    0 --- 1 --- 3
         *    |     |     |
         *    2     4     5
         */
        GrafoBFS grafo = new GrafoBFS(6);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(3, 5);

        // Iniciar el recorrido BFS desde el vértice 0
        grafo.bfs(0);
        // Salida esperada: 0 1 2 3 4 5
    }
}
```

**Traza de ejecución del ejemplo anterior:**

| Paso | Nodo procesado | Cola después de procesar | Visitados |
|------|---------------|--------------------------|-----------|
| 1    | 0 (inicio)    | [1, 2]                   | {0, 1, 2} |
| 2    | 1             | [2, 3, 4]                | {0, 1, 2, 3, 4} |
| 3    | 2             | [3, 4]                   | {0, 1, 2, 3, 4} |
| 4    | 3             | [4, 5]                   | {0, 1, 2, 3, 4, 5} |
| 5    | 4             | [5]                      | {0, 1, 2, 3, 4, 5} |
| 6    | 5             | []                       | {0, 1, 2, 3, 4, 5} |

---

### 1.3 Ejemplo Práctico: Red de Conexiones de una Red Social

**Contexto:** En una red social, queremos encontrar el **grado de separación** entre dos usuarios: cuántos intermediarios mínimos hay entre el usuario A y el usuario B (similar al concepto de "seis grados de separación").

BFS es ideal para esto porque garantiza encontrar el camino más corto en número de saltos.

```java
import java.util.*;

/**
 * Simulación de red social para encontrar el grado de separación
 * entre dos usuarios usando BFS.
 *
 * Los usuarios se representan como enteros (IDs).
 * Las aristas representan relaciones de amistad (bidireccionales).
 */
public class RedSocialBFS {

    private int numUsuarios;
    private List<List<Integer>> amigos; // lista de adyacencia

    /**
     * @param numUsuarios número total de usuarios en la red
     */
    public RedSocialBFS(int numUsuarios) {
        this.numUsuarios = numUsuarios;
        amigos = new ArrayList<>();
        for (int i = 0; i < numUsuarios; i++) {
            amigos.add(new ArrayList<>());
        }
    }

    /**
     * Registra una amistad entre dos usuarios.
     * @param u ID del primer usuario
     * @param v ID del segundo usuario
     */
    public void agregarAmistad(int u, int v) {
        amigos.get(u).add(v);
        amigos.get(v).add(u);
    }

    /**
     * Calcula el grado de separación (número mínimo de intermediarios)
     * entre dos usuarios usando BFS.
     *
     * @param origen  usuario de partida
     * @param destino usuario que se quiere alcanzar
     * @return número de saltos mínimos, o -1 si no hay conexión
     */
    public int gradoDeSeparacion(int origen, int destino) {
        if (origen == destino) return 0;

        boolean[] visitado = new boolean[numUsuarios];

        // distancia[i] almacena el número de saltos desde el origen hasta i
        int[] distancia = new int[numUsuarios];

        Queue<Integer> cola = new LinkedList<>();

        visitado[origen] = true;
        distancia[origen] = 0;
        cola.add(origen);

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            for (int amigo : amigos.get(actual)) {
                if (!visitado[amigo]) {
                    visitado[amigo] = true;
                    distancia[amigo] = distancia[actual] + 1; // un salto más que el nodo actual

                    // Si llegamos al destino, retornamos la distancia encontrada
                    if (amigo == destino) {
                        return distancia[amigo];
                    }

                    cola.add(amigo);
                }
            }
        }

        // Si el destino nunca fue alcanzado, no hay conexión
        return -1;
    }

    /**
     * Ejemplo de uso con 7 usuarios (0 a 6):
     *
     *  Ana(0) -- Luis(1) -- Pedro(3) -- Sofía(5)
     *   |          |                      |
     *  María(2)  Carlos(4)             Diego(6)
     */
    public static void main(String[] args) {
        RedSocialBFS red = new RedSocialBFS(7);

        // Amistades entre usuarios
        red.agregarAmistad(0, 1); // Ana - Luis
        red.agregarAmistad(0, 2); // Ana - María
        red.agregarAmistad(1, 3); // Luis - Pedro
        red.agregarAmistad(1, 4); // Luis - Carlos
        red.agregarAmistad(3, 5); // Pedro - Sofía
        red.agregarAmistad(5, 6); // Sofía - Diego

        // ¿Cuántos saltos hay entre Ana (0) y Diego (6)?
        int saltos = red.gradoDeSeparacion(0, 6);

        if (saltos != -1) {
            System.out.println("Grado de separación entre Ana y Diego: " + saltos + " salto(s)");
            // Resultado: 4 saltos → Ana → Luis → Pedro → Sofía → Diego
        } else {
            System.out.println("No existe conexión entre los usuarios.");
        }

        // ¿Entre Ana (0) y Carlos (4)?
        System.out.println("Grado de separación entre Ana y Carlos: "
                           + red.gradoDeSeparacion(0, 4) + " salto(s)");
        // Resultado: 2 saltos → Ana → Luis → Carlos
    }
}
```

**¿Por qué usar BFS y no DFS aquí?**

DFS podría encontrar *un* camino entre dos usuarios, pero no necesariamente el más corto. BFS, al explorar nivel por nivel, garantiza que el primer camino encontrado hasta el destino es el de menor número de saltos.

---

### 1.4 Ejercicio de Aplicación

#### Enunciado: Sistema de Rutas del Metro

Una ciudad tiene un sistema de **metro con N estaciones** conectadas por líneas de tren. Se le entrega la siguiente información:

- Un número de estaciones `N` (numeradas de 0 a N-1).
- Una lista de conexiones directas entre pares de estaciones (las líneas son bidireccionales).
- Una estación de **origen** y una estación de **destino**.

Se le proporciona la siguiente implementación de BFS adaptada al problema:

```java
import java.util.*;

public class SistemaMetro {

    private int numEstaciones;
    private List<List<Integer>> conexiones;

    public SistemaMetro(int numEstaciones) {
        this.numEstaciones = numEstaciones;
        conexiones = new ArrayList<>();
        for (int i = 0; i < numEstaciones; i++) {
            conexiones.add(new ArrayList<>());
        }
    }

    public void conectarEstaciones(int a, int b) {
        conexiones.get(a).add(b);
        conexiones.get(b).add(a);
    }

    /**
     * Encuentra la ruta con menor número de transbordos entre origen y destino.
     * Retorna la lista de estaciones en el orden del recorrido óptimo,
     * o una lista vacía si no hay conexión.
     */
    public List<Integer> rutaMinima(int origen, int destino) {
        boolean[] visitado = new boolean[numEstaciones];
        int[] anterior = new int[numEstaciones]; // guarda de qué nodo llegamos a cada uno
        Arrays.fill(anterior, -1);

        Queue<Integer> cola = new LinkedList<>();
        visitado[origen] = true;
        cola.add(origen);

        boolean encontrado = false;

        while (!cola.isEmpty() && !encontrado) {
            int actual = cola.poll();

            for (int vecino : conexiones.get(actual)) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    anterior[vecino] = actual; // registrar por dónde llegamos

                    if (vecino == destino) {
                        encontrado = true;
                        break;
                    }
                    cola.add(vecino);
                }
            }
        }

        // Reconstruir el camino desde destino hasta origen usando el arreglo 'anterior'
        List<Integer> ruta = new ArrayList<>();
        if (!encontrado && origen != destino) return ruta;

        for (int nodo = destino; nodo != -1; nodo = anterior[nodo]) {
            ruta.add(0, nodo); // insertar al inicio para obtener el orden correcto
        }
        return ruta;
    }

    public static void main(String[] args) {
        // TODO: completar según las instrucciones del ejercicio
    }
}
```

#### Instrucciones

Dado el siguiente mapa del metro:

```
Estación 0 (Terminal Norte) ── Estación 1 (Centro)
Estación 1 (Centro)          ── Estación 2 (Universidad)
Estación 1 (Centro)          ── Estación 3 (Estadio)
Estación 2 (Universidad)     ── Estación 4 (Hospital)
Estación 3 (Estadio)         ── Estación 5 (Aeropuerto)
Estación 4 (Hospital)        ── Estación 6 (Terminal Sur)
Estación 5 (Aeropuerto)      ── Estación 6 (Terminal Sur)
```

**Complete el método `main` con lo siguiente:**

1. Construya el grafo del metro con las estaciones y conexiones indicadas.
2. Encuentre e imprima la ruta de menor número de paradas entre:
   - Terminal Norte (0) y Terminal Sur (6).
   - Terminal Norte (0) y Aeropuerto (5).
3. Imprima el **número de transbordos** de cada ruta (número de paradas intermedias, es decir, longitud del camino menos 2, o 0 si van directo).
4. Responda: ¿existen varias rutas óptimas de la misma longitud? ¿Cuáles serían? (Respuesta escrita en comentario en el código).

**Criterios de evaluación:**

| Criterio | Descripción |
|----------|-------------|
| Construcción correcta del grafo | Las aristas corresponden exactamente al mapa dado |
| Uso adecuado del método `rutaMinima` | Se llama correctamente con los parámetros esperados |
| Salida correcta e interpretada | Se imprime la ruta y el número de transbordos con nombres de estaciones |
| Análisis de rutas alternativas | Se identifican y justifican las rutas óptimas equivalentes |

> **Nota:** No es necesario modificar los métodos ya implementados. El ejercicio evalúa su comprensión del algoritmo BFS y su capacidad de aplicarlo en un problema real.

---

## 2. DFS — Búsqueda en Profundidad (Depth-First Search)

### 2.1 Explicación Conceptual

**DFS** (Depth-First Search) es un algoritmo de recorrido de grafos que explora tan lejos como sea posible siguiendo un camino antes de retroceder y explorar otras rutas. En lugar de ir nivel por nivel (como BFS), DFS va "hacia dentro" del grafo hasta llegar a un nodo sin vecinos no visitados, y solo entonces regresa (**backtracking**).

**Intuición:** Imagina que exploras un laberinto y, en cada bifurcación, eliges siempre el primer camino disponible. Si llegas a un callejón sin salida, retrocedes hasta la última bifurcación y tomas el siguiente camino. Así funciona DFS.

**Estructura de datos clave:** DFS puede implementarse de dos formas:

- **Recursiva:** usando la pila de llamadas del sistema implícitamente.
- **Iterativa:** usando una **pila (Stack)** explícita (LIFO — último en entrar, primero en salir).

**Propiedades importantes:**

- No garantiza el camino más corto entre dos nodos.
- Es muy útil para: detección de ciclos, componentes conexas, orden topológico, laberintos.
- Complejidad temporal: **O(V + E)**, donde V = vértices y E = aristas.
- Complejidad espacial: **O(V)** por la pila de recursión y el arreglo de visitados.

**Pasos del algoritmo (versión recursiva):**

1. Marcar el nodo actual como visitado.
2. Procesar el nodo actual.
3. Para cada vecino no visitado del nodo actual, llamar recursivamente a DFS sobre ese vecino.

---

### 2.2 Implementación en Java

Se presentan las dos versiones: recursiva e iterativa.

```java
import java.util.*;

/**
 * Representación de un grafo no dirigido usando listas de adyacencia.
 * Incluye dos implementaciones de DFS: recursiva e iterativa.
 */
public class GrafoDFS {

    private int numVertices;
    private List<List<Integer>> listaAdyacencia;

    /**
     * Constructor: inicializa el grafo con numVertices vértices sin aristas.
     * @param numVertices número total de vértices del grafo
     */
    public GrafoDFS(int numVertices) {
        this.numVertices = numVertices;
        listaAdyacencia = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia.add(new ArrayList<>());
        }
    }

    /**
     * Agrega una arista no dirigida entre u y v.
     */
    public void agregarArista(int u, int v) {
        listaAdyacencia.get(u).add(v);
        listaAdyacencia.get(v).add(u);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // VERSIÓN RECURSIVA
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Método público para iniciar DFS recursivo desde un vértice de inicio.
     * Crea el arreglo de visitados y delega al método auxiliar recursivo.
     * @param inicio vértice desde el que comienza el recorrido
     */
    public void dfsRecursivo(int inicio) {
        boolean[] visitado = new boolean[numVertices];
        System.out.print("Recorrido DFS recursivo desde el nodo " + inicio + ": ");
        dfsRecursivoAux(inicio, visitado);
        System.out.println();
    }

    /**
     * Método auxiliar recursivo de DFS.
     * Explora en profundidad desde el nodo actual.
     * @param nodo     nodo que se está procesando en esta llamada
     * @param visitado arreglo compartido de nodos ya visitados
     */
    private void dfsRecursivoAux(int nodo, boolean[] visitado) {
        // Marcar el nodo actual como visitado y procesarlo
        visitado[nodo] = true;
        System.out.print(nodo + " ");

        // Para cada vecino, si no ha sido visitado, explorar en profundidad
        for (int vecino : listaAdyacencia.get(nodo)) {
            if (!visitado[vecino]) {
                dfsRecursivoAux(vecino, visitado); // llamada recursiva (va más profundo)
            }
        }
        // Al terminar el bucle, se produce el backtracking implícito
    }

    // ─────────────────────────────────────────────────────────────────────────
    // VERSIÓN ITERATIVA (con pila explícita)
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Implementación iterativa de DFS usando una pila explícita.
     * Produce el mismo tipo de recorrido en profundidad sin usar recursión.
     * Útil para grafos muy grandes donde la recursión podría causar desbordamiento de pila.
     * @param inicio vértice desde el que comienza el recorrido
     */
    public void dfsIterativo(int inicio) {
        boolean[] visitado = new boolean[numVertices];

        // Pila para simular el comportamiento recursivo (LIFO)
        Deque<Integer> pila = new ArrayDeque<>();

        pila.push(inicio); // empujar el nodo inicial

        System.out.print("Recorrido DFS iterativo desde el nodo " + inicio + ": ");

        while (!pila.isEmpty()) {
            int nodoActual = pila.pop(); // extraer el nodo en el tope de la pila

            // Solo procesar si no ha sido visitado (pueden haber duplicados en la pila)
            if (!visitado[nodoActual]) {
                visitado[nodoActual] = true;
                System.out.print(nodoActual + " ");

                // Agregar vecinos a la pila (en orden inverso para mantener consistencia con la versión recursiva)
                List<Integer> vecinos = listaAdyacencia.get(nodoActual);
                for (int i = vecinos.size() - 1; i >= 0; i--) {
                    if (!visitado[vecinos.get(i)]) {
                        pila.push(vecinos.get(i));
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Punto de entrada para probar el grafo y los recorridos DFS.
     */
    public static void main(String[] args) {
        /*
         * Grafo de ejemplo con 6 vértices (0 a 5):
         *
         *    0 --- 1 --- 3
         *    |     |     |
         *    2     4     5
         */
        GrafoDFS grafo = new GrafoDFS(6);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(3, 5);

        grafo.dfsRecursivo(0);
        // Salida esperada: 0 1 3 5 4 2

        grafo.dfsIterativo(0);
        // Salida esperada: 0 1 3 5 4 2
    }
}
```

**Traza de ejecución del DFS recursivo:**

```
dfs(0) → visita 0 → llama dfs(1)
  dfs(1) → visita 1 → llama dfs(3)
    dfs(3) → visita 3 → llama dfs(5)
      dfs(5) → visita 5 → no tiene vecinos sin visitar → RETORNA (backtracking)
    dfs(3) → continúa → no quedan vecinos → RETORNA
  dfs(1) → continúa → llama dfs(4)
    dfs(4) → visita 4 → no tiene vecinos sin visitar → RETORNA
  dfs(1) → RETORNA
dfs(0) → llama dfs(2)
  dfs(2) → visita 2 → no tiene vecinos sin visitar → RETORNA
dfs(0) → RETORNA

Orden de visita: 0 → 1 → 3 → 5 → 4 → 2
```

**Comparación BFS vs DFS sobre el mismo grafo:**

| Aspecto               | BFS                        | DFS                        |
|-----------------------|----------------------------|----------------------------|
| Orden de visita (ej.) | 0, 1, 2, 3, 4, 5           | 0, 1, 3, 5, 4, 2           |
| Estructura usada      | Cola (Queue)               | Pila (Stack) / Recursión   |
| Camino más corto      | ✅ Garantizado             | ❌ No garantizado           |
| Detección de ciclos   | Posible                    | ✅ Más natural              |
| Memoria               | Más (guarda nivel completo)| Menos (camino actual)      |
| Caso de uso típico    | Rutas mínimas, distancias  | Laberintos, ciclos, topología |

---

### 2.3 Ejemplo Práctico: Detección de Ciclos en Dependencias de Módulos

**Contexto:** En el desarrollo de software, los módulos o paquetes pueden depender entre sí. Si el módulo A depende de B, y B depende de A (directa o indirectamente), se produce un **ciclo de dependencias**, lo cual es un error de diseño que puede impedir la compilación o causar bucles infinitos de inicialización.

DFS es ideal para detectar ciclos porque lleva un registro de los nodos actualmente en la pila de recursión.

```java
import java.util.*;

/**
 * Detector de ciclos en un grafo dirigido de dependencias de módulos.
 * Usa DFS con seguimiento del camino activo (pila de recursión).
 *
 * Un ciclo existe si, durante el DFS, se visita un nodo que ya está
 * en el camino actual de recursión (no solo "visitado en general").
 */
public class DetectorCiclosDFS {

    private int numModulos;
    private List<List<Integer>> dependencias; // grafo dirigido: A → B significa A depende de B
    private String[] nombresModulos;

    /**
     * @param numModulos      número total de módulos
     * @param nombresModulos  nombres de los módulos (para la salida)
     */
    public DetectorCiclosDFS(int numModulos, String[] nombresModulos) {
        this.numModulos = numModulos;
        this.nombresModulos = nombresModulos;
        dependencias = new ArrayList<>();
        for (int i = 0; i < numModulos; i++) {
            dependencias.add(new ArrayList<>());
        }
    }

    /**
     * Registra que el módulo 'origen' depende del módulo 'destino'.
     * La arista es dirigida: origen → destino.
     */
    public void agregarDependencia(int origen, int destino) {
        dependencias.get(origen).add(destino);
    }

    /**
     * Verifica si el grafo de dependencias contiene algún ciclo.
     * @return true si existe al menos un ciclo, false si el grafo es acíclico
     */
    public boolean tieneCiclo() {
        boolean[] visitado = new boolean[numModulos];       // nodos ya completamente procesados
        boolean[] enPilaRecursion = new boolean[numModulos]; // nodos en el camino actual

        // Verificar desde cada módulo (puede haber componentes desconectados)
        for (int i = 0; i < numModulos; i++) {
            if (!visitado[i]) {
                if (dfsDetectaCiclo(i, visitado, enPilaRecursion)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * DFS auxiliar para detectar ciclos.
     *
     * @param nodo             módulo actual en exploración
     * @param visitado         marca permanente: este nodo y sus descendientes ya fueron procesados
     * @param enPilaRecursion  marca temporal: este nodo está en el camino activo actual
     * @return true si se detectó un ciclo desde este nodo
     */
    private boolean dfsDetectaCiclo(int nodo, boolean[] visitado, boolean[] enPilaRecursion) {
        // Marcar como parte del camino activo antes de explorar
        enPilaRecursion[nodo] = true;

        for (int vecino : dependencias.get(nodo)) {
            if (!visitado[vecino]) {
                // El vecino aún no fue procesado: explorar en profundidad
                if (dfsDetectaCiclo(vecino, visitado, enPilaRecursion)) {
                    return true; // propagar el hallazgo del ciclo
                }
            } else if (enPilaRecursion[vecino]) {
                // El vecino YA está en el camino activo → ¡ciclo detectado!
                System.out.println("Ciclo detectado: " + nombresModulos[nodo]
                                   + " → " + nombresModulos[vecino]);
                return true;
            }
        }

        // Al terminar de explorar este nodo, quitarlo del camino activo
        enPilaRecursion[nodo] = false;
        visitado[nodo] = true; // marcar como completamente procesado
        return false;
    }

    /**
     * Ejemplo con 5 módulos (0 a 4):
     *
     *  Auth(0) → Database(1) → Config(2) → Logger(3)
     *                 ↑                        |
     *               Utils(4) ←─────────────────┘   ← ciclo: Config→Logger→Utils→Database→Config
     */
    public static void main(String[] args) {
        String[] nombres = {"Auth", "Database", "Config", "Logger", "Utils"};
        DetectorCiclosDFS detector = new DetectorCiclosDFS(5, nombres);

        // Dependencias sin ciclo
        detector.agregarDependencia(0, 1); // Auth depende de Database
        detector.agregarDependencia(1, 2); // Database depende de Config
        detector.agregarDependencia(2, 3); // Config depende de Logger
        detector.agregarDependencia(3, 4); // Logger depende de Utils
        // Introducir ciclo: Utils depende de Database (¡crea ciclo!)
        detector.agregarDependencia(4, 1); // Utils → Database → Config → Logger → Utils → ...

        if (detector.tieneCiclo()) {
            System.out.println("ERROR: Dependencias circulares detectadas en el proyecto.");
        } else {
            System.out.println("OK: No hay dependencias circulares.");
        }
    }
}
```

**¿Por qué usar DFS y no BFS aquí?**

La detección de ciclos en grafos dirigidos requiere saber si un nodo en el **camino actual de exploración** vuelve a aparecer. BFS no tiene un concepto natural de "camino activo" porque explora en abanico (por niveles). DFS, al seguir un único camino en profundidad antes de retroceder, hace que el concepto de "nodo en la pila activa" sea inmediato y eficiente.

---

### 2.4 Ejercicio de Aplicación

#### Enunciado: Exploración de un Laberinto

Se le presenta un laberinto representado como un **grafo no dirigido**. Cada celda del laberinto es un nodo, y dos nodos están conectados si hay un pasillo entre ellos (sin muros). Su tarea es usar DFS para encontrar **si existe un camino** entre la entrada y la salida, e imprimir ese camino.

Se le proporciona la siguiente implementación:

```java
import java.util.*;

/**
 * Explorador de laberintos usando DFS.
 * El laberinto se modela como un grafo no dirigido donde:
 *   - Cada nodo es una celda.
 *   - Una arista entre dos nodos indica que hay un pasillo entre ellas.
 */
public class LaberintoDFS {

    private int numCeldas;
    private List<List<Integer>> pasillos;

    public LaberintoDFS(int numCeldas) {
        this.numCeldas = numCeldas;
        pasillos = new ArrayList<>();
        for (int i = 0; i < numCeldas; i++) {
            pasillos.add(new ArrayList<>());
        }
    }

    public void agregarPasillo(int a, int b) {
        pasillos.get(a).add(b);
        pasillos.get(b).add(a);
    }

    /**
     * Busca un camino desde 'entrada' hasta 'salida' usando DFS.
     * @return lista con las celdas del camino encontrado (puede no ser el más corto),
     *         o lista vacía si no existe camino.
     */
    public List<Integer> encontrarCamino(int entrada, int salida) {
        boolean[] visitado = new boolean[numCeldas];
        List<Integer> camino = new ArrayList<>();

        if (dfsConCamino(entrada, salida, visitado, camino)) {
            return camino;
        }
        return new ArrayList<>(); // lista vacía si no hay camino
    }

    /**
     * DFS recursivo que construye el camino mientras avanza.
     * @param actual   celda actual en exploración
     * @param salida   celda destino
     * @param visitado arreglo de nodos ya visitados
     * @param camino   lista que acumula el camino actual
     * @return true si se encontró la salida desde este punto
     */
    private boolean dfsConCamino(int actual, int salida,
                                  boolean[] visitado, List<Integer> camino) {
        visitado[actual] = true;
        camino.add(actual); // agregar al camino al entrar

        // Caso base: llegamos a la salida
        if (actual == salida) return true;

        for (int vecino : pasillos.get(actual)) {
            if (!visitado[vecino]) {
                if (dfsConCamino(vecino, salida, visitado, camino)) {
                    return true; // camino encontrado, propagar el éxito
                }
            }
        }

        // Backtracking: este nodo no lleva a la salida, quitarlo del camino
        camino.remove(camino.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        // TODO: completar según las instrucciones del ejercicio
    }
}
```

#### Instrucciones

El siguiente diagrama representa el laberinto. Los números son identificadores de celda y las líneas son los pasillos disponibles:

```
Entrada
  │
 [0] ── [1] ── [2]
         │      │
        [3]    [4] ── [5]
         │             │
        [6] ── [7] ── [8]
                       │
                      [9] ← Salida
```

**Pasillos disponibles:**
`0-1, 1-2, 1-3, 2-4, 4-5, 3-6, 5-8, 6-7, 7-8, 8-9`

**Complete el método `main` con lo siguiente:**

1. Construya el grafo del laberinto con las celdas y pasillos indicados.
2. Encuentre e imprima el camino que DFS encuentra desde la entrada (celda 0) hasta la salida (celda 9).
3. ¿El camino encontrado por DFS es el más corto posible? Justifique su respuesta en un comentario en el código e indique cuál sería el camino más corto.
4. Elimine el pasillo entre las celdas 7 y 8 del grafo. ¿Sigue existiendo un camino de la entrada a la salida? ¿Cuál es?
5. Ahora elimine también el pasillo entre 5 y 8. ¿Qué ocurre? Imprima un mensaje apropiado si no hay camino.

**Pista:** Para "eliminar" un pasillo, no llame a `agregarPasillo` para esa conexión al construir el grafo o construya el grafo en etapas.

**Criterios de evaluación:**

| Criterio | Descripción |
|----------|-------------|
| Construcción correcta del grafo | Las aristas corresponden exactamente al laberinto dado |
| Uso correcto de `encontrarCamino` | Se llama con los parámetros adecuados y se maneja la lista vacía |
| Análisis crítico del camino | Se compara el resultado de DFS con el camino más corto real |
| Manejo de casos sin camino | Se detecta e imprime correctamente cuando no hay salida disponible |
| Comprensión del backtracking | Se explica en comentario cómo DFS retrocede cuando llega a un callejón |

> **Nota:** El ejercicio evalúa su comprensión del comportamiento de DFS, sus diferencias con BFS, y cómo el backtracking le permite explorar todas las opciones del laberinto.

---

## Resumen Comparativo: BFS vs DFS

| Característica        | BFS                              | DFS                              |
|-----------------------|----------------------------------|----------------------------------|
| Estrategia            | Por niveles (anchura)            | Por profundidad                  |
| Estructura            | Cola (FIFO)                      | Pila / Recursión (LIFO)          |
| Camino más corto      | ✅ Sí (grafos no ponderados)     | ❌ No garantizado                |
| Detección de ciclos   | Posible                          | ✅ Más natural y eficiente       |
| Orden topológico      | ❌ No                            | ✅ Sí                            |
| Componentes conexas   | ✅ Sí                            | ✅ Sí                            |
| Laberintos            | Funciona, pero usa más memoria   | ✅ Más natural (backtracking)    |
| Redes sociales        | ✅ Ideal (grados de separación)  | No ideal                         |
| Complejidad temporal  | O(V + E)                         | O(V + E)                         |
| Complejidad espacial  | O(V)                             | O(V)                             |

**Regla general:**
- ¿Necesita el **camino más corto**? → Use **BFS**.
- ¿Necesita explorar **todas las posibilidades** o detectar **ciclos**? → Use **DFS**.

---

*Estructuras de Datos — Ingeniería de Sistemas - Universidad Santiago de Cali 2026A*
