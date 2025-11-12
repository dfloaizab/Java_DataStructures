# Estructuras de Datos, 2025B
## Algoritmos sobre grafos (implementación en Java)

**Objetivos**

1. Entender y distinguir DFS y BFS (búsqueda en profundidad y en anchura).
2. Comprender algoritmos de caminos mínimos: Dijkstra y A* y cuándo usar cada uno.
3. Implementar representaciones de grafos (listas de adyacencia) en Java.
4. Aplicar los cuatro algoritmos en casos de estudio prácticos: un grafo de logística dirigido y ponderado, y un grafo social no dirigido y no ponderado.
5. Analizar complejidad y resultados en ejemplos concretos.

--- 

### Caso de estudio (contexto breve)

Se plantean dos escenarios relacionados con la misma ciudad pequeña:

- **Grafo A — Red de logística (dirigido y ponderado):** calles con dirección (sentido único en algunos tramos) y coste asociado (tiempo o distancia). Usaremos Dijkstra y A* para encontrar rutas óptimas entre almacenes.

- **Grafo B — Red social (no dirigida, no ponderada):** personas conectadas por amistad. Usaremos BFS y DFS para explorar componentes y encontrar conexiones (por ejemplo: distancia en saltos entre dos usuarios) y para detectar ciclos y recorridos.

Ambos grafos serán lo suficientemente sencillos para ejecutarse en clase y para que los estudiantes tracen resultados manualmente.


## Ejercicios propuestos

1. **Modificar Grafo A**: agregar una arista con alto peso que represente una avenida en reparación y volver a calcular Dijkstra y A*. ¿Cambió el camino óptimo? Explicar por qué.
2. **Heurística real para A***: añadir coordenadas 2D a cada nodo del grafo `road` y calcular heurística Euclidiana para A*. Comparar número de nodos expandidos con Dijkstra.
3. **Componentes conectados**: usar DFS para listar todas las componentes conexas del grafo social y discutir qué representan en el contexto de la red.
4. **Camino más corto en saltos con restricciones**: modificar BFS para evitar pasar por ciertos nodos (por ejemplo, nodos bloqueados) y encontrar ruta alternativa.
5. **Reporte breve**: cada grupo entregue un informe de 1 página explicando resultados, complejidad observada y conclusiones.

---

# Conceptos Clave de Grafos y Resumen de Algoritmos Fundamentales

## 1. Conceptos Básicos de Grafos

### 🔹 Definición general
Un **grafo** es una estructura de datos formada por un conjunto de **vértices (nodos)** y **aristas (edges)** que representan relaciones o conexiones entre dichos vértices.

**Formalmente:**  
Un grafo se denota como **G = (V, E)**  
donde:
- **V** es el conjunto de vértices (nodos)
- **E** es el conjunto de aristas que conectan pares de vértices

---

## 2. Tipos de Grafos

| Tipo | Descripción | Ejemplo / Uso típico |
|------|--------------|----------------------|
| **No dirigido** | Las aristas no tienen dirección. Si hay conexión entre A y B, se puede ir en ambos sentidos. | Redes sociales, amistad |
| **Dirigido (Digrafo)** | Cada arista tiene un sentido (A → B). | Calles con sentido único, flujos de trabajo |
| **Ponderado** | Las aristas tienen un peso o coste asociado (distancia, tiempo, energía). | Rutas logísticas, mapas |
| **No ponderado** | Las aristas solo indican conexión (sin peso). | Redes sociales, grafos de relaciones |
| **Conexo** | Existe al menos un camino entre cualquier par de vértices. | Red sin nodos aislados |
| **Disperso (sparse)** | Tiene pocas aristas en relación con la cantidad máxima posible (E ≪ V²). | Mapas de grandes ciudades |
| **Denso (dense)** | Tiene muchas aristas, cercano al máximo posible. | Red totalmente interconectada |
| **Cíclico / Acíclico** | Si hay (o no hay) ciclos dentro del grafo. | Dependencias de tareas (acíclico) |
| **Multigrafo** | Puede haber más de una arista entre dos nodos. | Rutas alternativas entre ciudades |
| **Grafo completo (Kₙ)** | Todos los nodos están conectados entre sí. | Pequeñas redes fuertemente vinculadas |

---

## 3. Grado de un vértice

El **grado** de un vértice mide cuántas aristas lo tocan.

| Tipo de grado | Definición | Fórmula / Ejemplo |
|----------------|-------------|------------------|
| **Grado de salida (out-degree)** | Cantidad de aristas que **salen** de un nodo (solo en grafos dirigidos). | `out(v)` |
| **Grado de entrada (in-degree)** | Cantidad de aristas que **entran** a un nodo. | `in(v)` |
| **Grado total** | Suma de aristas que entran y salen. | `deg(v) = in(v) + out(v)` |

**Ejemplo:**  
En un grafo dirigido:  
- Nodo A → B, A → C, D → A  
→ `in(A)=1`, `out(A)=2`, `deg(A)=3`

---

## 4. Representaciones comunes

| Representación | Descripción | Ventajas | Desventajas |
|----------------|-------------|-----------|--------------|
| **Matriz de adyacencia** | Matriz `V x V` donde `M[i][j]` indica si hay arista entre i y j (y su peso si aplica). | Rápido para verificar conexión directa. | Consume mucha memoria en grafos grandes. |
| **Lista de adyacencia** | Cada nodo almacena una lista de nodos adyacentes. | Eficiente en grafos dispersos. | Más lenta para verificar conexión directa. |
| **Matriz de incidencia** | Relación entre nodos y aristas (filas: nodos, columnas: aristas). | Útil para análisis algebraico. | Menos práctica para implementaciones directas. |

---

## 5. Resumen de Algoritmos Fundamentales sobre Grafos

### DFS — Depth First Search (Búsqueda en Profundidad)

**Idea:**  
Explora tan profundo como sea posible a lo largo de cada rama antes de retroceder.

**Características:**
- Usa una **pila (stack)** (explícita o recursiva).
- Recorre cada componente conexa.
- Útil para: detección de ciclos, topological sort, componentes conexas.

**Complejidad:** `O(V + E)`

**Pseudocódigo:**
```text
DFS(v):
    marcar v como visitado
    para cada vecino w de v:
        si w no está visitado:
            DFS(w)
BFS(s):
    encolar s
    mientras la cola no esté vacía:
        u = desencolar()
        para cada vecino v de u:
            si v no está visitado:
                marcar v
                encolar v

Inicializar distancias = ∞; dist[origen] = 0
Mientras haya nodos no visitados:
    u = nodo con menor dist[u]
    marcar u como visitado
    para cada vecino v de u:
        si dist[u] + peso(u,v) < dist[v]:
            dist[v] = dist[u] + peso(u,v)

A*(inicio, meta):
    g[inicio] = 0
    f[inicio] = h(inicio)
    mientras openSet no vacío:
        u = nodo con menor f[u]
        si u == meta: reconstruir camino
        para cada vecino v:
            tentativo = g[u] + peso(u,v)
            si tentativo < g[v]:
                g[v] = tentativo
                f[v] = g[v] + h(v)

