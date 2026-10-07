# ESTRUCTURAS DE DATOS - 2026B
# UNIDAD 2: ESTRUCTURAS DE DATOS NO LINEALES
## Dividir y Vencer en Java - Ejercicios


## Cómo trabajar cada ejercicio

Para cada uno, antes de programar:

1. Identifica el **caso base**.
2. Define cómo **dividir** el problema.
3. Decide si hay que recursar en **una** mitad o en **ambas**.
4. Define cómo **combinar** (si aplica).
5. Escribe la recurrencia T(n) y resuélvela (árbol de recursión o Teorema Maestro).
6. Prueba con los ejemplos dados y con casos borde: arreglo vacío, un solo elemento, todos los valores iguales.

## Resumen

| # | Ejercicio | Recursa en | Complejidad esperada |
|---|-----------|------------|----------------------|
| 1 | Mínimo y máximo simultáneos | ambas mitades | O(n) |
| 2 | Primera versión defectuosa | una mitad | O(log n) |
| 3 | Contar eventos de un código | una mitad (dos veces) | O(log n) |
| 4 | Búsqueda en buffer circular | una mitad | O(log n) |
| 5 | Elemento mayoritario | ambas mitades | O(n log n) |
| 6 | Pico de carga | una mitad | O(log n) |

---

## 1. Mínimo y máximo simultáneos

**Contexto:** un reporte de métricas debe mostrar la latencia mínima y la máxima de un periodo, recorriendo los datos una sola vez.

```java
static int[] minMax(int[] latencias, int lo, int hi)  // retorna {min, max}
```

**Idea:**
- **Caso base:** un solo elemento (min = max) o dos elementos (se comparan entre sí).
- **Dividir:** parte el rango por la mitad.
- **Combinar:** el mínimo global es el menor de los dos mínimos; el máximo global es el mayor de los dos máximos.

**Ejemplo:** `[7, 2, 9, 4, 1, 8]` → `{1, 9}`

**Para pensar:**
- Plantea T(n) = 2T(n/2) + 1. ¿Qué complejidad da?
- ¿Es mejor que un ciclo simple que recorre el arreglo? Compara el número de comparaciones de ambos enfoques.

---

## 2. Primera versión defectuosa (estilo `git bisect`)

**Contexto:** tu equipo tiene `n` versiones numeradas de 1 a `n`. A partir de cierta versión todas son defectuosas, y cada prueba (`esDefectuosa(v)`) es costosa porque corre una batería de tests. Encuentra la primera versión mala con el menor número de pruebas posible.

```java
static boolean esDefectuosa(int version) { ... }   // simulada: la provee el enunciado

static int primeraDefectuosa(int lo, int hi)
```

**Idea:**
- Prueba la versión del medio.
- Si es defectuosa, la primera defectuosa está en esa posición o antes; si no, está después.
- **Caso base:** `lo == hi`.

**Ejemplo:** con n = 10 y primera defectuosa = 7, el método retorna `7` y debe hacer a lo sumo 4 pruebas.

**Para pensar:**
- ¿Por qué `mid = lo + (hi - lo) / 2` es más seguro que `(lo + hi) / 2`?
- ¿Cuántas pruebas necesitas para n = 1.000.000?

---

## 3. Contar eventos de un código en un log ordenado

**Contexto:** tienes un arreglo de códigos de evento **ordenado**, por ejemplo códigos HTTP o niveles de severidad. Necesitas saber cuántas veces aparece un código sin recorrer todo el arreglo.

```java
static int primeraPosicion(int[] a, int x, int lo, int hi)   // -1 si no existe
static int ultimaPosicion(int[] a, int x, int lo, int hi)    // -1 si no existe
static int contar(int[] a, int x)
```

**Idea:**
- Es una búsqueda binaria modificada: cuando encuentras `x`, **no te detienes**, sigues buscando hacia la izquierda (primera posición) o hacia la derecha (última).
- `contar = ultima - primera + 1` (o 0 si no existe).

