# Taller 3 - Fundamentos de Programación Funcional y Concurrente

## Descripción general

Este informe documenta el desarrollo del Taller número 3 de la materia Fundamentos de Programación Funcional y Concurrente, cuyo objetivo fue reforzar los conceptos de programación funcional aplicados a un sistema de maniobras ferroviarias, utilizando el lenguaje Scala.

Durante este taller, se implementó y documentó la clase `ManiobrasTrenes.scala`, la cual simula el movimiento de vagones entre tres vías: Principal, Uno y Dos. Se emplearon conceptos fundamentales del paradigma funcional como recursión de cola, reconocimiento de patrones y tipos algebraicos. Además, se desarrollaron pruebas unitarias automatizadas en `ManiobrasTrenesTest.scala` para validar los movimientos definidos.

## Objetivos del Taller

- Modelar estados de un sistema ferroviario utilizando tipos funcionales.
- Utilizar reconocimiento de patrones para implementar lógica de maniobras.
- Aplicar recursión de cola para garantizar eficiencia en operaciones de listas.
- Diseñar pruebas unitarias detalladas para validar la lógica de maniobras ferroviarias.
- Simular secuencias completas de movimientos y verificar los estados intermedios.

## Estructura del Proyecto

Se desarrollaron dos archivos principales:

- **`ManiobrasTrenes.scala`**: Contiene la implementación de las maniobras posibles entre los trenes. Incluye:
    - Tipos definidos para `Vagon`, `Tren`, `Estado`, `Maniobra` y `Movimiento`.
    - Funciones para aplicar un solo movimiento (`aplicarMovimiento`) o una secuencia de ellos (`aplicarMovimientos`).
    - Métodos auxiliares recursivos para mover elementos (`moverPrimerosElementos` y `moverUltimosElementos`).

- **`ManiobrasTrenesTest.scala`**: Contiene un conjunto completo de pruebas unitarias usando ScalaTest y JUnit para validar distintos casos de movimiento de vagones. Las pruebas cubren:
    - Movimientos simples entre las vías.
    - Casos de borde como mover más vagones de los que hay.
    - Validación del estado final esperado después de secuencias específicas.

## Pruebas Realizadas

Se desarrollaron pruebas que cubren distintos escenarios:

- **Movimientos individuales simples**: Mover n vagones desde Principal a Uno o Dos, y viceversa.
- **Casos del ejemplo del enunciado**: Validación paso a paso de la secuencia del documento guía del taller.
- **Casos con valores negativos**: Para simular el retorno de vagones desde Uno o Dos a Principal.
- **Movimientos con cero elementos**: Validación de que el estado se mantiene inalterado.
- **Movimientos excediendo la cantidad disponible**: Verificación de comportamiento seguro sin desbordamiento.

Las pruebas aseguran la robustez del sistema y la correcta implementación de la lógica funcional.

## Conclusiones

- Se logró implementar un sistema de maniobras ferroviarias funcional, usando los principios del paradigma funcional en Scala.
- La recursión de cola permitió implementar funciones auxiliares eficientes y sin riesgo de desbordamiento de pila.
- El uso de patrones y tipos algebraicos permitió estructurar la lógica de manera clara, expresiva y extensible.
- Se validó correctamente el comportamiento esperado del sistema a través de pruebas detalladas.
- Se simuló el comportamiento completo del sistema de maniobras mediante secuencias de movimientos aplicadas a estados iniciales.

## Integrantes del Equipo

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/SantiagoLopezUV.png" width="100px;"><br>
      <a href="https://github.com/SantiagoLopezUV">SantiagoLopezUV</a>
    </td>
    <td align="center">
      <img src="https://github.com/juanjo380.png" width="100" width="100px;"><br>
      <a href="https://github.com/juanjo380">juanjo380</a>
    </td>
    <td align="center">
      <img src="https://github.com/JulianAndresRojasPalacio.png" width="100px;"><br>
      <a href="https://github.com/JulianAndresRojasPalacio">JulianAndresRojasPalacio</a>
    </td>
  </tr>
</table>

---
