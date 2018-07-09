import oscP5.{OscEventListener, OscP5}
import processing.core.{PApplet, PConstants => pc}

import scala.util.Random

object Main extends PApplet {
  def main(args: Array[String]): Unit = {
    PApplet.main("Main")
  }

  val rnd = Random


}

class Main extends PApplet {
  val controller: OscP5 = new OscP5(this, 12348)
  controller.status(0)
  println(controller.properties())
  val rnd = Random
  val fwidth = 1600//1920
  val fheight = 900//850
  val fullscr = false
  var center = Point2d(fwidth / 2, fheight / 2)
  val scale = Math.sqrt(Math.pow(fwidth,2)+Math.pow(fheight,2))
  override def settings(): Unit = {
    if (fullscr) {
      //((fwidth == -1 || fheight == -1) || (fwidth == this.displayWidth && fheight == this.displayHeight)) {
      fullScreen()
    } else {
      size(fwidth, fheight)
    }

  }


  def randomPoint(): Point2d = Point2d(rnd.nextInt(fwidth),rnd.nextInt(fheight))


  val scenes = List(StaticScene,TriangleScene, LineScene, CAScene, ParticleScene) // CA // ShapeIm
  val sceneapps = scenes.map(sc => sc(this))
  var curidx = 0

  var curscene = sceneapps(curidx)

  override def setup(): Unit = {
    rectMode(pc.CENTER)
    var center = Point2d(width / 2, height / 2)
//    println("setting up")
//    println(s"fwidth ${fwidth} fheight ${fheight}")
//    println("center " + center)
    noStroke()
    frameRate = 30
    colorMode(pc.HSB, 360, 100, 100)
    background(0)
    sceneapps.foreach(s => s.init())
  }

  override def draw(): Unit = {
    // First create the shape
    background(0)
    curscene.curscene()
  }

  override def keyPressed(): Unit = {

    //if (theCurScene.isInstanceOf[Nothing]) controller.removeListener(theCurScene.asInstanceOf[Nothing])
    if (key == '`') {
      curidx = if (curidx < sceneapps.length - 1) curidx + 1 else 0
      curscene = sceneapps(curidx)
    }
    controller.addListener(curscene.asInstanceOf[OscEventListener])
//      nextScene.keyPressed(this.key)
//    }
    curscene.control()

  }

//  override def oscStatus(oscStatus: OscStatus): Unit = {}
//
//  private val pages = List("/1","/2","/3","/4","/5","/6")
//  override def oscEvent(oscMessage: OscMessage): Unit = {
//    curscene.oscEvent(oscMessage)
//    val addr = oscMessage.addrPattern
//    controller.removeListener(curscene.asInstanceOf[OscEventListener])
//    if (pages.contains(addr)) {
//      if (addr == "/1") {
//        curscene = StaticScene(this)
//        controller.addListener(curscene.asInstanceOf[OscEventListener])
//        curscene.init()
//        draw()
//      } else if (addr == "/2") {
//        curscene = TriangleScene(this)
//        controller.addListener(curscene.asInstanceOf[OscEventListener])
//        curscene.init()
//        draw()
//      } else if (addr == "/3") {
//        curscene = LineScene(this)
//        controller.addListener(curscene.asInstanceOf[OscEventListener])
//        curscene.init()
//        draw()
//      } else if (addr == "/4") {
//        curscene = CircleScene(this)
//        controller.addListener(curscene.asInstanceOf[OscEventListener])
//        curscene.init()
//        draw()
//      } else if (addr == "/5") {
//        curscene = CAScene(this)
//        controller.addListener(curscene.asInstanceOf[OscEventListener])
//        curscene.init()
//        draw()
//      } else if (addr == "/6") {
//        curscene = ParticleScene(this)
//        controller.addListener(curscene.asInstanceOf[OscEventListener])
//        curscene.init()
//        draw()
//      }
//    }
//  }
}

