import processing.core.PShape

case class TriangleScene(parent: Main) extends ShapeField(parent){
  var numTris = 1000
  var initposField0 = (0 until numTris).map(
    _ => parent.randomPoint()
  )
  override val initposField: IndexedSeq[Point2d] =
    initposField0.sortWith{
      case (pnt1,pnt2) => pnt1.y < pnt2.y
    }

  override def shape(parent: Main, pnt: Point2d): ShapeObj =
    new TriangleShape(parent,pnt)
}

object TrianglePShape {
  def apply(parent: Main, initpos: Point2d): PShape = {
    val shape = parent.createShape()
    shape.beginShape()
    shape.vertex(initpos.x,initpos.y)
    shape.vertex(initpos.x - 40, initpos.y + parent.fheight)
    shape.vertex(initpos.x + 40, initpos.y + parent.fheight)
    shape.fill(Colors.randomColor())
    shape.endShape()
    shape
  }
}

class TriangleShape(parent: Main, override val initpos: Point2d) extends ShapeObj(parent,TrianglePShape(parent,initpos)) {

  override def iteration(): Unit = {}

  override def init(): Unit = {

  }

  override def control(): Unit = {}
}
