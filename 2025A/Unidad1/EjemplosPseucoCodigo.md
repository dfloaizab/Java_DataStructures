# Ejemplos en Pseudocódigo: Pila, Cola y Lista Simplemente Enlazada

---

## 1. Pila (Stack) — LIFO

> **Escenario:** verificar si una cadena de caracteres tiene paréntesis balanceados.
> Por cada `(` se apila; por cada `)` se desapila. Al final, la pila debe quedar vacía.

```
ALGORITMO VerificarParentesis(cadena)
  ENTRADA: cadena  ← secuencia de caracteres
  SALIDA:  booleano ← true si los paréntesis están balanceados

  pila ← NuevaPila()

  PARA CADA caracter EN cadena HACER
    SI caracter == '(' ENTONCES
      pila.push(caracter)
    SINO SI caracter == ')' ENTONCES
      SI pila.isEmpty() ENTONCES
        RETORNAR false          // cierre sin apertura
      FIN SI
      pila.pop()
    FIN SI
  FIN PARA

  RETORNAR pila.isEmpty()       // true si todos los '(' fueron cerrados
FIN ALGORITMO
```

**Traza** con `cadena = "(a(b)c)"`:

| Paso | Carácter | Operación   | Estado de la pila |
|------|----------|-------------|-------------------|
| 1    | `(`      | push(`(`)   | `[ ( ]`           |
| 2    | `a`      | —           | `[ ( ]`           |
| 3    | `(`      | push(`(`)   | `[ ( ( ]`         |
| 4    | `b`      | —           | `[ ( ( ]`         |
| 5    | `)`      | pop()       | `[ ( ]`           |
| 6    | `c`      | —           | `[ ( ]`           |
| 7    | `)`      | pop()       | `[ ]`             |
| —    | fin      | isEmpty()   | `true` ✓          |

---

## 2. Cola (Queue) — FIFO

> **Escenario:** simulación de una fila de impresión. Los trabajos se encolan en orden
> de llegada y se procesan uno a uno desde el frente.

```
ALGORITMO SimularColaImpresion(trabajos[])
  ENTRADA: trabajos[] ← arreglo de nombres de documento
  SALIDA:  (imprime el orden de procesamiento)

  cola ← NuevaCola()

  // Fase 1: encolar todos los trabajos
  PARA CADA trabajo EN trabajos HACER
    cola.enqueue(trabajo)
    IMPRIMIR "Trabajo agregado: " + trabajo
  FIN PARA

  IMPRIMIR "--- Iniciando impresión ---"

  // Fase 2: procesar en orden FIFO
  MIENTRAS NO cola.isEmpty() HACER
    actual ← cola.dequeue()
    IMPRIMIR "Imprimiendo: " + actual
  FIN MIENTRAS

  IMPRIMIR "Cola de impresión vacía."
FIN ALGORITMO
```

**Traza** con `trabajos = ["reporte.pdf", "tesis.docx", "foto.png"]`:

| Paso | Operación                  | Estado de la cola                          |
|------|----------------------------|--------------------------------------------|
| 1    | enqueue("reporte.pdf")     | `[ reporte.pdf ]`                          |
| 2    | enqueue("tesis.docx")      | `[ reporte.pdf, tesis.docx ]`              |
| 3    | enqueue("foto.png")        | `[ reporte.pdf, tesis.docx, foto.png ]`    |
| 4    | dequeue() → "reporte.pdf"  | `[ tesis.docx, foto.png ]`                 |
| 5    | dequeue() → "tesis.docx"   | `[ foto.png ]`                             |
| 6    | dequeue() → "foto.png"     | `[ ]`                                      |

---

## 3. Lista Simplemente Enlazada

> **Escenario:** gestión de una lista de tareas pendientes. Se pueden agregar tareas
> al inicio o al final, buscar una tarea por nombre y eliminarla.

### Estructura del nodo

```
ESTRUCTURA Nodo
  dato    : cadena
  siguiente : referencia a Nodo   // null si es el último
FIN ESTRUCTURA
```

### Operaciones

