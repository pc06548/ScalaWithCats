package ch01.printable

/*
* Scala provides a toString method to let us convert any value to a String.
However, this method comes with a few disadvantages: it is implemented for
every type in the language, many implementa􀦞ons are of limited use, and we
can’t opt-in to specific implementa􀦞ons for specific types.
Let’s define a Printable type class to work around these problems:
1. Define a type class Printable[A] containing a single method format.
format should accept a value of type A and return a String.
2. Create an object PrintableInstances containing instances of
Printable for String and Int.
3. Define an object Printable with two generic interface methods:
format accepts a value of type A and a Printable of the corresponding
type. It uses the relevant Printable to convert the A to a String.
print accepts the same parameters as format and returns Unit. It
prints the A value to the console using println.
* */
trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val PrintableInt = new Printable[Int] {
    override def format(value: Int): String = {
      value.toString
    }
  }

  implicit val PrintableString = new Printable[String] {
    override def format(value: String): String = value
  }

  implicit val PrintableCat = new Printable[Cat] {
    override def format(cat: Cat): String = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"${name} is a ${age} year-old ${color} cat."
    }
  }
}

object Printable {
  def format[A](value: A)(implicit printer: Printable[A]): String = {
    printer.format(value)
  }

  def print[A](value: A)(implicit printer: Printable[A]) = {
    println(printer.format(value))
  }
}

/*
* Let’s make our prin􀦞ng library easier to use by defining some extension methods
to provide be􀂂er syntax:
1. Create an object called PrintableSyntax.
2. Inside PrintableSyntax define an implicit class PrintableOps[
A] to wrap up a value of type A.
3. In PrintableOps define the following methods:
• format accepts an implicit Printable[A] and returns a String
representa􀦞on of the wrapped A;
* */

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit printer: Printable[A]): String = {
      printer.format(value)
    }

    def print(implicit printer: Printable[A]) = {
      println(printer.format(value))
    }
  }
}


