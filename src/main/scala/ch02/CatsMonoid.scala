package ch02

import cats.instances.string._
import cats.instances.int._
import cats.syntax.semigroup._
object CatsMonoid {
  def main(args: Array[String]): Unit = {
    println(cats.Monoid[String].combine("abc", "def"))
    cats.Monoid[String].empty

    println(cats.Semigroup[String].combine("prash", "ant"))

    println(1 |+| 2)
  }

}
