import scala.io.Source
import collection.mutable.Map

object Day6_1 {
  private val ROOT_NODE = "COM"

  def main(args: Array[String]) {
    val tree = Map[String, String]()
    val in = Source.
      fromFile("input").
      getLines.
      foreach(p =>{
        val parent :: child :: _ = p.split("\\)").toList
        tree(child) = parent
      })

    var orbits = 0
    tree.keys.
      foreach(k => {
        orbits = orbits + count(tree, k)
      })
    println(orbits)
  }

  def count(t: Map[String, String], key: String): Int = {
    var p: String = ROOT_NODE
    while({p = t(key); p} != ROOT_NODE ) {
      return count(t, p) + 1
    }
    return 1
  }
}
