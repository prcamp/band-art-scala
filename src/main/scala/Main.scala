import processing.core.PApplet


object Main extends PApplet {
  def main(args: Array[String]): Unit = {
    PApplet.main("Main")
  }
}

class Main extends PApplet {

  override def settings(): Unit = {
    //fullScreen()
    size(640, 360)
  }

  val movingDots = MovingDots(this)

  override def setup(): Unit = {
    movingDots.init()


  }




  override def draw(): Unit = {
    // First create the shape

    movingDots.scene()

    Thread.sleep(50)
  }

  override def keyPressed(): Unit = {
    movingDots.action()
  }
}

case class Point2d(var x: Float, var y: Float) {
  def +(other: Point2d) = {
    x = x + other.x
    y = y + other.y
  }

  override def toString: String = s"x: $x, y: $y"
}

case class MovingDots(parent: PApplet) {
  var star: processing.core.PShape = _
  def init(): Unit = {
    star = parent.createShape()
    star.beginShape
    // You can set fill and stroke
    star.fill(0,255,0)
    // Here, we are hardcoding a series of vertices
    star.vertex(0, -50)
    star.vertex(14, -20)
    star.vertex(47, -15)
    star.vertex(23, 7)
    star.vertex(29, 40)
    star.vertex(0, 25)
    star.vertex(-29, 40)
    star.vertex(-23, 7)
    star.vertex(-47, -15)
    star.vertex(-14, -20)
    star.endShape
    parent.shape(star)
  }

//  val star = Star(parent)
  val basex = parent.width
  val basey = parent.height
  val rnd = scala.util.Random
  private def rndPoint2d(): Point2d = Point2d(rnd.nextInt(30)-15,rnd.nextInt(30)-15)
  var dir = rndPoint2d
  var dir2 = rndPoint2d
  var pos = Point2d(basex,basey)
  var pos2 = Point2d(basex,basey)
  def action(): Unit = {
    if (parent.key == 'n') {
      pos = Point2d(basex,basey)
      pos2 = Point2d(basex,basey)
      dir = rndPoint2d()
      dir2 = rndPoint2d()
    }
  }
  def scene(): Unit ={
    print(s"\r pos: $pos, pos2: $pos2")

    parent.background(0)
    parent.shape(star)

    val p1 = Pointer(parent,pos)
    val p2 = Pointer(parent,pos2)

      pos + dir
      pos2 + dir2

//      {
//        i += 1; i
//      }

//    pos.x = pos.x + 5
//    if (movingX >= 640) movingX = 0
  }
}

case class Pointer(parent: PApplet, p: Point2d) {
  parent.stroke(255, 0)
  parent.stroke(255)
  parent.point(p.x,p.y)
}

case class Star(parent: PApplet) {
  // First create the shape

  val star: processing.core.PShape  = parent.createShape()
  star.beginShape
  // You can set fill and stroke
  star.fill(0,255,0)
  // Here, we are hardcoding a series of vertices
  star.vertex(0, -50)
  star.vertex(14, -20)
  star.vertex(47, -15)
  star.vertex(23, 7)
  star.vertex(29, 40)
  star.vertex(0, 25)
  star.vertex(-29, 40)
  star.vertex(-23, 7)
  star.vertex(-47, -15)
  star.vertex(-14, -20)
  star.endShape

  def show(): Unit = parent.shape(star)
}
