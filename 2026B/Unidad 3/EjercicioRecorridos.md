# Estructuras de Datos 2026B
# Universidad Santiago de Cali

## Guía de preparación: implementación y recorridos de grafos en Java

## 1. Propósito

Esta guía permite estudiar y practicar los conceptos necesarios para una
evaluación sobre grafos, concentrándose en:

-   Representación mediante listas de adyacencia.
-   Representación mediante matrices de adyacencia.
-   Recorrido en profundidad: DFS (*Depth-First Search*).
-   Recorrido en anchura: BFS (*Breadth-First Search*).
-   Caminos mínimos con el algoritmo de Dijkstra.

Se asume una implementación en Java con nodos genéricos `Nodo<E>`, de
modo que cada vértice pueda almacenar un dato de cualquier tipo:
`String`, `Integer`, objetos `Ciudad`, `Estudiante`, `Estación`, etc.

> Nota: en ocasiones se escribe por error «BFS, BFS» al enumerar los
> recorridos. Los dos recorridos básicos que se estudian aquí son **DFS
> y BFS**, además de Dijkstra.

------------------------------------------------------------------------

## 2. Conceptos fundamentales

Un **grafo** es una estructura matemática formada por un conjunto de
vértices (o nodos) y un conjunto de aristas que conectan pares de
vértices.

Se suele representar como:

\[ G = (V, E) \]

Donde:

-   (V): conjunto de vértices.
-   (E): conjunto de aristas.

### 2.1 Vértices y aristas

-   Un **vértice** representa una entidad: una ciudad, una persona, una
    página web o una estación.
-   Una **arista** representa una relación o conexión entre dos
    vértices.
-   Un **peso** es un valor asociado a una arista. Puede representar
    distancia, costo, tiempo u otra magnitud.

Ejemplo: si los vértices son ciudades, una arista puede representar una
carretera y su peso la distancia en kilómetros.

### 2.2 Grafos dirigidos y no dirigidos

**Grafo no dirigido:** las conexiones se pueden recorrer en ambos
sentidos. Si existe una arista entre A y B, se puede ir de A a B y de B
a A.

**Grafo dirigido:** cada arista tiene una dirección. Una arista A → B no
implica necesariamente que exista B → A.

### 2.3 Grafos ponderados y no ponderados

**Ponderado:** cada arista tiene un peso. Por ejemplo, A → B con peso
12.

**No ponderado:** las aristas no tienen un costo explícito. Para algunos
algoritmos se puede asumir que cada arista tiene peso 1.

### 2.4 Grado de un vértice

En un grafo no dirigido, el **grado** de un vértice es la cantidad de
aristas incidentes en él.

En un grafo dirigido se distinguen:

-   **Grado de entrada:** cantidad de aristas que llegan al vértice.
-   **Grado de salida:** cantidad de aristas que salen del vértice.

### 2.5 Camino, ciclo y conectividad

-   Un **camino** es una secuencia de vértices conectados por aristas.
-   Un **ciclo** es un camino que comienza y termina en el mismo
    vértice.
-   Un grafo no dirigido es **conexo** si existe un camino entre
    cualquier par de vértices.
-   Un grafo puede tener componentes desconectados. Un recorrido que
    comienza en un vértice solo visita los vértices alcanzables desde
    él.

------------------------------------------------------------------------

## 3. Modelado de nodos en Java: `Nodo<E>`

Una clase genérica permite almacenar distintos tipos de información sin
crear una clase de nodo diferente para cada tipo de dato.

Ejemplo conceptual:

``` java
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
}
```

### ¿Qué significa `<E>`?

`E` es un parámetro de tipo. Al crear un nodo, se define qué tipo de
dato almacenará:

``` java
Nodo<String> ciudad = new Nodo<>(1, "Cali");
Nodo<Integer> numero = new Nodo<>(2, 45);
```

El mismo diseño sirve para ambos casos.

### ¿Por qué conviene tener un identificador?

El identificador permite distinguir nodos aunque almacenen datos
iguales. Por ejemplo, dos vértices podrían almacenar el nombre «Central»
y representar estaciones diferentes.

Si se utiliza `HashMap`, `HashSet`, `contains()` o `indexOf()` para
localizar nodos, es importante definir coherentemente `equals()` y
`hashCode()`. En la plantilla de clase se puede establecer que dos nodos
son iguales cuando tienen el mismo `id`.

