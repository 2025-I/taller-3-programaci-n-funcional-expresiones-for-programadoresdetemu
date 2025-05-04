# ManiobrasTrenes.scala

El archivo `ManiobrasTrenes.scala` contiene la implementación de la lógica funcional para simular el movimiento de vagones entre tres vías de un sistema ferroviario. A continuación se explica en detalle cada parte del código:

---

## Definiciones de Tipos

```scala
type Vagon = Any
type Tren = List[Vagon]
type Estado = (Tren, Tren, Tren)
type Maniobra = List[Movimiento]
```

- **Vagon**: representa un vagón genérico, de cualquier tipo.
- **Tren**: una lista de vagones.
- **Estado**: tupla que representa las tres vías (`Principal`, `Uno`, `Dos`).
- **Maniobra**: lista de movimientos a aplicar.

---

## Definición del Trait y Clases de Movimiento

```scala
trait Movimiento
case class Principal(n: Int) extends Movimiento
case class Uno(n: Int) extends Movimiento
case class Dos(n: Int) extends Movimiento
```

- **Movimiento**: trait base para representar un movimiento.
- **Principal(n)**, **Uno(n)** y **Dos(n)**: clases que indican hacia dónde y cuántos vagones mover.
  - `n > 0`: mover del Principal a Uno/Dos.
  - `n < 0`: mover desde Uno/Dos al Principal.
  - `n == 0`: no hacer nada.

---

## aplicarMovimiento

```scala
def aplicarMovimiento(e: Estado, m: Movimiento): Estado
```

Esta función aplica un solo movimiento `m` a un estado `e` del sistema ferroviario. Utiliza `pattern matching` para identificar el tipo de movimiento (`Uno` o `Dos`) y su dirección (valor de `n`).

### Lógica de los Casos

- **Uno(n)**:
  - `n > 0`: mover los últimos `n` vagones del Principal hacia Uno.
  - `n < 0`: mover los primeros `n` vagones desde Uno al Principal (o desde Principal si Uno está vacío).
- **Dos(n)**:
  - `n > 0`: mover los últimos `n` vagones del Principal hacia Dos.
  - `n < 0`: mover los primeros `n` vagones desde Dos al Principal (o desde Principal si Dos está vacío).
- **n == 0**: no hacer ningún cambio.

Se manejan casos de listas vacías o movimientos mayores al tamaño real para evitar errores.

---

## moverPrimerosElementos

```scala
def moverPrimerosElementos[T](lista: List[T], n: Int): (List[T], List[T])
```

Función auxiliar recursiva que separa los **primeros `n` elementos** de una lista. Devuelve una tupla:
- `(elementosMovidos, elementosRestantes)`

Se usa recursión de cola mediante una función interna `moverPrimerosElementosAux`.

---

## moverUltimosElementos

```scala
def moverUltimosElementos[T](lista: List[T], n: Int): (List[T], List[T])
```

Función similar a la anterior, pero mueve los **últimos `n` elementos**. La lista se invierte primero, y luego se aplica una lógica similar a `moverPrimerosElementos`.

---

## aplicarMovimientos

```scala
def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado]
```

Aplica una lista completa de maniobras (`movs`) de forma secuencial sobre un estado inicial `e`, acumulando todos los estados intermedios en una lista.

Utiliza una función recursiva auxiliar `aplicarMovimientosAux` que:
- Toma el estado anterior (`acc.head`).
- Aplica el siguiente movimiento.
- Agrega el nuevo estado al acumulador.

Devuelve todos los estados, desde el inicial hasta el final.

---

## Conclusión

El código modela de forma clara y funcional el movimiento de vagones en un sistema ferroviario:
- Usa tipos algebraicos para representar estados y movimientos.
- Implementa lógica condicional robusta con `pattern matching`.
- Utiliza recursión de cola para eficiencia y seguridad.
- Ofrece trazabilidad de los estados gracias a la función `aplicarMovimientos`.

