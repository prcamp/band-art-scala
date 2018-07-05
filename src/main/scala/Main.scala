import processing.core.{PApplet, PConstants => pc}

object Main extends PApplet {
  def main(args: Array[String]): Unit = {
    PApplet.main("Main")
  }
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
//    val lib = PJOGL.profile
//    PJOGL.profile = 1
    println(pc.P2D)
    size(fwidth, fheight)
  }
    println(width)
    println(height)
    println(displayWidth)
    println(displayHeight)
    println(sketchWidth())
    println(sketchHeight())
  }

  val center = Point2d(fwidth/4,fheight/4)

  val scenes = List(MovingDots, Star, Stars, ImagePatch) // CA // ShapeIm
  val sceneapps = scenes.map(sc => sc(this))
  var curidx = 0

  var curscene = sceneapps(curidx)
  override def setup(): Unit = {

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

object Point2d {
  def ring(center:Point2d, radius: Float, numPoints: Int = 10, angleOffset: Float = 0): List[Point2d] = {
    (0 until numPoints).map(
      i => {
        val angle = angleOffset + (i.toFloat/(numPoints+1).toFloat)*2*Math.PI
        val radialOffset = Point2d(radius* Math.cos(angle).toFloat, radius * Math.sin(angle).toFloat)
        //        println("angle: "+ angle + "ro"+radialOffset)
        center + radialOffset
      }
    ).toList
  }
}

case class Point2d(var x: Float, var y: Float) {
  def +(other: Point2d): Point2d = {
    Point2d(x + other.x,y + other.y)
  }
//  def ^() = (x,y)
  override def toString: String = s"x: $x, y: $y"
  def *(scale: Float):Point2d = Point2d(scale*x,scale*y)
}
