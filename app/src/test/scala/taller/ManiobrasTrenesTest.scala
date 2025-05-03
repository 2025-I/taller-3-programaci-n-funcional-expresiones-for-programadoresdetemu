package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ManiobrasTrenesTest extends AnyFunSuite {
  val objManiobrasTrenes = new ManiobrasTrenes()

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
  // Casos de Prueba, siempre funciona
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

  //*******************************************************************************************************
  // Test de AplicarMovimientos
  test(){
    val estadoInicial = (List)
  }

}

