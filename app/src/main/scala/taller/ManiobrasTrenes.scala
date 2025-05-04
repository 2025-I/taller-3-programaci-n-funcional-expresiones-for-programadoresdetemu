package taller

import scala.annotation.tailrec

class ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)
  type Maniobra = List[Movimiento]

  trait Movimiento

  case class Principal(n: Int) extends Movimiento

  case class Uno(n: Int) extends Movimiento

  case class Dos(n: Int) extends Movimiento

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {
    val (principal, uno, dos) = e
    m match {
      case Uno(n) =>
        if (n < 0) {
          if (uno.isEmpty) {
            val (nuevoPrincipal, nuevoUno) = moverElementos(n, principal, uno, true)
            (nuevoPrincipal, nuevoUno, dos)
          }else {
            val (nuevoPrincipal, nuevoUno) = moverElementos(n, uno, principal, false)
            (nuevoPrincipal, nuevoUno, dos)
          }
        }else if(n > 0) {
          val (nuevoPrincipal, nuevoUno) = moverElementos(n, principal, uno, true)
          (nuevoPrincipal, nuevoUno, dos)
        }else (principal, uno, dos)
      case Dos(n) =>
        if (n < 0) {
          if (dos.isEmpty) {
            val (nuevoPrincipal, nuevoDos) = moverElementos(n, principal, dos, true)
            (nuevoPrincipal, uno, nuevoDos)
          }else {
            val (nuevoPrincipal, nuevoDos) = moverElementos(n, dos, principal, false)
            (nuevoPrincipal, uno, nuevoDos)
          }
        }else if(n > 0) {
          val (nuevoPrincipal, nuevoDos) = moverElementos(n, principal, dos, true)
          (nuevoPrincipal, uno, nuevoDos)
        }else (principal, uno, dos)
    }
  }

  def moverElementos[T](n: Int, trenOrigen: List[T], trenFinal: List[T], desdePrincipal: Boolean): (List[T], List[T]) = {
    val cantidadVagonesMover = n.abs min trenOrigen.length
    if (n < 0) {
      val (vagonesMovidos, vagonesPendientes) = trenOrigen.splitAt(cantidadVagonesMover)
      if (desdePrincipal) (vagonesPendientes, trenFinal ++ vagonesMovidos) else (trenFinal ++ vagonesMovidos, vagonesPendientes)
    }
    else {
      val (vagonesPendientes, vagonesMovidos) = trenOrigen.splitAt(trenOrigen.length - cantidadVagonesMover)
      if (desdePrincipal) (vagonesPendientes, vagonesMovidos ++ trenFinal) else (trenOrigen ++ vagonesMovidos, vagonesPendientes)
    }
  }


  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] = {
    @tailrec
    def aplicarMovimientosAux(movs: Maniobra, acc: List[Estado]): List[Estado] = {
      movs match {
        case Nil => acc.reverse
        case x :: xs =>
          val nuevoEstado = aplicarMovimiento(acc.head, x)
          aplicarMovimientosAux(xs, nuevoEstado :: acc)
      }
    }
    aplicarMovimientosAux(movs, List(e))
  }
}

/*    def definirManiobras(t1: Tren, t2: Tren): Maniobra = {
      @tailrec
      def definirManiobrasAux(trenActual: Tren, trenFinal: Tren, uno: Tren, dos: Tren, movs: Maniobra): Maniobra = {
        if (trenActual == trenFinal && uno.isEmpty && dos.isEmpty) movs.reverse
        else {
          val nuevoMov = calcularMovimiento(trenActual, trenFinal, uno, dos)
          val estacion = aplicarMovimiento((trenActual, uno, dos), nuevoMov)
          val (nuevoActual, nuevoUno, nuevoDos) = estacion
          definirManiobrasAux(nuevoActual, trenFinal, nuevoUno, nuevoDos, nuevoMov :: movs)
        }
      }
      definirManiobrasAux(t1, t2, Nil, Nil, Nil)
    }

    def calcularMovimiento(trenActual: Tren, trenFinal: Tren, uno: Tren, dos: Tren): Movimiento = {
      if (trenActual.isEmpty) Uno(1) else Uno(0)
    }*/

