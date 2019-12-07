import scala.io.{Source, StdIn}
import collection.mutable.ListBuffer

object OpCode extends Enumeration {
  type OpCode = Value
  val TERM = Value(99)
  val ADD  = Value(1)
  val MUL  = Value(2)
  val INP = Value(3)
  val OUT = Value(4)
  val JMT = Value(5)
  val JMF = Value(6)
  val LT = Value(7)
  val EQ = Value(8)
}

object ParameterMode extends Enumeration {
  type ParameterMode = Value
  val POSITION = Value(0)
  val IMMEDIATE = Value(1)
}

class Op(instruction: Int) {

  private val code = OpCode(instruction % 100);
  private val modes = (instruction / 100).
    toString.
    reverse.
    map(d => {ParameterMode(d.asDigit)}).
    toArray

  def getOpCode(): OpCode.OpCode  = {
    code
  }

  def getParameterMode(idx: Int): ParameterMode.ParameterMode = {
    if(idx <  modes.length) modes(idx) else ParameterMode.POSITION
  }
}

class IntComputer(input: String) {

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
      val op = consumeOp()
      val inc = op.getOpCode match {
        case OpCode.TERM => return
        case OpCode.ADD => add(op)
        case OpCode.MUL => mul(op)
        case OpCode.INP => inp(op)
        case OpCode.OUT => out(op)
        case OpCode.JMT => jmt(op)
        case OpCode.JMF => jmf(op)
        case OpCode.LT => lt(op)
        case OpCode.EQ => eq(op)
      }
      ip += inc
    }
  }

  // p2 = p0 + p1
  private def add(op: Op): Int = {
    setParamValue(op, 2, getParamValue(op, 0) + getParamValue(op, 1))
    3
  }

  // p2 = p0 * p1
  private def mul(op: Op): Int = {
    setParamValue(op, 2, getParamValue(op, 0) * getParamValue(op, 1))
    3
  }

  // p0 = input from console
  private def inp(op: Op): Int = {
    setParamValue(op, 0, StdIn.readLine("INPUT: ").toInt)
    1
  }

  // output p0 to console
  private def out(op: Op): Int = {
    println(getParamValue(op, 0))
    1
  }

  // jmp to p1 if p0 is not zero
  private def jmt(op: Op): Int = {
    if(getParamValue(op, 0) != 0) {ip = getParamValue(op, 1); 0} else 2
  }

  // jmp to p1 if p0 is zero
  private def jmf(op: Op): Int = {
    if(getParamValue(op, 0) == 0) {ip = getParamValue(op, 1); 0} else 2
  }

  // store 1 in p2 if p0 < pq
  private def lt(op: Op): Int = {
    val res = if(getParamValue(op, 0) < getParamValue(op, 1)) 1 else 0
    setParamValue(op, 2, res)
    3
  }

  // jmp to p2 if p0 = p1
  private def eq(op: Op): Int = {
    val res = if(getParamValue(op, 0) == getParamValue(op, 1)) 1 else 0
    setParamValue(op, 2, res)
    3
  }

  private def consumeOp(): Op = {
    val op = new Op(getMem(ip))
    ip = ip + 1
    op
  }

  // write integer to a param location
  private def setParamValue(op: Op, paramIdx: Int, value: Int) {
    setMem(getMem(ip + paramIdx), value)
  }

  // get the value of a parameter, taking into account the mode
  private def getParamValue(op: Op, paramIdx: Int): Int = {
    val v = getMem(ip + paramIdx)
    op.getParameterMode(paramIdx) match {
      case ParameterMode.POSITION => getMem(v)
      case ParameterMode.IMMEDIATE => v
    }
  }
}
