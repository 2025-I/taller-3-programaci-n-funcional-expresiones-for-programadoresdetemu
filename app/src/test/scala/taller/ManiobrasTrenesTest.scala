package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ManiobrasTrenesTest extends AnyFunSuite {
  val objManiobrasTrenes = new ManiobrasTrenes()

  test("Caso 1: movimientos definidos") {
    val estadoInicial = (List('A', 'B', 'C'), Nil, Nil)
    val movimientos = List(objManiobrasTrenes.Uno(2),
      objManiobrasTrenes.Dos(1),
      objManiobrasTrenes.Dos(-1),
      objManiobrasTrenes.Uno(-2))
    val estadoFinalEsperado = (List('A', 'B', 'C'), Nil, Nil)
    val resultado = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultado == estadoFinalEsperado)
  }

  test("Caso 2: cambio completo a via 2") {
    val estadoInicial = (List('X', 'Y', 'Z'), Nil, Nil)
    val movimientos = List(objManiobrasTrenes.Uno(3))
    val estadoFinalEsperado = (Nil, List('X', 'Y', 'Z'), Nil)
    val resultado = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultado == estadoFinalEsperado)
  }

  test("Caso 3: ida y vuelta a via 3") {
    val estadoInicial = (List('M', 'N'), Nil, Nil)
    val movimientos = List(objManiobrasTrenes.Dos(2), objManiobrasTrenes.Dos(-2))
    val estadoFinalEsperado = (List('M', 'N'), Nil, Nil)
    val resultado = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultado == estadoFinalEsperado)
  }

  test("Caso 4: uno a via 2 y regreso") {
    val estadoInicial = (List('R', 'S'), Nil, Nil)
    val movimientos = List(objManiobrasTrenes.Uno(1), objManiobrasTrenes.Uno(-1))
    val estadoFinalEsperado = (List('R', 'S'), Nil, Nil)
    val resultado = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultado == estadoFinalEsperado)
  }

  test("Caso 5: mezcla de Uno y Dos") {
    val estadoInicial = (List('L', 'M', 'N', 'O'), Nil, Nil)
    val movimientos = List(
      objManiobrasTrenes.Uno(2), // L, M -> via2
      objManiobrasTrenes.Dos(1), // M -> via3
      objManiobrasTrenes.Uno(2), // N, O -> via2
      objManiobrasTrenes.Uno(-2), // O, N -> via1
      objManiobrasTrenes.Dos(-1)  // M -> via2
    )
    val estadoFinalEsperado = (List('L', 'N', 'M'), List('O'), List())
    val resultado = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultado == estadoFinalEsperado)
  }


  // ---------------------------------------------
  // Prueba de juguete: 10 vagones y 10 movimientos
  test("Prueba de juguete con 10 vagones y 10 movimientos") {
    val vagones = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')
    val movimientos = List(
      objManiobrasTrenes.Uno(3),
      objManiobrasTrenes.Uno(2),
      objManiobrasTrenes.Dos(-2),
      objManiobrasTrenes.Uno(-2),
      objManiobrasTrenes.Uno(1),
      objManiobrasTrenes.Uno(-3),
      objManiobrasTrenes.Uno(-3),
      objManiobrasTrenes.Uno(-3),
      objManiobrasTrenes.Uno(-2),
      objManiobrasTrenes.Uno(-1)
    )
    val estadoInicial = (vagones, Nil, Nil)
    val resultadoFinal = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultadoFinal._1.nonEmpty || resultadoFinal._2.nonEmpty || resultadoFinal._3.nonEmpty)
  }

  // ---------------------------------------------
  // Prueba pequeña: 100 vagones y 100 movimientos
  test("Prueba pequeña con 100 vagones y 100 movimientos") {
    val vagones = (1 to 100).map(i => (('a' + (i % 26)).toChar)).toList
    val movimientos = (1 to 100).map { _ =>
      val n = Iterator.continually(scala.util.Random.between(-3, 4)).dropWhile(_ == 0).next()
      if (scala.util.Random.nextBoolean()) objManiobrasTrenes.Uno(n) else objManiobrasTrenes.Dos(n)
    }.toList
    val estadoInicial = (vagones, Nil, Nil)
    val resultadoFinal = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultadoFinal._1.nonEmpty || resultadoFinal._2.nonEmpty || resultadoFinal._3.nonEmpty)
  }

  // ---------------------------------------------
  // Prueba mediana: 500 vagones y 500 movimientos
  test("Prueba mediana con 500 vagones y 500 movimientos") {
    val vagones = (1 to 500).map(i => (('a' + (i % 26)).toChar)).toList
    val movimientos = (1 to 500).map { _ =>
      val n = Iterator.continually(scala.util.Random.between(-3, 4)).dropWhile(_ == 0).next()
      if (scala.util.Random.nextBoolean()) objManiobrasTrenes.Uno(n) else objManiobrasTrenes.Dos(n)
    }.toList
    val estadoInicial = (vagones, Nil, Nil)
    val resultadoFinal = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultadoFinal._1.nonEmpty || resultadoFinal._2.nonEmpty || resultadoFinal._3.nonEmpty)
  }

  // ---------------------------------------------
  // Prueba grande: 1000 vagones y 1000 movimientos
  test("Prueba grande con 1000 vagones y 1000 movimientos") {
    val vagones = (1 to 1000).map(i => (('a' + (i % 26)).toChar)).toList
    val movimientos = (1 to 1000).map { _ =>
      val n = Iterator.continually(scala.util.Random.between(-3, 4)).dropWhile(_ == 0).next()
      if (scala.util.Random.nextBoolean()) objManiobrasTrenes.Uno(n) else objManiobrasTrenes.Dos(n)
    }.toList
    val estadoInicial = (vagones, Nil, Nil)
    val resultadoFinal = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultadoFinal._1.nonEmpty || resultadoFinal._2.nonEmpty || resultadoFinal._3.nonEmpty)
  }