**Cuidado:** los identificadores deben ser únicos dentro de un mismo
grafo. Si se permite repetirlos, dos vértices distintos podrían
confundirse.

------------------------------------------------------------------------

## 4. Representación mediante listas de adyacencia

### 4.1 Idea

Una lista de adyacencia almacena, para cada vértice, la colección de
vértices a los que está conectado.

Ejemplo de grafo no dirigido:

``` text
A ----- B
|       |
|       |
C ----- D
```

Una posible lista de adyacencia es:

``` text
A: B, C
B: A, D
C: A, D
D: B, C
```

Cada conexión aparece en las listas de ambos extremos porque el grafo es
no dirigido.

### 4.2 Estructura auxiliar en Java

Una implementación habitual utiliza un `Map`:

``` java
Map<Nodo<E>, List<Nodo<E>>> adyacencias;
```

-   La clave es un nodo.
-   El valor es la lista de sus vecinos.

Si se trabaja con aristas ponderadas, cada elemento de la lista puede
ser un objeto `Arista<E>` que almacene el nodo destino y el peso:

``` java
class Arista<E> {
    private Nodo<E> destino;
    private double peso;
}
```

Por tanto, la estructura puede ser:

``` java
Map<Nodo<E>, List<Arista<E>>> adyacencias;
```

### 4.3 Operaciones básicas

**Crear el grafo:** inicializar el mapa y definir si será dirigido o no
dirigido.

``` java
Map<Nodo<E>, List<Arista<E>>> adyacencias =
    new LinkedHashMap<>();
```

**Agregar un nodo:** crear una entrada para el nodo, inicialmente con
una lista vacía.

``` java
adyacencias.put(nodo, new ArrayList<>());
```

Antes de agregarlo, se debe comprobar que no exista ya un nodo con el
mismo identificador.

**Agregar una arista:** localizar la lista del nodo origen y añadir una
arista cuyo destino sea el nodo final.

``` java
adyacencias.get(origen).add(
    new Arista<>(destino, peso)
);
```

Si el grafo es no dirigido, también se agrega la conexión inversa:

``` java
adyacencias.get(destino).add(
    new Arista<>(origen, peso)
);
```

**Consultar vecinos:** recuperar la lista asociada al nodo.

``` java
List<Arista<E>> vecinos = adyacencias.get(nodo);
```

### 4.4 Ventajas y limitaciones

**Ventajas**

-   Usa memoria proporcional al número de vértices y aristas.
-   Es eficiente para recorrer los vecinos de un vértice.
-   Resulta conveniente para grafos dispersos, es decir, con
    relativamente pocas aristas.

**Limitaciones**

-   Comprobar si existe una arista concreta puede requerir recorrer la
    lista de vecinos.
-   La implementación debe gestionar cuidadosamente nodos inexistentes y
    conexiones duplicadas, si no se desean.

### 4.5 Complejidad habitual

Con una implementación basada en mapas y listas:

-   Agregar un nodo: normalmente O(1) promedio, sin contar el costo de
    verificar duplicados.
-   Recorrer los vecinos de un nodo: O(grado del nodo).
-   Recorrer todo el grafo: O(V + E).
-   Almacenamiento: O(V + E).

Estas complejidades son orientativas y dependen de las operaciones
concretas y de la estructura elegida.

------------------------------------------------------------------------

## 5. Representación mediante matriz de adyacencia

### 5.1 Idea

Una matriz de adyacencia utiliza una matriz cuadrada. Cada fila y cada
columna representan un vértice.

La celda `matriz[i][j]` indica si existe una arista desde el vértice
ubicado en la posición `i` hasta el ubicado en la posición `j`.

Ejemplo no ponderado:

``` text
    A  B  C  D
A   0  1  1  0
B   1  0  0  1
C   1  0  0  1
D   0  1  1  0
```

-   `1`: existe una arista.
-   `0`: no existe una arista.

En un grafo no dirigido, la matriz es simétrica: si `matriz[i][j]`
indica una conexión, también `matriz[j][i]`.

### 5.2 Matriz para grafos ponderados

En un grafo ponderado, la celda puede almacenar el peso de la arista.

Por ejemplo:

``` text
    A   B   C
A   0  12   7
B  12   0   ∞
C   7   ∞   0
```

Se puede utilizar `Double.POSITIVE_INFINITY` para representar que no
existe una arista. Otra alternativa es usar un valor especial, siempre
que no pueda confundirse con un peso válido.

Es importante distinguir entre:

-   Una arista de peso cero, que podría ser válida.
-   La ausencia de una arista.

Por esa razón, usar cero como «no hay conexión» puede ser problemático
en grafos ponderados.

### 5.3 Estructuras auxiliares en Java

Se necesita:

``` java
List<Nodo<E>> nodos;
double[][] matriz;
```

La lista permite asociar cada posición de la matriz con un objeto
`Nodo<E>`.

Por ejemplo:

``` text
Índice:  0       1         2
Nodo:   Cali   Palmira   Buga
```

### 5.4 Operaciones básicas

**Crear el grafo:** inicializar una lista vacía y una matriz de tamaño
cero.

``` java
nodos = new ArrayList<>();
matriz = new double[0][0];
```

**Agregar un nodo:** añadirlo a la lista y crear una matriz de tamaño
`(n + 1) x (n + 1)`, copiando los valores anteriores.

**Agregar una arista:** buscar los índices de origen y destino y
actualizar la celda correspondiente.

``` java
int i = nodos.indexOf(origen);
int j = nodos.indexOf(destino);

matriz[i][j] = peso;
```

Para un grafo no dirigido también se actualiza:

``` java
matriz[j][i] = peso;
```

**Consultar una arista:** revisar directamente la celda `matriz[i][j]`.

**Consultar vecinos:** recorrer la fila correspondiente al vértice y
seleccionar las columnas que representen conexiones existentes.

### 5.5 Ventajas y limitaciones

**Ventajas**

-   Consultar si existe una arista es O(1), una vez conocidos los
    índices.
-   La representación es sencilla y permite visualizar todas las
    conexiones.
-   Resulta conveniente para grafos densos, con muchas aristas.

**Limitaciones**

-   Consume O(V²) memoria, incluso si hay pocas aristas.
-   Recorrer los vecinos de un vértice requiere revisar una fila
    completa: O(V).
-   Agregar un nodo a una matriz dinámica puede requerir crear y copiar
    una matriz nueva.

### 5.6 Complejidad habitual

-   Consultar una arista: O(1), una vez obtenidos los índices.
-   Recorrer los vecinos de un nodo: O(V).
-   Recorrer todo el grafo: O(V²).
-   Almacenamiento: O(V²).

Si se usa `List.indexOf()` para obtener los índices, esa búsqueda cuesta
O(V). La consulta de la celda sigue siendo O(1) después de obtenerlos.

------------------------------------------------------------------------

## 6. Recorrido en profundidad: DFS

### 6.1 ¿Qué hace?

DFS significa *Depth-First Search* (búsqueda en profundidad).

El algoritmo comienza en un vértice y explora un camino tanto como sea
posible antes de retroceder para explorar otras alternativas.

Una forma intuitiva de entenderlo es imaginar que se explora un
laberinto: se avanza por un camino hasta que no se puede continuar;
entonces se retrocede hasta encontrar una alternativa.

### 6.2 Estructura auxiliar

DFS utiliza:

-   Un conjunto `visitados` para no procesar repetidamente un vértice.
-   Una pila (`Stack` o `Deque`) si se implementa de forma iterativa, o
    la pila de llamadas del sistema si se implementa recursivamente.

### 6.3 Implementación recursiva conceptual

``` java
void DFS(Nodo<E> actual, Set<Nodo<E>> visitados) {

    visitados.add(actual);

    System.out.println(actual.getDato());

    for (Arista<E> arista : obtenerVecinos(actual)) {

        Nodo<E> vecino = arista.getDestino();

        if (!visitados.contains(vecino)) {
            DFS(vecino, visitados);
        }
    }
}
```

Para iniciar el recorrido:

``` java
Set<Nodo<E>> visitados = new HashSet<>();

DFS(inicio, visitados);
```

### 6.4 ¿Cómo funciona paso a paso?

1.  Se recibe el nodo inicial.
2.  Se marca como visitado.
3.  Se procesa o imprime su dato.
4.  Se consultan sus vecinos.
5.  Si un vecino no ha sido visitado, se ejecuta DFS desde ese vecino.
6.  Cuando ya no quedan vecinos sin visitar, la ejecución regresa al
    nivel anterior.

