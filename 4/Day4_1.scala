import scala.io.Source

object Day4_1 {
  def main(args: Array[String]) {
    val in = Source.
      fromFile("input").
      getLines.
      take(1).
      mkString.
      split("-")

    val min = in(0).toInt
    val max = in(1).toInt

    var sum = 0
    for(v <- min to max) {
      if(test(v)) {
        println(v)
        sum = sum + 1
      }
    }
    println(sum)
  }

  def test(v: Int): Boolean = {
    val s = v.toString.split("")
    var double = false
    var ordered = true
    s.zipWithIndex.
      drop(1).
      foreach(d => {
        val digit = d._1.toInt
        val prev = s(d._2-1).toInt

        if(digit == prev) {
          double = true
        }
        if(digit < prev) {
          ordered = false
        }
      })

    ordered && double
  }
}