//********************************************************************************************
  test("Movimiento de Vagones: Mover 2 vagones de Principal a Uno, Numero Positivo") {
    val estadoInicial = (List('a', 'b', 'c'), Nil, Nil)
    val estadoFinal = (List('a'), List('b', 'c'), Nil)
    val estadoResultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(2))
    assert(estadoFinal == estadoResultado)
  }

  test("Movimiento 1 del ejemplo del PDF, e1, Uno(2)") {
    val estadoInicial = (List('a', 'b', 'c', 'd'), Nil, Nil)
    val estadoFinal = (List('a', 'b'), List('c', 'd'), Nil)
    val estadoResultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(2))
    assert(estadoFinal == estadoResultado)
  }

  test("Movimiento 2 del ejemplo del PDF, e2, Dos(3)") {
    val estadoInicial = (List('a', 'b'), List('c', 'd'), Nil)
    val estadoFinal = (Nil, List('c', 'd'), List('a', 'b'))
    val estadoResultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Dos(3))
    assert(estadoFinal == estadoResultado)
  }

  test("Movimiento 3 del ejemplo del PDF, e3, Dos(-1)") {
    val estadoInicial = (Nil, List('c', 'd'), List('a', 'b'))
    val estadoFinal = (List('a'), List('c', 'd'), List('b'))
    val estadoResultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Dos(-1))
    assert(estadoFinal == estadoResultado)
  }

  test("Movimiento 4 del ejemplo del PDF, e4, Uno(-2)") {
    val estadoInicial = (List('a'), List('c', 'd'), List('b'))
    val estadoFinal = (List('a', 'c', 'd'), Nil, List('b'))
    val estadoResultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(-2))
    assert(estadoFinal == estadoResultado)
  }

  test("Movimiento de Vagones: Mover 2 vagones de Principal a Dos, Numero Positivo") {
    val estadoInicial = (List('a', 'b', 'c', 'd', 'e'), Nil, Nil)
    val estadoFinal = (List('a', 'b', 'c'), Nil, List('d', 'e'))
    val estadoResultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Dos(2))
    assert(estadoFinal == estadoResultado)
  }

  test("Movimiento de Vagones: Mover 1 vagon de Principal a Uno, Numero Negativo") {
    val estadoInicial = (List('a', 'b', 'c', 'd'), Nil, Nil)
    val estadoFinal = (List('b', 'c', 'd'), List('a'), Nil)
    val estadoResultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(-1))
    assert(estadoFinal == estadoResultado)
  }

  test("Mover 2 vagones de Principal a Uno, n positivo") {
    val estadoInicial = (List('a', 'b', 'c', 'd'), Nil, Nil)
    val esperado = (List('a', 'b'), List('c', 'd'), Nil)
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(2))
    assert(resultado == esperado)
  }

  test("Mover 3 vagones de Principal a Dos, n positivo") {
    val estadoInicial = (List('x', 'y', 'z', 'w'), Nil, Nil)
    val esperado = (List('x'), Nil, List('y', 'z', 'w'))
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Dos(3))
    assert(resultado == esperado)
  }

  test("Mover 1 vagón de Uno a Principal, n negativo") {
    val estadoInicial = (List('b'), List('c'), Nil)
    val esperado = (List('b', 'c'), Nil, Nil)
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(-1))
    assert(resultado == esperado)
  }

  test("Mover 2 vagones de Dos a Principal, n negativo") {
    val estadoInicial = (List('a'), Nil, List('x', 'y', 'z'))
    val esperado = (List('a', 'x', 'y'), Nil, List('z'))
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Dos(-2))
    assert(resultado == esperado)
  }

  test("Movimiento Uno(0) no cambia nada") {
    val estadoInicial = (List('a', 'b'), List('c'), List('d'))
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(0))
    assert(resultado == estadoInicial)
  }

  test("Movimiento Dos(0) no cambia nada") {
    val estadoInicial = (List('a', 'b'), List('c'), List('d'))
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Dos(0))
    assert(resultado == estadoInicial)
  }

  test("Mover más vagones de los que hay (Uno(10))") {
    val estadoInicial = (List('a', 'b'), Nil, Nil)
    val esperado = (Nil, List('a', 'b'), Nil)
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(10))
    assert(resultado == esperado)
  }

  test("Mover más vagones de los que hay en Uno hacia Principal (Uno(-5))") {
    val estadoInicial = (List('z'), List('x', 'y'), Nil)
    val esperado = (List('z', 'x', 'y'), Nil, Nil)
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(-5))
    assert(resultado == esperado)
  }

  test("Prueba aplicar Movimientos (Uno(1))") {
    val estadoInicial = (List('a', 'b'), List('c'), List('d'))
    val esperado = (List('a'), List('b', 'c'), List('d'))
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(1))
    assert(resultado == esperado)
  }

  test("Prueba aplicar Movimientos (Dos(1))") {
    val estadoInicial = (List('a'), List('b', 'c'), List('d'))
    val esperado = (Nil, List('b', 'c'), List('a', 'd'))
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Dos(1))
    assert(resultado == esperado)
  }

  test("Prueba aplicar Movimientos (Uno(-2))") {
    val estadoInicial = (Nil, List('b', 'c'), List('a', 'd'))
    val esperado = (List('b', 'c'), Nil, List('a', 'd'))
    val resultado = objManiobrasTrenes.aplicarMovimiento(estadoInicial, objManiobrasTrenes.Uno(-2))
    assert(resultado == esperado)
  }

  test("Movimientos del pdf Uno(1),Dos(1),Uno(-2)") {
    val estadoInicial = (List('a', 'b'), List('c'), List('d'))
    val esperado = (List('b', 'c'), List(), List('a', 'd'))
    val movimientos = List(objManiobrasTrenes.Uno(1),
      objManiobrasTrenes.Dos(1),
      objManiobrasTrenes.Uno(-2))
    val resultado = objManiobrasTrenes.aplicarMovimientos(estadoInicial, movimientos).last
    assert(resultado == esperado)
  }
}

    //*******************************************************************************************************
    // Test definir maniobra
