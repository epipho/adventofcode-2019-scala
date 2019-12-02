import scala.io._

object Day2_2 {
    def main(args: Array[String]) {
        val desired = 19690720
        var noun = 0
        var verb = 0

        for( noun <- 0 to 99 ) {
	    for( verb <- 0 to 99 ) {
                var c = new IntComputer("input")
                c.setMem(1, noun)
                c.setMem(2, verb)
		c.run()
	        val answer = c.getMem(0)
		if(answer == desired) {
		    println(100 * noun + verb)
		    return
		}
	    }
	}
    }
}