El orden exacto depende del orden en que se almacenen o recorran los
vecinos.

### 6.5 Complejidad

Con listas de adyacencia:

\[ O(V + E) \]

Con matriz de adyacencia:

\[ O(V\^2) \]

En ambos casos, se necesita espacio adicional O(V) para los nodos
visitados y la pila de ejecución o estructura explícita, sin contar la
representación del grafo.

### 6.6 Aplicaciones

-   Verificar si un vértice es alcanzable desde otro.
-   Detectar ciclos (con adaptaciones según sea dirigido o no dirigido).
-   Encontrar componentes conexas.
-   Explorar laberintos y espacios de estados.

------------------------------------------------------------------------

## 7. Recorrido en anchura: BFS

### 7.1 ¿Qué hace?

BFS significa *Breadth-First Search* (búsqueda en anchura).

El algoritmo visita primero los vecinos directos del nodo inicial,
después los vecinos de esos vecinos y así sucesivamente.

Se comporta como una exploración por niveles.

### 7.2 Estructura auxiliar

BFS utiliza:

-   Una **cola** (`Queue`), normalmente implementada con `LinkedList` o
    `ArrayDeque`.
-   Un conjunto `visitados` para evitar insertar varias veces el mismo
    vértice.

La cola sigue la política FIFO (*First In, First Out*): el primero en
entrar es el primero en salir.

### 7.3 Implementación conceptual

``` java
void BFS(Nodo<E> inicio) {

    Queue<Nodo<E>> cola = new ArrayDeque<>();

    Set<Nodo<E>> visitados = new HashSet<>();

    visitados.add(inicio);
    cola.add(inicio);

    while (!cola.isEmpty()) {

        Nodo<E> actual = cola.poll();

        System.out.println(actual.getDato());

        for (Arista<E> arista : obtenerVecinos(actual)) {

            Nodo<E> vecino = arista.getDestino();

            if (!visitados.contains(vecino)) {

                visitados.add(vecino);
                cola.add(vecino);
            }
        }
    }
}
```

### 7.4 ¿Cómo funciona paso a paso?

1.  Se crea una cola vacía.
2.  Se marca el nodo inicial como visitado y se inserta en la cola.
3.  Mientras la cola tenga elementos:
    -   Se extrae el primer nodo.
    -   Se procesa.
    -   Se recorren sus vecinos.
    -   Cada vecino no visitado se marca como visitado y se inserta en
        la cola.
4.  El recorrido termina cuando la cola queda vacía.

Es importante marcar el nodo como visitado **cuando se inserta en la
cola**, no cuando se extrae. Así se evita insertar varias veces el mismo
vértice.

### 7.5 Complejidad

Con listas de adyacencia:

\[ O(V + E) \]

Con matriz de adyacencia:

\[ O(V\^2) \]

El conjunto de visitados y la cola requieren hasta O(V) espacio
adicional.

### 7.6 Aplicaciones

-   Encontrar el menor número de aristas entre dos vértices en un grafo
    no ponderado.
-   Explorar redes por niveles.
-   Encontrar personas o elementos a cierta distancia en una red.
-   Resolver problemas de laberintos cuando cada movimiento tiene el
    mismo costo.

**Importante:** BFS no reemplaza a Dijkstra en grafos ponderados con
costos diferentes. BFS minimiza la cantidad de aristas, no
necesariamente la suma de sus pesos.

------------------------------------------------------------------------

## 8. Algoritmo de Dijkstra

### 8.1 ¿Qué hace?

Dijkstra encuentra los caminos de costo mínimo desde un vértice de
origen hacia los demás vértices alcanzables de un grafo ponderado con
pesos no negativos.

Por ejemplo, si las aristas representan distancias entre ciudades,
Dijkstra permite encontrar la ruta cuya distancia total sea menor.

### 8.2 Estructuras auxiliares

Una implementación eficiente utiliza:

-   `Map<Nodo<E>, Double> distancias`: almacena la mejor distancia
    conocida desde el origen.
-   `Map<Nodo<E>, Nodo<E>> predecesores`: permite reconstruir el camino
    mínimo.