/*
    test("Definir maniobras - Caso básico t1 -> t2") {
      val t1 = List('a', 'b', 'c', 'd')
      val t2 = List('d', 'b', 'c', 'a')
      val maniobra = objManiobrasTrenes.definirManiobras(t1, t2)
      val estados = objManiobrasTrenes.aplicarMovimientos((t1, Nil, Nil), maniobra)
      val estadoFinal = estados.head
      assert(estadoFinal == (t2, Nil, Nil))
    }

    test("Definir maniobras 2 - Caso básico t1 -> t2") {
      val t1 = List('a', 'b', 'c', 'd')
      val t2 = List('d', 'c', 'b', 'a')
      val maniobra = objManiobrasTrenes.definirManiobras(t1, t2)
      val estados = objManiobrasTrenes.aplicarMovimientos((t1, Nil, Nil), maniobra)
      val estadoFinal = estados.head
      assert(estadoFinal == (t2, Nil, Nil))
    }

    test("Definir maniobras - Inversión total 10 elementos") {
      val t1 = (1 to 10).toList
      val t2 = t1.reverse
      val maniobra = objManiobrasTrenes.definirManiobras(t1, t2)
      val estados = objManiobrasTrenes.aplicarMovimientos((t1, Nil, Nil), maniobra)
      val estadoFinal = estados.head
      assert(estadoFinal == (t2, Nil, Nil))
    }

    test("Definir maniobras - Prueba pequeña 100 elementos") {
      val t1 = (1 to 100).toList
      val t2 = t1.reverse
      val maniobra = objManiobrasTrenes.definirManiobras(t1, t2)
      val estados = objManiobrasTrenes.aplicarMovimientos((t1, Nil, Nil), maniobra)
      val estadoFinal = estados.head
      assert(estadoFinal == (t2, Nil, Nil))
    }
*/