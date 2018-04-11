package ch01.printable

import ch01.printable.Printable.{format, print}


object Demo {
  import PrintableInstances._

  import PrintableSyntax._

  def main(args: Array[String]): Unit = {
    val cat = Cat("Tin", 12, "Black")
    format(cat)
    print(cat)

    cat.print
  }
}