package ch02

object SetMonoid {

  implicit def setUnionMonoid[A]: Monoid[Set[A]] = {
    new Monoid[Set[A]] {
      override def empty: Set[A] = Set.empty[A]

      override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
    }
  }

  def main(args: Array[String]): Unit = {
    val intSetMonoid = Monoid[Set[Int]]
    println(intSetMonoid.combine(Set(1,2), Set(2,3)))
  }

}
