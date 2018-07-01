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

  val scenes = List(MovingDots(this),Star(this))
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

    Thread.sleep(50)
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
    x = x + other.x
    y = y + other.y
    this
  }
  def ^() = (x,y)
  override def toString: String = s"x: $x, y: $y"
}
//
//object Scene {
//  def merge(A: Scene, B: Scene) = {
//      //if (this.parent != other.parent) throw new Exception("can only merge scene's that share a parent")
//      new Scene() {
//        override def init(): Unit = {
//          this.init()
//          other.init()
//        }
//        override def control(): Unit = {
//          this.control()
//          other.control()
//        }
//
//        override def scene(): Unit = {
//          this.scene()
//          other.scene()
//        }
//      }
//    }
//  }
//}
//
//object Scene {
//  def merge(A: Scene, B: Scene): Scene = {
//
//  }
//}



abstract class Scene(parent:PApplet) {
  def init(): Unit = {}
  def control(): Unit = {}
  def scene(): Unit = {}
//  def merge(other: Scene): Scene = {
//    new Scene(this.parent) {
//      override def init(): Unit = {
//        super.init()
//        //other.init()
//      }
//
//      override def control(): Unit = {
//        super.control()
//        //other.control()
//      }
//
//      override def scene(): Unit = {
//        super.scene()
//        other.scene()
//      }
//    }
//  }
}




case class MovingDots(parent: PApplet) extends Scene(parent) {

  override def init(): Unit = {}

//  val star = Star(parent)
  val basex = parent.displayWidth/2
  val basey = parent.displayHeight/2
  val rnd = scala.util.Random
  private def rndPoint2d(): Point2d = Point2d(rnd.nextInt(30)-15,rnd.nextInt(30)-15)
  var dir = rndPoint2d
  var dir2 = rndPoint2d
  var pos = Point2d(basex,basey)
  var pos2 = Point2d(basex,basey)
  override def control(): Unit = {
    if (parent.key == 'n') {
      pos = Point2d(basex,basey)
      pos2 = Point2d(basex,basey)
      dir = rndPoint2d()
      dir2 = rndPoint2d()
    }
  }
  override def scene(): Unit ={
    print(s"\r pos: $pos, pos2: $pos2")




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

case class Star(parent: PApplet) extends Scene(parent) {
  // First create the shape
  val p = Point2d
  var star: processing.core.PShape = _
  val basex = parent.sketchWidth()/2
  val basey = parent.sketchHeight()/2
  val pos = Point2d(basex,basey)

  override def init(): Unit = {
    star = parent.createShape()
    star.beginShape
    def v(p:Point2d) = star.vertex(p.x,p.y)
    // You can set fill and stroke
    star.fill(0,255,0)
    // Here, we are hardcoding a series of vertices
    v(pos + p(0,-50))
    v(pos + p(14, -20))
    v(pos + p(47, -15))
    v(pos + p(23, 7))
    v(pos + p(29, 40))
    v(pos + p(0, 25))
    v(pos + p(-29, 40))
    v(pos + p(-23, 7))
    v(pos + p(-47, -15))
    v(pos + p(-14, -20))
    star.endShape
  }

  override def control(): Unit = {}

  override def scene(): Unit = parent.shape(star)
}
