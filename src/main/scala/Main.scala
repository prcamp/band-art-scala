import processing.core.{PApplet, PShape, PConstants => pc}


object Main extends PApplet {
  def main(args: Array[String]): Unit = {
    PApplet.main("Main")
  }
}

class Main extends PApplet {
  val fwidth = 640
  val fheight = 360
  override def settings(): Unit = {
//    fullScreen()
    size(fwidth, fheight)
    println(width)
    println(height)
    println(displayWidth)
    println(displayHeight)
    println(sketchWidth())
    println(sketchHeight())
  }

  val scenes = List(MovingDots(this),Star(this), Stars(this))
  var curidx = 0
//
//  val movingDots = MovingDots(this)
//  val star = Star(this)
//  val scene = new Scene(this) {
//    override def init(): Unit = {
//      movingDots.init()
//      star.init()
//    }
//
//    override def control(): Unit = {
//      movingDots.init()
//      star.init()
//    }
//
//    override def scene(): Unit = {
//      movingDots.scene()
//      star.scene()
//    }
//
//  }
  var scene = scenes(curidx)
  override def setup(): Unit = {

    background(0)
    scenes.foreach(s => s.init())
  }

  override def draw(): Unit = {
    // First create the shape

    scene.scene()

//    Thread.sleep(50)
  }

  override def keyPressed(): Unit = {
    scene.control()
    if (key == '1') {
      curidx = if (curidx < scenes.length-1) curidx+1 else 0
      scene = scenes(curidx)
    }
  }
}

case class Point2d(var x: Float, var y: Float) {
  def +(other: Point2d): Point2d = {
    Point2d(x + other.x,y + other.y)
  }
  def ^() = (x,y)
  override def toString: String = s"x: $x, y: $y"
}

case class MovingDots(parent: Main) extends Scene(parent) {

  override def init(): Unit = {}

//  val star = Star(parent)
  val basex = parent.fwidth/2
  val basey = parent.fheight/2//sketchHeight()/2
  val rnd = scala.util.Random
  private def rndPoint2d(): Point2d = Point2d(rnd.nextInt(30)-15,rnd.nextInt(30)-15)
  var dir = rndPoint2d
  var dir2 = rndPoint2d
  var pos = Point2d(basex,basey)
  var pos2 = Point2d(basex,basey)
  override def control(): Unit = {
    println(parent.keyCode + "\n")
    if (parent.key == 'n') {
      pos = Point2d(basex,basey)
      pos2 = Point2d(basex,basey)
      dir = rndPoint2d()
      dir2 = rndPoint2d()
    }

    if (parent.key == pc.LEFT) pos = pos + Point2d(-10,0)
    if (parent.key == pc.RIGHT) pos = pos + Point2d(10,0)
    if (parent.key == pc.UP) pos = pos + Point2d(0,-10)
    if (parent.key == pc.DOWN) pos = pos + Point2d(0,10)
  }
  override def scene(): Unit ={
    print(s"\r pos: $pos, pos2: $pos2")

    pos = pos + dir
    pos2 = pos2 + dir2

    val p1 = Pointer(parent,pos)
    val p2 = Pointer(parent,pos2)

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

case class StarCoords() {
  val coords = Seq(
     Point2d(0,-50),
       Point2d(14, -20),
       Point2d(47, -15),
       Point2d(23, 7),
       Point2d(29, 40),
       Point2d(0, 25),
       Point2d(-29, 40),
       Point2d(-23, 7),
       Point2d(-47, -15),
       Point2d(-14, -20)
  )
}

case class Star(parent: Main) extends Scene(parent) {
  // First create the shape
  val p = Point2d
  var star: processing.core.PShape = _
  val basex = parent.fwidth/2
  val basey = parent.fheight/2
  var pos = Point2d(basex,basey)
  val coords = StarCoords()

  override def init(): Unit = {
    star = parent.createShape()
    star.beginShape
    def v(p:Point2d) = star.vertex(p.x,p.y)
    // You can set fill and stroke
    star.fill(0,0,255)
    // Here, we are hardcoding a series of vertices
    coords.coords.foreach(
      pnt => v(pos + pnt)
    )
    star.endShape
  }

  override def control(): Unit = {

    if (parent.key == 'n') pos = Point2d(basex,basey)
    if (parent.keyCode == 37) pos = pos + Point2d(-10,0)
    if (parent.keyCode == 39) pos = pos + Point2d(10,0)
    if (parent.keyCode == 38) pos = pos + Point2d(0,-10)
    if (parent.keyCode == 40) pos = pos + Point2d(0,10)
  }

  override def scene(): Unit = {
    print(s"\r pos: $pos")
    parent.background(0)
    init()
//    parent.translate(pos.x,pos.y)
    parent.shape(star)
  }
}

case class Stars(parent: Main) extends Scene(parent) {
  // First create the shape
  val p = Point2d
  val basex = parent.fwidth/2
  val basey = parent.fheight/2
  var pos = Point2d(basex,basey)
  val verts = StarCoords().coords
  val radial = 100
  val numStars = 20

  var baseShapes = List[PShape]()

  override def init() = {
    val centers = (0 until numStars).map(
      i => {
        val angle = (i.toFloat/(numStars+1).toFloat)*2*Math.PI
        val radialOffset = Point2d(radial* Math.cos(angle).toFloat, radial * Math.sin(angle).toFloat)
//        println("angle: "+ angle + "ro"+radialOffset)
        pos + radialOffset
      }
    )
    baseShapes = centers.map {
      cent => {
        val shp = parent.createShape()
        shp.beginShape
        def v(p:Point2d) = shp.vertex(p.x,p.y)
        // You can set fill and stroke
        shp.fill(0,0,255)
        verts.foreach(
          pnt => {
            v(cent + pnt)
          }
        )
        shp.endShape
        shp
      }
      }.toList
  }
  override def control(): Unit = {

    if (parent.key == 'n') pos = Point2d(basex,basey)
    if (parent.keyCode == 37) pos = pos + Point2d(-10,0)
    if (parent.keyCode == 39) pos = pos + Point2d(10,0)
    if (parent.keyCode == 38) pos = pos + Point2d(0,-10)
    if (parent.keyCode == 40) pos = pos + Point2d(0,10)
  }

  override def scene(): Unit = {
    print(s"\r pos: $pos")
    parent.background(0)
    init()
    //    parent.translate(pos.x,pos.y)
    baseShapes.foreach(shp => parent.shape(shp))
  }

}