import scala.io.Source
import collection.mutable.{Map,ListBuffer}

object Day6_2 {
  private val ROOT_NODE = "COM"
  private val YOU_NODE = "YOU"
  private val SANTA_NODE = "SAN"

  def main(args: Array[String]) {
    val tree = Map[String, String]()
    val in = Source.
      fromFile("input").
      getLines.
      foreach(p =>{
        val parent :: child :: _ = p.split("\\)").toList
        tree(child) = parent
      })

    val youPath = getPath(tree, YOU_NODE)
    val sanPath = getPath(tree, SANTA_NODE)
    println(getDistance(youPath, sanPath))
  }

  def getPath(t: Map[String, String], key: String): List[String] = {
    var path = ListBuffer[String]()
    var p = key
    while({p = t(p); p} != ROOT_NODE) {
      path += p
    }
    path.toList
  }

  def getDistance(yp: List[String], sp: List[String]): Int = {
    yp.
      zipWithIndex.
      foreach(o => {
        val idx = sp.indexOf(o._1)
        if(idx != -1) {
          return idx + o._2
        }
      })
    0
  }
}
