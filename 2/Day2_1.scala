import scala.io._

object Day2_1 {
    def main(args: Array[String]) {
        var c = new IntComputer("input")
	c.setMem(1, 12)
	c.setMem(2, 2)
	c.run()
	var answer = c.getMem(0)
	println(answer)
    }
}
