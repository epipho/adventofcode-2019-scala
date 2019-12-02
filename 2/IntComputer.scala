import scala.io._

class IntComputer(input: String) {

    private val OP_TERM = 99
    private val OP_ADD  = 1
    private val OP_MUL  = 2

    private var ip = 0

    private var mem: Array[Int] = Source.
    fromFile(input).
    getLines.
    mkString.
    split(",").
    map(_.toInt).
    toArray;

    def setMem(idx: Int, value: Int) {
        mem(idx) = value
    }

    def getMem(idx: Int) : Int = {
        mem(idx)
    }

    def printState() {
        println(mem.mkString(","))
    }

    def run() {
        while(true) {
	    val (op, v1, v2, dst) = getOp()
	    op match {
	        case OP_TERM => return
		case OP_ADD => setMem(dst, v1 + v2)
		case OP_MUL => setMem(dst, v1 * v2)
	    }
	}
    }

    private def getOp() = {
        var op = getMem(ip)
	ip = ip + 1
        if(op == OP_TERM) {
	    (OP_TERM, 0, 0, 0)
	} else {
	    var v1 = getMem(getMem(ip+0))
	    var v2 = getMem(getMem(ip+1))
	    var dst = getMem(ip+2)
	    ip = ip + 3
	    (op, v1, v2, dst)
	}
    }

}