-   `PriorityQueue`: selecciona el vértice pendiente con menor distancia
    provisional.
-   Un conjunto `visitados` o una estrategia de descarte de entradas
    obsoletas.

La cola de prioridad organiza los elementos según su distancia
acumulada. No es una cola FIFO convencional.

### 8.3 Idea del algoritmo

1.  Inicializar la distancia del origen en cero.
2.  Inicializar las distancias de los demás nodos en infinito.
3.  Insertar el origen en la cola de prioridad.
4.  Extraer el nodo con menor distancia provisional.
5.  Examinar sus aristas salientes.
6.  Calcular la distancia alternativa hacia cada vecino.
7.  Si la nueva distancia es menor que la registrada, actualizar la
    distancia y el predecesor, e insertar o actualizar la prioridad del
    vecino.
8.  Repetir hasta que no queden candidatos pendientes.

La operación de mejorar una distancia se conoce como **relajación de una
arista**.

### 8.4 Ejemplo de relajación

Supongamos:

``` text
A ---- 4 ---- B
 \            |
  2           1
   \          |
    C --------
```

Desde A:

-   Distancia inicial a A: 0.
-   Distancia inicial a B: infinito.
-   Distancia inicial a C: infinito.

Al procesar A:

-   A → B: distancia alternativa 4. Se actualiza B a 4.
-   A → C: distancia alternativa 2. Se actualiza C a 2.

Al procesar C, se puede mejorar la distancia hacia B:

-   A → C → B = 2 + 1 = 3.
-   Como 3 es menor que 4, la distancia de B se actualiza a 3.

El camino mínimo es A → C → B, con costo total 3.

### 8.5 Implementación de referencia con listas de adyacencia

El siguiente código muestra una versión didáctica. Utiliza una cola de
prioridad y permite reconstruir el camino mediante predecesores.

``` java
public Map<Nodo<E>, Double> dijkstra(Nodo<E> origen) {

    Map<Nodo<E>, Double> distancias = new HashMap<>();

    Map<Nodo<E>, Nodo<E>> predecesores = new HashMap<>();

    PriorityQueue<NodoDistancia<E>> cola =
        new PriorityQueue<>(
            Comparator.comparingDouble(nd -> nd.distancia)
        );

    // Inicializar distancias
    for (Nodo<E> nodo : obtenerNodos()) {
        distancias.put(nodo, Double.POSITIVE_INFINITY);
    }

    if (!distancias.containsKey(origen)) {
        throw new IllegalArgumentException("Origen inexistente");
    }

    distancias.put(origen, 0.0);

    cola.add(new NodoDistancia<>(origen, 0.0));

    while (!cola.isEmpty()) {

        NodoDistancia<E> actual = cola.poll();

        Nodo<E> u = actual.nodo;

        // Descartar entradas obsoletas
        if (actual.distancia > distancias.get(u)) {
            continue;
        }

        for (Arista<E> arista : obtenerVecinos(u)) {

            Nodo<E> v = arista.getDestino();
            double peso = arista.getPeso();

            if (peso < 0) {
                throw new IllegalArgumentException(
                    "Dijkstra no admite pesos negativos");
            }

            double nuevaDistancia =
                distancias.get(u) + peso;

            if (nuevaDistancia < distancias.get(v)) {

                distancias.put(v, nuevaDistancia);
                predecesores.put(v, u);

                cola.add(
                    new NodoDistancia<>(v, nuevaDistancia)
                );
            }
        }
    }

    return distancias;
}
```

La clase auxiliar para la cola:

``` java
class NodoDistancia<E> {

    Nodo<E> nodo;
    double distancia;

    NodoDistancia(Nodo<E> nodo, double distancia) {
        this.nodo = nodo;
        this.distancia = distancia;
    }
}
```

**Observación importante sobre el ejemplo:** para mantener el código
compacto, `predecesores` es local. Si se desea reconstruir la ruta
después de llamar a `dijkstra()`, se puede devolver un objeto resultado
que contenga tanto las distancias como los predecesores, o conservar
ambos mapas como atributos.

También es recomendable validar los pesos negativos antes de comenzar el
algoritmo, o al menos garantizar que el grafo de entrada no los
contiene. La validación dentro del recorrido solo detecta los pesos
negativos que se alcanzan desde el origen.

