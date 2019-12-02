import scala.io._

object Day1_1 {

  def getFuel(mass: Int) : Int = {
    (mass / 3) - 2
  }

  def main(args: Array[String]) {
    var fuel: Int = 0
    Source.
      fromFile("input").
      getLines.
      map(_.toInt).
      foreach( fuel += getFuel(_))

    println(fuel)
  }
}