**Ejemplos:**
- `[1, 3, 3, 3, 5, 8, 8]`, `x = 3` → `3`
- `[1, 3, 3, 3, 5, 8, 8]`, `x = 4` → `0`

**Para pensar:**
- ¿Qué pasa con un arreglo donde todos los elementos son iguales a `x`?
- ¿Por qué esto es mejor que recorrer el arreglo cuando hay millones de registros?

---

## 4. Búsqueda en un buffer circular

**Contexto:** un log circular guarda eventos ordenados por identificador, pero el punto de inicio quedó "rotado" en el arreglo. El arreglo `[1, 2, 3, 4, 5, 6, 7, 8]` puede verse como `[6, 7, 8, 1, 2, 3, 4, 5]`. Busca un identificador en O(log n).

```java
static int buscarRotado(int[] a, int objetivo, int lo, int hi)  // índice o -1
```

**Idea:**
- Al partir por la mitad, **al menos una de las dos mitades está ordenada**.
- Comprueba si el objetivo cae dentro del rango de la mitad ordenada. Si cae, recursa ahí; si no, recursa en la otra.
- Supón que no hay valores repetidos.

**Ejemplos:**
- `[6, 7, 8, 1, 2, 3, 4, 5]`, objetivo `3` → `5`
- `[6, 7, 8, 1, 2, 3, 4, 5]`, objetivo `9` → `-1`

**Para pensar:**
- ¿Cómo sabes cuál mitad está ordenada? (Pista: compara `a[lo]` con `a[mid]`.)
- ¿Qué se rompe si el arreglo tiene valores repetidos?

---

## 5. Elemento mayoritario

**Contexto:** en un sistema de réplicas, cada nodo reporta un valor y se acepta el valor que reporta **más de la mitad** de los nodos (quórum). Encuentra ese valor, o indica que no hay mayoría.

```java
static int mayoritario(int[] a, int lo, int hi)  // -1 si no hay mayoría (valores >= 0)
```

**Idea:**
- **Caso base:** un solo elemento es mayoritario de sí mismo.
- **Dividir:** calcula el mayoritario de cada mitad.
- **Combinar:**
  - Si ambas mitades coinciden, ese es el candidato.
  - Si difieren, **cuenta** cuántas veces aparece cada candidato en todo el rango y elige el que supere la mitad.

**Ejemplos:**
- `[2, 2, 1, 1, 1, 2, 2]` → `2` (aparece 4 de 7 veces)
- `[1, 2, 3]` → `-1`

**Para pensar:**
- Si un elemento es mayoritario en el arreglo completo, ¿por qué debe ser mayoritario en al menos una de las dos mitades?
- Plantea T(n) = 2T(n/2) + n. ¿Qué complejidad da? Investiga el algoritmo de Boyer-Moore, que lo resuelve en O(n), y compara.

---

## 6. Pico de carga

**Contexto:** tienes la carga de CPU medida en puntos sucesivos de una línea de tiempo. Un "pico" es una posición cuyo valor es mayor o igual que el de sus vecinos. Encuentra cualquier pico sin recorrer todo el arreglo.

```java
static int pico(int[] carga, int lo, int hi)  // retorna el índice de un pico
```

**Idea:**
- Mira el elemento del medio y su vecino derecho.
- Si `carga[mid] < carga[mid + 1]`, hay un pico a la derecha (la carga sigue subiendo). Si no, hay un pico en `mid` o a la izquierda.
- **Caso base:** `lo == hi`.

**Ejemplos:**
- `[1, 3, 7, 5, 2]` → `2`
- `[1, 2, 3, 4]` → `3`
- `[1, 5, 2, 6, 3]` → `1` o `3` (ambos son picos válidos)

**Para pensar:**
- ¿Por qué siempre existe al menos un pico?
- ¿Por qué basta explorar solo una mitad? Intenta justificarlo con un argumento breve.