```
ALGORITMO InsertarInicio(lista, dato)
  ENTRADA: lista ← referencia a la cabeza, dato ← valor a insertar
  SALIDA:  nueva cabeza de la lista

  nuevoNodo        ← NuevoNodo()
  nuevoNodo.dato   ← dato
  nuevoNodo.siguiente ← lista.cabeza
  lista.cabeza     ← nuevoNodo
FIN ALGORITMO

─────────────────────────────────────────────

ALGORITMO InsertarFinal(lista, dato)
  ENTRADA: lista ← referencia a la cabeza, dato ← valor a insertar

  nuevoNodo           ← NuevoNodo()
  nuevoNodo.dato      ← dato
  nuevoNodo.siguiente ← null

  SI lista.cabeza == null ENTONCES
    lista.cabeza ← nuevoNodo
    RETORNAR
  FIN SI

  actual ← lista.cabeza
  MIENTRAS actual.siguiente != null HACER
    actual ← actual.siguiente
  FIN MIENTRAS

  actual.siguiente ← nuevoNodo
FIN ALGORITMO

─────────────────────────────────────────────

ALGORITMO Buscar(lista, dato)
  ENTRADA: lista, dato ← valor a buscar
  SALIDA:  referencia al nodo encontrado, o null

  actual ← lista.cabeza
  MIENTRAS actual != null HACER
    SI actual.dato == dato ENTONCES
      RETORNAR actual
    FIN SI
    actual ← actual.siguiente
  FIN MIENTRAS

  RETORNAR null     // no encontrado
FIN ALGORITMO

─────────────────────────────────────────────

ALGORITMO Eliminar(lista, dato)
  ENTRADA: lista, dato ← valor a eliminar
  SALIDA:  (modifica la lista en su lugar)

  SI lista.cabeza == null ENTONCES
    RETORNAR                     // lista vacía
  FIN SI

  SI lista.cabeza.dato == dato ENTONCES
    lista.cabeza ← lista.cabeza.siguiente
    RETORNAR
  FIN SI

  anterior ← lista.cabeza
  actual   ← lista.cabeza.siguiente

  MIENTRAS actual != null HACER
    SI actual.dato == dato ENTONCES
      anterior.siguiente ← actual.siguiente
      RETORNAR
    FIN SI
    anterior ← actual
    actual   ← actual.siguiente
  FIN MIENTRAS
FIN ALGORITMO
```

### Programa principal de ejemplo

```
ALGORITMO GestionTareas()
  lista ← NuevaLista()   // cabeza = null

  InsertarFinal(lista, "Estudiar TADs")
  InsertarFinal(lista, "Hacer ejercicios")
  InsertarInicio(lista, "Revisar apuntes")   // va al frente

  // Estado: [ Revisar apuntes → Estudiar TADs → Hacer ejercicios ]

  nodo ← Buscar(lista, "Estudiar TADs")
  SI nodo != null ENTONCES
    IMPRIMIR "Encontrada: " + nodo.dato
  FIN SI

  Eliminar(lista, "Estudiar TADs")

  // Estado: [ Revisar apuntes → Hacer ejercicios ]

  actual ← lista.cabeza
  MIENTRAS actual != null HACER
    IMPRIMIR "- " + actual.dato
    actual ← actual.siguiente
  FIN MIENTRAS
FIN ALGORITMO
```

**Traza del estado de la lista:**

| Paso | Operación                       | Lista resultante                                         |
|------|---------------------------------|----------------------------------------------------------|
| 1    | InsertarFinal("Estudiar TADs")  | `Estudiar TADs → null`                                   |
| 2    | InsertarFinal("Hacer ejercicios") | `Estudiar TADs → Hacer ejercicios → null`              |
| 3    | InsertarInicio("Revisar apuntes") | `Revisar apuntes → Estudiar TADs → Hacer ejercicios → null` |
| 4    | Buscar("Estudiar TADs")         | Devuelve nodo en posición 2                              |
| 5    | Eliminar("Estudiar TADs")       | `Revisar apuntes → Hacer ejercicios → null`              |

---

## Comparación rápida de las tres estructuras

| Criterio             | Pila              | Cola              | Lista enlazada         |
|----------------------|-------------------|-------------------|------------------------|
| Orden de acceso      | LIFO              | FIFO              | Secuencial (cualquier extremo) |
| Inserción            | Solo en el tope   | Solo por la cola  | Inicio, final o medio  |
| Extracción           | Solo del tope     | Solo por la cabeza| Cualquier posición     |
| Acceso aleatorio     | ✗                 | ✗                 | ✗ (O(n) búsqueda)      |
| Caso de uso típico   | Deshacer, llamadas recursivas | Turnos, buffers | Colecciones dinámicas  |
```