/*
//Codigo funcionamiento funcional con tail recursion, No funciona definirManiobra
  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {
    val (principal, uno, dos) = e // los Estados significan los trenes
    m match {
      case Uno(n) if n < 0 => {
        // La condicion es para que en los negativos se desplace
        if (uno.length == 0) {
          // Utilizamos la condicion para cuidar el desbordamiento y solamente mueva los que se puedan moverElementos sin ningun error
          val cantidadVagonesMover = if (n < principal.length) n else principal.length
          val (vagonesMovidos, vagonesPendientes) = moverPrimerosElementos(principal, cantidadVagonesMover)
          (vagonesPendientes, uno ++ vagonesMovidos, dos)
        } else {
          val cantidadVagonesMover = if (n < principal.length) n else principal.length
          val (vagonesMovidos, vagonesPendientes) = moverPrimerosElementos(uno, cantidadVagonesMover)
          (principal ++ vagonesMovidos, vagonesPendientes, dos)
        }
      }
      case Uno(n) if n > 0 => {
        val cantidadVagonesMover = if (n < principal.length) n else principal.length
        val (vagonesMovidos, vagonesPendientes) = moverUltimosElementos(principal, cantidadVagonesMover)
        //(vagonesPendientes, uno ++ vagonesMovidos, dos)// funciona perfectamente
        (vagonesPendientes, vagonesMovidos ++ uno, dos)

      }
      case Uno(0) => {
        (principal, uno, dos)
      }
      case Dos(n) if n < 0 => {
        if (dos.length == 0) {
          val cantidadVagonesMover = if (n < principal.length) n else principal.length
          val (vagonesMovidos, vagonesPendientes) = moverPrimerosElementos(principal, cantidadVagonesMover)
          (vagonesPendientes, uno, dos ++ vagonesMovidos)
        } else {
          val cantidadVagonesMover = if (n < principal.length) n else principal.length
          val (vagonesMovidos, vagonesPendientes) = moverPrimerosElementos(dos, cantidadVagonesMover)
          (principal ++ vagonesMovidos, uno, vagonesPendientes)
        }
      }
      case Dos(n) if n > 0 => {
        val cantidadVagonesMover = if (n < principal.length) n else principal.length
        val (vagonesMovidos, vagonesPendientes) = moverUltimosElementos(principal, cantidadVagonesMover)
        //(vagonesPendientes, uno, dos ++ vagonesMovidos)// funciona perfectamente
        (vagonesPendientes, uno, vagonesMovidos ++ dos)
      }
      case Dos(0) => {
        (principal, uno, dos)
      }
    }
  }

  def moverPrimerosElementos[T](lista: List[T], n: Int): (List[T], List[T]) = {
    @tailrec
    def moverPrimerosElementosAux(l: List[T], i: Int, numVagon: Int, acc1: List[T], acc2: List[T]): (List[T], List[T]) = {
      l match {
        case Nil => (acc1, acc2)
        case x :: xs => {
          if (i <= numVagon) moverPrimerosElementosAux(xs, i + 1, numVagon, acc1 :+ x, acc2)
          else moverPrimerosElementosAux(xs, i + 1, numVagon, acc1, acc2 :+ x)
        }
      }
    }
    val numVagon = if (n < 0) n * -1 else n
    moverPrimerosElementosAux(lista, 1, numVagon, Nil, Nil)
  }

  def moverUltimosElementos[T](lista: List[T], n: Int): (List[T], List[T]) = {
    @tailrec
    def moverUltimosElementosAux(l: List[T], i: Int, numVagon: Int, acc1: List[T], acc2: List[T]): (List[T], List[T]) = {
      l match {
        case Nil => (acc1, acc2)
        case x :: xs => {
          if (i <= numVagon) moverUltimosElementosAux(xs, i + 1, numVagon, x :: acc1, acc2)
          else moverUltimosElementosAux(xs, i + 1, numVagon, acc1, x :: acc2)
        }
      }
    }

    val numVagon = if (n < 0) n * -1 else n
    moverUltimosElementosAux(lista.reverse, 1, numVagon, Nil, Nil)
  }

  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] = {
    @tailrec
    def aplicarMovimientosAux(movs: Maniobra, acc: List[Estado]): List[Estado] = {
      movs match {
        case Nil => acc
        case x :: xs =>
          val nuevoEstado = aplicarMovimiento(acc.head, x)
          aplicarMovimientosAux(xs, nuevoEstado :: acc) // Agregamos el nuevo estado
      }
    }
    aplicarMovimientosAux(movs, List(e))
  }

  def definirManiobras(t1: Tren, t2: Tren): Maniobra = {
    @tailrec
    def definirManiobrasAux(trenActual: Tren, trenFinal: Tren, uno: Tren, dos: Tren, movs: Maniobra): Maniobra = {
      if(trenActual == trenFinal && uno.isEmpty && dos.isEmpty) movs.reverse
      else {
        val nuevoMov = calcularMovimiento(trenActual, trenFinal, uno, dos)
        val estacion = aplicarMovimiento((trenActual, uno, dos), nuevoMov)
        val (nuevoActual, nuevoUno, nuevoDos) = estacion
        definirManiobrasAux(nuevoActual, trenFinal, nuevoUno, nuevoDos, nuevoMov::movs)
      }
    }
    definirManiobrasAux(t1, t2, Nil, Nil, Nil)
  }

  def calcularMovimiento(trenActual: Tren, trenFinal: Tren, uno: Tren, dos: Tren): Movimiento = {
    if (trenActual.isEmpty) Uno(1) else Uno(0)
  }
*/
