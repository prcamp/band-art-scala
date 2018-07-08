import processing.core.{PShape, PConstants => pc}

case class LineScene(parent: Main) extends ShapeField(parent){
  var numShapes = 1000
  var initposField0 = (0 until numShapes).map(
    _ => parent.randomPoint()
  )
  override val initposField: IndexedSeq[Point2d] =
    initposField0.sortWith{
      case (pnt1,pnt2) => pnt1.y < pnt2.y
    }

  override def shape(parent: Main, pnt: Point2d): ShapeObj =
    new LineShape(parent,pnt)
}

object LinePShape {
  def apply(parent: Main, initpnt: Point2d): PShape = {
    val shape = parent.createShape()
    shape.beginShape()
    shape.vertex(-parent.scale.toFloat,initpnt.y)
    shape.vertex(parent.fwidth.toFloat+parent.scale.toFloat,initpnt.y)
    shape.strokeWeight(10)
    shape.stroke(Colors.randomColor())
    shape.endShape()
    shape
  }
}

class LineShape(parent: Main, override val initpos: Point2d) extends ShapeObj(parent,LinePShape(parent,initpos)) {
  var weight = 10

  override def iteration(): Unit = {
    shape.setStrokeWeight(weight)
    shape.setStroke(this.color)
  }

  override def init(): Unit = {


  }

  override def control(): Unit = {}
}