### 8.6 Reconstrucción del camino

Si se conservan los predecesores, se reconstruye el camino desde el
destino hacia el origen:

``` java
List<Nodo<E>> reconstruirCamino(
        Nodo<E> origen,
        Nodo<E> destino,
        Map<Nodo<E>, Nodo<E>> predecesores) {

    List<Nodo<E>> camino = new ArrayList<>();

    Nodo<E> actual = destino;

    while (actual != null) {
        camino.add(actual);

        if (actual.equals(origen)) {
            break;
        }

        actual = predecesores.get(actual);
    }

    Collections.reverse(camino);

    if (camino.isEmpty() ||
        !camino.get(0).equals(origen)) {
        return Collections.emptyList();
    }

    return camino;
}
```

### 8.7 Complejidad

Con listas de adyacencia y una cola de prioridad basada en heap binario,
la complejidad habitual es:

\[ O((V + E)`\log `{=tex}V) \]

Con matriz de adyacencia y selección lineal del vértice no procesado de
menor distancia, la implementación clásica tiene complejidad:

\[ O(V\^2) \]

El almacenamiento auxiliar de distancias, predecesores y cola es O(V),
en términos de elementos, aunque una cola de prioridad puede contener
entradas repetidas en una implementación que inserta una nueva entrada
cada vez que mejora una distancia.

### 8.8 Restricción fundamental

**Dijkstra no debe utilizarse con aristas de peso negativo.**

Si existen pesos negativos, se debe estudiar otro algoritmo, como
Bellman-Ford, que está fuera del alcance de esta guía.

------------------------------------------------------------------------

## 9. Tabla comparativa de las representaciones

  -----------------------------------------------------------------------
  Criterio                Listas de adyacencia    Matriz de adyacencia
  ----------------------- ----------------------- -----------------------
  Estructura principal    Mapa de nodos y listas  Matriz bidimensional
                          de vecinos              

  Memoria                 O(V + E)                O(V²)

  Consultar una arista    O(grado del nodo),      O(1) después de obtener
                          normalmente             índices

  Recorrer vecinos        O(grado del nodo)       O(V)

  Agregar una arista      O(1) promedio al final  O(1), una vez obtenidos
                          de la lista             índices

  Agregar un nodo         O(1) promedio en el     Puede requerir ampliar
                          mapa                    y copiar la matriz

  Adecuada para           Grafos dispersos        Grafos densos

  Recorrido completo      O(V + E)                O(V²)
  -----------------------------------------------------------------------

------------------------------------------------------------------------

## 10. Tabla comparativa de DFS, BFS y Dijkstra

  -----------------------------------------------------------------------
  Criterio          DFS               BFS               Dijkstra
  ----------------- ----------------- ----------------- -----------------
  Objetivo          Explorar en       Explorar por      Encontrar caminos
                    profundidad       niveles           de costo mínimo

  Estructura        Pila o            Cola FIFO         Cola de prioridad
  auxiliar          recursividad                        

  Estructura de     Visitados         Visitados         Distancias y
  control                                               predecesores;
                                                        cola de prioridad

  Considera pesos   No                No                Sí

  Admite pesos      No aplica al      No aplica al      No
  negativos         recorrido         recorrido         

  Encuentra camino  No necesariamente En cantidad de    En suma de pesos
  mínimo                              aristas, si no    no negativos
                                      hay pesos         
                                      diferentes        

  Complejidad con   O(V + E)          O(V + E)          O((V + E) log V)
  listas                                                

  Complejidad con   O(V²)             O(V²)             O(V²) en la
  matriz                                                versión clásica

  Aplicación típica Exploración y     Distancias por    Rutas de menor
                    conectividad      niveles           costo
  -----------------------------------------------------------------------

------------------------------------------------------------------------

## 11. Ejemplo integrador para practicar

Considere el siguiente grafo no dirigido y ponderado:

``` text
          4
     A -------- B
     |          | \
   2 |        1 |  \ 5
     |          |   \
     C -------- D -- E
          3       2
```

Aristas:

  Origen   Destino     Peso
  -------- --------- ------
  A        B              4
  A        C              2
  B        D              1
  B        E              5
  C        D              3
  D        E              2

### Actividades de preparación

1.  Identifique V y E.
2.  Indique si el grafo es dirigido o no dirigido y si es ponderado o no
    ponderado.
