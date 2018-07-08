import Main.color
import processing.core.{PConstants => pc}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Colors {

  val modes = Vector("bw")
  val rnd = Random

  def randomColor(): Int = color(rnd.nextInt(360), rnd.nextInt(100), rnd.nextInt(100))

  def gencolors(parent: Main, mode: String, numColors: Int): ArrayBuffer[Int] = {
    //parent.colorMode(pc.HSB, 360, 100, 100)
    val colarray = ArrayBuffer[Int]()
    (0 until numColors).foreach(
      _ => {
        if (mode == "bw") {
          val col = parent.color(0,10, rnd.nextInt(100))
          colarray += col
        } else if (mode == "neon") colarray += randomColor()
      }
    )
    colarray
  }

  def randomColor(colarray: ArrayBuffer[Int]): Int = {
    val idx = rnd.nextInt(colarray.length)
    colarray(idx)
  }


}
