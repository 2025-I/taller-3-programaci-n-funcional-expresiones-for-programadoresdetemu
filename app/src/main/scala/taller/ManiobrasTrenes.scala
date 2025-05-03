package taller

import scala.annotation.tailrec
/*
type Vagon = Any
type Tren = List[Vagon]
type Estado = (Tren, Tren, Tren)

trait Movimiento
case class Principal(n: Int) extends Movimiento
case class Uno(n: Int) extends Movimiento
case class Dos(n: Int) extends Movimiento
*/
class ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)

  trait Movimiento
  case class Principal(n: Int) extends Movimiento
  case class Uno(n: Int) extends Movimiento
  case class Dos(n: Int) extends Movimiento

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {
    val(principal, uno, dos) = e  // los Estados signfican los trenes

    m match {
      case Uno(n) if n>0 => {
        // La funcion min escoje el valor minimo entre los dos, entonces si necesita mover 10 vagones y solo se pueden 2, que escoja 2
        //val cantidadVagonesMover = if(n<principal.length) n else principal.length
        val cantidadVagonesMover = (n min principal.length) // Utilizamos la funcion min para que coja lo que se puede y no haya desbordes
        val (vagonesPendientes, vagonesMovidos) = moverUltimosElementos(principal, cantidadVagonesMover)
        //retornamos la posicion de como quedaron las 3 listas (estado), teniendo en cuenta que incrementa a Uno
        //Pendiente retornar la tripleta de listas, teniendo en cuenta lo anterior (lista1, lista2, lista3),
        //(Principal, Uno, Dos)
        (vagonesPendientes, uno ++ vagonesMovidos, dos)
      }
      case Uno(n) if n<0 => {
        val cantidadVagonesMover = (n min principal.length) // Utilizamos la funcion min para que coja lo que se puede y no haya desbordes
        val (vagonesMovidos, vagonesPendientes) = moverPrimerosElementos(uno, cantidadVagonesMover)
        //retornamos la posicion de como quedaron las 3 listas (estado), teniendo en cuenta que incrementa a Uno
        (vagonesMovidos ++ principal, vagonesPendientes, dos)
      }/*
      case Uno(n) if n==0 =>{
        //retornamos la posicion de como quedaron las 3 listas (estado)
      }*/
      case Dos(n) if n>0 => {
        val cantidadVagonesMover = (n min principal.length)
        val (vagonesPendientes, vagonesMovidos) = moverUltimosElementos(principal, cantidadVagonesMover)
        (vagonesPendientes, uno, dos ++ vagonesMovidos)
      }/*
      case Dos(n) if n<0 => {
        //retornamos la posicion de como quedaron las 3 listas (estado), teniendo en cuenta que incrementa a Dos
      }
      case Dos(n) if n==0 => {
        //retornamos la posicion de como quedaron las 3 listas (estado)
      }*/
    }
  }


  def moverPrimerosElementos[T](lista: List[T], n: Int): (List[T], List[T]) = {
    @tailrec
    def moverPrimerosElementosAux(l: List[T], i: Int, acc: List[T]): (List[T], List[T]) = {
      l match {
        case Nil => (acc, Nil)
        case x :: xs =>
          if (i < n) moverPrimerosElementosAux(xs, i + 1, x::acc)
          else (acc, l)
      }
    }
    moverPrimerosElementosAux(lista, 0, Nil)
  }


  def moverUltimosElementos[T](lista: List[T], n :Int): (List[T],List[T]) = {
    @tailrec
    def moverUltimosElementosAux(l: List[T], i: Int, acc: List[T]): (List[T],List[T]) = {
      l match {
        case Nil => (acc,Nil)
        case x::xs =>
          if (i < n) moverUltimosElementosAux(xs, i + 1, acc :+ x)
          else (acc, l)
      }
    }
    moverUltimosElementosAux(lista, 0 ,Nil)
  }


    def aplicarMovimientos(e: Estado ,movs: Maniobra): List[Estado] = {
      @tailrec
      def aplicarMovimientosAux(movs: Maniobra, acc: List[Estado]): List[Estado] = {
        movs match {
          case Nil => acc
          case x::xs =>
            val nuevoEstado = aplicarMovimiento(acc.head, x)
            // Se llama la funcion aplicar movimiento y se le envia la cabeza para que aplique
            // el siguiente movimiento
            aplicarMovimientosAux(xs, nuevoEstado :: acc) // Agregamos el nuevo estado
          //al acumulador
        }
      }
      aplicarMovimientosAux(movs, List(e))
      }*/
}