import processing.core.{PConstants => pc}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Colors {

  val modes = Vector("bw","neon")
  val rnd = Random

  def randomColor(): ColorCoords = {
    ColorCoords(rnd.nextInt(360),rnd.nextInt(100),rnd.nextInt(100))
  }


  def gencolors(parent: Main, mode: String, numColors: Int): ArrayBuffer[ColorCoords] = {
    //parent.colorMode(pc.HSB, 360, 100, 100)
    val colarray = ArrayBuffer[ColorCoords]()
    (0 until numColors).foreach(
      _ => {
        if (mode == "bw") {
          val col = ColorCoords(0,10,rnd.nextInt(100))
          colarray += col
        } else if (mode == "rand") {
          colarray += randomColor()
        } else if (mode == "neon") {
          colarray += ColorCoords(rnd.nextInt(360),100,rnd.nextInt(100))
        }
      }
    )
    colarray
  }


  def gencolors(parent: Main, hue: Int, numColors: Int): ArrayBuffer[ColorCoords] = {
    val colarray = ArrayBuffer[ColorCoords]()
    (0 until numColors).foreach(
      _ => colarray += ColorCoords(hue,rnd.nextInt(100),rnd.nextInt(100))
    )
    colarray
  }

  def randomColor(colarray: ArrayBuffer[ColorCoords]): ColorCoords = {
    val idx = rnd.nextInt(colarray.length)
    colarray(idx)
  }

  case class ColorCoords(h: Int, s: Int, b: Int) {
    def toColor(parent: Main) = parent.color(h,s,b)
  }


}
