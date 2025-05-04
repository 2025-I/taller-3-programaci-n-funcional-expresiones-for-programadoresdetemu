# Informe del Taller #3: Maniobras de Trenes en Scala

Este informe documenta el desarrollo del Taller #3, enfocado en la aplicación de conceptos del paradigma funcional para simular maniobras de trenes. Se trabajó con técnicas como la recursión de cola, funciones de alto orden, reconocimiento de patrones y manejo funcional de listas en Scala.

## Descripción General del Código

El programa define una clase ManiobrasTrenes con el objetivo de representar y aplicar una secuencia de movimientos (maniobras) sobre trenes distribuidos en tres vías (principal, uno y dos). Cada movimiento puede implicar desplazar un número de vagones desde o hacia una vía secundaria.

### Tipos Definidos

scala
type Vagon = Any
type Tren = List[Vagon]
type Estado = (Tren, Tren, Tren)
type Maniobra = List[Movimiento]


### Jerarquía de Movimientos

scala
trait Movimiento
case class Principal(n: Int) extends Movimiento
case class Uno(n: Int) extends Movimiento
case class Dos(n: Int) extends Movimiento


## Funciones Principales

### aplicarMovimiento

Recibe un estado (principal, uno, dos) y un movimiento, y retorna el nuevo estado resultante. Utiliza *reconocimiento de patrones* y valida si hay que mover vagones desde o hacia la vía principal, y si hay suficientes vagones disponibles. La lógica incluye:

- Manejo de casos en los que se intenta mover más vagones de los disponibles usando min
- Uso de abs para evitar condicionales innecesarios
- Simplificación de estructura para evitar duplicación de lógica

### moverElementos

Función genérica que mueve n elementos entre dos listas según si el movimiento es desde la vía principal o no. Utiliza splitAt para dividir la lista de manera eficiente, evitando operaciones costosas como :+ y reverse.

### aplicarMovimientos

Aplica una lista de movimientos a un estado inicial, acumulando los estados resultantes. Implementa recursión de cola a través de la función auxiliar aplicarMovimientosAux, garantizando eficiencia en el procesamiento de listas grandes.

## Implementaciones Anteriores (Apuntes)

Inicialmente se crearon funciones separadas para mover los primeros y últimos elementos de una lista con recursión de cola:

scala
def moverPrimerosElementos[T](lista: List[T], n: Int): (List[T], List[T])
def moverUltimosElementos[T](lista: List[T], n: Int): (List[T], List[T])


Estas funciones funcionaban, pero usaban concatenaciones ineficientes y reversas de listas, lo cual elevó el costo computacional.

## Refactorización y Mejoras

Durante el desarrollo se identificaron problemas como el error de *"Java heap space"*, causado por estructuras recursivas poco optimizadas.

En vez de crear múltiples funciones específicas con estructuras imperativas, se refactorizó el código hacia una solución más eficiente y expresiva, utilizando programación funcional moderna:

- Se reemplazaron funciones específicas por una *única función genérica* moverElementos
- Se utilizó splitAt para evitar el uso de :+ y reverse, lo que redujo el consumo de memoria
- Se reorganizó el match con if dentro de los case para *simplificar la lógica sin sobrecargar la coincidencia de patrones*
- Se usó .abs y min para controlar los límites de movimiento de vagones, evitando errores por intentar mover más vagones de los disponibles

*Ejemplo:*
scala
val cantidadVagonesMover = n.abs min trenOrigen.length


Esto protege contra desbordamientos al mover solo los vagones que realmente están disponibles.

## Conclusión

El taller permitió poner en práctica los conceptos fundamentales del paradigma funcional en Scala para resolver un problema de simulación de maniobras. La refactorización fue clave para mejorar el rendimiento del programa y evitar errores de memoria. Las decisiones de diseño como el uso de recursión de cola, splitAt y tipos genéricos permitieron una solución más limpia, eficiente y mantenible.