3.  Construya la lista de adyacencia.
4.  Construya la matriz de adyacencia ponderada. Use infinito para
    indicar que no hay arista.
5.  Ejecute DFS desde A. Suponga que los vecinos se procesan en orden
    alfabético.
6.  Ejecute BFS desde A con el mismo orden de vecinos.
7.  Ejecute Dijkstra desde A. Registre la distancia provisional de cada
    vértice después de cada iteración.
8.  Indique el costo mínimo y el camino encontrado desde A hasta E.
9.  Explique qué estructura auxiliar utiliza cada algoritmo y por qué.
10. Compare qué representación sería más conveniente si el grafo tuviera
    miles de vértices y pocas aristas.

### Resultados que debe poder justificar

Con vecinos en orden alfabético:

-   DFS desde A: A, B, D, C, E (el orden puede variar si cambia la
    estrategia de exploración).
-   BFS desde A: A, B, C, D, E.
-   Distancias mínimas desde A:
    -   A: 0
    -   B: 4
    -   C: 2
    -   D: 5
    -   E: 7
-   Un camino mínimo de A a E es A → B → D → E, con costo 7.

No basta con memorizar estos resultados: debe poder explicar cada
decisión del algoritmo.

------------------------------------------------------------------------

## 12. Errores frecuentes que conviene evitar

1.  **No marcar los nodos visitados:** puede provocar ciclos infinitos o
    procesamiento repetido.
2.  **Confundir pila y cola:** DFS utiliza una pila o recursividad; BFS
    utiliza una cola FIFO.
3.  **Marcar un nodo demasiado tarde en BFS:** se debe marcar cuando se
    inserta en la cola.
4.  **Suponer que BFS minimiza distancias ponderadas:** BFS minimiza
    cantidad de aristas, no suma de pesos.
5.  **Usar Dijkstra con pesos negativos:** sus garantías dejan de ser
    válidas.
6.  **Olvidar la arista inversa:** en un grafo no dirigido, al insertar
    A--B deben registrarse A--B y B--A.
7.  **Confundir ausencia de arista con peso cero:** en grafos ponderados
    puede existir una arista de peso cero.
8.  **No validar la existencia de los nodos:** antes de agregar una
    arista, origen y destino deben pertenecer al grafo.
9.  **Ignorar los componentes desconectados:** DFS o BFS desde un único
    origen no visita necesariamente todo el grafo.
10. **Suponer que el orden de recorrido es único:** depende del orden de
    los vecinos.

------------------------------------------------------------------------

## 13. Lista de comprobación para la evaluación

Antes de presentar la evaluación, asegúrese de poder:

-   [ ] Explicar qué es un grafo, un vértice y una arista.
-   [ ] Diferenciar grafos dirigidos y no dirigidos.
-   [ ] Diferenciar grafos ponderados y no ponderados.
-   [ ] Explicar el uso de genéricos en `Nodo<E>`.
-   [ ] Construir una lista de adyacencia.
-   [ ] Construir una matriz de adyacencia.
-   [ ] Agregar nodos y aristas en ambas representaciones.
-   [ ] Identificar las ventajas y limitaciones de cada representación.
-   [ ] Ejecutar DFS manualmente y explicar el uso de la pila.
-   [ ] Ejecutar BFS manualmente y explicar el uso de la cola.
-   [ ] Ejecutar Dijkstra y registrar las relajaciones.
-   [ ] Reconstruir un camino mínimo mediante predecesores.
-   [ ] Comparar las complejidades de los algoritmos.
-   [ ] Justificar qué representación y algoritmo conviene utilizar
    según el problema.

## 14. Idea clave para recordar

La representación del grafo y el algoritmo de recorrido son decisiones
relacionadas, pero distintas:

-   **La representación** determina cómo se almacenan los vértices y las
    conexiones.
-   **El algoritmo** determina cómo se explora el grafo o cómo se
    calcula una solución.
-   **Las estructuras auxiliares** permiten controlar el orden de
    exploración, evitar repeticiones y almacenar resultados.

Un mismo grafo puede representarse mediante listas o matrices y
recorrerse con DFS o BFS. Dijkstra, por su parte, utiliza los pesos de
las aristas para calcular caminos de costo mínimo.
