import processing.core.{PApplet, PConstants => pc}

import scala.util.Random

object Main extends PApplet {
  def main(args: Array[String]): Unit = {
    PApplet.main("Main")
  }
  val rnd = Random
  def randomColor(): Int = color(rnd.nextInt(360),rnd.nextInt(100),rnd.nextInt(100))
}

class Main extends PApplet {


  var fwidth = 640
  var fheight = 360
  override def settings(): Unit = {
  if (fwidth == -1 || fheight == -1) {
    fwidth = this.displayWidth
    fheight = this.displayHeight
    fullScreen()
  } else {
    size(fwidth, fheight)
  }

  }

  val center = Point2d(fwidth/4,fheight/4)

  val scenes = List(MovingDots, GOL, StarScene, Stars, ImagePatch) // CA // ShapeIm
  val sceneapps = scenes.map(sc => sc(this))
  var curidx = 0

  var curscene = sceneapps(curidx)
  override def setup(): Unit = {
    colorMode(pc.HSB,360,100,100)
    background(0)
    sceneapps.foreach(s => s.init())
  }

  override def draw(): Unit = {
    // First create the shape
    background(0)
    curscene.curscene()

//    Thread.sleep(50)
  }

  override def keyPressed(): Unit = {
    curscene.control()
    if (key == '`') {
      curidx = if (curidx < sceneapps.length-1) curidx+1 else 0
      curscene = sceneapps(curidx)
    }
  }

//  override def init(): Unit = setup()
//
//  override def control(): Unit = keyPressed()
}

