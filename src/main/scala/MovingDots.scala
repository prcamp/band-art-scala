import processing.core.{PApplet,PConstants => pc}

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
    //    println(parent.keyCode + "\n")
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
  override def curscene(): Unit ={
    //    print(s"\r pos: $pos, pos2: $pos2")

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