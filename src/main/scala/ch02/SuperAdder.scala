package ch02

import cats.instances.int._
import cats.instances.option._
import cats.syntax.monoid._




object SuperAdder {

  /*
  * The cu􀦮ng edge SuperAdder v3.5a-32 is the world’s first choice for adding
  * together numbers. The main func􀦞on in the program has signature def
  * add(items: List[Int]): Int. In a tragic accident this code is deleted!
  * Rewrite the method and save the day!
  * Well done! SuperAdder’s market share con􀦞nues to grow, and now
  * there is demand for addi􀦞onal func􀦞onality. People now want to add
  * List[Option[Int]]. Change add so this is possible. The SuperAdder code
  * base is of the highest quality, so make sure there is no code duplica􀦞on!
  * */
  def add[T](items: List[T])(implicit monoid: cats.Monoid[T]): T = {
    items.foldLeft(monoid.empty)((acc, i) => {
      monoid.combine(acc, i)
    })
  }

  /*
  * Equivalent
  * */

  def add[T: cats.Monoid](items: List[T]): T = {
    items.foldLeft(Monoid[T].empty)(_ |+| _)
  }


  /*
  * SuperAdder is entering the POS (point-of-sale, not the other POS) market.
  * Now we want to add up Orders:
  * case class Order(totalCost: Double, quantity: Double)
  * We need to release this code really soon so we can’t make any modifica􀦞ons
  * to add. Make it so!
  * */

  implicit val orderMonoid = new cats.Monoid[Order] {
    def empty: Order = Order(0,0)
    def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }



  def main(args: Array[String]): Unit = {
    println("----"+add(List(1,2,3,4)))
    println("----"+add(List(Some(1),None,Some(3),None)))
  }
}

case class Order(totalCost: Double, quantity: Double)
