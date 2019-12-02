import scala.io._

object Day1_2 {

  def getFuel(mass: Int) : Int = {
    val f = (mass / 3) - 2
    if(f < 0) 0
    else f + getFuel(f)
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
